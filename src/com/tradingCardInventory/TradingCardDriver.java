package com.tradingCardInventory;

import com.tradingCardInventory.controllers.MenuController;

/**
 * Entry point of the Trading Card Inventory System.
 * <p>
 * This class launches the application by initializing the {@link MenuController},
 * which handles the startup logic and UI flow of the program.
 */
public class TradingCardDriver {
    public static void main(String[] args){

        /**
         * Main method that starts the Trading Card Inventory System.
         *
         * @param args command-line arguments (not used)
         */
        MenuController menu = new MenuController();
        menu.run();
    }
}