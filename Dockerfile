FROM openjdk:17

WORKDIR /usr/src/app

COPY target/exam-restaurant-service.jar /usr/src/app/exam-restaurant-service.jar

RUN ls -R /usr/src/app

CMD ["java", "-jar", "exam-restaurant-service.jar"]
