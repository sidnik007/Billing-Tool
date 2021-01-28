package com.nelkinda.billingtool.input;

import com.nelkinda.billingtool.stock.InMemoryStockItemLoader;
import com.nelkinda.billingtool.stock.StockItem;
import com.nelkinda.billingtool.stock.StockItemLoader;

import java.util.List;


public class InputHandler {

    String generateInput() {
        final StockItemLoader stockItemLoader = new InMemoryStockItemLoader();
        final List<StockItem> stockItems = stockItemLoader.loadStockItems();
        final StringBuilder input = new StringBuilder();
        input.append("Please select from the following items\n\n"
                + "|  **product** | **unit**   | **cost** |\n"
                + "| :---  | :---: | :---: |\n");

        for (final StockItem stockItem: stockItems) {
            final String column = " | ";
            input
                    .append(column)
                    .append(stockItem.getProductId())
                    .append(column)
                    .append(stockItem.getProductName())
                    .append(column)
                    .append(stockItem.getUnit())
                    .append(column)
                    .append(stockItem.getCost())
                    .append(column)
                    .append("\n");

        }
        return input.toString();
    }
}
