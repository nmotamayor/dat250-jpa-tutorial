Experiment Documentation: Dockerizing PostgreSQL and a Spring Boot Application

1. Using the PostgreSQL Docker Image

Step 1: Pull the PostgreSQL Docker Image
The first step was to pull the official PostgreSQL image from Docker Hub:
"docker pull postgres"

Step 2: Running PostgreSQL in a Docker Container
After pulling the image, I successfully ran the PostgreSQL container with environment variables specifying the user and password for the database:
"docker run -p 5432:5432 -e POSTGRES_USER=jpa_client -e POSTGRES_PASSWORD=secret -d --name my-postgres --rm postgres"

The container was confirmed to be running using:
"docker ps"

Step 3: Connecting to PostgreSQL
Using a SQL client (DBeaver/IntelliJ), I connected to PostgreSQL running inside the Docker container with the following credentials:

    Host: localhost
    Port: 5432
    Username: jpa_client
    Password: secret

Once connected, I created a new user and database for my JPA application.

2. Dockerizing a Spring Boot Application

Step 1: Writing the Dockerfile
I created a Dockerfile in the root of my Spring Boot project that follows a multi-stage build approach. The first stage builds the application using Gradle, and the second stage creates a lightweight image for running the Spring Boot application:
# Stage 1: Build the application
<br />FROM gradle:7.5.1-jdk17 AS build
<br />WORKDIR /app
<br />COPY . /app
<br />RUN gradle bootJar

# Stage 2: Create a slim image
<br />FROM eclipse-temurin:17-jdk
<br />WORKDIR /app
<br />COPY --from=build /app/build/libs/*.jar app.jar
<br />EXPOSE 8080
<br />ENTRYPOINT ["java", "-jar", "app.jar"]

Step 2: Building the Docker Image
I successfully built the Docker image for the Spring Boot application using the following command:
<br />"docker build -t pollapp ."

The image was built successfully after resolving some earlier issues related to the Java version mismatch, and the image was listed when I checked using:
"docker images"

Step 3: Running the Docker Container
When I attempted to run the container using the following command:
<br />"docker run -p 8080:8080 --rm pollapp"

I encountered the following error:
Connection to 127.0.0.1:5432 refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.


Troubleshooting and Issues
The error indicated that the Spring Boot application could not connect to the PostgreSQL database. Here are the steps I took to resolve the issue:

    PostgreSQL Hostname: Initially, I used 127.0.0.1 in my persistence.xml, but I realized that inside the Docker container, 127.0.0.1 refers to the container itself, not the host machine. To fix this, I updated the connection URL in persistence.xml to use host.docker.internal:
"<property name="hibernate.connection.url" value="jdbc:postgresql://host.docker.internal:5432/postgres> "


Network Configuration: If host.docker.internal didn’t work, I tried using Docker’s internal networking by referring to the PostgreSQL container name (e.g., my-postgres) in the connection URL:
<br /><property name="hibernate.connection.url" value="jdbc:postgresql://my-postgres:5432/postgres>

Database Connectivity: I also ensured that the PostgreSQL container was running properly by inspecting the logs:
<br />docker logs my-postgres

Despite these attempts, I encountered continued connectivity issues between the Spring Boot container and the PostgreSQL container. It seems the database connection is still being refused, likely due to a network configuration issue that I have yet to resolve. The next step is to ensure that both containers are properly networked, either through Docker’s internal networking or by exposing ports correctly.

Pending Issue
The remaining issue to resolve is establishing a proper connection between the Spring Boot application and the PostgreSQL container. While the application container runs successfully, it cannot connect to the database, resulting in the error related to connection refusal on 127.0.0.1:5432.


Conclusion
The experiment was partially successful. I was able to:

    Pull and run a PostgreSQL container.
    Connect to PostgreSQL using a SQL client.
    Build a Docker image for a Spring Boot application.
    Run the Spring Boot container, though with a persistent database connectivity issue.

I will continue to investigate networking configurations between containers to resolve this issue and establish a working database connection for the Spring Boot application.