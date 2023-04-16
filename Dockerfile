FROM openjdk:17
EXPOSE 8080
ADD /target/backend_template-0.1.0.jar /app/backend_template-0.1.0.jar
ENTRYPOINT ["java", "-jar", "/app/backend_template-0.1.0.jar"]