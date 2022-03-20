#!/bin/bash

sudo docker build -t app:trip .
sudo docker rm trip
sudo docker run --name trip -p 8080:8080 app:trip
