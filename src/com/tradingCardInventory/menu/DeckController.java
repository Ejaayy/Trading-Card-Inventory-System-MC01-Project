package com.tradingCardInventory.menu;

import com.tradingCardInventory.manager.ManageDeck;
import com.tradingCardInventory.model.Deck;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * DeckController handles all user interaction related to managing decks.
 * It serves as the controller in the MVC architecture, delegating logic to ManageDeck (model manager)
 * and facilitating input/output through the console.
 */
public class DeckController {

    // Properties
    private final ManageDeck manageDeck;
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
        int input = -1;
        Scanner scanner = new Scanner(System.in);

        //Shows Deck Menu UI
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

        //Error handling for Decks Menu
        try {
            input = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // clear the wrong input
        }

        System.out.println("\n-------------------------------------------");

        return input;
    }

    /*
     * Main logic loop for displaying and executing deck management options.
     * Handles menu input, deck/card operations, and user feedback.
     */
    public void manageDeckMenu(){

        boolean running = true;
        String deckName;

        //Continues to run until the user  chooses to exit ( Case 0 )
        while(running) {

            int input = manageDeckMenuTemplate();

            switch (input) {
                case 1:
                    //Create New Deck
                    System.out.println("[0. Exit]");
                    System.out.print("Enter name for Deck: ");
                    deckName = scanner.nextLine();

                    if(!deckName.equals("0")){

                        //Checks if deck already exists
                        if(manageDeck.searchDeck(deckName)!= null){
                            System.out.println("Deck already exists!");
                        }else{
                            manageDeck.createDeck(deckName);
                            System.out.println("Success! Deck created successfully");
                        }
                    }
                    break;
                case 2:
                    //Delete a Deck
                    System.out.println("[0. Exit]");
                    System.out.print("Enter name of Deck to delete: ");
                    String deckDelete = scanner.nextLine();

                    if(!deckDelete.equals("0")){
                        boolean statusDelete = manageDeck.deleteDeck(deckDelete);

                        if(statusDelete){
                            System.out.println("Deck deleted successfully");
                        }
                        else{
                            System.out.println("Deck deletion failed");
                        }
                    }

                    break;
                case 3:
                    //Add card to a deck

                    System.out.println("[0. Exit]");

                    //Input and search for Card in Collection
                    System.out.print("Enter Card Name from Collection: ");
                    String addCardName = scanner.nextLine();

                    if(!addCardName.equals("0")){
                        //Input and search Deck in Deck list
                        System.out.print("Enter which Deck to add card in: ");
                        String addDeckName = scanner.nextLine();

                        if(!addDeckName.equals("0")){
                            boolean status = manageDeck.addCardToDeck(addCardName, addDeckName);
                            if(status){
                                System.out.printf("Success! Card: %s has been added to deck: %s\n", addCardName, addDeckName);
                            }else{
                                System.out.println("Failed to add card to deck");
                            }
                        }
                    }
                    break;
                case 4:
                    //Remove card to a deck

                    System.out.println("[0. Exit]");

                    //Input and search for Card in Collection
                    System.out.print("Enter Card Name from Collection: ");
                    String removeCardName = scanner.nextLine();

                    if(!removeCardName.equals("0")){
                        //Input and search Deck in deck list
                        System.out.print("Enter which Deck to remove card from: ");
                        String removeDeckName = scanner.nextLine();

                        if(!removeDeckName.equals("0")){
                            boolean deckStatus = manageDeck.removeCardFromDeck(removeCardName, removeDeckName);
                            if(deckStatus){
                                System.out.printf("Success! Card: %s has been removed from deck: %s\n", removeCardName, removeDeckName);
                            }else{
                                System.out.println("Failed to remove card from deck");
                            }
                        }
                    }
                    break;
                case 5:
                    //View Deck
                    System.out.println("[0. Exit]");
                    System.out.print("Enter Deck Name to View: ");
                    deckName = scanner.nextLine();

                    if(!deckName.equals("0")){
                        Deck deck = manageDeck.viewSpecificDeck(deckName);

                        //Prompts user to view specific card in deck
                        if(deck != null){
                            if(!deck.getCards().isEmpty()){
                                System.out.print("Enter Card in Deck to View: ");
                                String deckCardName = scanner.nextLine();

                                if(!deckCardName.equals("0")){
                                    if(!manageDeck.viewSpecificCardinDeck(deckCardName, deck)){
                                        System.out.println("Card not found.");
                                    }
                                }
                            }
                        }else{
                            System.out.println("Deck not found.");
                        }
                    }

                    break;
                case 0:
                    //Exiting Deck Menu
                    running = false;
                    break;
                default:
                    //Error handling message
                    System.out.println("Invalid option. Please choose between 0 and 5.\n");
            }
        }
    }
}