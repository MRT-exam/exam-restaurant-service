FROM openjdk:17

WORKDIR /usr/src/app

COPY target/springboot-docker-compose.jar /usr/src/app/springboot-docker-compose.jar

RUN ls -R /usr/src/app

CMD ["java", "-jar", "springboot-docker-compose.jar"]
