package com.nelkinda.billingtool.basket;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasketItem {

    private final String productName;
    private final int quantity;
    private final BigDecimal cost;

    BasketItem(final String productName, final String quantity, final BigDecimal cost) {
        this.productName = productName;
        this.quantity = Integer.parseInt(quantity);
        this.cost = cost;
    }
}
