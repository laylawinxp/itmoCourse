package org.example.tests;

import org.example.tests.pages.LoginPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("login")
@DisplayName("Тесты авторизации")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успешный логин")
    @Timeout(10)
    void userCanLoginSuccessfully() {
        LoginPage.open()
                .loginAs("standard_user", "secret_sauce")
                .shouldBeLoggedIn();
    }

    @Test
    @DisplayName("Негативный логин: неверный пароль")
    void userCannotLoginWithWrongPassword() {
        LoginPage loginPage = LoginPage.open()
                .loginAs("standard_user", "wrong_password");

        assertAll(
                () -> loginPage.checkErrorMessage("Username and password do not match"),
                () -> assertTrue(loginPage.getCurrentUrl().contains("saucedemo.com"), "URL не должен меняться")
        );
    }

    @Test
    @Disabled("Тест отключен")
    @DisplayName("Логин через Google (не реализовано)")
    void userCanLoginWithGoogle() {
    }
}