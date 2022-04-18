# TourGuide Application

TourGuide is a Spring Boot application that has been a centerpiece in TripMasters app portfolio. It allows users to see tourist attractions and to get package deals on hotel stays and admission to various attractions.

## Technologies
- Java 16
- Gradle
- Spring
- JUnit
- Jacoco

## About TourGuide
### There is 4 containers with these specific names :
- microservicetourguide

docker compose alias : tour-guide,
This container work on port 80

- microserviceattraction

docker compose alias : attraction,
This container work on port 81

- microservicetrip

docker compose alias : trip,
This container work on port 82

- microservicevisitedlocation

docker compose alias : visited-location,
This container work on port 83

###How to use 
First, create each image with the specific names provided.

Second, use the command docker compose up with the docker-compose.yml in the tourguide project.

When the 4 containers are up, you have to use this endpoint before using the others
http://localhost:80/initializeUsers

After initializing the required users, 
use http://localhost:80/NameOfSpecificEndpoint with required parameters if necessary to use the application

## Authors

Our code squad : Rebecca & Francky

## Licensing

This project was built under the Creative Commons Licence.
