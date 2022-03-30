import os

import connexion
import requests

from swagger_server.models.businesses import Businesses  # noqa: E501
from swagger_server.models.trips import Trips  # noqa: E501
from swagger_server.models.value_response_business import ValueResponseBusiness  # noqa: E501

value_url = os.getenv('VALUE_URL')


def get_location_value(location_id, start_date=None, end_date=None):
    """Get the number of trips between pickup_datetime and dropoff_datetime in a given location.

    Returns an array of Trip objects.

    :param location_id: The ID of the Location
    :type location_id: int
    :param start_date: Every value data up to this date will be filtered out. If not specified no filtering is applied
    :type start_date: str
    :param end_date: Every value data after this date will be filtered out. If not specified no filtering is applied
    :type end_date: str

    :rtype: Trips
    """
    request_url = f"{value_url}/value/location/{location_id}"
    if start_date or end_date:
        request_url += f"?{connexion.request.query_string}"

    response = requests.get(request_url, data=connexion.request.get_json())
    return response


def top_value_businesses(num_results, start_date=None, end_date=None, categories=None):
    """Get the top valued businesses in a given time range

    :param num_results: number of top businesses to be returned
    :type num_results: float
    :param start_date: Every value data up to this date will be filtered out. If not specified no filtering is applied
    :type start_date: str
    :param end_date: Every value data after this date will be filtered out. If not specified no filtering is applied
    :type end_date: str
    :param categories: A comma-separated list of categories (check businesses categories documentation for more info)
    :type categories: List[str]

    :rtype: Businesses
    """
    request_url = f"{value_url}/value/top/businesses"
    if start_date or end_date:
        request_url += f"?{connexion.request.query_string}"
    response = requests.get(request_url, data=connexion.request.get_json())
    return response


def value_by_business(business_id, start_date=None, end_date=None):
    """Get the value associated to a business in a given time range

    :param business_id: ID of the business to get value
    :type business_id: str
    :param start_date: Every value data up to this date will be filtered out. If not specified no filtering is applied
    :type start_date: str
    :param end_date: Every value data after this date will be filtered out. If not specified no filtering is applied
    :type end_date: str

    :rtype: ValueResponseBusiness
    """
    request_url = f"{value_url}/value/business/{business_id}"
    if start_date or end_date:
        request_url += f"?{connexion.request.query_string}"

    response = requests.get(request_url, data=connexion.request.get_json())
    return response
