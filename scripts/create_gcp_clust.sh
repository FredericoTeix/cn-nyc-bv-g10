#!/bin/bash

REGION="europe-west1-b"
CLUSTER_NAME="cntrips-cluster-1"

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
  --cluster-version "1.21.10-gke.2000" --release-channel "stable" --machine-type "e2-standard-2" \
  --image-type "COS_CONTAINERD" --disk-type "pd-standard" --disk-size "30" --metadata disable-legacy-endpoints=true \
  --scopes=storage-ro,logging-write,monitoring-write,service-control,service-management,trace \
  --max-pods-per-node "110" --num-nodes "2" --enable-autoscaling --min-nodes "0" --max-nodes "3" --logging=SYSTEM,WORKLOAD --monitoring=SYSTEM --enable-ip-alias \
  --network "projects/${PROJECT}/global/networks/default" \
  --subnetwork "projects/${PROJECT}/regions/europe-west1/subnetworks/default" --no-enable-intra-node-visibility \
  --default-max-pods-per-node "110" \
  --no-enable-master-authorized-networks --addons HorizontalPodAutoscaling,HttpLoadBalancing,GcePersistentDiskCsiDriver \
  --enable-autoupgrade --enable-autorepair --max-surge-upgrade 1 --max-unavailable-upgrade 0 --enable-shielded-nodes \
  --node-locations ${REGION} --enable-autoprovisioning --max-cpu 2 --max-memory 8
gcloud container clusters get-credentials ${CLUSTER_NAME} --zone ${REGION}
kubectl create clusterrolebinding cluster-admin-binding --clusterrole=cluster-admin --user="${ACCOUNT}"

