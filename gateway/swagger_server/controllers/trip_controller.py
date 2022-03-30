import os

import connexion
import requests

from swagger_server.models.trip import Trip
from swagger_server import util
from swagger_server.controllers.authorization_controller import check_api_key

trips_url = os.getenv('TRIPS_URL')


def add_trip(body=None):
    """Add a trip to the data used to calculate the value

    :param body: A JSON object containing trip information
    :type body: dict | bytes

    :rtype: str
    """
    response = requests.post(f"{trips_url}/trip", data=connexion.request.get_json())
    return response


def remove_trip(trip_id):
    """Remove a trip in the data used to calculate the value

    :param trip_id: ID of the trip to delete
    :type trip_id: str

    :rtype: None
    """
    response = requests.delete(f"{trips_url}/trip/{trip_id}")
    return response


def update_trip(trip_id, body=None):
    """Change a trip in the data used to calculate the value

    :param trip_id: ID of the trip to delete
    :type trip_id: str
    :param body: A JSON object containing trip information
    :type body: dict | bytes

    :rtype: str
    """
    response = requests.put(f"{trips_url}/trip/{trip_id}", data=connexion.request.get_json())
    return response
