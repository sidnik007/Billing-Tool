package com.nelkinda.billingtool.basket;

import java.math.BigDecimal;
import java.util.List;

public class BasketCalculator {

    BigDecimal calculateTotal(final List<BasketItem> basket) {
        BigDecimal total = BigDecimal.ZERO;
        for (final BasketItem basketItem: basket) {
            total = total
                    .add(basketItem.getCost()
                            .multiply(BigDecimal.valueOf(basketItem.getQuantity())));
        }
        return total;
    }
}
