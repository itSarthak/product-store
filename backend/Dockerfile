# Use Maven 3.9.9 with OpenJDK 21 as the build image
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy all files and download dependencies (caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source files
COPY . .

# Package the application
RUN mvn clean package -DskipTests

# Use a lightweight JDK image to run the application
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the jar file from the build image
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (or whatever port your app runs on)
EXPOSE 9876

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
