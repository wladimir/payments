FROM openjdk:17-jdk-slim-buster

EXPOSE 8080

COPY ./app/target/app-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-Xmx2G", "-jar", "app.jar"]
