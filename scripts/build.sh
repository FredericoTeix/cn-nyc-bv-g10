#!/bin/sh
cd ..

# Build images
docker image build -f trip/DockerfileMongo . -t tripsmongo
docker image tag tripsmongo ralexandre00/tripsmongo:latest
docker push ralexandre00/tripsmongo:latest

docker image build -f trip/Dockerfile . -t trips
docker image tag trips ralexandre00/trips:latest
docker push ralexandre00/trips:latest

docker image build -f value/Dockerfile . -t value
docker image tag value docker1test1cn/value:latest
docker push docker1test1cn/value:latest