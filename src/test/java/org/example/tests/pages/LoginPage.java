package org.example.tests.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameInput = $("[data-test=username]");
    private final SelenideElement passwordInput = $("[data-test=password]");
    private final SelenideElement loginButton = $("[data-test=login-button]");
    private final SelenideElement title = $("[data-test=title]");
    private final SelenideElement errorMessage = $("[data-test=error]");

    public static LoginPage open() {
        return Selenide.open("/", LoginPage.class);
    }

    public LoginPage loginAs(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
        return this;
    }

    public InventoryPage shouldBeLoggedIn() {
        title.shouldHave(com.codeborne.selenide.Condition.text("Products"));
        return new InventoryPage();
    }

    public void checkErrorMessage(String expectedText) {
        errorMessage.shouldHave(partialText(expectedText));
    }
}