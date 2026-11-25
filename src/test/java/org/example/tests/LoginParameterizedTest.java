package org.example.tests;

import org.example.tests.models.User;
import org.example.tests.models.UserFactory;
import org.example.tests.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LoginParameterizedTest extends BaseTest {

    static Stream<User> validUsers() {
        return Stream.of(UserFactory.standardUser());
    }

    static Stream<User> invalidUsers() {
        return Stream.of(
                UserFactory.standardUserWithWrongPass(),
                UserFactory.lockedUserWithWrongPass(),
                UserFactory.errorUserWithWrongPass()
        );
    }

    @ParameterizedTest(name = "Логин пользователя: {0}")
    @MethodSource("validUsers")
    @DisplayName("Параметризованный тест: успешный вход")
    void validUsersCanLogin(User user) {
        LoginPage.open()
                .loginAs(user)
                .shouldBeLoggedIn()
                .isLoaded();
    }

    @ParameterizedTest(name = "Неверный пароль для юзера: {0}")
    @MethodSource("invalidUsers")
    @DisplayName("Параметризованный тест: негативные сценарии")
    void invalidCredentialsShouldShowError(User user) {
        LoginPage.open()
                .loginAs(user)
                .checkErrorMessage("Epic sadface:");
    }
}