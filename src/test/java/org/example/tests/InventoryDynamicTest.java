package org.example.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.example.tests.models.User;
import org.example.tests.models.UserFactory;
import org.example.tests.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryDynamicTest extends BaseTest {

    @TestFactory
    @DisplayName("Динамические тесты: Проверка карточек товаров")
    Stream<DynamicTest> checkAllProductCards() {
        User user = UserFactory.standardUser();

        LoginPage.open()
                .loginAs(user)
                .shouldBeLoggedIn()
                .isLoaded();

        Selenide.$$(".inventory_item_name").first().shouldBe(Condition.visible, Duration.ofSeconds(10));
        List<String> productNames = Selenide.$$(".inventory_item_name").texts();

        return productNames.stream()
                .map(productName -> DynamicTest.dynamicTest(
                        "Проверка товара: " + productName,
                        () -> {
                            assertTrue(Selenide.$$(".inventory_item_name")
                                    .findBy(Condition.text(productName))
                                    .isDisplayed(), "Товар не отображается: " + productName);
                        }
                ));
    }
}