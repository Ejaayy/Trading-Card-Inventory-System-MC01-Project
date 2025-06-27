package com.tradingCardInventory;

import com.tradingCardInventory.menu.Menu;

/*
 * Entry point of the Trading Card Inventory System.
 * Initializes the main menu and starts the user interface loop.
 */
public class Main {
    public static void main(String[] args){

        // Create a new Menu instance which handles the UI and logic
        Menu menu = new Menu();

        // Run the menu system (main loop)
        menu.run();

    }
}