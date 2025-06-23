package com.TradingCardInventoryClasses.menu;

import com.TradingCardInventoryClasses.manager.ManageDeck;
import java.util.Scanner;

//Uses DeckUI
public class DeckController {

    // Properties
    private ManageDeck manageDeck;
    private Scanner scanner;

    //Methods

    //Instantiate necessary properties in constructor
    public DeckController(ManageDeck manageDeck, Scanner scanner){
        this.manageDeck = manageDeck;
        this.scanner = scanner;

    }

    public int manageDeckMenuTemplate(){
        int input = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("MCO1 - Manage Deck Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Create New Deck");
        System.out.println("2. Delete a Deck");
        System.out.println("3. Add card to a deck");
        System.out.println("4. Remove card from a deck");
        System.out.println("5. View Deck");
        System.out.println("6. Exit");
        System.out.print("Enter Choice: ");
        input = scanner.nextInt();
        System.out.println("\n-------------------------------------------");

        return input;
    }

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
                    System.out.println("Enter name of Binder to delete: ");
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

                    //Input and search Binder in Binders list
                    System.out.print("Enter which Deck to add card in: ");
                    String addDeckName = scanner.nextLine();

                    boolean status = manageDeck.addCardToDeck(addCardName, addDeckName);
                    if(status){
                        System.out.printf("Success! Card: %s has been added to Binder: %s\n", addCardName, addDeckName);
                    }
                    break;
                case 4:
                    //Remove card to a deck

                    //Input and search for Card in Collection
                    System.out.print("Enter Card Name from Collection: ");
                    String removeCardName = scanner.nextLine();

                    //Input and search Binder in Binders list
                    System.out.print("Enter which Deck to remove card in: ");
                    String removeDeckName = scanner.nextLine();

                    boolean deckstatus = manageDeck.removeCardFromDeck(removeCardName, removeDeckName);
                    if(deckstatus){
                        System.out.printf("Success! Card: %s has been added to Binder: %s\n", removeCardName, removeDeckName);
                    }
                    break;
                case 5:
                    //View Deck
                    System.out.print("Enter Binder Name to View: ");
                    deckName = scanner.nextLine();
                    manageDeck.viewSpecificDeck(deckName);
                    break;
                case 6:
                    running = false;
                    break;
            }
        }
    }
}
