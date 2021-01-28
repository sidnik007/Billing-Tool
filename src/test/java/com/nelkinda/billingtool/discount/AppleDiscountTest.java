package com.nelkinda.billingtool.discount;

import com.nelkinda.billingtool.basket.BasketItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.nelkinda.billingtool.TestConstants.APPLES;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppleDiscountTest {

    @Test
    @DisplayName("The items in the basket are eligible for discount and within the discount date")
    void testAppleDiscountWhenDiscountDateIsValid() {
        final Map<String, BasketItem> productBasketMap = new ConcurrentHashMap<>();
        productBasketMap.put(APPLES, new BasketItem(APPLES, "1", BigDecimal.valueOf(0.10), new AppleDiscount()));

        final Discount discount = new AppleDiscount();
        final Clock clock = Clock.fixed(Instant.parse("2021-01-22T10:15:30.00Z"), ZoneId.of("UTC"));
        discount.setClock(clock);
        discount.setStartDate(LocalDate.parse("2021-01-21"));
        discount.setEndDate(LocalDate.parse("2021-01-23"));

        final BigDecimal actualTotal = discount.calculateDiscount(productBasketMap, new BigDecimal("0.10"));
        assertEquals(new BigDecimal("0.09"), actualTotal);





    }

}
