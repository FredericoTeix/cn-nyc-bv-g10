#!/bin/bash

sudo docker build -t app:trip .
sudo docker run --rm --name trip -p 8080:8080 app:trip
