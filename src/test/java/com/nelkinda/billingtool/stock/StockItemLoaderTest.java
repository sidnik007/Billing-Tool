package com.nelkinda.billingtool.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockItemLoaderTest {

    @Test
    @DisplayName("Stock loader returns the stock items")
    void testStockItemLoader() {
        final StockItemLoader stockItemLoader = new StockItemLoader();
        final List<StockItem> actualStockItem = stockItemLoader.loadStockItems();
        final List<StockItem> expectedStockItem = new ArrayList<>(Arrays.asList(
                new StockItem("soup", "tin", "0.65"),
                new StockItem("bread", "loaf", "0.80"),
                new StockItem("milk", "bottle", "1.30"),
                new StockItem("apples", "single", "0.10")
        ));
        assertEquals(expectedStockItem, actualStockItem);
    }
}
