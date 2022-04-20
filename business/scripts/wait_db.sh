#!/bin/bash

set -eu

cmd="$@"

if [ -z "${MONGO_DATABASE_URL:-}" ]; then
  echo "Please define the MONGO_DATABASE_URL environment variable"
  exit 1
fi

wait_for_mongodb() {
  local timeout=60
  local start_time="$(date +%s)"

  printf "Trying to connect to the mongodb...\\n"
  until mongosh "${MONGO_DATABASE_URL}" --eval "print('MongoDB init process complete; ready for start up.')"
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

printf "Databases up, executing >$cmd \\n"
exec $cmd