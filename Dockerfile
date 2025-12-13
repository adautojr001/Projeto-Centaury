# Stage 1: build
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copia arquivos Maven e código fonte
COPY mvnw pom.xml ./
COPY .mvn .mvn
COPY src ./src

# Torna o Maven wrapper executável
RUN chmod +x mvnw

# Compila o projeto e gera um jar executável com Spring Boot
RUN ./mvnw clean package -DskipTests

# Stage 2: runtime (menor)
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia apenas o jar executável
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

# Expõe porta conforme fly.toml
EXPOSE 8080

# Inicializa o Spring Boot
ENTRYPOINT ["java","-jar","app.jar"]

