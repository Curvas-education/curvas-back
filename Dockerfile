FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/curvas-back-mvp-1.0.0.jar curvas-back-mvp-1.0.0.jar
EXPOSE 8089
CMD ["java", "-jar", "curvas-back-mvp-1.0.0.jar"]