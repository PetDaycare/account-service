FROM openjdk:17
EXPOSE 8080
COPY target/account_service-0.1.0.jar /app/myproject.jar
CMD ["java", "-jar", "/app/myproject.jar"]

