# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.location import Location  # noqa: E501
from swagger_server.models.trip import Trip  # noqa: E501
from swagger_server.models.trips import Trips  # noqa: E501
from swagger_server.test import BaseTestCase


class TestTripController(BaseTestCase):
    """TripController integration test stubs"""

    def test_add_trip(self):
        """Test case for add_trip

        Add a trip to the data used to calculate the value
        """
        body = Trip()
        response = self.client.open(
            '/trip',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_get_location_by_id(self):
        """Test case for get_location_by_id

        Find location description by ID
        """
        response = self.client.open(
            '/trip/location/{id}'.format(id=56),
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_get_trip_between_date_time(self):
        """Test case for get_trip_between_date_time

        Find trips between pickup_datetime and dropoff_datetime.
        """
        query_string = [('start_date', '2013-10-20T19:20:30+01:00'),
                        ('end_date', '2013-10-20T19:20:30+01:00'),
                        ('limit', 1)]
        response = self.client.open(
            '/trip',
            method='GET',
            query_string=query_string)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_remove_trip(self):
        """Test case for remove_trip

        Remove a trip in the data used to calculate the value
        """
        response = self.client.open(
            '/trip/{id}'.format(id='id_example'),
            method='DELETE')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_update_trip(self):
        """Test case for update_trip

        Change a trip in the data used to calculate the value
        """
        body = Trip()
        response = self.client.open(
            '/trip/{id}'.format(id='id_example'),
            method='PUT',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
