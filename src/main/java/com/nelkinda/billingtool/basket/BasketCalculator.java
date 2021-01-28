package com.nelkinda.billingtool.basket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasketCalculator {

    private final Map<String, BasketItem> productBasket = new ConcurrentHashMap<>();

    /**
     * Calculates total of items presented in the List parameter.
     * @param basket - list of BasketItems
     * @return - total of the items in the List along with the discount
     */
    public BigDecimal calculateTotal(final List<BasketItem> basket) {
        BigDecimal total = BigDecimal.ZERO;
        for (final BasketItem basketItem: basket) {
            productBasket.put(basketItem.getProductName(), basketItem);
            total = total
                    .add(basketItem.getCost()
                            .multiply(BigDecimal.valueOf(basketItem.getQuantity())));
        }
        for (final Map.Entry<String, BasketItem> entry: productBasket.entrySet()) {
            total = productBasket
                    .get(entry.getKey())
                    .getDiscount()
                    .calculateDiscount(productBasket, total);
        }
        return total;
    }
}
