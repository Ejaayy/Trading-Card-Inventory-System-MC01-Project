package com.tradingCardInventory.menu;

import com.tradingCardInventory.manager.ManageDeck;
import com.tradingCardInventory.model.Deck;

import java.util.Scanner;

/*
 * DeckController handles all user interaction related to managing decks.
 * It serves as the controller in the MVC architecture, delegating logic to ManageDeck (model manager)
 * and facilitating input/output through the console.
 */
public class DeckController {

    // Properties
    private ManageDeck manageDeck;
    private Scanner scanner;

    //Methods

    /*
     * Instantiates necessary properties in the constructor.
     *
     * @param manageDeck object that manages deck operations
     * @param scanner input reader for user interaction
     */
    public DeckController(ManageDeck manageDeck, Scanner scanner){
        this.manageDeck = manageDeck;
        this.scanner = scanner;

    }

    /*
     * Template for displaying the Manage Deck menu and retrieving user input.
     *
     * @return the selected menu option as an integer
     *
     * Pre-condition:
     * - User is prompted via console
     * Post-condition:
     * - User input is returned
     */
    public int manageDeckMenuTemplate(){
        int input = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------------------");
        System.out.println("MCO1 - Manage Deck Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Create New Deck");
        System.out.println("2. Delete a Deck");
        System.out.println("3. Add card to a deck");
        System.out.println("4. Remove card from a deck");
        System.out.println("5. View Deck");
        System.out.println("0. Exit");
        System.out.print("Enter Choice: ");
        input = scanner.nextInt(); //errorr
        scanner.nextLine();
        System.out.println("\n-------------------------------------------");

        return input;
    }

    /*
     * Main logic loop for displaying and executing deck management options.
     * Handles menu input, deck/card operations, and user feedback.
     */
    public void manageDeckMenu(){

        boolean running = true;
        int input = 0;
        String deckName;

        while(running) {
            input = manageDeckMenuTemplate();
            switch (input) {
                case 1:
                    //Create New Deck
                    System.out.println("Enter name for Deck: ");
                    deckName = scanner.nextLine();
                    manageDeck.createDeck(deckName);
                    break;
                case 2:
                    //Delete a Deck
                    System.out.println("Enter name of Deck to delete: ");
                    String deckDelete = scanner.nextLine();
                    boolean statusDelete = manageDeck.deleteDeck(deckDelete);
                    if(statusDelete){
                        System.out.println("Deck deleted successfully");
                    }
                    else{
                        System.out.println("Deck deletion failed");
                    }

                    break;
                case 3:
                    //Add card to a deck

                    //Input and search for Card in Collection
                    System.out.print("Enter Card Name from Collection: ");
                    String addCardName = scanner.nextLine();

                    //Input and search Deck in Deck list
                    System.out.print("Enter which Deck to add card in: ");
                    String addDeckName = scanner.nextLine();

                    boolean status = manageDeck.addCardToDeck(addCardName, addDeckName);
                    if(status){
                        System.out.printf("Success! Card: %s has been added to deck: %s\n", addCardName, addDeckName);
                    }else{
                        System.out.println("Failed to add card to deck");
                    }
                    break;
                case 4:
                    //Remove card to a deck

                    //Input and search for Card in Collection
                    System.out.print("Enter Card Name from Collection: ");
                    String removeCardName = scanner.nextLine();

                    //Input and search Deck in deck list
                    System.out.print("Enter which Deck to remove card from: ");
                    String removeDeckName = scanner.nextLine();

                    boolean deckStatus = manageDeck.removeCardFromDeck(removeCardName, removeDeckName);
                    if(deckStatus){
                        System.out.printf("Success! Card: %s has been removed from deck: %s\n", removeCardName, removeDeckName);
                    }else{
                        System.out.println("Failed to remove card from deck");
                    }
                    break;
                case 5:
                    //View Deck
                    System.out.print("Enter Deck Name to View: ");
                    deckName = scanner.nextLine();
                    Deck deck = manageDeck.viewSpecificDeck(deckName);

                    System.out.println("Enter Card in Deck to View: ");
                    String deckCardName = scanner.nextLine();
                    if(!manageDeck.viewSpecificCardinDeck(deckCardName, deck)){
                        System.out.println("Card not found.");
                    };


                    break;
                case 6:
                    running = false;
                    break;
            }
        }
    }
}
