# Use Openjdk:11-jdk as an image base
FROM openjdk:11-jdk

# Expose container port nº8080
EXPOSE 8080

ENV TRIP 172.2.1.1
ENV TRIP_PORT 50051
ENV BUSINESS 192.0.0.10
ENV BUSINESS_PORT 8080

ADD value/build/libs/value-0.0.1-SNAPSHOT.jar value-0.0.1-SNAPSHOT.jar

# Command to run the jar
ENTRYPOINT ["java","-jar","/value-0.0.1-SNAPSHOT.jar"]
