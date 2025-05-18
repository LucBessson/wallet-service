FROM openjdk:17-jdk
COPY target/wallet-1.0.0.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
