import os
import connexion
import requests

from swagger_server.models.business import Business  # noqa: E501
from swagger_server.models.businesses import Businesses  # noqa: E501

business_url = os.getenv('BUSINESS_URL')


def add_business(body=None):
    """Add a business to the data used to calculate the value

    :param body: A JSON object containing business information
    :type body: dict | bytes

    :rtype: str
    """
    response = requests.post(f"{business_url}/business", data=connexion.request.get_json())
    return response


def get_business(business_id):
    """Get the details of a business

    Retrieves information regarding a specific business

    :param business_id: ID of the business to retrieve
    :type business_id: float

    :rtype: Business
    """
    response = requests.get(f"{business_url}/business/{business_id}", data=connexion.request.get_json())
    return response


def get_businesses(lat, lng, radius, limit=None, skip=None):
    """Get a list of businesses within the radius of the coordinate given

    Retrieves a list of businesses

    :param lat: Search coordinates that match or are like &#x27;latitude&#x27;
    :type lat: str
    :param lng: Search coordinates that match or are like &#x27;longitude&#x27;
    :type lng: str
    :param radius: Radius Area considered to search
    :type radius: float
    :param limit: Number of elements to be returned
    :type limit: int
    :param skip: Number of elements to be skipped
    :type skip: int

    :rtype: Businesses
    """
    response = requests.get(f"{business_url}/business", data=connexion.request.get_json())
    return response


def remove_business(business_id):
    """Remove a business in the data used to calculate the value

    :param business_id: ID of the trip to delete
    :type business_id: str

    :rtype: None
    """
    response = requests.delete(f"{business_url}/business/{business_id}", data=connexion.request.get_json())
    return response


def update_business(business_id, body=None):
    """Change a business in the data used to calculate the value

    :param business_id: ID of the trip to delete
    :type business_id: str
    :param body: A JSON object containing business information
    :type body: dict | bytes

    :rtype: str
    """
    response = requests.put(f"{business_url}/business/{business_id}", data=connexion.request.get_json())
    return response
