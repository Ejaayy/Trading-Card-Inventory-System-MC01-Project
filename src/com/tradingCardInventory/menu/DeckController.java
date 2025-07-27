package com.tradingCardInventory.menu;

import com.tradingCardInventory.manager.ManageDeck;
import com.tradingCardInventory.model.Deck;
import com.tradingCardInventory.view.MainView;

import com.tradingCardInventory.view.panels.ManageDecksView.*;
import com.tradingCardInventory.view.panels.NavigationView.NavigationPanel;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

/*
 * DeckController handles all user interaction related to managing decks.
 * It serves as the controller in the MVC architecture, delegating logic to ManageDeck (model manager)
 * and facilitating input/output through the console.
 */
public class DeckController {

    // Properties
    private final ManageDeck manageDeck;
    private Scanner scanner = new Scanner(System.in);
    private MainView mainView;
    private MenuController menuController;
    //Methods

    /*
     * Instantiates necessary properties in the constructor.
     *
     * @param manageDeck object that manages deck operations
     * @param scanner input reader for user interaction
     */
    public DeckController(ManageDeck manageDeck, MainView mainView, MenuController menuController){
        this.manageDeck = manageDeck;
        this.mainView = mainView;
        this.menuController = menuController;
    }
    public void run(){
        DeckController deckController = this;
        //Used LinkedHashMap so that it will be ordered in NavBar
        mainView.setLeftPanel(new NavigationPanel(new LinkedHashMap<>() {{
            put("Create Deck", ev ->  mainView.setCenterPanel(new CreateDeckPanel(deckController)));
            put("Delete Deck", ev -> mainView.setCenterPanel(new DeleteDeckPanel(deckController)));
            put("Add Card to Deck", ev -> mainView.setCenterPanel(new AddCardPanel(deckController)));
            put("Remove Card", ev -> mainView.setCenterPanel(new RemoveCardPanel(deckController)));
            put("View Deck", ev -> mainView.setCenterPanel(createPlaceholderPanel("Manage Decks")));
            put("Sell Deck", ev -> mainView.setCenterPanel(createPlaceholderPanel("Manage Decks")));
            put("Back", ev -> menuController.loadMainMenu());
        }}));

        //Setup center panel content
        mainView.setCenterPanel(new ManageDecksMenuPanel());

    }

    //DUMMY PANELS
    private JPanel createPlaceholderPanel(String title) {
        JPanel panel = new JPanel();
        return panel;
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
    public boolean createDeck(String deckName){
        //Checks if deck already exists
        if(manageDeck.searchDeck(deckName)!= null){
            return  false;
        }else {
            manageDeck.createDeck(deckName);
            return true;
        }
    }

    public boolean deleteDeck(String deckName){
        //Checks if deck already exists
        if(manageDeck.searchDeck(deckName)!= null){ //if it exists
            manageDeck.deleteDeck(deckName);
            return  true;
        }else {
            return false;
        }
    }

    public boolean addCardToDeck(String deckName, String cardName){
        return manageDeck.addCardToDeck(cardName, deckName);
    }

    public boolean removeCardFromDeck(String deckName, String cardName){
        return manageDeck.removeCardFromDeck(deckName, cardName);
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

            //int input = manageDeckMenuTemplate();

            switch (1) {
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