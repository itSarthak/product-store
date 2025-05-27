FROM ubuntu:latest

WORKDIR /app
COPY . /app
RUN mvn clean package 

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=BUILD /app/target/*.jar productApp.jar
EXPOSE 9876
ENTRYPOINT ["java", "-jar", "productApp.jar"]