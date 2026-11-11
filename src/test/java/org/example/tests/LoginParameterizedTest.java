package org.example.tests;

import org.example.tests.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LoginParameterizedTest extends BaseTest {

    @ParameterizedTest(name = "Логин пользователя: {0}")
    @ValueSource(strings = {"standard_user"})
    @DisplayName("Параметризованный тест: успешный вход")
    void validUsersCanLogin(String username) {
        LoginPage.open()
                .loginAs(username, "secret_sauce")
                .shouldBeLoggedIn();
    }

    @ParameterizedTest(name = "Неверный пароль {1} для юзера {0}")
    @CsvSource({
            "standard_user, wrong_pass",
            "locked_out_user, secretsauce",
            "error_user, secret_sauc"
    })
    @DisplayName("Параметризованный тест: негативные сценарии")
    void invalidCredentialsShouldShowError(String username, String password) {
        LoginPage.open()
                .loginAs(username, password)
                .checkErrorMessage("Epic sadface:");
    }
}