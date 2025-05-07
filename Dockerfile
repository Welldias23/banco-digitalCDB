FROM openjdk:17

COPY target/banco-digitalCDB

ENTRYPOINT ["java", "-jar", "/app.jar"]