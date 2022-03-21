#!/bin/bash

function ctrl_c() {
        echo "** Trapped CTRL-C"
	sudo docker rm trip
}
trap ctrl_c INT

sudo docker build -t app:trip .
sudo docker rm trip
sudo docker run --name trip -p 8080:8080 app:trip
