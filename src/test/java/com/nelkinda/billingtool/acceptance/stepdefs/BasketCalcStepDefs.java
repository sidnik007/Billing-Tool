package com.nelkinda.billingtool.acceptance.stepdefs;

import com.nelkinda.billingtool.basket.BasketCalculator;
import com.nelkinda.billingtool.basket.BasketItem;
import com.nelkinda.billingtool.basket.BasketItemFactory;
import com.nelkinda.billingtool.discount.Discount;
import com.nelkinda.billingtool.stock.InMemoryStockItemLoader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasketCalcStepDefs {

    private final List<BasketItem> basket = new ArrayList<>();
    private BigDecimal actualTotal = BigDecimal.ZERO;

    private Clock clock;

    @Given("today's date is {string}")
    public void todaySDateIs(final String currentDate) {
        final Instant instant = LocalDate.parse(currentDate).atStartOfDay(ZoneId.of("UTC")).toInstant();
        clock = Clock.fixed(instant, ZoneId.of("UTC"));
    }

    @And("the basket contains following items")
    public void theBasketContainsFollowingItems(final DataTable dataTable) {
        final List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        final BasketItemFactory factory = new BasketItemFactory(new InMemoryStockItemLoader());
        for (final Map<String, String> columns : rows) {
            final BasketItem item = factory.createBasketItem(columns.get("product"), columns.get("quantity"));
            basket.add(item);
        }
    }

    @And("the items are eligible for discount from {string} to {string}")
    public void theItemsAreEligibleForDiscountFromTo(final String startDate, final String endDate) {
        for (final BasketItem basketItem : basket) {
            final Discount discount = basketItem.getDiscount();
            discount.setClock(clock);
            discount.setStartDate(LocalDate.parse(startDate));
            discount.setEndDate(LocalDate.parse(endDate));
        }
    }

    @When("the basket is calculated")
    public void theBasketIsCalculated() {
        final BasketCalculator calculator = new BasketCalculator();
        actualTotal = calculator.calculateTotal(basket);
    }

    @Then("the total is {string}")
    public void theTotalIs(final String expectedTotal) {
        Assertions.assertEquals(new BigDecimal(expectedTotal), actualTotal);
    }
}
