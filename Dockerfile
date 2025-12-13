# Use a lightweight OpenJDK 17 image
FROM eclipse-temurin:17-jdk-jammy AS build

# Set working directory
WORKDIR /app

# Copy Maven/Gradle wrapper and build files
COPY mvnw pom.xml ./
COPY .mvn .mvn
COPY src ./src

# Make Maven wrapper executable (if using Maven)
RUN chmod +x mvnw

# Build the Spring Boot app (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Use a smaller runtime image for the final container
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
