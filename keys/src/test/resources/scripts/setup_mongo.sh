#!/usr/bin/env bash

if [ -z "${MONGO_DB1_URL:-}" ]; then
  echo "Please define the MONGO_DATABASE_URL environment variable"
  exit 1
fi

wait_for_mongodb() {
  local timeout=60
  local start_time="$(date +%s)"

  printf "Trying to connect to the mongodb...\\n"
  until mongosh "${MONGO_DB1_URL}" --eval "print('MongoDB is waiting for connections')"
    do
      current_time="$(date +%s)"
      if [ "$(( current_time - start_time ))" -ge "$timeout" ]; then
        printf "Timeout reached. Exiting...\\n"
        exit 1
      fi
      sleep 1
    done

  printf "Connection established with the database.\\n"
}

wait_for_mongodb

#var multi_cfg = {
#    "_id": "rs0",
#    "protocolVersion": 1,
#    "version": 1,
#    "members": [
#        {
#            "_id": 0,
#            "host": "${MONGODB1}:27017",
#            "priority": 2
#        },
#        {
#            "_id": 1,
#            "host": "${MONGODB2}:27017",
#            "priority": 0
#        },
#        {
#            "_id": 2,
#            "host": "${MONGODB3}:27017",
#            "priority": 0
#        }
#    ],settings: {chainingAllowed: true}
#};

mongosh "${MONGO_DB1_URL}" <<EOF
var cfg = {
    "_id": "rs0",
    "protocolVersion": 1,
    "version": 1,
    "members": [
        {
            "_id": 0,
            "host": "${MONGO_DB1_URL}",
            "priority": 2
        }
    ],settings: {chainingAllowed: true}
};
rs.initiate(cfg, { force: true });
rs.reconfig(cfg, { force: true });
db.getMongo().setReadPref('nearest');
EOF