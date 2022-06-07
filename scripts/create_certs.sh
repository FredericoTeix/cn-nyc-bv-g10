#!/bin/bash

default_services=("ingress" "business" "keys" "trip" "value")
services=("${@:-${default_services[@]}}")

for svc in ${services[@]}; do
    rm $svc/$svc.key
    rm $svc/$svc.csr
    rm $svc/$svc.crt
    rm $svc/$svc.p12
done 

for svc in ${services[@]}; do
    echo "Generating certificate for $svc microservice"
    openssl req -nodes -newkey rsa:4096 -keyout $svc/$svc.key -out $svc/$svc.csr -subj "/CN=$svc"
    openssl x509 -req -days 365 -in $svc/$svc.csr -signkey $svc/$svc.key -out $svc/$svc.crt
    openssl pkcs12 -export -out $svc/$svc.p12 -inkey $svc/$svc.key -in $svc/$svc.crt -name $svc -passout pass:
    echo $'\n'
done
