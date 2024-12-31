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
- Запустите приложение командой для использования MySQL:
- ```bash
  java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar

  ```
- или командой для использования Postgresql
- ```bash
  java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar

  ```
- для смены БД необходимо остановить приложения с помощью команды ctrl+c и перезапустить с необходимой Dspring.datasource.url.

---

## 5. Запуск тестов
- Выполните команду для очистки и запуска тестов с MySQL:
  ```bash
  ./gradlew "-Ddb.url=jdbc:mysql://localhost:3306/app" clean test
  ```
    -  Либо выполните команду для очистки и запуска тестов с Postgresql:
  ```bash
  ./gradlew "-Ddb.url=jdbc:postgresql://localhost:5432/app" clean test
  ```
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


