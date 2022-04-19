FROM openjdk:11-jdk as build
WORKDIR /workspace/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle* settings.gradle* ./
RUN ./gradlew --version

COPY proto proto
COPY server server
COPY scripts scripts
RUN target=/root/.gradle ./gradlew bootJar

FROM adoptopenjdk/openjdk11:alpine as final
RUN apk add --no-cache bash

WORKDIR /home
RUN mkdir -p ./app

COPY --from=build workspace/app/scripts/wait_db.sh ./app/wait_db.sh
COPY --from=build workspace/app/scripts/mongosh-0.13.2-linux-x64.tgz ./app/mongosh.tgz
RUN tar -zxvf ./app/mongosh.tgz
RUN ls
RUN mv ./bin/mongosh /usr/local/bin/
RUN mv ./bin/mongocryptd-mongosh /usr/local/bin/

COPY --from=build workspace/app/server/build/libs/server.jar ./app/

USER root
ENTRYPOINT ["sh", "./app/wait_db.sh","java","-jar","./app/business.jar"]