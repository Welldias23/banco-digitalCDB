FROM eclipse-temurin:17
LABEL maintainer="Wellington Dias<wellingtond712@gmail.com>"
WORKDIR /app
COPY target/banco-digital-CDBW-0.0.1-SNAPSHOT.jar /app/banco-digital-CDB.jar
ENTRYPOINT ["java", "-jar", "banco-digital-CDB.jar"]