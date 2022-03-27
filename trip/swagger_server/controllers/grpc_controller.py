from datetime import datetime

from swagger_server.proto import trips_pb2
from swagger_server.proto import trips_pb2_grpc

from swagger_server.controllers import operations as bo
from swagger_server.models.trip import Trip
from swagger_server.proto.trips_pb2 import TripID


class TripsServicer(trips_pb2_grpc.TripsServicer):

    def AddTrip(self, request, context):
        trip = Trip(
            pickup_datetime=datetime.fromtimestamp(request.pickup_datetime.seconds + request.pickup_datetime.nanos/1e9),
            dropoff_datetime=datetime.fromtimestamp(request.dropoff_datetime.seconds + request.dropoff_datetime.nanos/1e9),
            passenger_count=request.passenger_count,
            dropoff_location_id=request.dropoff_location_id,
            pickup_location_id=request.pickup_location_id
        )
        print("qdqdwqd1",flush=True)
        trip_id = bo.add_trip(trip)
        return TripID(trip_id=trip_id)
