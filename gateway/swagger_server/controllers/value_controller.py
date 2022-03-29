import connexion
import six

from swagger_server.models.businesses import Businesses  # noqa: E501
from swagger_server.models.trips import Trips  # noqa: E501
from swagger_server.models.value_response_business import ValueResponseBusiness  # noqa: E501
from swagger_server import util


def get_trips_count(location_id, start_date=None, end_date=None):  # noqa: E501
    """Get the number of trips between pickup_datetime and dropoff_datetime in a given location.

    Returns an array of Trip objects. # noqa: E501

    :param location_id: The ID of the Location
    :type location_id: int
    :param start_date: Every value data up to this date will be filtered out. If not specified no filtering is applied
    :type start_date: str
    :param end_date: Every value data after this date will be filtered out. If not specified no filtering is applied
    :type end_date: str

    :rtype: Trips
    """
    start_date = util.deserialize_datetime(start_date)
    end_date = util.deserialize_datetime(end_date)
    return 'do some magic!'


def top_value_businesses(num_results, start_date=None, end_date=None, categories=None):  # noqa: E501
    """Get the top valued businesses in a given time range

     # noqa: E501

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
    start_date = util.deserialize_datetime(start_date)
    end_date = util.deserialize_datetime(end_date)
    return 'do some magic!'


def value_by_business(business_id, start_date=None, end_date=None):  # noqa: E501
    """Get the value associated to a business in a given time range

     # noqa: E501

    :param business_id: ID of the business to get value
    :type business_id: str
    :param start_date: Every value data up to this date will be filtered out. If not specified no filtering is applied
    :type start_date: str
    :param end_date: Every value data after this date will be filtered out. If not specified no filtering is applied
    :type end_date: str

    :rtype: ValueResponseBusiness
    """
    start_date = util.deserialize_datetime(start_date)
    end_date = util.deserialize_datetime(end_date)
    return 'do some magic!'
