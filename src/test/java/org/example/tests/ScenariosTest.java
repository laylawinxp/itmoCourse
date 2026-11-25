package org.example.tests;

import org.example.tests.models.User;
import org.example.tests.models.UserFactory;
import org.example.tests.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScenariosTest extends BaseTest {

    @Test
    @DisplayName("Успешное создание записи (добавление товара)")
    void userCanPublishPost() {
        User user = UserFactory.standardUser();
        LoginPage.open()
                .loginAs(user)
                .shouldBeLoggedIn()
                .addToCart("Sauce Labs Backpack")
                .checkCartCount("1")
                .openCart()
                .shouldContainProduct("Sauce Labs Backpack");
    }

    @Test
    @DisplayName("Попытка авторизации с неверным паролем (Негатив)")
    void userCannotLoginWithWrongPassword() {
        LoginPage.open()
                .loginAs(UserFactory.standardUserWithWrongPass())
                .checkErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    @DisplayName("Проверка заблокированного пользователя")
    void lockedOutUserCannotLogin() {
        User user = UserFactory.lockedUser();
        LoginPage.open()
                .loginAs(user)
                .checkErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    @DisplayName("Добавление нескольких постов (товаров) в ленту (корзину)")
    void userCanAddMultiplePosts() {
        User user = UserFactory.standardUser();
        LoginPage.open()
                .loginAs(user)
                .shouldBeLoggedIn()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Sauce Labs Bike Light")
                .checkCartCount("2")
                .openCart()
                .shouldContainProduct("Sauce Labs Backpack")
                .shouldContainProduct("Sauce Labs Bike Light");
    }
}