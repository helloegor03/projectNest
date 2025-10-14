FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/gateway-0.0.1-SNAPSHOT.jar app.jar

RUN curl -L https://github.com/jwilder/dockerize/releases/download/v0.7.0/dockerize-linux-amd64-v0.7.0.tar.gz \
    | tar -C /usr/local/bin -xz

EXPOSE 8222

ENTRYPOINT ["dockerize", "-wait", "http://config-server:8888/gateway/default", "-timeout", "60s", "java", "-jar", "app.jar"]

