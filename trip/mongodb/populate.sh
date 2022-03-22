#!/usr/bin/env bash

mongoimport --collection zones --type CSV --file /scripts/zone_lookup.csv --uri mongodb://rhythmfishing:Jw8HsxQtQHFPzDQj7W8b@mongo-trips:27017/trips-db --fields "_id","borough","zone","service_zone"
