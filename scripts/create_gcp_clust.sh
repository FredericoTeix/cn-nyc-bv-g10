#!/bin/bash

REGION="europe-west1-b"
CLUSTER_NAME="cntrips-cluster-2"

# Set project id if not specified yet
{
PROJECT=$(gcloud config get project)
} &> /dev/null
if [[ "${PROJECT}" == "" ]]; then
  read -p "No project id assigned. Specify an id: " PROJECT
  gcloud config set project "${PROJECT}"
fi

{
ACCOUNT=$(gcloud config get account)
} &> /dev/null
if [[ "${ACCOUNT}" == "" ]]; then
  echo "No account assigned. Please login"
  gcloud auth login
fi

gcloud services enable cloudbuild.googleapis.com container.googleapis.com
gcloud container clusters create ${CLUSTER_NAME} --zone ${REGION} --no-enable-basic-auth --preemptible \
  --cluster-version "1.21.11-gke.1100" --release-channel "stable" --machine-type "e2-standard-2" \
  --image-type "COS_CONTAINERD" --disk-type "pd-standard" --disk-size "30" --metadata disable-legacy-endpoints=true \
  --scopes=storage-ro,logging-write,monitoring-write,service-control,service-management,trace \
  --max-pods-per-node "110" --num-nodes "1" --enable-autoscaling --min-nodes "0" --max-nodes "4" --logging=SYSTEM,WORKLOAD --monitoring=SYSTEM --enable-ip-alias \
  --network "projects/${PROJECT}/global/networks/default" \
  --subnetwork "projects/${PROJECT}/regions/europe-west1/subnetworks/default" --no-enable-intra-node-visibility \
  --default-max-pods-per-node "110" \
  --no-enable-master-authorized-networks --addons HorizontalPodAutoscaling,HttpLoadBalancing,GcePersistentDiskCsiDriver \
  --enable-autoupgrade --enable-autorepair --max-surge-upgrade 1 --max-unavailable-upgrade 0 --enable-shielded-nodes \
  --node-locations ${REGION} --enable-autoprovisioning --max-cpu 8 --max-memory 32
gcloud container clusters get-credentials ${CLUSTER_NAME} --zone ${REGION}
kubectl create clusterrolebinding cluster-admin-binding --clusterrole=cluster-admin --user="${ACCOUNT}"

