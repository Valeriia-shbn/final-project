# Пошаговая инструкция для запуска и тестирования проекта

## 1. Установка необходимых инструментов
- Установите **Docker** и **Docker Compose**.
- Установите **Java 17**.
- Установите **IntelliJ IDEA**.

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

## 4. Настройка для использования MySQL
1. Откройте файл `artifacts/application.properties`.
2. Закомментируйте строку с PostgreSQL:
   ```properties
   # spring.datasource.url=jdbc:postgresql://localhost:5432/app
   ```
3. Откройте файл `final-project/src/test/java/data/DataBaseHelper.java`.
4. Закомментируйте переменную `URL` для PostgreSQL:
   ```java
   // private static final String URL = "jdbc:postgresql://localhost:5432/app";
   ```

---

## 5. Запуск приложения
- Перейдите в директорию `final-project/artifacts`.
- Запустите приложение командой:
  ```bash
  java -jar .\aqa-shop.jar
  ```

---

## 6. Запуск тестов
- Выполните команду для очистки и запуска тестов:
  ```bash
  ./gradlew clean test
  ```

---

## 7. Генерация и просмотр отчета Allure
1. Сгенерируйте отчет:
   ```bash
   ./gradlew allureReport
   ```
2. Откройте отчет в браузере:
   ```bash
   ./gradlew allureServe
   ```

---

## 8. Настройка для использования PostgreSQL
1. Остановите приложение с помощью сочетания клавиш `Ctrl+C`.
2. В файле `final-project/src/test/java/data/DataBaseHelper.java`:
    - Закомментируйте строку с MySQL:
      ```java
      // private static final String URL = "jdbc:mysql://localhost:3306/app";
      ```
    - Раскомментируйте строку с PostgreSQL.
3. В файле `artifacts/application.properties`:
    - Закомментируйте строку с MySQL:
      ```properties
      # spring.datasource.url=jdbc:mysql://localhost:3306/app
      ```
    - Раскомментируйте строку с PostgreSQL.

---

## 9. Повторный запуск приложения и тестов
- Запустите приложение снова (см. шаг 5).
- Запустите тесты и сгенерируйте отчет (см. шаги 6 и 7).

---

