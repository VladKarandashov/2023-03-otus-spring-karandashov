FROM bellsoft/liberica-openjdk-alpine-musl:17.0.7-7
COPY /target/hw16.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]