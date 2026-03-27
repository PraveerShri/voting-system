FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
