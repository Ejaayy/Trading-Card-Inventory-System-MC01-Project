package com.tradingCardInventory.menu;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * CollectionController handles user interaction for managing the main card collection.
 * It serves as the "Controller" in the MVC architecture and interacts with the Collection model.
 *
 * It displays menus for collection-related actions, accept and validate user input
 * and call appropriate methods on the Collection model
 */
public class CollectionController {

    //Properties
    private final Collection collection;
    private Scanner scanner;

    //Methods

    /*
     * Constructs a CollectionController with a given collection and scanner.
     *
     * @param collection the Collection model to be managed
     * @param scanner the Scanner object for user input
     *
     * Pre-condition:
     * - 'collection' and 'scanner' should not be null.
     *
     * Post-condition:
     * - Initializes the controller with references to the collection and scanner.
     */

    public CollectionController(Collection collection, Scanner scanner){
        this.collection = collection;
        this.scanner = scanner;
    }

    /*
     * collectionMenu() - Handles the main menu for managing the card collection.
     *
     * Pre-condition:
     * - Supporting methods like addInputCard(), increaseDecrease(), and display() must be implemented.
     * - Scanner for user input must be initialized.
     *
     * Post-condition:
     * - Executes menu operations in a loop until the user selects "Exit".
     */
    public void collectionMenu(){

        boolean running = true;

        //Continues to run until the user  chooses to exit ( Case 0 )
        while(running) {

            //Calls Collection Menu UI
            int input = collectionMenuTemplate();

            //Calls appropriate input based from user's decision
            switch (input) {
                case 1:
                    //Add card method
                    this.addInputCard();
                    break;
                case 2:
                    //Increase / Decrease Card Count
                    this.increaseDecrease();
                    break;
                case 3:
                    //Display Card / Collection
                    this.display();
                    break;
                case 0:
                    //Exits collection menu
                    running = false;
                    break;
                default:
                    //Error handling message
                    System.out.println("Invalid option. Please choose between 0 and 3.\n");
            }
        }
    }

    /*
     * Displays the collection menu and prompts the user for a choice.
     *
     * Pre-condition:
     * - Scanner must be initialized.
     *
     * Post-condition:
     * - Returns the user's menu choice as an integer (or -1 if input is invalid).
     */
    public int collectionMenuTemplate() {
        int input = -1;

        //Prints UI for Main Menu
        System.out.println("-------------------------------------------");
        System.out.println("MCO1 - Collection Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Add Card");
        System.out.println("2. Increase / Decrease Card Count");
        System.out.println("3. Display Card / Collection");
        System.out.println("0. Exit");
        System.out.print("Enter Choice: ");

        //Error handling for Collection Menu
        try {
            input = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // clear the wrong input
        }

        System.out.println("-------------------------------------------");
        return input;
    }

    /*
     * Menu logic for handling increases and decreases in specific Cards.
     *
     * Pre-condition:
     * - Scanner and Collection instance must be initialized.
     * - Collection must have methods: increaseCardCount(String), decreaseCardCount(String).
     *
     * Post-condition:
     * - Updates the count of a card based on user input until user exits the menu.
     */
    public void increaseDecrease(){

        boolean running = true;

        //Continues to run until the user  chooses to exit ( Case 0 )
        while(running) {

            int input = -1;

            //Prints UI for Increase or Decrease
            System.out.println("Increase or Decrease Card Count");
            System.out.println("-------------------------------------------");
            System.out.println("1. Increase Card Count");
            System.out.println("2. Decrease Card Count");
            System.out.println("0. Exit");
            System.out.print("Enter Choice: ");

            //Error handling for Collection Menu
            try {
                input = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear the wrong input
            }

            System.out.println("-------------------------------------------");

            //Calls appropriate input based from user's decision
            switch(input) {
                case 1:
                    // Increase Card
                    System.out.println("[0. Exit]");
                    System.out.print("Enter Card Name: ");
                    String increaseCardName = this.scanner.nextLine();
                    if(!increaseCardName.equals("0"))
                        this.collection.changeCardCount(increaseCardName, 1);
                    break;
                case 2:
                    // Decrease Card
                    System.out.println("[0. Exit]");
                    System.out.print("Enter Card Name: ");
                    String decreaseCardName = this.scanner.nextLine();
                    if(!decreaseCardName.equals("0"))
                        this.collection.changeCardCount(decreaseCardName, -1);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 0 and 2.\n");
            }
        }
    }

