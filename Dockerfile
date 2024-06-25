FROM maven:3.9.8-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTest

FROM openjdk:21-ea-1-jdk-slim
COPY --from=build /target/volleyball-scoreboard-backend-0.0.1-SNAPSHOT.jar volleyball-scoreboard-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","volleyball-scoreboard-backend.jar"]