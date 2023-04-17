FROM openjdk:17
EXPOSE 8080
COPY target/backend_template-0.1.0.jar /app/myproject.jar
CMD ["java", "-Djasypt.encryptor.password=$encryption_password","-jar", "/app/myproject.jar"]

