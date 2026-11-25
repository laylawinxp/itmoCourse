package org.example.tests;

import org.example.tests.models.User;
import org.example.tests.models.UserFactory;
import org.example.tests.pages.LoginPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("login")
@DisplayName("Тесты авторизации")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успешный логин")
    @Timeout(20)
    void userCanLoginSuccessfully() {
        User user = UserFactory.standardUser();
        LoginPage.open()
                .loginAs(user)
                .shouldBeLoggedIn();
    }

    @Test
    @DisplayName("Негативный логин: неверный пароль")
    void userCannotLoginWithWrongPassword() {
        LoginPage loginPage = LoginPage.open()
                .loginAs(UserFactory.standardUserWithWrongPass());

        assertAll(
                () -> loginPage.checkErrorMessage("Epic sadface: Username and password do not match any user in this service"),
                () -> assertTrue(loginPage.getCurrentUrl().contains("saucedemo.com"), "URL не должен меняться")
        );
    }

    @Test
    @Disabled("Тест отключен")
    @DisplayName("Логин через Google (не реализовано)")
    void userCanLoginWithGoogle() {
    }
}