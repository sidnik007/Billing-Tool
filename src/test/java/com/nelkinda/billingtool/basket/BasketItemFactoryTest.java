package com.nelkinda.billingtool.basket;

import com.nelkinda.billingtool.discount.SoupDiscount;
import com.nelkinda.billingtool.stock.InMemoryStockItemLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.nelkinda.billingtool.TestConstants.SOUP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketItemFactoryTest {

    @Test
    @DisplayName("Given a product name and quantity returns a BasketItem object")
    void testBasketItemFactory() {
        final BasketItemFactory factory = new BasketItemFactory(new InMemoryStockItemLoader());
        final BasketItem actualBasketItem = factory.createBasketItem(SOUP, "2");
        final BasketItem expectedBasketItem = new BasketItem(SOUP, "2", BigDecimal.valueOf(0.65), new SoupDiscount());
        assertEquals(expectedBasketItem, actualBasketItem);
    }

}
