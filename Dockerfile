# --- Etap 1: budowanie aplikacji ---
FROM eclipse-temurin:21-jdk AS budowanie
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# --- Etap 2: uruchamianie ---
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=budowanie /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]