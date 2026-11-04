package org.example.tests.components;

import com.codeborne.selenide.SelenideElement;

public class ProductCard {
    private final SelenideElement root;

    private final SelenideElement titleElement;
    private final SelenideElement addToCartButton;

    public ProductCard(SelenideElement root) {
        this.root = root;
        this.titleElement = root.$(".inventory_item_name");
        this.addToCartButton = root.$("button");
    }

    public String getTitle() {
        return titleElement.getText();
    }

    public void addToCart() {
        addToCartButton.click();
    }
}