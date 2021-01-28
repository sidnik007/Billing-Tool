package com.nelkinda.billingtool.discount;

import com.nelkinda.billingtool.basket.BasketItem;

import java.math.BigDecimal;
import java.util.Map;

public class NoDiscount extends Discount {

    @Override
    public BigDecimal calculateDiscount(final Map<String, BasketItem> basketProductMap,
                                        final BigDecimal total) {
        return total;
    }
}
