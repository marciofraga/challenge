FROM maven:3.5.2-jdk-8-alpine as MAVEN_BUILD

COPY pom.xml /tmp
COPY src /tmp/src/

WORKDIR /tmp/
RUN mvn package -DskipTests

FROM openjdk:8-jre-alpine 

WORKDIR /app
COPY --from=MAVEN_BUILD /tmp/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]