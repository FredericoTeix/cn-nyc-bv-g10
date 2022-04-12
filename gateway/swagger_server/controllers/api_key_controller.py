import connexion
import six

from swagger_server.models.key import Key  # noqa: E501
from swagger_server import util


def edit_key_info(id, quota, contact):  # noqa: E501
    """Update API Key quota or contact

     # noqa: E501

    :param id: The API Key.
    :type id: str
    :param quota: The API Key maximum requests.
    :type quota: str
    :param contact: The API Key consumer contact.
    :type contact: str

    :rtype: None
    """
    return 'do some magic!'


def generate_key(contact, quota=None):  # noqa: E501
    """Generate an API Key

     # noqa: E501

    :param contact: contact of key consumer
    :type contact: str
    :param quota: maximum number of requests allowed
    :type quota: float

    :rtype: None
    """
    return 'do some magic!'


def inspect_key(id):  # noqa: E501
    """Get contact and quota of an API Key

     # noqa: E501

    :param id: The API Key.
    :type id: str

    :rtype: Key
    """
    return 'do some magic!'


def refresh_key(id):  # noqa: E501
    """Change the API Key

     # noqa: E501

    :param id: The API Key.
    :type id: str

    :rtype: Key
    """
    return 'do some magic!'


def revoke_key(id):  # noqa: E501
    """Revoke an API Key

     # noqa: E501

    :param id: The API Key.
    :type id: str

    :rtype: None
    """
    return 'do some magic!'
