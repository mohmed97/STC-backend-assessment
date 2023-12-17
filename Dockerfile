# Specify the base image with Java 17
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/ManagingFiles-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application will listen on
EXPOSE 8080

# Run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]