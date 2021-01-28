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

    /**
     * Creates a Basket Item.
     * @param productName - the product name to be added to the basket
     * @param quantity - quantity of the product
     * @param cost - cost of single unit of the product
     * @param discount - discount applicable
     */
    public BasketItem(final String productName,
                      final String quantity,
                      final BigDecimal cost,
                      final Discount discount) {
        this.productName = productName;
        this.quantity = Integer.parseInt(quantity);
        this.cost = cost;
        this.discount = discount;
    }
}
