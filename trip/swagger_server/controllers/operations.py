from __future__ import print_function

import itertools
from datetime import datetime, timedelta

import connexion
from dateutil.rrule import rrule, MONTHLY, YEARLY, DAILY
from pymongo import ReturnDocument

from swagger_server.models.location import Location  # noqa: E501
from swagger_server.models.trip import Trip  # noqa: E501
from swagger_server.models.trip_update import TripUpdate  # noqa: E501
from swagger_server.models.trips import Trips  # noqa: E501
from swagger_server import util

import sys
import os

import pymongo
import bson

client = pymongo.MongoClient(os.getenv('MONGO_URL'))
db = client["trips-db"]


def increment_trip_counter(location_id, unit, counter, n):
    db["trips_" + unit].update_one(
        {
            unit: counter,
            "location_id": location_id
        },
        {
            "$inc": {"counter": n}
        },
        upsert=True
    )


def modify_trip_counters(trip, n):
    locations = [trip['pickup_location_id'], trip['dropoff_location_id']]
    dates = [trip['pickup_datetime'], trip['dropoff_datetime']]

    for location, date in zip(locations, dates):
        year = date.year - 2000
        month = year * 12 + date.month
        day = month * 31 + date.day
        increment_trip_counter(location, "year", year, n)
        increment_trip_counter(location, "month", month, n)
        increment_trip_counter(location, "day", day, n)


def last_day_of_month(any_day):
    # this will never fail
    # get close to the end of the month for any day, and add 4 days 'over'
    next_month = any_day.replace(day=28) + timedelta(days=4)
    # subtract the number of remaining 'overage' days to get last day of current month, or said programattically said, the previous day of the first of next month
    return next_month - timedelta(days=next_month.day)


def add_trip(trip, trip_id=None):
    """Add a trip to the data used to calculate the value

    :param trip: A object containing trip information
    :type trip: Trip
    :param trip_id: Optional id to put in trip
    :type trip_id: bson.ObjectId

    :rtype: str
    """
    dict_trip = trip.to_dict()
    dict_trip['pickup_datetime'] = datetime.strptime(dict_trip['pickup_datetime'][:-7], "%Y-%m-%d:%H:%M:%S")
    dict_trip['dropoff_datetime'] = datetime.strptime(dict_trip['dropoff_datetime'][:-7], "%Y-%m-%d:%H:%M:%S")
    modify_trip_counters(dict_trip, trip.passenger_count)
    if trip_id:
        dict_trip["_id"] = bson.ObjectId(trip_id)
    created_trip = db["trips"].insert_one(dict_trip)
    # print(created_trip.inserted_id, file=sys.stderr)

    return {"_id": str(created_trip.inserted_id)}


def get_location_by_id(location_id):  # noqa: E501
    """Find location description by ID

    Returns LocationID object.

    :param location_id: The ID of the Location to return.
    :type location_id: str

    :rtype: dict
    """
    zone = db.zones.find_one({"_id": int(location_id)})
    zone.pop('_id')
    return zone


def get_trips_count(location_id, start_date=None, end_date=None):  # noqa: E501
    """Find trips between pickup_datetime and dropoff_datetime.

    Returns an array of Trip objects.

    :param location_id: The ID of the Location to get the count
    :type location_id: str
    :param start_date: Every value data up to this date will be filtered out. If not specified no filtering is applied
    :type start_date: datetime
    :param end_date: Every value data after this date will be filtered out. If not specified no filtering is applied
    :type end_date: datetime

    :rtype: int
    """
    count = 0
    start_date = start_date.replace(microsecond=0)
    end_date = end_date.replace(microsecond=0)
    dates_year = [dt for dt in rrule(YEARLY, dtstart=start_date, until=end_date)]
    for date in dates_year[1:-1]:
        trip = db.trips_year.find_one({
            "location_id": int(location_id),
            "year": (date.year - 2000)
        })
        count += trip["counter"] if trip else 0

    if len(dates_year) == 1:
        dates_month_total = [dt for dt in rrule(MONTHLY, dtstart=start_date,
                                                until=last_day_of_month(end_date).replace(hour=23, minute=58,
                                                                                          second=59))]
    else:
        dates_month_1 = [dt for dt in rrule(MONTHLY, dtstart=start_date, until=dates_year[0].replace(month=11))]
        dates_month_2 = [dt for dt in rrule(MONTHLY, dtstart=dates_year[-1].replace(month=1),
                                            until=last_day_of_month(end_date).replace(hour=23, minute=58,
                                                                                      second=59))]
        dates_month_total = dates_month_1 + dates_month_2
    for date in dates_month_total[1:-1]:
        trip = db.trips_month.find_one({
            "location_id": int(location_id),
            "month": ((date.year - 2000) * 12 + date.month)
        })
        count += trip["counter"] if trip else 0

    dates_day_1 = [dt for dt in rrule(DAILY, dtstart=start_date,
                                      until=last_day_of_month(dates_month_total[0]).replace(hour=23, minute=58,
                                                                                            second=59))]
    dates_day_2 = [dt for dt in rrule(DAILY, dtstart=dates_month_total[-1].replace(day=1), until=end_date)]
    dates_day_total = [dates_day_1, dates_day_2]
    for date_list in dates_day_total:
        for date in date_list[1:-1]:
            trip = db.trips_day.find_one({
                "location_id": int(location_id),
                "day": ((date.year - 2000) * 12 + date.month) * 31 + date.day
            })
            count += trip["counter"] if trip else 0

    for date in db.trips.find({'pickup_location_id': location_id, 'pickup_datetime': {'$gte': start_date,
                                                                                      '$lte': dates_day_1[0].replace(
                                                                                          hour=23, minute=59,
                                                                                          second=59)}}):
        count += date["passenger_count"]
    for date in db.trips.find({'dropoff_location_id': location_id,
                               'dropoff_datetime': {'$gte': dates_day_2[-1].replace(hour=0, minute=0, second=0),
                                                    '$lte': end_date}}):
        count += date["passenger_count"]

    return count


def remove_trip(trip_id):  # noqa: E501
    """Remove a trip in the data used to calculate the value

    :param trip_id: ID of the trip to delete
    :type trip_id: str

    :rtype: None
    """
    deleted_trip = db.trips.find_one_and_delete({"_id": bson.ObjectId(trip_id)})

    modify_trip_counters(deleted_trip, -deleted_trip['passenger_count'])


def update_trip(trip_id, trip):  # noqa: E501
    """Change a trip in the data used to calculate the value


    :param trip_id: ID of the trip to delete
    :type trip_id: str
    :param trip: A object containing trip information
    :type trip: Trip

    :rtype: dict
    """
    trip_dict = {k: v for k, v in trip.to_dict().items() if v is not None}

    original_trip = db.trips.find_one(
        {'_id': bson.ObjectId(trip_id)}
    )

    updated_trip = db.trips.find_one_and_update(
        {'_id': bson.ObjectId(trip_id)},
        {'$set': trip_dict},
        return_document=ReturnDocument.AFTER
    )

    modify_trip_counters(updated_trip, updated_trip['passenger_count'])

    modify_trip_counters(original_trip, -original_trip['passenger_count'])

    del updated_trip["_id"]
    return updated_trip
