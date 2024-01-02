
FROM maven:3.8.5-openjdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /target/helpdesk-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8091
ENTRYPOINT [ "java", "-jar", "app.jar" ]
