#!/bin/sh
cd ..

kubectl delete deployment trips-deployment
kubectl delete deployment mongo-trips-deployment

# Start cluster
kubectl apply -f config/mongo-trips-secret.yaml
kubectl apply -f config/mongo-trips-configmap.yaml
kubectl apply -f config/mongo-trips.yaml
kubectl apply -f config/trips.yaml
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.3/deploy/static/provider/cloud/deploy.yaml

kubectl create secret tls ingress-secret --cert ingress/ingress.crt --key ingress/ingress.key
kubectl apply -f config/ingress.yaml

NGINX_INGRESS_IP=$(kubectl get service ingress-nginx-controller -n ingress-nginx -ojson | jq -r '.status.loadBalancer.ingress[].ip')
export NGINX_INGRESS_IP

echo "${NGINX_INGRESS_IP}"
