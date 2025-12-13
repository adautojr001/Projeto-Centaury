# Stage 1: build
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copia apenas o código e pom.xml
COPY pom.xml ./
COPY src ./src

# Instala Maven e builda o projeto (skip tests para acelerar)
RUN apt-get update && apt-get install -y maven \
    && mvn clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia o jar gerado
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta configurada no fly.toml
EXPOSE 8080

# Inicializa o Spring Boot
ENTRYPOINT ["java","-jar","app.jar"]

