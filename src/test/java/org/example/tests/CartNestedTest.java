package org.example.tests;

import org.example.tests.pages.InventoryPage;
import org.example.tests.pages.LoginPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Тесты корзины (Nested)")
public class CartNestedTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeEach
    void loginBeforeEach() {
        inventoryPage = LoginPage.open()
                .loginAs("standard_user", "secret_sauce")
                .shouldBeLoggedIn();
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
            assertAll(
                    () -> inventoryPage.addToCart("Sauce Labs Backpack"),
                    () -> inventoryPage.addToCart("Sauce Labs Bike Light"),
                    () -> inventoryPage.checkCartCount("2")
            );
        }
    }

    @Nested
    @DisplayName("Удаление товаров")
    class RemoveFromCartTests {
        @Test
        @DisplayName("Удаление товара из корзины")
        void removeItem() {
            inventoryPage.addToCart("Sauce Labs Backpack")
                    .removeFromCart("Sauce Labs Backpack")
                    .checkCartIsEmpty();
        }
    }
}