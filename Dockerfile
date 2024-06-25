FROM openjdk:21

WORKDIR /app

COPY target/volleyball-scoreboard-backend.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
