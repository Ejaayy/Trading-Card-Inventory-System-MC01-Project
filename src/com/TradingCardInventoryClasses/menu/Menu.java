package com.TradingCardInventoryClasses.menu;

import com.TradingCardInventoryClasses.manager.ManageBinders;
import com.TradingCardInventoryClasses.manager.ManageDeck;
import com.TradingCardInventoryClasses.model.Collection;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    Collection collection;
    ManageBinders manageBinder;
    ManageDeck manageDeck;
    CollectionController collectionUI;
    BindersController binderUI;
    DeckController deckUI;

    public Menu(){
        this.collection = new Collection();
        this.manageBinder = new ManageBinders(collection);
        this.manageDeck= new ManageDeck(collection);
        this.collectionUI = new CollectionController(collection, this.scanner);
        this.binderUI = new BindersController(manageBinder, this.scanner);
        this.deckUI = new DeckController(manageDeck, this.scanner);
    }

    public void run() {

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

        System.out.println("MCO1 - Trading Card Inventory System");
        System.out.println("-------------------------------------------");
        System.out.println("1. Manage Collection");
        System.out.println("2. Manage Binders");
        System.out.println("3. Manage Decks");
        System.out.println("4. Exit");
        System.out.print("Enter Choice: ");
        input = this.scanner.nextInt();
        this.scanner.nextLine(); //For input buffers
        System.out.println("\n-------------------------------------------");

        return input;
    }

}
