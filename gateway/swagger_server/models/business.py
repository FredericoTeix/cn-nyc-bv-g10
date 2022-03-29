# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server import util


class Business(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, id: int=None, name: str=None, address: str=None, latitude: float=None, longitude: float=None):  # noqa: E501
        """Business - a model defined in Swagger

        :param id: The id of this Business.  # noqa: E501
        :type id: int
        :param name: The name of this Business.  # noqa: E501
        :type name: str
        :param address: The address of this Business.  # noqa: E501
        :type address: str
        :param latitude: The latitude of this Business.  # noqa: E501
        :type latitude: float
        :param longitude: The longitude of this Business.  # noqa: E501
        :type longitude: float
        """
        self.swagger_types = {
            'id': int,
            'name': str,
            'address': str,
            'latitude': float,
            'longitude': float
        }

        self.attribute_map = {
            'id': 'id',
            'name': 'name',
            'address': 'address',
            'latitude': 'latitude',
            'longitude': 'longitude'
        }
        self._id = id
        self._name = name
        self._address = address
        self._latitude = latitude
        self._longitude = longitude

    @classmethod
    def from_dict(cls, dikt) -> 'Business':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The Business of this Business.  # noqa: E501
        :rtype: Business
        """
        return util.deserialize_model(dikt, cls)

    @property
    def id(self) -> int:
        """Gets the id of this Business.


        :return: The id of this Business.
        :rtype: int
        """
        return self._id

    @id.setter
    def id(self, id: int):
        """Sets the id of this Business.


        :param id: The id of this Business.
        :type id: int
        """

        self._id = id

    @property
    def name(self) -> str:
        """Gets the name of this Business.


        :return: The name of this Business.
        :rtype: str
        """
        return self._name

    @name.setter
    def name(self, name: str):
        """Sets the name of this Business.


        :param name: The name of this Business.
        :type name: str
        """

        self._name = name

    @property
    def address(self) -> str:
        """Gets the address of this Business.


        :return: The address of this Business.
        :rtype: str
        """
        return self._address

    @address.setter
    def address(self, address: str):
        """Sets the address of this Business.


        :param address: The address of this Business.
        :type address: str
        """

        self._address = address

    @property
    def latitude(self) -> float:
        """Gets the latitude of this Business.


        :return: The latitude of this Business.
        :rtype: float
        """
        return self._latitude

    @latitude.setter
    def latitude(self, latitude: float):
        """Sets the latitude of this Business.


        :param latitude: The latitude of this Business.
        :type latitude: float
        """

        self._latitude = latitude

    @property
    def longitude(self) -> float:
        """Gets the longitude of this Business.


        :return: The longitude of this Business.
        :rtype: float
        """
        return self._longitude

    @longitude.setter
    def longitude(self, longitude: float):
        """Sets the longitude of this Business.


        :param longitude: The longitude of this Business.
        :type longitude: float
        """

        self._longitude = longitude
