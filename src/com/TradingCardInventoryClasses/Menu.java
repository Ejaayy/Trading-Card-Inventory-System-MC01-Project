package com.TradingCardInventoryClasses;

import java.util.Scanner;

public class Menu {

    public void run() {

        Collection collection = new Collection();
        ManageBinder manageBinder = new ManageBinder();
        ManageDeck manageDeck = new ManageDeck();

        boolean running = true;

        while(running){
            int input = displayMainMenu();

            switch (input){
                case 1:
                    collection.collectionMenu();
                    break;
                case 2:
                    manageBinder.manageBinderMenu();
                    break;
                case 3:
                    manageDeck.manageDeckMenu();
                    break;
                case 4:
                    running = false;
                    break;
            }
        }

    }

    public int displayMainMenu(){
        int input = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("MCO1 - Trading Card Inventory System");
        System.out.println("-------------------------------------------");
        System.out.println("1. Manage Collection");
        System.out.println("2. Manage Binders");
        System.out.println("3. Manage Decks");
        System.out.println("4. Exit");
        System.out.print("Enter Choice: ");
        input = scanner.nextInt();
        System.out.println("\n-------------------------------------------");

        return input;
    }





}
