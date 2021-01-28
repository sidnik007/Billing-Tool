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
        for (final Map.Entry<String, BasketItem> entry: basketProductMap.entrySet()) {
            total = basketProductMap.get(entry.getKey()).getDiscount().calculateDiscount(basketProductMap, total);
        }
        return total;
    }
}
