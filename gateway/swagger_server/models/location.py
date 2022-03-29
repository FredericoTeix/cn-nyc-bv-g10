# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server import util


class Location(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, location_id: int=None, borough: str=None, zone: str=None, service_zone: str=None):  # noqa: E501
        """Location - a model defined in Swagger

        :param location_id: The location_id of this Location.  # noqa: E501
        :type location_id: int
        :param borough: The borough of this Location.  # noqa: E501
        :type borough: str
        :param zone: The zone of this Location.  # noqa: E501
        :type zone: str
        :param service_zone: The service_zone of this Location.  # noqa: E501
        :type service_zone: str
        """
        self.swagger_types = {
            'location_id': int,
            'borough': str,
            'zone': str,
            'service_zone': str
        }

        self.attribute_map = {
            'location_id': 'location_id',
            'borough': 'borough',
            'zone': 'zone',
            'service_zone': 'service_zone'
        }
        self._location_id = location_id
        self._borough = borough
        self._zone = zone
        self._service_zone = service_zone

    @classmethod
    def from_dict(cls, dikt) -> 'Location':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The Location of this Location.  # noqa: E501
        :rtype: Location
        """
        return util.deserialize_model(dikt, cls)

    @property
    def location_id(self) -> int:
        """Gets the location_id of this Location.


        :return: The location_id of this Location.
        :rtype: int
        """
        return self._location_id

    @location_id.setter
    def location_id(self, location_id: int):
        """Sets the location_id of this Location.


        :param location_id: The location_id of this Location.
        :type location_id: int
        """
        if location_id is None:
            raise ValueError("Invalid value for `location_id`, must not be `None`")  # noqa: E501

        self._location_id = location_id

    @property
    def borough(self) -> str:
        """Gets the borough of this Location.


        :return: The borough of this Location.
        :rtype: str
        """
        return self._borough

    @borough.setter
    def borough(self, borough: str):
        """Sets the borough of this Location.


        :param borough: The borough of this Location.
        :type borough: str
        """
        if borough is None:
            raise ValueError("Invalid value for `borough`, must not be `None`")  # noqa: E501

        self._borough = borough

    @property
    def zone(self) -> str:
        """Gets the zone of this Location.


        :return: The zone of this Location.
        :rtype: str
        """
        return self._zone

    @zone.setter
    def zone(self, zone: str):
        """Sets the zone of this Location.


        :param zone: The zone of this Location.
        :type zone: str
        """
        if zone is None:
            raise ValueError("Invalid value for `zone`, must not be `None`")  # noqa: E501

        self._zone = zone

    @property
    def service_zone(self) -> str:
        """Gets the service_zone of this Location.


        :return: The service_zone of this Location.
        :rtype: str
        """
        return self._service_zone

    @service_zone.setter
    def service_zone(self, service_zone: str):
        """Sets the service_zone of this Location.


        :param service_zone: The service_zone of this Location.
        :type service_zone: str
        """
        if service_zone is None:
            raise ValueError("Invalid value for `service_zone`, must not be `None`")  # noqa: E501

        self._service_zone = service_zone
