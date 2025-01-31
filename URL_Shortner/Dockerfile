# Use an official OpenJDK runtime as the base image
FROM openjdk:21


# Copy the JAR file into the container
COPY target/urlshorter.jar urlshorter.jar

# Expose the application's port (change this to your application's port)
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "/urlshorter.jar"]
