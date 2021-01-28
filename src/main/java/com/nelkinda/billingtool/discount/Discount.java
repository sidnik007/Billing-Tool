package com.nelkinda.billingtool.discount;

import com.nelkinda.billingtool.basket.BasketItem;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Map;

public abstract class Discount {

    protected Clock clock;
    protected LocalDate startDate;
    protected LocalDate endDate;

    public void setClock(final Clock clock) {
        this.clock = clock;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    public abstract BigDecimal calculateDiscount(
            final Map<String, BasketItem> basketProductMap,
            final BigDecimal total
    );
}
