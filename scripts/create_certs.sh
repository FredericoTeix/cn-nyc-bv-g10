#!/bin/bash

myArray=("ingress" "business" "keys" "trip" "value")
for str in ${myArray[@]}; do
    echo "Generating certificate for $str microservice"
    openssl req -nodes -newkey rsa:4096 -keyout $str/$str.key -out $str/$str.csr -subj "/CN=$str" 
    openssl x509 -req -days 365 -in $str/$str.csr -signkey $str/$str.key -out $str/$str.crt 
    echo $'\n'
done