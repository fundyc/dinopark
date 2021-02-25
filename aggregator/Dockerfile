FROM adoptopenjdk/openjdk11:jdk-11.0.10_9-alpine-slim

# The port that your application listens to.
EXPOSE 8080

WORKDIR /app

# Prefer not to run as root.
RUN addgroup -S java && adduser -S java -G java
USER java

COPY target/*.jar ./app.jar

CMD ["java","-jar","app.jar"]