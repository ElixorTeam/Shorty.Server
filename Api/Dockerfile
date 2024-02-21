# Используем официальный образ с Java и Gradle
FROM gradle:latest as builder

# Копируем файлы проекта в образ
COPY --chown=gradle:gradle . /home/gradle/src

# Устанавливаем рабочую директорию
WORKDIR /home/gradle/src

# Собираем проект с помощью Gradle
RUN gradle bootJar --no-daemon

# Создаем минимальный образ с Java
FROM openjdk:17

# Копируем JAR-файл из предыдущего этапа сборки
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/app.jar

# Задаем порт, который будет прослушиваться во время выполнения контейнера
EXPOSE 2000

# Запускаем приложение при старте контейнера
CMD ["java", "-jar", "/app/app.jar", "--spring.profiles.active=dev"]
