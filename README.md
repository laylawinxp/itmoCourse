# UI-автотесты на Selenide

Проект содержит автоматизированные UI-тесты для проверки пользовательских сценариев.

В качестве тестового стенда используется `saucedemo.com`.

* Java 21
* Selenide 7.2.2
* JUnit 5
* Gradle

## Архитектура

Проект реализован с использованием паттерна **Page Object** и принципов инкапсуляции:
* Локаторы и действия со страницами скрыты внутри классов в пакете `pages`.
* Повторяющиеся блоки интерфейса вынесены в отдельные компоненты.

### Структура проекта

```text
src/test/java/org/example/tests/
├── BaseTest.java                  # Базовый класс (настройка Selenide, @BeforeAll, @AfterEach)
├── ScenariosTest.java             # Базовые E2E-сценарии
├── LoginTest.java                 # Тесты логина (базовые аннотации JUnit)
├── LoginParameterizedTest.java    # Параметризованные тесты логина
├── CartNestedTest.java            # Тесты корзины (@Nested)
├── InventoryDynamicTest.java      # Динамические тесты (@TestFactory)
├── components/
│   └── ProductCard.java           # Компонент карточки товара
└── pages/
    ├── LoginPage.java             # Page Object страницы логина
    ├── InventoryPage.java         # Page Object страницы товаров
    └── CartPage.java              # Page Object страницы корзины
```

## Реализованные требования JUnit 5

### Базовые аннотации и методы

- **`@Test`** — все тестовые методы.
- **`@BeforeAll`** — настройка `Configuration` Selenide перед запуском.
- **`@AfterEach`** — очистка куки и `LocalStorage` после каждого теста.
- **`@BeforeEach`** — предусловие (логин) перед тестами корзины.
- **`@Tag("login")`** — тегирование группы тестов (в `LoginTest`).
- **`@Disabled`** — отключение нереализованного теста (`userCanLoginWithGoogle`).
- **`@DisplayName`** — читаемые названия тестов и классов.
- **`@Timeout(10)`** — ограничение времени выполнения теста.
- **`assertAll()`** — группировка проверок (в `LoginTest` и `CartNestedTest`).

### Дополнительные аннотации

- **`@ParameterizedTest` + `@ValueSource` + `@CsvSource`** — запуск одного теста с разными наборами данных (`LoginParameterizedTest`).
- **`@Nested`** — логическая группировка тестов внутри одного класса (`CartNestedTest`).
- **`@TestFactory`** — генерация динамических тестов на основе данных со страницы (`InventoryDynamicTest`).
