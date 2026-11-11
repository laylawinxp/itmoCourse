package org.example.tests.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class ProductCard {
    private final SelenideElement root;
    private final SelenideElement titleElement;
    private final SelenideElement addToCartButton;
    private final SelenideElement removeButton;

    public ProductCard(SelenideElement root) {
        this.root = root;
        this.titleElement = root.$(".inventory_item_name");
        this.addToCartButton = root.$("button[data-test^='add-to-cart']");
        this.removeButton = root.$("button[data-test^='remove']");
    }

    public String getTitle() {
        return titleElement.getText();
    }

    public void addToCart() {
        addToCartButton.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();
    }

    public void removeFromCart() {
        removeButton.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();
    }
}