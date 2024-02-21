FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the jar file into the container
COPY target/*.jar /app/app.jar

EXPOSE 8000

# Specify the command to run on container start
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
