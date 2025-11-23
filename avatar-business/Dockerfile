FROM ghcr.io/linuxserver/nginx:latest

# 安装 OpenJDK 8
RUN apk add --no-cache openjdk8-jre

WORKDIR /app

# 具体指定 JAR 文件名
COPY target/avatar-business-1.0-SNAPSHOT.jar app.jar

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
RUN chown -R appuser:appgroup /app
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]