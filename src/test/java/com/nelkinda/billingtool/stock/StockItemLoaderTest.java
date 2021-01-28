package com.nelkinda.billingtool.stock;

import com.nelkinda.billingtool.discount.AppleDiscount;
import com.nelkinda.billingtool.discount.NoDiscount;
import com.nelkinda.billingtool.discount.SoupDiscount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nelkinda.billingtool.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StockItemLoaderTest {

    @Test
    @DisplayName("Stock loader returns the stock items")
    void testStockItemLoader() {
        final StockItemLoader stockItemLoader = new InMemoryStockItemLoader();
        final List<StockItem> actualStockItem = stockItemLoader.loadStockItems();
        final List<StockItem> expectedStockItem = new ArrayList<>(Arrays.asList(
                new StockItem(SOUP, "tin", "0.65", new SoupDiscount()),
                new StockItem(BREAD, "loaf", "0.80", new NoDiscount()),
                new StockItem(MILK, "bottle", "1.30", new NoDiscount()),
                new StockItem(APPLES, "single", "0.10", new AppleDiscount())
        ));
        assertEquals(expectedStockItem, actualStockItem);
    }
}
