# ====== BUILD STAGE ======
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copier les fichiers Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copier le code source
COPY src ./src

# Construire le JAR
RUN ./mvnw clean package -DskipTests


# ====== RUNTIME STAGE ======
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copier le JAR depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Ton port interne → 8081
EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
