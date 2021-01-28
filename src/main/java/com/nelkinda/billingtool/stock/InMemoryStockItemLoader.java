package com.nelkinda.billingtool.stock;

import com.nelkinda.billingtool.discount.AppleDiscount;
import com.nelkinda.billingtool.discount.NoDiscount;
import com.nelkinda.billingtool.discount.SoupDiscount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryStockItemLoader implements StockItemLoader {
    @Override
    public List<StockItem> loadStockItems()  {
        return new ArrayList<>(Arrays.asList(
                new StockItem("soup", "tin", "0.65", new SoupDiscount()),
                new StockItem("bread", "loaf", "0.80", new NoDiscount()),
                new StockItem("milk", "bottle", "1.30", new NoDiscount()),
                new StockItem("apples", "single", "0.10", new AppleDiscount())
        ));
    }
}
