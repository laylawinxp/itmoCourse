package org.example.tests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.tests.components.ProductCard;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage {
    private final ElementsCollection cards = $$(".inventory_item");
    private final SelenideElement cartBadge = $(".shopping_cart_badge");
    private final SelenideElement cartLink = $(".shopping_cart_link");

    public InventoryPage addToCart(String productName) {
        cards.first().shouldBe(Condition.visible, Duration.ofSeconds(10));
        new ProductCard(cards.findBy(Condition.text(productName)))
                .addToCart();
        return this;
    }

    public InventoryPage removeFromCart(String productName) {
        cards.first().shouldBe(Condition.visible, Duration.ofSeconds(10));
        new ProductCard(cards.findBy(Condition.text(productName)))
                .removeFromCart();
        return this;
    }

    public InventoryPage checkCartCount(String expectedCount) {
        cartBadge.shouldHave(Condition.text(expectedCount), Duration.ofSeconds(10));
        return this;
    }

    public InventoryPage checkCartIsEmpty() {
        cartBadge.shouldNotBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }

    public CartPage openCart() {
        cartLink.click();
        return new CartPage();
    }
}