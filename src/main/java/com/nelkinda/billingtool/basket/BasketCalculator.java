package com.nelkinda.billingtool.basket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasketCalculator {

    private final Map<String, BasketItem> basketProductMap = new ConcurrentHashMap<>();


    BigDecimal calculateTotal(final List<BasketItem> basket) {
        BigDecimal total = BigDecimal.ZERO;
        for (final BasketItem basketItem: basket) {
            basketProductMap.put(basketItem.getProductName(), basketItem);
            total = total
                    .add(basketItem.getCost()
                            .multiply(BigDecimal.valueOf(basketItem.getQuantity())));
        }
        total = soupDiscount(total);
        if (basketProductMap.containsKey("apples")) {
            final double discount = 0.01;
            final int totalApples = basketProductMap.get("apples").getQuantity();
            final double amountToReduce = totalApples * discount;
            total = total.subtract(BigDecimal.valueOf(amountToReduce));
        }
        return total;
    }

    private BigDecimal soupDiscount(final BigDecimal total) {
        BigDecimal discountedPrice = total;
        if (basketProductMap.containsKey("soup")
                && basketProductMap.containsKey("bread")
                && basketProductMap.get("soup").getQuantity() == 2
        ) {
            final BigDecimal costOfBread = basketProductMap.get("bread").getCost();
            final BigDecimal amountToReduce = costOfBread.divide(new BigDecimal(2));
            discountedPrice = discountedPrice.subtract(amountToReduce);
        }
        return discountedPrice;
    }
}
