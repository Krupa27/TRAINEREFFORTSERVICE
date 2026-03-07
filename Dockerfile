# Use the stable Eclipse Temurin JDK 17 on Ubuntu (Jammy)
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and project file first to cache dependencies
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Give execution rights and download dependencies (offline mode for speed)
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copy the actual source code
COPY src ./src

# Build the application and skip tests for faster deployment
RUN ./mvnw package -DskipTests

# Move the generated JAR to a standard name so the CMD always works
RUN cp target/*.jar app.jar

# Expose the port used in your application.properties
EXPOSE 8085

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]