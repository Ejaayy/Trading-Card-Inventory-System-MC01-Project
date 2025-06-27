package com.tradingCardInventory.menu;

import com.tradingCardInventory.manager.ManageBinders;
import com.tradingCardInventory.manager.ManageDeck;
import com.tradingCardInventory.model.Collection;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Menu class serves as the main controller that initializes all components of the system
 * and presents the main menu for navigating between the Collection, Binders, and Decks modules.
 */
public class Menu {

    // Properties and Attributes
    Scanner scanner = new Scanner(System.in);

    private final Collection collection;
    private final ManageBinders manageBinder;
    private final ManageDeck manageDeck;
    private final CollectionController collectionController;
    private final BindersController bindersController;
    private final DeckController decksController;

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
        this.collectionController = new CollectionController(collection, this.scanner);
        this.bindersController = new BindersController(manageBinder, this.scanner);
        this.decksController = new DeckController(manageDeck, this.scanner);
    }

    /*
     * Starts the main loop of the system.
     * Displays the main menu and calls the appropriate module based on user input.
     * Runs until the user selects the Exit option.
     */
    public void run() {

        boolean running = true;

        //Continues to run until the user  chooses to exit ( Case 0 )
        while(running){

            //Calls Main Menu UI
            int input = displayMainMenu();

            //Calls appropriate input based from user's decision
            switch (input){
                case 1:
                    //Calls Collection Menu
                    collectionController.collectionMenu();
                    break;
                case 2:
                    //Calls Binder Menu
                    bindersController.manageBinderMenu();
                    break;
                case 3:
                    //Calls Deck Menu
                    decksController.manageDeckMenu();
                    break;
                case 0:
                    //Ends Program
                    running = false;
                    scanner.close();
                    break;
                default:
                    //Error handling message
                    System.out.println("Invalid option. Please choose between 0 and 3.\n");
            }
        }
    }

    /*
     * Displays the main menu of the program and reads the user’s choice.
     *
     * @return the user’s chosen option as an integer
     */
    public int displayMainMenu(){

        int input = -1;

        //Prints UI for Main Menu
        System.out.println("-------------------------------------------");
        System.out.println("MCO1 - Trading Card Inventory System");
        System.out.println("-------------------------------------------");
        System.out.println("1. Manage Collection");
        System.out.println("2. Manage Binders");
        System.out.println("3. Manage Decks");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------------");
        System.out.print("Enter Choice: ");

        //Error handling for Main Menu
        try {
            input = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // clear the wrong input
        }

        System.out.println("-------------------------------------------\n");
        return input;
    }
}