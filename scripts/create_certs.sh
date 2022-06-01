#!/bin/bash

myArray=("ingress" "business" "keys" "trip" "value")
for str in ${myArray[@]}; do
    rm $str/$str.key
    rm $str/$str.csr
    rm $str/$str.crt
    rm $str/$str.p12
done 

for str in ${myArray[@]}; do
    echo "Generating certificate for $str microservice"
    openssl req -nodes -newkey rsa:4096 -keyout $str/$str.key -out $str/$str.csr -subj "/CN=$str"
    openssl x509 -req -days 365 -in $str/$str.csr -signkey $str/$str.key -out $str/$str.crt
    openssl pkcs12 -export -out $str/$str.p12 -inkey $str/$str.key -in $str/$str.crt -name $str -passout pass:
    echo $'\n'
done
