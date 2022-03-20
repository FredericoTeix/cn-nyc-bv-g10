from __future__ import print_function
from swagger_server.models.location import Location  # noqa: E501
from swagger_server.models.trip import Trip  # noqa: E501
from swagger_server.models.trips import Trips  # noqa: E501
from swagger_server import util

import sys

import pymongo

client = pymongo.MongoClient(
    "mongodb+srv://rAlexandre:F4cFUl5yW77ynldv@cn.9ah4l.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client.test


def add_trip(trip):
    """Add a trip to the data used to calculate the value

    :param trip: A object containing trip information
    :type trip: Trip

    :rtype: str
    """
    dict_trip = trip.to_json()

    created_trip = db["trips"].insert_one(dict_trip)
    # print(created_trip.inserted_id, file=sys.stderr)

    return str(created_trip.inserted_id)


def get_location_by_id(id):  # noqa: E501
    """Find location description by ID

    Returns LocationID object.

    :param id: The ID of the Location to return.
    :type id: str

    :rtype: Location
    """
    return 'do some magic!'


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
