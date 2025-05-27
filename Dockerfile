FROM ubuntu:latest

FROM maven:3.9.9-openjdk-21 AS BUILD
WORKDIR /app
COPY backend /app
RUN mvn clean package 

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=BUILD /app/target/*.jar productApp.jar
EXPOSE 9876
ENTRYPOINT ["java", "-jar", "productApp.jar"]