import sys

import connexion

from swagger_server.models.location import Location  # noqa: E501
from swagger_server.models.trip import Trip  # noqa: E501
from swagger_server.models.trips import Trips  # noqa: E501
from swagger_server.models.trip_id_body import TripIdBody  # noqa: E501
from swagger_server.logic import basic_operations as bo 
from swagger_server import util


def add_trip(body=None):  # noqa: E501
    """Add a trip to the data used to calculate the value

    :param body: A JSON object containing trip information
    :type body: dict | bytes

    :rtype: str
    """
    if connexion.request.is_json:
        body = Trip.from_dict(connexion.request.get_json())  # noqa: E501
    return bo.add_trip(body)


def get_location_by_id(location_id):  # noqa: E501
    """Find location description by ID

    Returns LocationID object. # noqa: E501

    :param location_id: The ID of the Location to return.
    :type location_id: str

    :rtype: Location
    """
    return bo.get_location_by_id(location_id)


def get_trip_between_date_time(start_date=None, end_date=None, limit=None):  # noqa: E501
    """Find trips between pickup_datetime and dropoff_datetime.

    Returns an array of Trip objects. # noqa: E501

    :param start_date: Every value data up to this date will be filtered out. If not specified no filtering is applied
    :type start_date: str
    :param end_date: Every value data after this date will be filtered out. If not specified no filtering is applied
    :type end_date: str
    :param limit: Number of elements to be returned
    :type limit: int

    :rtype: Trips
    """
    start_date = util.deserialize_datetime(start_date)
    end_date = util.deserialize_datetime(end_date)
    return bo.get_trip_between_date_time(start_date, end_date, limit)


def remove_trip(trip_id):  # noqa: E501
    """Remove a trip in the data used to calculate the value

     # noqa: E501

    :param id: ID of the trip to delete
    :type id: str

    :rtype: None
    """
    bo.remove_trip(trip_id)


def update_trip(trip_id, body=None):  # noqa: E501
    """Change a trip in the data used to calculate the value

     # noqa: E501

    :param trip_id: ID of the trip to delete
    :type trip_id: str
    :param body: A JSON object containing trip information
    :type body: dict | bytes

    :rtype: str
    """
    if connexion.request.is_json:
        body = TripIdBody.from_dict(connexion.request.get_json())  # noqa: E501
    return bo.update_trip(trip_id, body)
