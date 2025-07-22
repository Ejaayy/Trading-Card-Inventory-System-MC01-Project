package com.tradingCardInventory;

import com.tradingCardInventory.menu.MenuController;
import com.tradingCardInventory.view.StartMenuPanel;

/*
 * Entry point of the Trading Card Inventory System.
 * Initializes the main menu and starts the user interface loop.
 */
public class TradingCardDriver {
    public static void main(String[] args){

        // Create a new Menu instance which handles the UI and logic
        MenuController menu = new MenuController();
        menu.run();
    }
}