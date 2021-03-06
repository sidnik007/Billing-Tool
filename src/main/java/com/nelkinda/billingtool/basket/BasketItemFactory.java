package com.nelkinda.billingtool.basket;

import com.nelkinda.billingtool.stock.StockItem;
import com.nelkinda.billingtool.stock.StockItemLoader;

import java.util.List;

public class BasketItemFactory {

    private final StockItemLoader stockItemLoader;

    public BasketItemFactory(final StockItemLoader stockItemLoader) {
        this.stockItemLoader = stockItemLoader;
    }

    /**
     * Creates a BasketItem object from product and quantity.
     * @param productName - the name of the product in the basket
     * @param quantity - the total number in the basket
     * @return - BasketItem object
     */
    public BasketItem createBasketItem(final String productName, final String quantity) {
        BasketItem basketItem = null;
        final List<StockItem> stockItems = stockItemLoader.loadStockItems();
        for (final StockItem stockItem : stockItems) {
            if (stockItem.getProductName().equals(productName)) {
                basketItem = createBasketItem(productName, quantity, stockItem);
            }
        }
        return basketItem;
    }

    private BasketItem createBasketItem(final String productName, final String quantity, final StockItem stockItem) {
        return new BasketItem(productName, quantity, stockItem.getCost(), stockItem.getDiscount());
    }
}
