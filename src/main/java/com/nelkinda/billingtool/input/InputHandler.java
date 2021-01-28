package com.nelkinda.billingtool.input;

import com.nelkinda.billingtool.basket.BasketCalculator;
import com.nelkinda.billingtool.basket.BasketItem;
import com.nelkinda.billingtool.basket.BasketItemFactory;
import com.nelkinda.billingtool.stock.InMemoryStockItemLoader;
import com.nelkinda.billingtool.stock.StockItem;
import com.nelkinda.billingtool.stock.StockItemLoader;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SuppressWarnings("all")
public class InputHandler {

    private List<StockItem> stockItems;
    private final List<BasketItem> basket = new ArrayList<>();
    private final BasketItemFactory itemFactory = new BasketItemFactory(new InMemoryStockItemLoader());

    String generateInput() {
        final StockItemLoader stockItemLoader = new InMemoryStockItemLoader();
        stockItems = stockItemLoader.loadStockItems();
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

    void handleInput() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Henry's Grocery");
        final String textToBeDisplayed = generateInput();
        while (true) {
            System.out.println(textToBeDisplayed);


            System.out.println("Press the id of the product to select");
            System.out.println("Press c to calculate Basket");
            System.out.println("Press q to exit");

            final String productId = scanner.next();
            if (StringUtils.isNumeric(productId)) {
                System.out.println("Please select the quantity");
                final String quantity = scanner.next();
                for (final StockItem stockItem: stockItems) {
                    if (stockItem.getProductId() == Integer.parseInt(productId)) {
                        handleBasket(stockItem.getProductName(), quantity);
                    }
                }
            } else if ("c".equals(productId)) {
                calculateBasket();
                scanner.close();
                return;
            } else if ("q".equals(productId)) {
                scanner.close();
                System.exit(0);
            }

        }
    }

    private boolean calculateBasket() {
        final BasketCalculator calculator = new BasketCalculator();
        System.out.println("Calculating Basket...");
        System.out.println("******************");
        System.out.println("Your total is " + calculator.calculateTotal(basket));
        System.out.println("******************");
        System.out.println("Thank you for shopping with us!");
        return true;
    }

    private void handleBasket(final String product, final String quantity) {
        System.out.println("Added " + product + " with quantity " + quantity + " to the basket");
        basket.add(itemFactory.createBasketItem(product, quantity));
    }
}
