# Step 1: Use a stable JDK 17 base image
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# Step 2: Copy Maven configuration first (for better caching)
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw

# Step 3: Copy the source code (now that it's back in src/)
COPY src ./src

# Optional: Debug step to verify the structure in logs if it fails
RUN ls -R src

# Step 4: Build the application
# We use the clean flag to ensure no old artifacts interfere
RUN ./mvnw clean package -DskipTests

# Step 5: Final Production Image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the built JAR from the build stage
# Using a wildcard to match whatever name is in your pom.xml
COPY --from=build /app/target/*.jar app.jar

# Step 6: Expose the port from application.properties
EXPOSE 8085

# Step 7: Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]