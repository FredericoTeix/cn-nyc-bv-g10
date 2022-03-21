# API Keys service

## Overview


## Running with Docker

To run the service on a Docker environment, please execute the following from the root directory:

```bash
docker compose -f .\.docker\compose.yml up
```

A MongoDB database is configured with a replica set to enable multi-document transactions, however, since we are in the early stages of development, only one replica is created. If replicas or transactions aren't required, the deployment could be simplified by removing the ``mongo-setup`` container, responsible for setting up the mongodb insntances.  

The database should be available at ``mongodb://keys-mongodb1:27017`` in the docker network, and locally at ``mongodb://localhost:27017``