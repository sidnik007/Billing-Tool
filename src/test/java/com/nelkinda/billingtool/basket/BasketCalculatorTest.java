package com.nelkinda.billingtool.basket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketCalculatorTest {

    @Test
    @DisplayName("Empty basket return total as zero")
    void testEmptyBasket() {
        final BasketCalculator calculator = new BasketCalculator();
        final BigDecimal total = calculator.calculateTotal(Collections.emptyList());
        assertEquals(BigDecimal.ZERO, total);
    }
}
