FROM maven:3.8.5-openjdk-18 AS build
COPY . .
RUN mvn clean package -DskipTest

FROM openjdk:18.0-jdk-slim
COPY --from=build /target/volleyball-scoreboard-backend-0.0.1-SNAPSHOT.jar volleyball-scoreboard-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","volleyball-scoreboard-backend.jar"]