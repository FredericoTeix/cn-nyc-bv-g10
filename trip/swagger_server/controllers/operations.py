from __future__ import print_function

from datetime import datetime

import connexion
from pymongo import ReturnDocument

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


def modify_trip_counters(date, n):
    year = date.year - 2000
    month = year * 12 + date.month
    day = month * 31 + date.day

    increment_trip_counter("year", year, n)
    increment_trip_counter("month", month, n)
    increment_trip_counter("day", day, n)


def add_trip(trip, trip_id=None):
    """Add a trip to the data used to calculate the value

    :param trip: A object containing trip information
    :type trip: Trip
    :param trip_id: Optional id to put in trip
    :type trip_id: bson.ObjectId

    :rtype: str
    """
    modify_trip_counters(trip.pickup_datetime, trip.passenger_count)
    modify_trip_counters(trip.dropoff_datetime, trip.passenger_count)
    dict_trip = trip.to_dict()
    dict_trip['pickup_datetime'] = datetime.strptime(dict_trip['pickup_datetime'][:-7], "%Y-%m-%d:%H:%M:%S")
    dict_trip['dropoff_datetime'] = datetime.strptime(dict_trip['dropoff_datetime'][:-7], "%Y-%m-%d:%H:%M:%S")
    if trip_id:
        dict_trip["_id"] = bson.ObjectId(trip_id)
    created_trip = db["trips"].insert_one(dict_trip)
    # print(created_trip.inserted_id, file=sys.stderr)

    return str(created_trip.inserted_id)


def get_location_by_id(location_id):  # noqa: E501
    """Find location description by ID

    Returns LocationID object.

    :param location_id: The ID of the Location to return.
    :type location_id: str

    :rtype: dict
    """
    zone = db.zones.find_one({"_id": int(location_id)})
    zone.pop('_id')
    return zone


def get_trips_count(start_date=None, end_date=None, limit=10):  # noqa: E501
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

    modify_trip_counters(deleted_trip['pickup_datetime'], -deleted_trip['passenger_count'])
    modify_trip_counters(deleted_trip['dropoff_datetime'], -deleted_trip['passenger_count'])


def update_trip(trip_id, trip):  # noqa: E501
    """Change a trip in the data used to calculate the value


    :param trip_id: ID of the trip to delete
    :type trip_id: str
    :param trip: A object containing trip information
    :type trip: Trip

    :rtype: dict
    """
    trip_dict = {k: v for k, v in trip.to_dict().items() if v is not None}

    original_trip = db.trips.find_one(
        {'_id': bson.ObjectId(trip_id)}
    )

    updated_trip = db.trips.find_one_and_update(
        {'_id': bson.ObjectId(trip_id)},
        {'$set': trip_dict},
        return_document=ReturnDocument.AFTER
    )

    modify_trip_counters(updated_trip['pickup_datetime'], updated_trip['passenger_count'])
    modify_trip_counters(updated_trip['dropoff_datetime'], updated_trip['passenger_count'])

    modify_trip_counters(original_trip['pickup_datetime'], -original_trip['passenger_count'])
    modify_trip_counters(original_trip['dropoff_datetime'], -original_trip['passenger_count'])

    del updated_trip["_id"]
    return updated_trip
