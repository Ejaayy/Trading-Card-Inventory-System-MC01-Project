package com.tradingCardInventory.menu;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;

import java.util.InputMismatchException;
import java.util.Scanner;

//This class only handles UI, user input, and showing Displays
//Logic comes from the Models
public class CollectionController {

    //Properties
    private Collection collection;
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
        int input;
        while(running) {
            input = collectionMenuTemplate();
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
                case 4:
                    running = false;
                    break;
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
        System.out.println("-------------------------------------------");
        System.out.println("MCO1 - Collection Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Add Card");
        System.out.println("2. Increase / Decrease Card Count");
        System.out.println("3. Display Card / Collection");
        System.out.println("4. Exit");
        System.out.print("Enter Choice: ");

        try {
            input = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
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
        int input;
        boolean running = true;

        while(running) {

            System.out.println("Increase or Decrease Card Count");
            System.out.println("-------------------------------------------");
            System.out.println("1. Increase Card Count");
            System.out.println("2. Decrease Card Count");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            input = scanner.nextInt();
            scanner.nextLine();
            System.out.println("-------------------------------------------");

            switch(input) {
                case 1:
                    // Increase Card
                    System.out.print("Enter Card Name: ");
                    String increaseCardName = this.scanner.nextLine();
                    this.collection.increaseCardCount(increaseCardName);
                    break;
                case 2:
                    // Increase Card
                    System.out.print("Enter Card Name: ");
                    String decreaseCardName = this.scanner.nextLine();
                    this.collection.decreaseCardCount(decreaseCardName);
                    break;
                case 3:
                    running = false;
                    break;
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

        int input;
        boolean running = true;

        while(running) {

            System.out.println("View Card and View Collection Menu");
            System.out.println("-------------------------------------------");
            System.out.println("1. View Card");
            System.out.println("2. View Collection");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            input = scanner.nextInt();
            scanner.nextLine();
            System.out.println("-------------------------------------------");

            switch(input) {
                case 1:
                    // View Card

                    System.out.print("Enter Card Name: ");
                    String cardName = this.scanner.nextLine();
                    boolean status = this.collection.displayCard(cardName);

                    if(!status){
                        System.out.println("Card not found");
                    }

                    break;
                case 2:
                    //View Collection
                    this.collection.displayCollection();
                    break;
                case 3:
                    running = false;
                    break;
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
        System.out.print("Enter Card name: ");
        String cardName = this.scanner.nextLine();

        //Search loop to find if the card exists already
        Card foundCard = this.collection.searchCard(cardName);

        if(foundCard != null){
            System.out.println("Card already Exists! Would you like to increase the card count? [Y/N]: ");
            String response = this.scanner.nextLine();
            if (response.equals("Y")){
                foundCard.incrementCount(1);
            }
        }
        else{
            // Input Rarity
            Rarity rarity = null;
            while (rarity == null) {
                System.out.print("Enter Rarity (common, uncommon, rare, legendary): ");
                String rarityInput = this.scanner.nextLine().trim().toUpperCase();
                try {
                    rarity = Rarity.valueOf(rarityInput);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid rarity. Try again.");
                }
            }

            // Input Variant (only if rare or legendary)
            Variant variant = Variant.NORMAL;  // default
            if (rarity == Rarity.RARE || rarity == Rarity.LEGENDARY) {
                while (true) {
                    System.out.print("Enter Variant (normal, extended_art, full_art, alt_art): ");
                    String variantInput = this.scanner.nextLine().trim().toUpperCase();
                    try {
                        variant = Variant.valueOf(variantInput);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid variant. Try again.");
                    }
                }
            }

            //Input Value
            System.out.print("Enter Value: ");
            double value = this.scanner.nextDouble();
            scanner.nextLine();
            this.collection.addCard(cardName, rarity, variant, value);

        }

    }
}
