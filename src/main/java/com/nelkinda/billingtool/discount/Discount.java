package com.nelkinda.billingtool.discount;

import com.nelkinda.billingtool.basket.BasketItem;

import java.math.BigDecimal;
import java.util.Map;

@SuppressWarnings("java:S1610")
public abstract class Discount {

    public abstract BigDecimal calculateDiscount(
            final Map<String, BasketItem> basketProductMap,
            final BigDecimal total
    );
}
