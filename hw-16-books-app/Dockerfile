FROM openjdk:17-jre-slim
COPY /target/hw16.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]