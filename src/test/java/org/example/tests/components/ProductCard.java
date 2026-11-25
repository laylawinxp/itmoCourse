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

    private String slug() {
        return productName.toLowerCase()
                .replace(" ", "-")
                .replaceAll("[^a-z0-9-]", "");
    }

    public SelenideElement root() {
        return $x("//div[@class='inventory_item'][.//div[@class='inventory_item_name' and normalize-space()='"
                + productName + "']]")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return root().$(".inventory_item_name").getText();
    }

    public void addToCart() {
        $("button[data-test='add-to-cart-" + slug() + "']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        $("button[data-test='remove-" + slug() + "']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void removeFromCart() {
        $("button[data-test='remove-" + slug() + "']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        $("button[data-test='add-to-cart-" + slug() + "']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}