    /*
     * Menu for viewing a specific card or the entire collection.
     *
     * Pre-condition:
     * - Scanner and Collection instance must be initialized.
     * - Collection must have methods: displayCard(String), displayCollection().
     *
     * Post-condition:
     * - Allows user to view a card or the full collection until they choose to exit.
     */
    public void display(){
        
        boolean running = true;

        while(running) {

            int input = -1;

            System.out.println("View Card and View Collection Menu");
            System.out.println("-------------------------------------------");
            System.out.println("1. View Card");
            System.out.println("2. View Collection");
            System.out.println("0. Exit");
            System.out.print("Enter Choice: ");

            //Error handling for Collection Menu
            try {
                input = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear the wrong input
            }
            System.out.println("-------------------------------------------");

            switch(input) {
                case 1:
                    // View Card
                    System.out.println("[0. Exit]");
                    System.out.print("Enter Card Name: ");
                    String cardName = this.scanner.nextLine();
                    boolean status = this.collection.displayCard(cardName);

                    if(!cardName.equals("0"))
                        if(!status){
                            System.out.println("Card not found");
                        }
                    break;
                case 2:
                    //View Collection
                    this.collection.displayCollection();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 0 and 2.\n");
            }

        }

    }

    /*
     * UI for asking inputs when adding a new card to the collection.
     *
     * Pre-condition:
     * - Scanner and Collection instances must be initialized.
     * - Enum types Rarity and Variant must be defined.
     * - Collection must have methods: searchCard(String), addCard(...).
     *
     * Post-condition:
     * - Adds a new card or increments the count of an existing one based on user input.
     */
    public void addInputCard(){

        //Input Card Name
        System.out.println("[0. Exit]");
        System.out.print("Enter Card name: ");
        String cardName = this.scanner.nextLine();

        if(!cardName.equals("0")){
            //Search loop to find if the card exists already
            Card foundCard = this.collection.searchCard(cardName);

            //Checks if Card already exists
            if(foundCard != null){
                //Confirms user if user wants to continue adding that card
                System.out.print("Card already Exists! Would you like to increase the card count? [Y/N]: ");
                String response = this.scanner.nextLine();

                if (response.equalsIgnoreCase("Y") && !response.equals("0")){
                    foundCard.incrementCount(1);
                    System.out.println("Card Increment successful!");
                }else{
                    System.out.println("Add Card cancelled.");
                }
            }
            else{
                // Input Rarity
                Rarity rarity = null;
                boolean flag = true;
                while (rarity == null && flag) {
                    System.out.print("Enter Rarity (common, uncommon, rare, legendary): ");
                    String rarityInput = this.scanner.nextLine().trim().toUpperCase();

                    //Error Handling
                    try {
                        if(!rarityInput.equals("0"))
                            rarity = Rarity.valueOf(rarityInput);
                        else
                            flag = false;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid rarity. Try again.");
                    }
                }

                if(flag){
                    // Input Variant (only if rare or legendary)
                    Variant variant = Variant.NORMAL;  // default
                    if (rarity == Rarity.RARE || rarity == Rarity.LEGENDARY) {
                        while (true) {
                            System.out.print("Enter Variant (normal, extended_art, full_art, alt_art): ");
                            String variantInput = this.scanner.nextLine().trim().toUpperCase();

                            //Error Handling
                            try {
                                if(!variantInput.equals("0"))
                                    variant = Variant.valueOf(variantInput);
                                else
                                    flag = false;
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid variant. Try again.");
                            }
                        }
                    }

                    // Input Value
                    double value = 0.0;
                    boolean valid = false;

                    if(flag){
                        while (!valid) {
                            System.out.print("Enter Value: ");

                            //Error Handling
                            try {
                                value = this.scanner.nextDouble();
                                scanner.nextLine(); // consume newline
                                valid = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a numeric value.");
                                scanner.nextLine(); // clear invalid input
                            }
                        }
                        if(value != 0){
                            // After a valid value is entered
                            this.collection.addCard(cardName, rarity, variant, value);
                            System.out.println("Input Card Success!");
                        } else{
                            System.out.println("Is the input (0) to cancel or the value of the card?");
                            System.out.println("0. Cancel");
                            System.out.println("1. Value");
                            System.out.print("Input: ");

                            double input = scanner.nextDouble();
                            scanner.nextLine();

                            if(input!=0){
                                this.collection.addCard(cardName, rarity, variant, value);
                                System.out.println("Input Card Success!");
                            }
                        }
                    }
                }
            }
        }
    }
}