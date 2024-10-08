# Profile Service
## This microservice is responsible for:
* Registration user profiles

## Tech stack
* Build tool: maven >= 3.8.8
* Java: 21
* Framework: Spring boot 3.2.x
* DBMS: Postgresql

## Prerequisites
* Java SDK 21
* A PG Admin server

## JaCoCo report
`mvn clean test jacoco:report`

## Spotless
`mvn spotless:apply`

## SonarQube
* `docker pull sonarqube:lts-community`
* `docker run --name sonar-qube -p 9000:9000 -d sonarqube:lts-community`
* `mvn clean verify sonar:sonar -Dsonar.projectKey=devery.profile.master -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_b33a4a7a230bcb98ad783b9ee2b678ae9c8fb404`

## Start application
`mvn spring-boot:run`

## Build application
`mvn clean package`

## Docker guideline
### Build docker image
`docker build -t profile-service:0.0.9 .`
### Push docker image to Docker Hub
`docker image push <account>/profile-service:0.0.9`
### Create network:
`docker network create pnk-network`
### Start Postgresql in pnk-network
`docker run --network pnk-network --name postgresql -p 5432:5432 -e POSTGRESQL_ROOT_PASSWORD=root -d postgresql:8.0.36-debian`
### Run your application in pnk-network
`docker run --name profile-service --network pnk-network -p 9190:9190 -e DBMS_CONNECTION=jdbc:postgresql://localhost:5432/hospital profile-service:0.0.9`
