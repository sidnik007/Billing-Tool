package com.nelkinda.billingtool.stock;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockItem {

    private String productName;

    private String unit;

    private BigDecimal cost;

    StockItem(final String productName, final String unit, final String cost) {
        this.productName = productName;
        this.unit = unit;
        this.cost = new BigDecimal(cost);
    }
}
