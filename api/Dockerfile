FROM openjdk:8-alpine

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar", "/app.jar"]

CMD [ "/bin/bash", "/.docker/entrypoint.sh"]