FROM maven:3.8.4-openjdk-17-slim

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean test install

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]
