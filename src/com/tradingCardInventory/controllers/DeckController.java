package com.tradingCardInventory.controllers;

import com.tradingCardInventory.manager.ManageDeck;
import com.tradingCardInventory.model.Binders.Binder;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Decks.Deck;
import com.tradingCardInventory.model.Decks.DeckSellable;
import com.tradingCardInventory.options.DeckType;
import com.tradingCardInventory.view.MainView;

import com.tradingCardInventory.view.panels.ManageDecksView.*;
import com.tradingCardInventory.view.panels.NavigationView.NavigationPanel;

import java.util.*;

/*
 * DeckController handles all user interaction related to managing decks.
 * It serves as the controller in the MVC architecture, delegating logic to ManageDeck (model manager)
 * and facilitating input/output through the console.
 */
public class DeckController {

    // Properties
    private final ManageDeck manageDeck;
    private MainView mainView;
    private MenuController menuController;
    private CollectionController collectionController;
    //Methods

    /*
     * Instantiates necessary properties in the constructor.
     *
     * @param manageDeck object that manages deck operations
     * @param scanner input reader for user interaction
     */
    public DeckController(ManageDeck manageDeck, MainView mainView, MenuController menuController, CollectionController collectionController){
        this.manageDeck = manageDeck;
        this.mainView = mainView;
        this.menuController = menuController;
        this.collectionController = collectionController;
    }

    public void run(){
        DeckController deckController = this;
        //Used LinkedHashMap so that it will be ordered in NavBar
        mainView.setLeftPanel(new NavigationPanel(new LinkedHashMap<>() {{
            put("Create Deck", ev ->  mainView.setCenterPanel(new CreateDeckPanel(deckController)));
            put("Delete Deck", ev -> mainView.setCenterPanel(new DeleteDeckPanel(deckController)));
            put("Add Card to Deck", ev -> mainView.setCenterPanel(new AddCardPanel(deckController)));
            put("Remove Card", ev -> mainView.setCenterPanel(new RemoveCardPanel(deckController)));
            put("View Deck", ev -> mainView.setCenterPanel(new ViewDeckPanel(deckController)));
            put("Sell Deck", ev -> mainView.setCenterPanel(new SellDeckPanel(deckController)));
            put("Back", ev -> menuController.loadMainMenu());
        }}));

        //Setup center panel content
        mainView.setCenterPanel(new ManageDecksMenuPanel());

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
    public boolean createDeck(String deckName, DeckType deckType){
        //Checks if deck already exists
        if(manageDeck.searchDeck(deckName)!= null){
            return  false;
        }else {
            manageDeck.createDeck(deckName, deckType);
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

    public List<String> getAllDeckNames(){
        List<String> deckNames = new ArrayList<>();

        for(Deck deck: manageDeck.getDecks()){
            deckNames.add(deck.getName());
        }

        return deckNames;
    }

    public List<String> getAllSellableDeckNames(){
        List<String> sellableDeckNames = new ArrayList<>();

        for(Deck deck: manageDeck.getDecks()){
            if(deck instanceof DeckSellable){
                sellableDeckNames.add(deck.getName());
            }
        }

        return sellableDeckNames;
    }

    public List<String> getAllDeckCardNames(String deckName){
        List<String> deckNames = new ArrayList<>();

        Deck found = manageDeck.searchDeck(deckName);

        if(found != null){
            for(Card card: found.getCards()){
                deckNames.add(card.getName());
            }
            return deckNames;
        }
        return null;
    }

    public List<String> getCollectionCardNames(){
        List<String> collectionCardNames = new ArrayList<>();
        for(String cardName: collectionController.getAllCardNames()){
            if(collectionController.getCollection().getCard(cardName).getCount() > 0){
                collectionCardNames.add(cardName);
            }
        }
        return collectionCardNames;
    }

    public List<Card> getCards(String deckName){
        return manageDeck.getCards(deckName);
    }

    public boolean sellDeck(String deckName){
        return manageDeck.sellDeck(deckName);
    }
}