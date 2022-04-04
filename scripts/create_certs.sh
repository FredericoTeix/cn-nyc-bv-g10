#!/bin/bash

openssl req -nodes -newkey rsa:4096 -keyout ingress/ingress.key -out ingress/ingress.csr -subj "/CN=ingress"
openssl x509 -req -days 365 -in ingress/ingress.csr -signkey ingress/ingress.key -out ingress/ingress.crt

