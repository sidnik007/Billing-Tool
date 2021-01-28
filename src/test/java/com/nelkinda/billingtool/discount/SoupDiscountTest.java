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

import static com.nelkinda.billingtool.TestConstants.BREAD;
import static com.nelkinda.billingtool.TestConstants.SOUP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SoupDiscountTest {

    private static final String TOTAL = "2.10";
    private Discount discount;

    private Map<String, BasketItem> productBasketMap;


    @BeforeEach
    void setup() {
        discount = new SoupDiscount();
        productBasketMap = new ConcurrentHashMap<>();
    }

    @Test
    @DisplayName("The items in the basket are eligible for discount and within discount date")
    void testSoupDiscountWhenDiscountDateIsValid() {
        productBasketMap = createProductBasketMap();
        setDates("2021-01-22T10:15:30.00Z", "2021-01-21", "2021-01-23");
        final BigDecimal actualTotal = discount.calculateDiscount(productBasketMap, new BigDecimal(TOTAL));
        assertEquals(new BigDecimal("1.70"), actualTotal);
    }

    @Test
    @DisplayName("The items in the basket are eligible for discount but current date is after discount end date")
    void testSoupDiscountWhenDiscountDateIsPassed() {
        productBasketMap = createProductBasketMap();
        setDates("2021-01-19T10:15:30.00Z", "2021-01-17", "2021-01-18");
        final BigDecimal actualTotal = discount.calculateDiscount(productBasketMap, new BigDecimal(TOTAL));
        assertEquals(new BigDecimal(TOTAL), actualTotal);
    }

    @Test
    @DisplayName("The items in the basket are eligible for discount but current date is before discount start date")
    void testSoupDiscountWhenDiscountDateIsYetToCome() {
        productBasketMap = createProductBasketMap();
        setDates("2021-01-16T10:15:30.00Z", "2021-01-17", "2021-01-18");
        final BigDecimal actualTotal = discount.calculateDiscount(productBasketMap, new BigDecimal(TOTAL));
        assertEquals(new BigDecimal(TOTAL), actualTotal);
    }

    @Test
    @DisplayName("The items in the basket are not eligible for discount but within discount date")
    void testSoupDiscountWhenBasketIsInvalid() {
        productBasketMap = new ConcurrentHashMap<>();
        productBasketMap.put(BREAD, new BasketItem(BREAD, "1", BigDecimal.valueOf(0.80), new NoDiscount()));
        setDates("2021-01-22T10:15:30.00Z", "2021-01-21", "2021-01-23");
        final BigDecimal actualTotal = discount.calculateDiscount(productBasketMap, new BigDecimal(TOTAL));
        assertEquals(new BigDecimal(TOTAL), actualTotal);
    }

    private Map<String, BasketItem> createProductBasketMap() {
        productBasketMap = new ConcurrentHashMap<>();
        productBasketMap.put(SOUP, new BasketItem(SOUP, "2", BigDecimal.valueOf(0.65), new SoupDiscount()));
        productBasketMap.put(BREAD, new BasketItem(BREAD, "1", BigDecimal.valueOf(0.80), new NoDiscount()));
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
