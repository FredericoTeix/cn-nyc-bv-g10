#!/usr/bin/env python3
import os
import threading
from concurrent import futures

import connexion
import grpc
from grpc_interceptor import ExceptionToStatusInterceptor
from grpc_interceptor.exceptions import NotFound

from swagger_server import encoder
from swagger_server.controllers.grpc_controller import TripsServicer
from swagger_server.proto import trips_pb2_grpc

from prometheus_flask_exporter import ConnexionPrometheusMetrics

interceptors = [ExceptionToStatusInterceptor()]
grpc_server = grpc.server(futures.ThreadPoolExecutor(max_workers=10), interceptors=interceptors)


def start_flask():
    app = connexion.App(__name__, specification_dir='./swagger/')
    app.app.json_encoder = encoder.JSONEncoder
    app.add_api('swagger.yaml', arguments={'title': 'NYC Trips'}, pythonic_params=True)
    metrics = ConnexionPrometheusMetrics.for_app_factory()
    metrics.info('app_info', 'Trips Service', version='1.0')
    metrics.init_app(app)
    app.run(port=8080)


def start_grpc():
    trips_pb2_grpc.add_TripsServicer_to_server(TripsServicer(), grpc_server)
    grpc_server.add_insecure_port('[::]:50051')
    grpc_server.start()


def main():
    start_grpc()
    start_flask()


if __name__ == '__main__':
    main()
