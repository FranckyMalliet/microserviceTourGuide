FROM openjdk:16-jdk-alpine3.13
EXPOSE 80
VOLUME /tmp
ARG JAR_FILE=microservice-tourGuide/build/libs/microserviceTourGuide.jar
COPY ${JAR_FILE} microserviceTourGuide.jar
ENTRYPOINT ["java","-jar","/microserviceTourGuide.jar"]