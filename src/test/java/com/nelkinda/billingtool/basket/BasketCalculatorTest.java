package com.nelkinda.billingtool.basket;

import com.nelkinda.billingtool.stock.InMemoryStockItemLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.nelkinda.billingtool.TestConstants.SOUP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketCalculatorTest {

    private BasketCalculator calculator;

    private BasketItemFactory factory;

    @BeforeEach
    void setup() {
        calculator = new BasketCalculator();
        factory = new BasketItemFactory(new InMemoryStockItemLoader());
    }

    @Test
    @DisplayName("Empty basket returns total as zero")
    void testEmptyBasket() {
        final BigDecimal total = calculator.calculateTotal(Collections.emptyList());
        assertEquals(BigDecimal.ZERO, total);
    }

    @Test
    @DisplayName("Basket with one non-discounted item returns the price of the item")
    void testSingleItemQuantityOne() {
        final List<BasketItem> basket = new ArrayList<>(Collections.singletonList(
                factory.createBasketItem(SOUP, "1")
        ));
        assertTotal(basket, "0.65");
    }

    @Test
    @DisplayName("Basket with two counts of non-discounted item returns the total price")
    void testSingleItemQuantityTwo() {
        final List<BasketItem> basket = new ArrayList<>(Collections.singletonList(
                factory.createBasketItem(SOUP, "2")
        ));
        assertTotal(basket, "1.30");
    }

    private void assertTotal(final List<BasketItem> basket, final String expectedTotal) {
        final BigDecimal actualTotal = calculator.calculateTotal(basket);
        assertEquals(new BigDecimal(expectedTotal), actualTotal);
    }
}
