package org.example.tests.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$;

public class ProductCard {
    private final String productName;

    public ProductCard(String productName) {
        this.productName = productName;
    }

    private SelenideElement root() {
        return $$(".inventory_item")
                .findBy(Condition.text(productName))
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return root().$(".inventory_item_name").getText();
    }

    public void addToCart() {
        root().$("button[data-test^='add-to-cart']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        root().$("button[data-test^='remove']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void removeFromCart() {
        root().$("button[data-test^='remove']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        root().$("button[data-test^='add-to-cart']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}