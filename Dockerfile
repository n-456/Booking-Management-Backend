# =========================
# 1. Build stage
# =========================
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy Maven project
COPY pom.xml .
COPY src ./src

# Build Spring Boot application
RUN ./mvnw clean package -DskipTests 2>/dev/null || \
    apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests


# =========================
# 2. Runtime stage
# =========================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy generated JAR
COPY --from=build /app/target/*.jar app.jar

# Spring Boot default port
EXPOSE 8080

# Render provides PORT environment variable
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]