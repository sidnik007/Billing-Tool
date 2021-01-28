package com.nelkinda.billingtool.discount;

import com.nelkinda.billingtool.basket.BasketItem;
import org.junit.jupiter.api.AfterEach;
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

import static com.nelkinda.billingtool.TestConstants.APPLES;
import static com.nelkinda.billingtool.TestConstants.BREAD;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppleDiscountTest {

    private static final String ACTUAL_TOTAL = "0.10";
    private Discount discount;

    private Map<String, BasketItem> productBasketMap;

    @BeforeEach
    void setup() {
        discount = new AppleDiscount();
        productBasketMap = new ConcurrentHashMap<>();
    }

    @Test
    @DisplayName("The items in the basket are eligible for discount and within the discount date")
    void testAppleDiscountWhenDiscountDateIsValid() {
        productBasketMap = createProductBasketMap();
        setDates("2021-01-22T10:15:30.00Z", "2021-01-21", "2021-01-23");
        final BigDecimal actualTotal = discount.calculateDiscount(productBasketMap, new BigDecimal(ACTUAL_TOTAL));
        assertEquals(new BigDecimal("0.09"), actualTotal);
    }

    @Test
    @DisplayName("The items in the basket are eligible for discount but current date is after discount end date")
    void testAppleDiscountWhenDiscountDateIsPassed() {
        productBasketMap = createProductBasketMap();
        setDates("2021-01-19T10:15:30.00Z", "2021-01-17", "2021-01-18");
        final BigDecimal actualTotal = discount.calculateDiscount(productBasketMap, new BigDecimal(ACTUAL_TOTAL));
        assertEquals(new BigDecimal(ACTUAL_TOTAL), actualTotal);
    }

    @Test
    @DisplayName("The items in the basket are eligible for discount but current date is before discount start date")
    void testAppleDiscountWhenDiscountDateIsYetToCome() {
        productBasketMap = createProductBasketMap();
        setDates("2021-01-16T10:15:30.00Z", "2021-01-17", "2021-01-18");
        final BigDecimal actualTotal = discount.calculateDiscount(productBasketMap, new BigDecimal(ACTUAL_TOTAL));
        assertEquals(new BigDecimal(ACTUAL_TOTAL), actualTotal);
    }

    @Test
    @DisplayName("The items in the basket are not eligible for discount but within discount date")
    void testAppleDiscountWhenBasketIsInvalid() {
        productBasketMap = new ConcurrentHashMap<>();
        productBasketMap.put(BREAD, new BasketItem(BREAD, "1", BigDecimal.valueOf(0.80), new NoDiscount()));
        setDates("2021-01-22T10:15:30.00Z", "2021-01-21", "2021-01-23");
        final BigDecimal actualTotal = discount.calculateDiscount(productBasketMap, new BigDecimal(ACTUAL_TOTAL));
        assertEquals(new BigDecimal(ACTUAL_TOTAL), actualTotal);
    }

    private Map<String, BasketItem> createProductBasketMap() {
        productBasketMap = new ConcurrentHashMap<>();
        productBasketMap.put(APPLES, new BasketItem(APPLES, "1", BigDecimal.valueOf(0.10), new AppleDiscount()));
        return productBasketMap;
    }

    private void setDates(final String currentDate, final String startDate, final String endDate) {
        final Clock clock = Clock.fixed(Instant.parse(currentDate), ZoneId.of("UTC"));
        discount.setClock(clock);
        discount.setStartDate(LocalDate.parse(startDate));
        discount.setEndDate(LocalDate.parse(endDate));
    }

    @AfterEach
    void tearDown() {
        discount.setClock(Clock.fixed(Instant.now(), ZoneId.of("UTC")));
    }

}
