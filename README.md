# REST API Testing (homework-1)

Тестируемый API: [dummyjson.com](https://dummyjson.com) — публичный REST API

## Стек

| Технология | Версия | Назначение |
|---|---|---|
| Java | 21 | язык |
| Gradle (Kotlin DSL) | 8.x | сборка |
| JUnit 5 | 5.10.0 | тест-фреймворк |
| REST Assured | 5.4.0 | HTTP-клиент для тестов |
| Jackson | 2.22.3 | сериализация POJO ↔ JSON |
| JSON Schema Validator | 5.4.0 | валидация структуры ответа |
| Allure | 2.24.0 | отчёты и логирование запросов |
| Datafaker | 2.1.0 | генерация тестовых данных |
| SLF4J Simple | 2.0.9 | логи запросов и ответов |
| Hamcrest | 2.2 | матчеры для ассертов |

## Что реализовано

### Тесты для REST API

- Тесты на CRUD-операции и пагинацию
- Позитивные сценарии: получение, создание, удаление пользователя
- Негативные сценарии: несуществующий ID (404), невалидный ID (400/404)
- Граничные значения: пагинация
- Валидация ответа по JSON Schema (`user-schema.json`)
- Паттерн ApiClient (`UserClient`)
- Переиспользуемые спецификации (`RequestSpecification` / `ResponseSpecification`)
- Генерация данных через Datafaker
- Логирование запросов и ответов через SLF4J
- Allure-отчёт с attachments
