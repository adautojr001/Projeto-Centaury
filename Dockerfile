# Use Java 17
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia o jar do Spring Boot
COPY target/app.jar ./app.jar

# Copia o script wait-for-it
COPY wait-for-it.sh ./
RUN chmod +x wait-for-it.sh

# Comando padrão
CMD ["java", "-jar", "app.jar"]
