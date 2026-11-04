package org.example.tests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.tests.components.ProductCard;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage {
    private final ElementsCollection cards = $$(".inventory_item");
    private final SelenideElement cartBadge = $(".shopping_cart_badge");
    private final SelenideElement cartLink = $(".shopping_cart_link");

    public InventoryPage addToCart(String productName) {
        new ProductCard(cards.findBy(com.codeborne.selenide.Condition.text(productName)))
                .addToCart();
        return this;
    }

    public InventoryPage checkCartCount(String expectedCount) {
        cartBadge.shouldHave(com.codeborne.selenide.Condition.text(expectedCount));
        return this;
    }

    public CartPage openCart() {
        cartLink.click();
        return new CartPage();
    }
}