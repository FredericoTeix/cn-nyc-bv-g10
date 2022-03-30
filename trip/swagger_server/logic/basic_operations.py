from __future__ import print_function
from swagger_server.models.location import Location  # noqa: E501
from swagger_server.models.trip import Trip  # noqa: E501
from swagger_server.models.trips import Trips  # noqa: E501
from swagger_server import util

import sys

import pymongo

client = pymongo.MongoClient(
    "mongodb+srv://rAlexandre:F4cFUl5yW77ynldv@cn.9ah4l.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client.main


def add_trip(trip):
    """Add a trip to the data used to calculate the value

    :param trip: A object containing trip information
    :type trip: Trip

    :rtype: str
    """
    year_p = trip.pickup_datetime.year - 2000
    year_d = trip.dropoff_datetime.year - 2000
    month_p = year_p * 12 + trip.pickup_datetime.month
    month_d = year_d * 12 + trip.dropoff_datetime.month
    day_p = month_p * 31 + month_p
    day_d = month_d * 31 + month_d

    increment_trip_counter("year", year_p)
    increment_trip_counter("year", year_d)
    increment_trip_counter("month", month_p)
    increment_trip_counter("month", month_d)
    increment_trip_counter("day", day_p)
    increment_trip_counter("day", day_d)

    dict_trip = trip.to_dict()
    created_trip = db["trips"].insert_one(dict_trip)
    # print(created_trip.inserted_id, file=sys.stderr)

    return str(created_trip.inserted_id)


def increment_trip_counter(unit, n):
    db["trips_" + unit].update_one(
        {
            unit: n
        },
        {
            "$inc": {"counter": 1}
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
    zone = db.zones.find_one({"location_id": location_id})
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


def remove_trip(id):  # noqa: E501
    """Remove a trip in the data used to calculate the value

    :param id: ID of the trip to delete
    :type id: str

    :rtype: None
    """
    return 'do some magic!'


def update_trip(id, trip):  # noqa: E501
    """Change a trip in the data used to calculate the value


    :param id: ID of the trip to delete
    :type id: str
    :param trip: A object containing trip information
    :type trip: Trip

    :rtype: str
    """
    return 'do some magic!'
