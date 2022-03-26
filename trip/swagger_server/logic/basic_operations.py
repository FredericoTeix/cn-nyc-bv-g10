from __future__ import print_function

from datetime import datetime

from swagger_server.models.location import Location  # noqa: E501
from swagger_server.models.trip import Trip  # noqa: E501
from swagger_server.models.trips import Trips  # noqa: E501
from swagger_server import util

import sys
import os

import pymongo
import bson

client = pymongo.MongoClient(os.getenv('MONGO_URL'))
db = client["trips-db"]


def add_trip(trip, trip_id=None):
    """Add a trip to the data used to calculate the value

    :param trip: A object containing trip information
    :type trip: Trip
    :param trip_id: Optional id to put in trip
    :type trip_id: bson.ObjectId

    :rtype: str
    """
    year_p = trip.pickup_datetime.year - 2000
    year_d = trip.dropoff_datetime.year - 2000
    month_p = year_p * 12 + trip.pickup_datetime.month
    month_d = year_d * 12 + trip.dropoff_datetime.month
    day_p = month_p * 31 + trip.pickup_datetime.day
    day_d = month_d * 31 + trip.dropoff_datetime.day

    increment_trip_counter("year", year_p, trip.passenger_count)
    increment_trip_counter("year", year_d, trip.passenger_count)
    increment_trip_counter("month", month_p, trip.passenger_count)
    increment_trip_counter("month", month_d, trip.passenger_count)
    increment_trip_counter("day", day_p, trip.passenger_count)
    increment_trip_counter("day", day_d, trip.passenger_count)

    dict_trip = trip.to_dict()
    dict_trip['pickup_datetime'] = datetime.strptime(dict_trip['pickup_datetime'][:-7], "%Y-%m-%d:%H:%M:%S")
    dict_trip['dropoff_datetime'] = datetime.strptime(dict_trip['dropoff_datetime'][:-7], "%Y-%m-%d:%H:%M:%S")
    if trip_id:
        dict_trip["_id"] = bson.ObjectId(trip_id)
    created_trip = db["trips"].insert_one(dict_trip)
    # print(created_trip.inserted_id, file=sys.stderr)

    return str(created_trip.inserted_id)


def increment_trip_counter(unit, counter, n):
    db["trips_" + unit].update_one(
        {
            unit: counter
        },
        {
            "$inc": {"counter": n}
        },
        upsert=True
    )


def decrement_trip_counter(unit, counter, n):
    db["trips_" + unit].update_one(
        {
            unit: counter
        },
        {
            "$inc": {"counter": -n}
        },
        upsert=True
    )


def get_location_by_id(location_id):  # noqa: E501
    """Find location description by ID

    Returns LocationID object.

    :param location_id: The ID of the Location to return.
    :type location_id: str

    :rtype: Location
    """
    zone = db.zones.find_one({"_id": int(location_id)})
    zone.pop('_id')
    return zone


def get_trip_between_date_time(start_date=None, end_date=None, limit=10):  # noqa: E501
    """Find trips between pickup_datetime and dropoff_datetime.

    Returns an array of Trip objects.

    :param start_date: Every value data up to this date will be filtered out. If not specified no filtering is applied
    :type start_date: datetime
    :param end_date: Every value data after this date will be filtered out. If not specified no filtering is applied
    :type end_date: datetime
    :param limit: Number of elements to be returned. Default is 10
    :type limit: int

    :rtype: Trips
    """
    return 'do some magic!'


def remove_trip(trip_id):  # noqa: E501
    """Remove a trip in the data used to calculate the value

    :param trip_id: ID of the trip to delete
    :type trip_id: str

    :rtype: None
    """
    deleted_trip = db.trips.find_one_and_delete({"_id": bson.ObjectId(trip_id)})

    year_p = deleted_trip['pickup_datetime'].year - 2000
    year_d = deleted_trip['dropoff_datetime'].year - 2000
    month_p = year_p * 12 + deleted_trip['pickup_datetime'].month
    month_d = year_d * 12 + deleted_trip['dropoff_datetime'].month
    day_p = month_p * 31 + deleted_trip['pickup_datetime'].day
    day_d = month_d * 31 + deleted_trip['dropoff_datetime'].day

    decrement_trip_counter("year", year_p, deleted_trip['passenger_count'])
    decrement_trip_counter("year", year_d, deleted_trip['passenger_count'])
    decrement_trip_counter("month", month_p, deleted_trip['passenger_count'])
    decrement_trip_counter("month", month_d, deleted_trip['passenger_count'])
    decrement_trip_counter("day", day_p, deleted_trip['passenger_count'])
    decrement_trip_counter("day", day_d, deleted_trip['passenger_count'])


def update_trip(trip_id, trip):  # noqa: E501
    """Change a trip in the data used to calculate the value


    :param trip_id: ID of the trip to delete
    :type trip_id: str
    :param trip: A object containing trip information
    :type trip: TripIdBody

    :rtype: str
    """
    remove_trip(trip_id)
    print(trip.to_dict(), file=sys.stderr)
    add_trip(trip)
    trip_dict = {k: v for k, v in trip.to_dict().items() if v}
    return trip_dict
