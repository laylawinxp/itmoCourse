package org.example.tests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage implements Loadable<CartPage> {
    private final SelenideElement title    = $("[data-test='title']");
    private final SelenideElement cartList = $("[data-test='cart-list']");

    @Override
    public CartPage isLoaded() {
        title.shouldHave(Condition.text("Your Cart"), Duration.ofSeconds(10));
        cartList.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }

    public CartPage shouldContainProduct(String productName) {
        $x(".//div[@data-test='cart-list']" +
                "//div[@data-test='inventory-item-name' and normalize-space()='" + productName + "']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }
}