#!/bin/bash

# Here to facilitate debug
{
kubectl delete deployment trips-deployment
kubectl delete deployment mongo-trips-deployment
kubectl delete deployment keys-deployment
kubectl delete deployment mongo-keys-deployment
kubectl delete deployment prometheus-deployment
kubectl delete deployment grafana-deployment
kubectl delete deployment business-deployment
kubectl delete deployment mongo-business-deployment
} &> /dev/null

# Start ingress controller (had to be done first because it is the one that takes the most time)
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.3/deploy/static/provider/cloud/deploy.yaml

# Start cluster
kubectl apply -f config/mongo-trips-secret.yaml
kubectl apply -f config/mongo-trips-configmap.yaml
kubectl apply -f config/mongo-trips.yaml
kubectl apply -f config/trips.yaml

kubectl apply -f config/mongo-keys.yaml
kubectl apply -f config/keys-configmap.yaml
kubectl apply -f config/keys.yaml

kubectl apply -f config/prometheus-cm.yaml
kubectl apply -f config/prometheus.yaml
kubectl apply -f config/grafana.yaml

kubectl apply -f config/mongo-business.yaml
kubectl apply -f config/business-configmap.yaml
kubectl apply -f config/business.yaml

kubectl apply -f config/value-configmap.yaml
kubectl apply -f config/value.yaml

kubectl create secret tls ingress-secret --cert ingress/ingress.crt --key ingress/ingress.key

printf "Waiting for ingress controller to initialize fully..."
{
kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=120s
}
printf "Waiting for grafana to initialize fully..."
{
kubectl wait deployment -n default \
  grafana-deployment \
  --for condition=Available=True \
  --timeout=90s
}
kubectl apply -f config/ingress.yaml

NGINX_INGRESS_IP=$(kubectl get service ingress-nginx-controller -n ingress-nginx -ojson | jq -r '.status.loadBalancer.ingress[].ip')
export NGINX_INGRESS_IP

echo External IP - "${NGINX_INGRESS_IP}"

GRAFANA_IP=$(kubectl get service grafana-service -ojson | jq -r '.status.loadBalancer.ingress[].ip')
echo Grafana IP - "${GRAFANA_IP}"
