# Stage 1: Build the application
FROM gradle:7.5.1-jdk17 AS build
WORKDIR /app
COPY . /app
RUN gradle bootJar

# Stage 2: Create a slim image
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
