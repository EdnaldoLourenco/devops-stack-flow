# Etapa 1: Build com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o projeto e instala as dependências
COPY pom.xml .
COPY . ./src

# Compila o projeto e gera o jar
RUN mvn clean package -DskipTests && \
    cp target/*.jar app.jar


# Etapa 2: Imagem leve com o JAR final
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o JAR da etapa anterior
COPY --from=build /app/app.jar app.jar

# Expõe a porta padrão
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
