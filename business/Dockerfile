FROM openjdk:11-jdk as build
WORKDIR /workspace/business

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
RUN mkdir -p ./business

COPY --from=build workspace/business/scripts/wait_db.sh ./business/wait_db.sh
COPY --from=build workspace/business/scripts/mongosh-0.13.2-linux-x64.tgz ./business/mongosh.tgz
RUN tar -zxvf ./business/mongosh.tgz
RUN ls
RUN mv ./bin/mongosh /usr/local/bin/
RUN mv ./bin/mongocryptd-mongosh /usr/local/bin/

COPY --from=build workspace/business/server/build/libs/server.jar ./business/

USER root
ENTRYPOINT ["sh", "./business/wait_db.sh","java","-jar","./business/server.jar"]