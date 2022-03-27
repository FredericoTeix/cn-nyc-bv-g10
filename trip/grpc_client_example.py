import grpc
from swagger_server.proto import trips_pb2_grpc
from swagger_server.proto import trips_pb2

from datetime import datetime
from google.protobuf.timestamp_pb2 import Timestamp


host = 'localhost'
server_port = 50051
channel = grpc.insecure_channel(f"{host}:{server_port}")
stub = trips_pb2_grpc.TripsStub(channel)

pickup_datetime = datetime(2021, 4, 13, 21, 43, 53)
dropoff_datetime = datetime(2021, 5, 24, 22, 43, 53)
pickup_datetimeT = Timestamp()
pickup_datetimeT.FromDatetime(pickup_datetime)
dropoff_datetimeT = Timestamp()
dropoff_datetimeT.FromDatetime(dropoff_datetime)

trip = trips_pb2.Trip(pickup_location_id="1", dropoff_location_id="2", passenger_count=21, pickup_datetime=pickup_datetimeT, dropoff_datetime=dropoff_datetimeT)

trip_id = stub.AddTrip(trip)
print(f"{trip_id.trip_id}")
