package com.nelkinda.billingtool;

import com.nelkinda.billingtool.basket.BasketItem;
import com.nelkinda.billingtool.discount.Discount;
import com.nelkinda.billingtool.discount.NoDiscount;
import com.nelkinda.billingtool.discount.SoupDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.nelkinda.billingtool.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SoupDiscountTest {

    private Discount discount;

    @BeforeEach
    void setup() {
        discount = new SoupDiscount();
    }

    @Test
    @DisplayName("The items in the basket are eligible for discount and within discount date")
    void discountDateIsValid() {
        final Map<String, BasketItem> productNameBasketItemMap =  new ConcurrentHashMap<>();
        productNameBasketItemMap.put(SOUP, new BasketItem(SOUP, "2", BigDecimal.valueOf(0.65), new SoupDiscount()));
        productNameBasketItemMap.put(BREAD, new BasketItem(BREAD, "1", BigDecimal.valueOf(0.80), new NoDiscount()));
        setDates("2021-01-22T10:15:30.00Z", "2021-01-21", "2021-01-23");
        final BigDecimal actualTotal = discount.calculateDiscount(productNameBasketItemMap, new BigDecimal("2.10"));
        assertEquals(new BigDecimal("1.70"), actualTotal);
    }

    @Test
    @DisplayName("The items in the basket are eligible for discount but not within discount date")
    void discountDateIsInValid() {
        final Map<String, BasketItem> productNameBasketItemMap =  new ConcurrentHashMap<>();
        productNameBasketItemMap.put(SOUP, new BasketItem(SOUP, "2", BigDecimal.valueOf(0.65), new SoupDiscount()));
        productNameBasketItemMap.put(BREAD, new BasketItem(BREAD, "1", BigDecimal.valueOf(0.80), new NoDiscount()));
        setDates("2021-01-19T10:15:30.00Z", "2021-01-17", "2021-01-18");
        final BigDecimal actualTotal = discount.calculateDiscount(productNameBasketItemMap, new BigDecimal("2.10"));
        assertEquals(new BigDecimal("2.10"), actualTotal);
    }

    private void setDates(final String currentDate, final String startDate, final String endDate) {
        final Clock clock = Clock.fixed(Instant.parse(currentDate), ZoneId.of("UTC"));
        discount.setClock(clock);
        discount.setStartDate(LocalDate.parse(startDate));
        discount.setEndDate(LocalDate.parse(endDate));
    }

}
