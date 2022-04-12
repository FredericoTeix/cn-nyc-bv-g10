FROM openjdk:11-jdk as build
WORKDIR /workspace/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle* settings.gradle* ./
RUN ./gradlew --version

COPY src src
RUN target=/root/.gradle ./gradlew assemble

FROM adoptopenjdk/openjdk11:alpine as final
RUN apk add --no-cache bash

WORKDIR /home
RUN mkdir -p ./app

COPY src/test/resources/scripts/wait_db.sh ./app/wait_db.sh
COPY src/test/resources/scripts/mongosh-0.13.2-linux-x64.tgz ./app/mongosh.tgz
RUN tar -zxvf ./app/mongosh.tgz
RUN mv bin/mongosh /usr/local/bin/
RUN mv bin/mongocryptd-mongosh /usr/local/bin/

COPY --from=build /workspace/app/build/libs/*.jar ./app/

USER root
ENTRYPOINT ["sh", "./app/wait_db.sh","java","-jar","./app/keys.jar"]