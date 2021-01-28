package com.nelkinda.billingtool.basket;

import com.nelkinda.billingtool.stock.InMemoryStockItemLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketItemFactoryTest {

    @Test
    @DisplayName("Given a product name and quantity returns a BasketItem object")
    void testBasketItemFactory() {
        final BasketItemFactory factory = new BasketItemFactory(new InMemoryStockItemLoader());
        final BasketItem actualBasketItem = factory.createBasketItem("soup", "2");
        final BasketItem expectedBasketItem = new BasketItem("soup", "2", BigDecimal.valueOf(0.65));
        assertEquals(expectedBasketItem, actualBasketItem);
    }

}
