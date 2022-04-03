#!/bin/sh

kubectl delete deployment trips-deployment
kubectl delete deployment mongo-trips-deployment

# Start cluster
kubectl apply -f config/mongo-trips-secret.yaml
kubectl apply -f config/mongo-trips-configmap.yaml
kubectl apply -f config/mongo-trips.yaml
kubectl apply -f config/trips.yaml
