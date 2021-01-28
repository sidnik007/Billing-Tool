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
                new StockItem(1, "soup", "tin", "0.65", new SoupDiscount()),
                new StockItem(2, "bread", "loaf", "0.80", new NoDiscount()),
                new StockItem(3, "milk", "bottle", "1.30", new NoDiscount()),
                new StockItem(4, "apples", "single", "0.10", new AppleDiscount())
        ));
    }
}
