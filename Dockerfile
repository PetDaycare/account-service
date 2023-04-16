FROM openjdk:17
EXPOSE 8080
COPY target/backend_template-0.1.0.jar /app/backend_template.jar
ENTRYPOINT ["java", "-jar", "/app/backend_template.jar"]