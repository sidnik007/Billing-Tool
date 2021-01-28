package com.nelkinda.billingtool.basket;

import com.nelkinda.billingtool.discount.Discount;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
public class BasketItem {

    private final String productName;
    private final int quantity;
    private final BigDecimal cost;
    @EqualsAndHashCode.Exclude
    private final Discount discount;

    BasketItem(final String productName,
               final String quantity,
               final BigDecimal cost,
               final Discount discount) {
        this.productName = productName;
        this.quantity = Integer.parseInt(quantity);
        this.cost = cost;
        this.discount = discount;
    }
}
