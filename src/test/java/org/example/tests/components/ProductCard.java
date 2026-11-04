package org.example.tests.components;

import com.codeborne.selenide.SelenideElement;

public class ProductCard {
    private final SelenideElement root;

    public ProductCard(SelenideElement root) {
        this.root = root;
    }

    public String getTitle() {
        return root.$(".inventory_item_name").getText();
    }

    public void addToCart() {
        root.$("button").click();
    }
}