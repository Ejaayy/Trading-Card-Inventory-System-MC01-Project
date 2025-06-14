package com.TradingCardInventoryClasses.menu;

import com.TradingCardInventoryClasses.manager.ManageBinder;
import com.TradingCardInventoryClasses.manager.ManageDeck;
import com.TradingCardInventoryClasses.model.Collection;

import java.util.Scanner;

public class Menu {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Collection collection = new Collection();
        ManageBinder manageBinder = new ManageBinder();
        ManageDeck manageDeck = new ManageDeck();
        CollectionUI collectionUI = new CollectionUI(collection, scanner);
        BinderUI binderUI = new BinderUI(manageBinder, scanner);
        DeckUI deckUI = new DeckUI(manageDeck, scanner);

        boolean running = true;

        while(running){
            int input = displayMainMenu();

            switch (input){
                case 1:
                    collectionUI.collectionMenu();
                    break;
                case 2:
                    binderUI.manageBinderMenu();
                    break;
                case 3:
                    deckUI.manageDeckMenu();
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
