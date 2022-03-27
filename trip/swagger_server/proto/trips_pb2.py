# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: trips.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()


from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2
from google.protobuf import timestamp_pb2 as google_dot_protobuf_dot_timestamp__pb2


DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x0btrips.proto\x12\x05trips\x1a\x1bgoogle/protobuf/empty.proto\x1a\x1fgoogle/protobuf/timestamp.proto\"\xc3\x01\n\x04Trip\x12\x1a\n\x12pickup_location_id\x18\x01 \x01(\t\x12\x1b\n\x13\x64ropoff_location_id\x18\x02 \x01(\t\x12\x33\n\x0fpickup_datetime\x18\x03 \x01(\x0b\x32\x1a.google.protobuf.Timestamp\x12\x34\n\x10\x64ropoff_datetime\x18\x04 \x01(\x0b\x32\x1a.google.protobuf.Timestamp\x12\x17\n\x0fpassenger_count\x18\x05 \x01(\x05\"T\n\x08Location\x12\x0c\n\x04zone\x18\x01 \x01(\t\x12\x0f\n\x07\x62orough\x18\x02 \x01(\t\x12\x13\n\x0blocation_id\x18\x03 \x01(\x05\x12\x14\n\x0cservice_zone\x18\x04 \x01(\t\"\x19\n\x06TripID\x12\x0f\n\x07trip_id\x18\x01 \x01(\t\"!\n\nLocationID\x12\x13\n\x0blocation_id\x18\x01 \x01(\t\"\x93\x01\n\x1eGetCountTripsInLocationRequest\x12.\n\nstart_date\x18\x01 \x01(\x0b\x32\x1a.google.protobuf.Timestamp\x12,\n\x08\x65nd_date\x18\x02 \x01(\x0b\x32\x1a.google.protobuf.Timestamp\x12\x13\n\x0blocation_id\x18\x03 \x01(\t\"0\n\x1fGetCountTripsInLocationResponse\x12\r\n\x05\x63ount\x18\x01 \x01(\x03\x32\xb8\x02\n\x05Trips\x12\'\n\x07\x41\x64\x64Trip\x12\x0b.trips.Trip\x1a\r.trips.TripID\"\x00\x12\x37\n\x0fGetLocationById\x12\x11.trips.LocationID\x1a\x0f.trips.Location\"\x00\x12\x35\n\nRemoveTrip\x12\r.trips.TripID\x1a\x16.google.protobuf.Empty\"\x00\x12*\n\nUpdateTrip\x12\r.trips.TripID\x1a\x0b.trips.Trip\"\x00\x12j\n\x17GetCountTripsInLocation\x12%.trips.GetCountTripsInLocationRequest\x1a&.trips.GetCountTripsInLocationResponse\"\x00\x62\x06proto3')



_TRIP = DESCRIPTOR.message_types_by_name['Trip']
_LOCATION = DESCRIPTOR.message_types_by_name['Location']
_TRIPID = DESCRIPTOR.message_types_by_name['TripID']
_LOCATIONID = DESCRIPTOR.message_types_by_name['LocationID']
_GETCOUNTTRIPSINLOCATIONREQUEST = DESCRIPTOR.message_types_by_name['GetCountTripsInLocationRequest']
_GETCOUNTTRIPSINLOCATIONRESPONSE = DESCRIPTOR.message_types_by_name['GetCountTripsInLocationResponse']
Trip = _reflection.GeneratedProtocolMessageType('Trip', (_message.Message,), {
  'DESCRIPTOR' : _TRIP,
  '__module__' : 'trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.Trip)
  })
_sym_db.RegisterMessage(Trip)

Location = _reflection.GeneratedProtocolMessageType('Location', (_message.Message,), {
  'DESCRIPTOR' : _LOCATION,
  '__module__' : 'trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.Location)
  })
_sym_db.RegisterMessage(Location)

TripID = _reflection.GeneratedProtocolMessageType('TripID', (_message.Message,), {
  'DESCRIPTOR' : _TRIPID,
  '__module__' : 'trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.TripID)
  })
_sym_db.RegisterMessage(TripID)

LocationID = _reflection.GeneratedProtocolMessageType('LocationID', (_message.Message,), {
  'DESCRIPTOR' : _LOCATIONID,
  '__module__' : 'trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.LocationID)
  })
_sym_db.RegisterMessage(LocationID)

GetCountTripsInLocationRequest = _reflection.GeneratedProtocolMessageType('GetCountTripsInLocationRequest', (_message.Message,), {
  'DESCRIPTOR' : _GETCOUNTTRIPSINLOCATIONREQUEST,
  '__module__' : 'trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.GetCountTripsInLocationRequest)
  })
_sym_db.RegisterMessage(GetCountTripsInLocationRequest)

GetCountTripsInLocationResponse = _reflection.GeneratedProtocolMessageType('GetCountTripsInLocationResponse', (_message.Message,), {
  'DESCRIPTOR' : _GETCOUNTTRIPSINLOCATIONRESPONSE,
  '__module__' : 'trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.GetCountTripsInLocationResponse)
  })
_sym_db.RegisterMessage(GetCountTripsInLocationResponse)

_TRIPS = DESCRIPTOR.services_by_name['Trips']
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  _TRIP._serialized_start=85
  _TRIP._serialized_end=280
  _LOCATION._serialized_start=282
  _LOCATION._serialized_end=366
  _TRIPID._serialized_start=368
  _TRIPID._serialized_end=393
  _LOCATIONID._serialized_start=395
  _LOCATIONID._serialized_end=428
  _GETCOUNTTRIPSINLOCATIONREQUEST._serialized_start=431
  _GETCOUNTTRIPSINLOCATIONREQUEST._serialized_end=578
  _GETCOUNTTRIPSINLOCATIONRESPONSE._serialized_start=580
  _GETCOUNTTRIPSINLOCATIONRESPONSE._serialized_end=628
  _TRIPS._serialized_start=631
  _TRIPS._serialized_end=943
# @@protoc_insertion_point(module_scope)
