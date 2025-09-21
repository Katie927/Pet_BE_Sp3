# ---------- build stage ----------
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /workspace

# copy mvnw & .mvn to keep wrapper behaviour and preserve exec bit
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# copy pom and sources (copy only what's cần để cache tốt hơn)
COPY pom.xml .
COPY src ./src

# build (skip tests in CI/dev; remove -DskipTests for full build)
RUN ./mvnw -B clean package -DskipTests

# ---------- runtime stage ----------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy fat jar from builder (một file .jar trong target/)
COPY --from=builder /workspace/target/*.jar app.jar

# non-root user (an toàn hơn)
RUN addgroup --system spring && adduser --system --ingroup spring spring
RUN chown spring:spring /app/app.jar
USER spring

# use the PORT env provided by Render if present; fallback 8080
ENV JAVA_OPTS="-Xms256m -Xmx512m"
EXPOSE 8080

# ENTRYPOINT uses shell to allow ${PORT} expansion at runtime
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
