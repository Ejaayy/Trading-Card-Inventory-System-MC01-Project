package com.tradingCardInventory.menu;

import com.tradingCardInventory.manager.ManageBinders;
import com.tradingCardInventory.manager.ManageDeck;
import com.tradingCardInventory.model.Collection;
import java.util.Scanner;

public class Menu {

    // Properties and Attributes
    Scanner scanner = new Scanner(System.in);

    private Collection collection;
    private ManageBinders manageBinder;
    private ManageDeck manageDeck;
    private CollectionController collectionUI;
    private BindersController binderUI;
    private DeckController deckUI;

    /*
     * Constructor initializes all components of the system and wires them together.
     * Pre-condition:
     * - All supporting classes (Collection, ManageBinders, etc.) must be defined.
     * Post-condition:
     * - Ready to run the menu-based system with all modules initialized.
     */
    public Menu(){
        this.collection = new Collection();
        this.manageBinder = new ManageBinders(collection);
        this.manageDeck= new ManageDeck(collection);
        this.collectionUI = new CollectionController(collection, this.scanner);
        this.binderUI = new BindersController(manageBinder, this.scanner);
        this.deckUI = new DeckController(manageDeck, this.scanner);
    }

    /*
     * Starts the main loop of the system.
     * Displays the main menu and calls the appropriate module based on user input.
     * Runs until the user selects the Exit option.
     */
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

    /*
     * Displays the main menu of the program and reads the user’s choice.
     *
     * @return the user’s chosen option as an integer
     */
    public int displayMainMenu(){
        int input = 0;
        System.out.println("-------------------------------------------");
        System.out.println("MCO1 - Trading Card Inventory System");
        System.out.println("-------------------------------------------");
        System.out.println("1. Manage Collection");
        System.out.println("2. Manage Binders");
        System.out.println("3. Manage Decks");
        System.out.println("4. Exit");
        System.out.println("-------------------------------------------");
        System.out.print("Enter Choice: ");
        input = scanner.nextInt();
        scanner.nextLine();
        System.out.println("-------------------------------------------\n");
        return input;
    }

}
