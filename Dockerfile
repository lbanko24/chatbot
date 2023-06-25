FROM openjdk:20
COPY bots/ bots/
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar" ]