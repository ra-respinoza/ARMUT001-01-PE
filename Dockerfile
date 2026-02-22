## Usamos imagen ligera de Java
#FROM eclipse-temurin:17-jdk-alpine
#
## Carpeta de trabajo
#WORKDIR /app
#
## Copiamos el jar generado
#COPY target/productList-0.0.1-SNAPSHOT.jar app.jar
#
## Exponemos el puerto
#EXPOSE 8080
#
## Ejecutamos la app
#ENTRYPOINT ["java","-jar","app.jar"]


# ---------- ETAPA 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# ---------- ETAPA 2: Runtime ----------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]