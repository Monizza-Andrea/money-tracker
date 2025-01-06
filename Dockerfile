FROM maven:3.9.5-eclipse-temurin-17 as build

WORKDIR /money-tracker

COPY pom.xml /money-tracker
COPY src /money-tracker/src
RUN mvn dependency:resolve

COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /money-tracker

COPY --from=build /money-tracker/target/money-tracker-0.0.2-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
