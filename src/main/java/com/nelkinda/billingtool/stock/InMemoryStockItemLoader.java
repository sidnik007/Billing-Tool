package com.nelkinda.billingtool.stock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryStockItemLoader implements StockItemLoader {
    @Override
    public List<StockItem> loadStockItems()  {
        return new ArrayList<>(Arrays.asList(
                new StockItem("soup", "tin", "0.65"),
                new StockItem("bread", "loaf", "0.80"),
                new StockItem("milk", "bottle", "1.30"),
                new StockItem("apples", "single", "0.10")
        ));
    }
}
