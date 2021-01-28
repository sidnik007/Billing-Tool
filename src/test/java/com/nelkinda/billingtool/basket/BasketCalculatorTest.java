package com.nelkinda.billingtool.basket;

import com.nelkinda.billingtool.stock.InMemoryStockItemLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.nelkinda.billingtool.TestConstants.SOUP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketCalculatorTest {

    @Test
    @DisplayName("Empty basket returns total as zero")
    void testEmptyBasket() {
        final BasketCalculator calculator = new BasketCalculator();
        final BigDecimal total = calculator.calculateTotal(Collections.emptyList());
        assertEquals(BigDecimal.ZERO, total);
    }

    @Test
    @DisplayName("Basket with one non-discounted item returns the price of the item")
    void testSingleItemQuantityOne() {
        final BasketCalculator calculator = new BasketCalculator();
        final BasketItemFactory factory = new BasketItemFactory(new InMemoryStockItemLoader());
        final List<BasketItem> basket = new ArrayList<>(Collections.singletonList(
                factory.createBasketItem(SOUP, "1")
        ));
        final BigDecimal actualTotal = calculator.calculateTotal(basket);
        assertEquals(new BigDecimal("0.65"), actualTotal);
    }

    @Test
    @DisplayName("Basket with two non-discounted items returns the price of the item")
    void testSingleItemQuantityTwo() {
        final BasketCalculator calculator = new BasketCalculator();
        final BasketItemFactory factory = new BasketItemFactory(new InMemoryStockItemLoader());
        final List<BasketItem> basket = new ArrayList<>(Collections.singletonList(
                factory.createBasketItem(SOUP, "2")
        ));
        final BigDecimal actualTotal = calculator.calculateTotal(basket);
        assertEquals(new BigDecimal("1.30"), actualTotal);
    }
}
