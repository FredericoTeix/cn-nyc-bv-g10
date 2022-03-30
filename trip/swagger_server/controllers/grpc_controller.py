from datetime import datetime

from google.protobuf.empty_pb2 import Empty
from google.protobuf.timestamp_pb2 import Timestamp

from swagger_server.models.trip import Trip
from swagger_server.proto import trips_pb2
from swagger_server.proto import trips_pb2_grpc

from swagger_server.controllers import operations as bo
from swagger_server.models.trip import Trip
from swagger_server.proto.trips_pb2 import TripID, Location, Trip, GetCountTripsInLocationRequest, \
    GetCountTripsInLocationResponse, LocationID


class TripsServicer(trips_pb2_grpc.TripsServicer):

    def AddTrip(self, request, context):
        trip = Trip(
            pickup_datetime=datetime.fromtimestamp(
                request.pickup_datetime.seconds + request.pickup_datetime.nanos / 1e9),
            dropoff_datetime=datetime.fromtimestamp(
                request.dropoff_datetime.seconds + request.dropoff_datetime.nanos / 1e9),
            passenger_count=request.passenger_count,
            dropoff_location_id=request.dropoff_location_id,
            pickup_location_id=request.pickup_location_id
        )
        trip_id = bo.add_trip(trip)
        return TripID(trip_id=trip_id)

    def GetLocationById(self, request, context):
        location = bo.get_location_by_id(request.location_id)
        return Location(
            zone=location['zone'],
            borough=location['borough'],
            location_id=int(request.location_id),
            service_zone=location['service_zone']
        )

    def GetLocationByCity(self, request, context):
        location = bo.get_location_by_city(request.city_name)
        return LocationID(location_id=location['_id'])

    def RemoveTrip(self, request, context):
        bo.remove_trip(request.trip_id)
        return Empty()

    def UpdateTrip(self, request, context):
        trip = Trip(
            pickup_datetime=datetime.fromtimestamp(
                request.trip.pickup_datetime.seconds + request.trip.pickup_datetime.nanos / 1e9),
            dropoff_datetime=datetime.fromtimestamp(
                request.trip.dropoff_datetime.seconds + request.trip.dropoff_datetime.nanos / 1e9),
            passenger_count=request.trip.passenger_count,
            dropoff_location_id=request.trip.dropoff_location_id,
            pickup_location_id=request.trip.pickup_location_id
        )
        updated_trip = bo.update_trip(request.trip_id.trip_id, trip)

        pickup_datetime = Timestamp()
        dropoff_datetime = Timestamp()
        pickup_datetime.FromDatetime(updated_trip['pickup_datetime'])
        dropoff_datetime.FromDatetime(updated_trip['dropoff_datetime'])
        return Trip(
            pickup_datetime=pickup_datetime,
            dropoff_datetime=dropoff_datetime,
            passenger_count=updated_trip['passenger_count'],
            dropoff_location_id=updated_trip['dropoff_location_id'],
            pickup_location_id=updated_trip['pickup_location_id']
        )

    def GetCountTripsInLocation(self, request, context):
        start_date = datetime.fromtimestamp(request.start_date.seconds + request.start_date.nanos / 1e9)
        end_date = datetime.fromtimestamp(request.start_date.seconds + request.start_date.nanos / 1e9)
        count = bo.get_trips_count(request.location_id, start_date=start_date, end_date=end_date)
        return GetCountTripsInLocationResponse(count=count)
