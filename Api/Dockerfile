# STAGE 1 (BUILD JAR)
FROM gradle:7.5.1-jdk17 AS builder

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle bootJar --no-daemon

# STAGE 2 (RUN JAR)
FROM openjdk:17-slim AS production

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/app.jar

EXPOSE 2000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]