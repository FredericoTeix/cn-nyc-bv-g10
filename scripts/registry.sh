#!/bin/bash
# Creates a Google Gloud Artifact Registry
# Grants permissions to collaboratos and creates a Service Sccount for GitHub CD

REGION="europe-west1"
REGISTRY_NAME="cntrips-registry"
DESCRIPTION="Registry for all images of CN NYC Business Value G10 project services"


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

gcloud services enable artifactregistry.googleapis.com

# Create the artifact repository
gcloud artifacts repositories create ${REGISTRY_NAME} --repository-format=docker  \
    --location=${REGION} --description="${DESCRIPTION}"

# Authenticate docker to use google registry
gcloud auth configure-docker ${REGION}-docker.pkg.dev

# Grant permissions to manage the repository 
gcloud artifacts repositories set-iam-policy ${REGISTRY_NAME} policy.yaml --location=${REGION}

# Create a service account for github to push images
gcloud iam service-accounts create github-image-writer \
    --description="Service Account that allows GitHub Actions to push images to cn-trips artifact registry" \
    --display-name="github-image-writer"

# Grant write permission to github account
gcloud artifacts repositories add-iam-policy-binding ${REGISTRY_NAME} \
 --location=${REGION} \
 --member="serviceAccount:github-image-writer@${PROJECT}.iam.gserviceaccount.com" \
 --role=roles/artifactregistry.writer

# Create github service account key
gcloud iam service-accounts keys create gh-private.json \
 --iam-account=github-image-writer@${PROJECT}.iam.gserviceaccount.com
