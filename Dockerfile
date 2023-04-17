FROM openjdk:17
EXPOSE 8080
ADD /target/user_service-0.1.0.jar /backend_template-0.1.0.jar
cmd java -Djasypt.encryptor.password=$encryption_password -jar /backend_template-0.1.0.jar
