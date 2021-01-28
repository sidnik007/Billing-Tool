package com.nelkinda.billingtool.discount;

import com.nelkinda.billingtool.basket.BasketItem;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

public class AppleDiscount extends Discount {

    /**
     * Creates an AppleDiscount object with dates injected from properties file.
     */
    public AppleDiscount() {
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
        if (isEligibleForDiscount(basketProductMap, currentDate)) {
            final double discount = 0.01;
            final int totalApples = basketProductMap.get("apples").getQuantity();
            final double amountToReduce = totalApples * discount;
            discountedPrice = discountedPrice.subtract(BigDecimal.valueOf(amountToReduce));
        }
        return discountedPrice;
    }

    private boolean isEligibleForDiscount(final Map<String, BasketItem> basketProductMap, final LocalDate currentDate) {
        return basketProductMap.containsKey("apples")
                && currentDate.isAfter(startDate)
                && currentDate.isBefore(endDate);
    }
}
