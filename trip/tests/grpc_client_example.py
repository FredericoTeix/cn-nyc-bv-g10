# some_file.py
import sys

# insert at 1, 0 is the script path (or '' in REPL)
sys.path.insert(1, '..')

import grpc
from swagger_server.proto import trips_pb2_grpc
from swagger_server.proto import trips_pb2

from datetime import datetime
from google.protobuf.timestamp_pb2 import Timestamp


def add_trip():
    pickup_datetime = datetime(2021, 4, 13, 21, 43, 53)
    dropoff_datetime = datetime(2021, 5, 24, 22, 43, 53)
    pickup_datetimeT = Timestamp()
    pickup_datetimeT.FromDatetime(pickup_datetime)
    dropoff_datetimeT = Timestamp()
    dropoff_datetimeT.FromDatetime(dropoff_datetime)

    trip = trips_pb2.Trip(pickup_location_id="1", dropoff_location_id="2", passenger_count=21,
                          pickup_datetime=pickup_datetimeT, dropoff_datetime=dropoff_datetimeT)

    trip_id = stub.AddTrip(trip)
    print(f"{trip_id.trip_id}")


def get_location_by_id(location_id):
    location_id = trips_pb2.LocationID(location_id=location_id)
    trip = stub.GetLocationById(location_id)
    print(trip)


def remove_trip(trip_id):
    trip_id = trips_pb2.TripID(trip_id=trip_id)
    stub.RemoveTrip(trip_id)


def update_trip(trip_id):
    pickup_datetime = datetime(2021, 4, 13, 21, 43, 53)
    dropoff_datetime = datetime(2021, 5, 24, 22, 43, 53)
    pickup_datetimeT = Timestamp()
    pickup_datetimeT.FromDatetime(pickup_datetime)
    dropoff_datetimeT = Timestamp()
    dropoff_datetimeT.FromDatetime(dropoff_datetime)

    trip = trips_pb2.Trip(pickup_location_id="1", dropoff_location_id="2", passenger_count=3,
                          pickup_datetime=pickup_datetimeT, dropoff_datetime=dropoff_datetimeT)
    trip_id = trips_pb2.TripID(trip_id=trip_id)
    rtr = trips_pb2.UpdateTripRequest(trip_id=trip_id, trip=trip)

    updated_trip = stub.UpdateTrip(rtr)
    print(f"{updated_trip}")


def get_counter(trip_id):
    start_time = datetime(2020, 4, 2, 21, 43, 53)
    end_time = datetime(2020, 5, 24, 22, 43, 53)
    start_timeT = Timestamp()
    start_timeT.FromDatetime(start_time)
    end_timeT = Timestamp()
    end_timeT.FromDatetime(end_time)
    gctilr = trips_pb2.GetCountTripsInLocationRequest(location_id=trip_id, start_date=start_timeT, end_date=end_timeT)
    counter = stub.GetCountTripsInLocation(gctilr)
    print(f"{counter.count}")


host = 'localhost'
server_port = 50051
channel = grpc.insecure_channel(f"{host}:{server_port}")
stub = trips_pb2_grpc.TripsStub(channel)
get_location_by_id("2")
