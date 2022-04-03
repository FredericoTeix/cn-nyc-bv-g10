#!/bin/sh

# Build images
docker image build -f trip/DockerfileMongo . -t tripsmongo
docker image tag tripsmongo ralexandre00/tripsmongo:latest
docker push ralexandre00/tripsmongo:latest

docker image build -f trip/Dockerfile . -t trips
docker image tag trips ralexandre00/trips:latest
docker push ralexandre00/trips:latest
