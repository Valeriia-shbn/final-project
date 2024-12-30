# Пошаговая инструкция для запуска и тестирования проекта

## 1. Установка необходимых инструментов
- Установите **Docker** и **Docker Compose**. [Документация Docker](https://docs.docker.com/get-docker/)
- Установите **Java 17**. [Загрузить JDK 17](https://adoptopenjdk.net/)
- Установите **IntelliJ IDEA**. [Загрузить IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

---

## 2. Сборка проекта с Gradle
- Выполните команду для сборки проекта:
  ```bash
  ./gradlew build
  ```
- Убедитесь, что сборка завершилась успешно.

---

## 3. Запуск контейнеров с помощью Docker Compose
- Перейдите в директорию с файлом `docker-compose.yml`.
- Запустите команду:
  ```bash
  docker-compose up
  ```
- Убедитесь, что три контейнера успешно запущены.

---

## 4. Запуск приложения
- Перейдите в директорию `final-project/artifacts`.
- Запустите приложение командой c dbUrl jdbc:mysql://localhost:3306/app для MySQL и jdbc:postgresql://localhost:5432/app для Postgresql:
- java -jar .\aqa-shop.jar -Dspring.datasource.url={dbUrl}
- ```bash
  java -jar .\aqa-shop.jar -Dspring.datasource.url=jdbc:mysql://localhost:3306/app

  ```

  
- для смены БД необходимо остановить приложения с помощью команды ctrl+c и перезапустить с необходимой dbUrl.

---

## 5. Запуск тестов
- Выполните команду для очистки и запуска тестов с выбранной БД:
  ```bash
  ./gradlew test -D db.url=jdbc:mysql://localhost:3306/app
  ```

[//]: # (  ./gradlew test -D db.url=jdbc:mysql://localhost:3306/app)

---

## 6. Генерация и просмотр отчета Allure
1. Сгенерируйте отчет:
   ```bash
   ./gradlew allureReport
   ```
2. Откройте отчет в браузере:
   ```bash
   ./gradlew allureServe
   ```

---


