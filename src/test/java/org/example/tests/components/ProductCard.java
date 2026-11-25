package org.example.tests.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductCard {
    private final String productName;

    public ProductCard(String productName) {
        this.productName = productName;
    }

    private SelenideElement cardRoot() {
        return $x(".//div[@data-test='inventory-item']" +
                "[.//div[@data-test='inventory-item-name' and normalize-space()='" + productName + "']]")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return cardRoot().$x(".//div[@data-test='inventory-item-name']").getText();
    }

    public void addToCart() {
        cardRoot().$x(".//button[starts-with(@data-test,'add-to-cart')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        cardRoot().$x(".//button[starts-with(@data-test,'remove')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void removeFromCart() {
        cardRoot().$x(".//button[starts-with(@data-test,'remove')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        cardRoot().$x(".//button[starts-with(@data-test,'add-to-cart')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}