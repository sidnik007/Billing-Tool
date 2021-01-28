package com.nelkinda.billingtool.discount;

import com.nelkinda.billingtool.basket.BasketItem;

import java.math.BigDecimal;
import java.util.Map;

public class AppleDiscount extends Discount {
    @Override
    public BigDecimal calculateDiscount(final Map<String, BasketItem> basketProductMap,
                                        final BigDecimal total) {
        BigDecimal discountedPrice = total;
        if (basketProductMap.containsKey("apples")) {
            final double discount = 0.01;
            final int totalApples = basketProductMap.get("apples").getQuantity();
            final double amountToReduce = totalApples * discount;
            discountedPrice = discountedPrice.subtract(BigDecimal.valueOf(amountToReduce));
        }
        return discountedPrice;
    }
}
