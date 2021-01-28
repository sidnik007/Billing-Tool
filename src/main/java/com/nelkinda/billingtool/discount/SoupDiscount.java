package com.nelkinda.billingtool.discount;

import com.nelkinda.billingtool.basket.BasketItem;

import java.math.BigDecimal;
import java.util.Map;

public class SoupDiscount extends Discount {

    @Override
    public BigDecimal calculateDiscount(final Map<String, BasketItem> basketProductMap,
                                        final BigDecimal total) {
        BigDecimal discountedPrice = total;
        if (basketProductMap.containsKey("soup")
                && basketProductMap.containsKey("bread")
                && basketProductMap.get("soup").getQuantity() >= 2
        ) {
            final BigDecimal costOfBread = basketProductMap.get("bread").getCost();
            final BigDecimal amountToReduce = costOfBread.divide(new BigDecimal(2));
            discountedPrice = discountedPrice.subtract(amountToReduce);
        }
        return discountedPrice;
    }
}
