package org.example.tests;

import org.example.tests.models.User;
import org.example.tests.models.UserFactory;
import org.example.tests.pages.InventoryPage;
import org.example.tests.pages.LoginPage;
import org.junit.jupiter.api.*;

@DisplayName("Тесты корзины (Nested)")
public class CartNestedTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeEach
    void loginBeforeEach() {
        User user = UserFactory.standardUser();
        inventoryPage = LoginPage.open()
                .loginAs(user)
                .shouldBeLoggedIn()
                .isLoaded();
    }

    @Nested
    @DisplayName("Добавление товаров")
    class AddToCartTests {

        @Test
        @DisplayName("Добавление одного товара")
        void addSingleItem() {
            inventoryPage.addToCart("Sauce Labs Backpack")
                    .checkCartCount("1");
        }

        @Test
        @DisplayName("Добавление нескольких товаров")
        void addMultipleItems() {
            inventoryPage.addToCart("Sauce Labs Backpack")
                    .checkCartCount("1")
                    .addToCart("Sauce Labs Bike Light")
                    .checkCartCount("2");
        }
    }

    @Nested
    @DisplayName("Удаление товаров")
    class RemoveFromCartTests {
        @Test
        @DisplayName("Удаление товара из корзины")
        void removeItem() {
            inventoryPage.addToCart("Sauce Labs Backpack")
                    .checkCartCount("1")
                    .removeFromCart("Sauce Labs Backpack")
                    .checkCartIsEmpty();
        }
    }
}