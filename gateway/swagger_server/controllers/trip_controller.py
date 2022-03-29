import connexion
import six

from swagger_server.models.trip import Trip  # noqa: E501
from swagger_server import util


def add_trip(body=None):  # noqa: E501
    """Add a trip to the data used to calculate the value

     # noqa: E501

    :param body: A JSON object containing trip information
    :type body: dict | bytes

    :rtype: str
    """
    if connexion.request.is_json:
        body = Trip.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def remove_trip(trip_id):  # noqa: E501
    """Remove a trip in the data used to calculate the value

     # noqa: E501

    :param trip_id: ID of the trip to delete
    :type trip_id: str

    :rtype: None
    """
    return 'do some magic!'


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
        body = Trip.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'
