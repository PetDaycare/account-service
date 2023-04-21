### About 

This is the backend service for accessing users and their login sessions.


### Deoloyment

Here is an example on how to deploy this project as a docker container using docker compose.
Fill in the environment variables with your values. You will get these from your Cognito user pool.

On the ports side the first port parameter is the port you want to acces the service on your machine. The second port number is the one that is specified inside the java code.

example:

5123:8080

host:service

You would be calling the service on: "ipadress:5123/users"

````
version: "3"
services:
  userservice:
    image: petdaycare/userservice:nightly
    container_name: dev_petdaycare_userservice
    restart: unless-stopped
    environment:
      - PUID=1000
      - PGID=1000
      - TZ=Europe/Vienna
      - accesskey=
      - clientId=
      - region=
      - secretkey=
      - userpoolid=
    ports:
      - 5123:8080/tcp
      - 5123:8080/udp
````
