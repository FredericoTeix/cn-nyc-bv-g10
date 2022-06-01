#!/usr/bin/env python3
import os
import threading
from concurrent import futures
from pathlib import Path

import connexion
import grpc
from grpc_interceptor import ExceptionToStatusInterceptor
from grpc_interceptor.exceptions import NotFound

from swagger_server import encoder
from swagger_server.controllers.grpc_controller import TripsServicer
from swagger_server.proto import trips_pb2_grpc

from prometheus_client import CollectorRegistry, generate_latest, multiprocess
import tempfile
import distutils.dir_util

interceptors = [ExceptionToStatusInterceptor()]
grpc_server = grpc.server(futures.ThreadPoolExecutor(max_workers=10), interceptors=interceptors)

app = connexion.App(__name__, specification_dir='./swagger/')
app.app.json_encoder = encoder.JSONEncoder
app.add_api('swagger.yaml', arguments={'title': 'NYC Trips'}, pythonic_params=True)

# python decorators feel like black magic to me
@app.app.route('/metrics', methods=['GET'])
def metrics():  # pylint: disable=unused-variable
    # /metrics API shouldn't be visible in the API documentation,
    # hence it's added here in the create_app step
    # requires environment variable prometheus_multiproc_dir
    registry = CollectorRegistry()
    multiprocess.MultiProcessCollector(registry)
    return generate_latest(registry)

def load(path):
    with open(Path(path).expanduser(), 'rb') as f:
        data = f.read()
    return data


def start_flask():
    coordination_dir = os.environ.get("prometheus_multiproc_dir", tempfile.gettempdir() + "/prometheus-multiproc-dir/")
    os.environ["prometheus_multiproc_dir"] = coordination_dir
    distutils.dir_util.mkpath(coordination_dir)
    cert = open("cert.pem", "a")
    cert.write(os.getenv('CERT'))
    cert.close()
    key = open("key.pem", "a")
    key.write(os.getenv('KEY'))
    key.close()
    app.run(port=8080)


def start_grpc():
    credentials = grpc.ssl_server_credentials(
        [(bytes(os.getenv('KEY'), 'utf-8'), bytes(os.getenv('CERT'), 'utf-8'))]
    )

    trips_pb2_grpc.add_TripsServicer_to_server(TripsServicer(), grpc_server)
    grpc_server.add_secure_port('[::]:50051', credentials)
    grpc_server.start()

def main():
    start_grpc()
    start_flask()


if __name__ == '__main__':
    main()
