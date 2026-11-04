package org.example.tests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    private final ElementsCollection cartItems = $$(".cart_item");

    public CartPage shouldContainProduct(String productName) {
        cartItems.findBy(Condition.text(productName)).shouldBe(Condition.visible);
        return this;
    }
}