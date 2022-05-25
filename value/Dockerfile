FROM openjdk:11-jdk as build
WORKDIR /workspace/value

COPY gradlew .
COPY gradle gradle
COPY build.gradle* settings.gradle* ./
RUN ./gradlew --version

COPY proto proto
COPY value value
RUN target=/root/.gradle ./gradlew bootJar

FROM adoptopenjdk/openjdk11:alpine as final
RUN apk add --no-cache bash

WORKDIR /home
RUN mkdir -p ./value

COPY --from=build workspace/value/value/build/libs/value-0.0.1-SNAPSHOT.jar ./value/

USER root
ENTRYPOINT ["java","-jar","./value/value-0.0.1-SNAPSHOT.jar"]