# Stage 1: build
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw pom.xml ./
COPY .mvn .mvn
COPY src ./src

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build Spring Boot app, skipping tests to speed up
RUN ./mvnw clean package -DskipTests

# Stage 2: runtime (smaller image)
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

# Expose port 8080 (matches fly.toml)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]



# Expõe porta 8080 (conforme fly.toml)
EXPOSE 8080

# Inicializa o Spring Boot
ENTRYPOINT ["java","-jar","app.jar"]


# Expõe porta conforme fly.toml
EXPOSE 8080

# Inicializa o Spring Boot
ENTRYPOINT ["java","-jar","app.jar"]

