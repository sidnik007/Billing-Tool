package com.nelkinda.billingtool.stock;

import com.nelkinda.billingtool.discount.Discount;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
public class StockItem {

    private final String productName;
    private final String unit;
    private final BigDecimal cost;
    @EqualsAndHashCode.Exclude
    private final Discount discount;

    StockItem(final String productName,
              final String unit,
              final String cost,
              final Discount discount) {
        this.productName = productName;
        this.unit = unit;
        this.cost = new BigDecimal(cost);
        this.discount = discount;
    }
}
