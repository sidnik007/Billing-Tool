package com.nelkinda.billingtool.discount;

import com.nelkinda.billingtool.basket.BasketItem;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

public class SoupDiscount extends Discount {

    /**
     * Creates a SoupDiscount object with dates injected from properties file.
     */
    public SoupDiscount() {
        super();
        clock = Clock.fixed(Instant.now(), ZoneId.of("UTC"));
        // Hardcoding the dates for assignment purpose. Should be loaded from external resource
        startDate = LocalDate.parse("2021-01-20");
        endDate = LocalDate.parse("2021-01-31");
    }

    @Override
    public void setClock(final Clock clock) {
        this.clock = clock;
    }

    @Override
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public BigDecimal calculateDiscount(final Map<String, BasketItem> basketProductMap,
                                        final BigDecimal total) {
        BigDecimal discountedPrice = total;
        final LocalDate currentDate = LocalDate.now(clock);
        if (isEligibleForDiscount(basketProductMap, currentDate)
        ) {
            final BigDecimal costOfBread = basketProductMap.get("bread").getCost();
            final BigDecimal amountToReduce = costOfBread.divide(new BigDecimal(2));
            discountedPrice = discountedPrice.subtract(amountToReduce);
        }
        return discountedPrice;
    }

    private boolean isEligibleForDiscount(final Map<String, BasketItem> basketProductMap,
                                          final LocalDate currentDate) {
        return currentDate.isAfter(startDate)
                && currentDate.isBefore(endDate)
                && basketProductMap.containsKey("soup")
                && basketProductMap.containsKey("bread")
                && basketProductMap.get("soup").getQuantity() >= 2;
    }
}
