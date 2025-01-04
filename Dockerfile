FROM maven:3.9.5-eclipse-temurin-17 as build

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:resolve

COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/target/money-tracker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
