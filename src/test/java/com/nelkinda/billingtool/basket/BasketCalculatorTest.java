package com.nelkinda.billingtool.basket;

import com.nelkinda.billingtool.discount.Discount;
import com.nelkinda.billingtool.stock.InMemoryStockItemLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.nelkinda.billingtool.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketCalculatorTest {

    private BasketCalculator calculator;

    private BasketItemFactory factory;

    @BeforeEach
    void setup() {
        calculator = new BasketCalculator();
        factory = new BasketItemFactory(new InMemoryStockItemLoader());

    }

    @Test
    @DisplayName("Empty basket returns total as zero")
    void testEmptyBasket() {
        final BigDecimal total = calculator.calculateTotal(Collections.emptyList());
        assertEquals(BigDecimal.ZERO, total);
    }

    @Test
    @DisplayName("Basket with 1 tin of soup returns the total as 0.65")
    void testBasketWithOneSoup() {
        final List<BasketItem> basket = new ArrayList<>(Collections.singletonList(
                factory.createBasketItem(SOUP, "1")
        ));
        assertTotal(basket, "0.65");
    }

    @Test
    @DisplayName("Basket with 2 tins of soup returns the total as 1.30")
    void testBasketWithTwoSoups() {
        final List<BasketItem> basket = new ArrayList<>(Collections.singletonList(
                factory.createBasketItem(SOUP, "2")
        ));
        assertTotal(basket, "1.30");
    }

    @Test
    @DisplayName("Basket with 1 tin of soup and 1 loaf of bread returns the total as 1.45")
    void testBasketWithOneSoupAndOneBread() {
        final List<BasketItem> basket = new ArrayList<>(Arrays.asList(
                factory.createBasketItem(SOUP, "1"),
                factory.createBasketItem(BREAD, "1")
        ));
        assertTotal(basket, "1.45");
    }

    @Test
    @DisplayName("Basket with 3 bottles of milk and 2 loaves of bread returns the total as 5.50")
    void testBasketWithThreeBottlesAndTwoBreads() {
        final List<BasketItem> basket = new ArrayList<>(Arrays.asList(
                factory.createBasketItem(MILK, "3"),
                factory.createBasketItem(BREAD, "2")
        ));
        assertTotal(basket, "5.50");
    }

    @Test
    @DisplayName("Basket with 2 tins of soups and 1 loaf bread returns the total as 1.70")
    void testBasketWithTwoSoupsAndOneBread() {
        final List<BasketItem> basket = new ArrayList<>();
        final BasketItem soup = factory.createBasketItem(SOUP, "2");
        final BasketItem bread = factory.createBasketItem(BREAD, "1");
        setValidDiscountDates("2021-01-22T10:15:30.00Z",
                "2021-01-21",
                "2021-01-23",
                soup.getDiscount());
        basket.add(soup);
        basket.add(bread);
        assertTotal(basket, "1.70");
    }

    @Test
    @DisplayName("Basket with 1 apple returns the total as 0.09")
    void testBasketWithOneApple() {
        final List<BasketItem> basket = new ArrayList<>();
        final BasketItem apples = factory.createBasketItem(APPLES, "1");
        setValidDiscountDates("2021-01-22T10:15:30.00Z",
                "2021-01-21",
                "2021-01-23",
                apples.getDiscount());
        basket.add(apples);
        assertTotal(basket, "0.09");
    }

    @Test
    @DisplayName("Basket with 3 tins of soup and 2 loaves of bread returns total as 3.15")
    void testBasketWithThreeSoupsAndTwoBreads() {
        final List<BasketItem> basket = new ArrayList<>();
        final BasketItem soup = factory.createBasketItem(SOUP, "3");
        final BasketItem bread = factory.createBasketItem(BREAD, "2");
        setValidDiscountDates("2021-01-22T10:15:30.00Z",
                "2021-01-21",
                "2021-01-23",
                soup.getDiscount());
        basket.add(soup);
        basket.add(bread);
        assertTotal(basket, "3.15");
    }

    @Test
    @DisplayName("Basket with 3 apples, 2 tins of soups and 1 loaf bread bought in 5 days time returns the total as 1.97")
    void testBasketWithThreeApplesTwoSoupsAndOneBreadBoughtInFiveDays() {
        final List<BasketItem> basket = new ArrayList<>();
        final BasketItem apples = factory.createBasketItem(APPLES, "3");
        final BasketItem soup = factory.createBasketItem(SOUP, "2");
        final BasketItem bread = factory.createBasketItem(BREAD, "1");
        setValidDiscountDates("2021-01-26T10:15:30.00Z",
                "2021-01-20",
                "2021-01-27",
                soup.getDiscount());
        setValidDiscountDates("2021-01-26T10:15:30.00Z",
                "2021-01-24",
                "2021-02-28",
                apples.getDiscount());
        basket.add(soup);
        basket.add(bread);
        basket.add(apples);
        assertTotal(basket, "1.97");
    }

    private void setValidDiscountDates(final String currentDate,
                                       final String startDate,
                                       final String endDate,
                                       final Discount discount) {
        final Clock clock = Clock.fixed(Instant.parse(currentDate), ZoneId.of("UTC"));
        discount.setClock(clock);
        discount.setStartDate(LocalDate.parse(startDate));
        discount.setEndDate(LocalDate.parse(endDate));
    }


    private void assertTotal(final List<BasketItem> basket, final String expectedTotal) {
        final BigDecimal actualTotal = calculator.calculateTotal(basket);
        assertEquals(new BigDecimal(expectedTotal), actualTotal);
    }
}
