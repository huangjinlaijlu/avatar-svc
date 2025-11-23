# 第一阶段：如果需要在 Docker 内构建，使用 GHCR 的 Maven
FROM ghcr.io/graalvm/graalvm-ce:java8 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline -q
COPY src ./src
RUN mvn clean package -DskipTests -q

# 第二阶段：使用 GHCR 的 OpenJDK 运行环境
FROM ghcr.io/graalvm/graalvm-ce:java8
RUN groupadd -r appgroup && useradd -r -g appgroup appuser
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
RUN chown -R appuser:appgroup /app
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]