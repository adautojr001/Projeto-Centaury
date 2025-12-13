# Stage 1: build
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copia Maven wrapper e arquivos do projeto
COPY mvnw pom.xml ./
COPY .mvn .mvn
COPY src ./src

# Torna o Maven wrapper executável
RUN chmod +x mvnw

# Build do Spring Boot, pulando testes
RUN ./mvnw clean package -DskipTests

# Stage 2: runtime (menor)
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia o jar gerado
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

# Expõe porta 8080
EXPOSE 8080

# Inicializa o Spring Boot
ENTRYPOINT ["java","-jar","app.jar"]
