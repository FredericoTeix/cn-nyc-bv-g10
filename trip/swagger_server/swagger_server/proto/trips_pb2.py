# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: swagger_server/proto/trips.proto
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


DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n swagger_server/proto/trips.proto\x12\x05trips\x1a\x1bgoogle/protobuf/empty.proto\x1a\x1fgoogle/protobuf/timestamp.proto\"\xc3\x01\n\x04Trip\x12\x1a\n\x12pickup_location_id\x18\x01 \x01(\t\x12\x1b\n\x13\x64ropoff_location_id\x18\x02 \x01(\t\x12\x33\n\x0fpickup_datetime\x18\x03 \x01(\x0b\x32\x1a.google.protobuf.Timestamp\x12\x34\n\x10\x64ropoff_datetime\x18\x04 \x01(\x0b\x32\x1a.google.protobuf.Timestamp\x12\x17\n\x0fpassenger_count\x18\x05 \x01(\x05\"T\n\x08Location\x12\x0c\n\x04zone\x18\x01 \x01(\t\x12\x0f\n\x07\x62orough\x18\x02 \x01(\t\x12\x13\n\x0blocation_id\x18\x03 \x01(\x05\x12\x14\n\x0cservice_zone\x18\x04 \x01(\t\"\x19\n\x06TripID\x12\x0f\n\x07trip_id\x18\x01 \x01(\t\"!\n\nLocationID\x12\x13\n\x0blocation_id\x18\x01 \x01(\t\"N\n\x11RemoveTripRequest\x12\x1e\n\x07trip_id\x18\x01 \x01(\x0b\x32\r.trips.TripID\x12\x19\n\x04trip\x18\x02 \x01(\x0b\x32\x0b.trips.Trip\"\x93\x01\n\x1eGetCountTripsInLocationRequest\x12.\n\nstart_date\x18\x01 \x01(\x0b\x32\x1a.google.protobuf.Timestamp\x12,\n\x08\x65nd_date\x18\x02 \x01(\x0b\x32\x1a.google.protobuf.Timestamp\x12\x13\n\x0blocation_id\x18\x03 \x01(\t\"0\n\x1fGetCountTripsInLocationResponse\x12\r\n\x05\x63ount\x18\x01 \x01(\x03\x32\xc3\x02\n\x05Trips\x12\'\n\x07\x41\x64\x64Trip\x12\x0b.trips.Trip\x1a\r.trips.TripID\"\x00\x12\x37\n\x0fGetLocationById\x12\x11.trips.LocationID\x1a\x0f.trips.Location\"\x00\x12@\n\nRemoveTrip\x12\x18.trips.RemoveTripRequest\x1a\x16.google.protobuf.Empty\"\x00\x12*\n\nUpdateTrip\x12\r.trips.TripID\x1a\x0b.trips.Trip\"\x00\x12j\n\x17GetCountTripsInLocation\x12%.trips.GetCountTripsInLocationRequest\x1a&.trips.GetCountTripsInLocationResponse\"\x00\x62\x06proto3')



_TRIP = DESCRIPTOR.message_types_by_name['Trip']
_LOCATION = DESCRIPTOR.message_types_by_name['Location']
_TRIPID = DESCRIPTOR.message_types_by_name['TripID']
_LOCATIONID = DESCRIPTOR.message_types_by_name['LocationID']
_REMOVETRIPREQUEST = DESCRIPTOR.message_types_by_name['RemoveTripRequest']
_GETCOUNTTRIPSINLOCATIONREQUEST = DESCRIPTOR.message_types_by_name['GetCountTripsInLocationRequest']
_GETCOUNTTRIPSINLOCATIONRESPONSE = DESCRIPTOR.message_types_by_name['GetCountTripsInLocationResponse']
Trip = _reflection.GeneratedProtocolMessageType('Trip', (_message.Message,), {
  'DESCRIPTOR' : _TRIP,
  '__module__' : 'swagger_server.proto.trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.Trip)
  })
_sym_db.RegisterMessage(Trip)

Location = _reflection.GeneratedProtocolMessageType('Location', (_message.Message,), {
  'DESCRIPTOR' : _LOCATION,
  '__module__' : 'swagger_server.proto.trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.Location)
  })
_sym_db.RegisterMessage(Location)

TripID = _reflection.GeneratedProtocolMessageType('TripID', (_message.Message,), {
  'DESCRIPTOR' : _TRIPID,
  '__module__' : 'swagger_server.proto.trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.TripID)
  })
_sym_db.RegisterMessage(TripID)

LocationID = _reflection.GeneratedProtocolMessageType('LocationID', (_message.Message,), {
  'DESCRIPTOR' : _LOCATIONID,
  '__module__' : 'swagger_server.proto.trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.LocationID)
  })
_sym_db.RegisterMessage(LocationID)

RemoveTripRequest = _reflection.GeneratedProtocolMessageType('RemoveTripRequest', (_message.Message,), {
  'DESCRIPTOR' : _REMOVETRIPREQUEST,
  '__module__' : 'swagger_server.proto.trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.RemoveTripRequest)
  })
_sym_db.RegisterMessage(RemoveTripRequest)

GetCountTripsInLocationRequest = _reflection.GeneratedProtocolMessageType('GetCountTripsInLocationRequest', (_message.Message,), {
  'DESCRIPTOR' : _GETCOUNTTRIPSINLOCATIONREQUEST,
  '__module__' : 'swagger_server.proto.trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.GetCountTripsInLocationRequest)
  })
_sym_db.RegisterMessage(GetCountTripsInLocationRequest)

GetCountTripsInLocationResponse = _reflection.GeneratedProtocolMessageType('GetCountTripsInLocationResponse', (_message.Message,), {
  'DESCRIPTOR' : _GETCOUNTTRIPSINLOCATIONRESPONSE,
  '__module__' : 'swagger_server.proto.trips_pb2'
  # @@protoc_insertion_point(class_scope:trips.GetCountTripsInLocationResponse)
  })
_sym_db.RegisterMessage(GetCountTripsInLocationResponse)

_TRIPS = DESCRIPTOR.services_by_name['Trips']
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  _TRIP._serialized_start=106
  _TRIP._serialized_end=301
  _LOCATION._serialized_start=303
  _LOCATION._serialized_end=387
  _TRIPID._serialized_start=389
  _TRIPID._serialized_end=414
  _LOCATIONID._serialized_start=416
  _LOCATIONID._serialized_end=449
  _REMOVETRIPREQUEST._serialized_start=451
  _REMOVETRIPREQUEST._serialized_end=529
  _GETCOUNTTRIPSINLOCATIONREQUEST._serialized_start=532
  _GETCOUNTTRIPSINLOCATIONREQUEST._serialized_end=679
  _GETCOUNTTRIPSINLOCATIONRESPONSE._serialized_start=681
  _GETCOUNTTRIPSINLOCATIONRESPONSE._serialized_end=729
  _TRIPS._serialized_start=732
  _TRIPS._serialized_end=1055
# @@protoc_insertion_point(module_scope)
