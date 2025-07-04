package com.tradingCardInventory;

import com.tradingCardInventory.menu.Menu;
import com.tradingCardInventory.view.MainView;

/*
 * Entry point of the Trading Card Inventory System.
 * Initializes the main menu and starts the user interface loop.
 */
public class TradingCardDriver {
    public static void main(String[] args){

        // Create a new Menu instance which handles the UI and logic
        Menu menu = new Menu();

        // Run the menu system (main loop) TESTING
        //menu.run();
        new MainView();
    }
}