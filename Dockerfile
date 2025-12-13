# Stage 1: build
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copia arquivos do projeto
COPY pom.xml ./
COPY src ./src

# Build do Spring Boot (requere Maven dentro do container)
RUN apt-get update && apt-get install -y maven \
    && mvn clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia o jar gerado
COPY --from=build /app/target/*.jar app.jar

# Expõe porta 8080
EXPOSE 8080

# Inicializa o Spring Boot
ENTRYPOINT ["java","-jar","app.jar"]

