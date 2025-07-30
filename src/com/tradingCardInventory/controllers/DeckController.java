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

/**
 * The {@code DeckController} class manages user interactions related to decks
 * in the trading card inventory system. Acting as the controller in the MVC pattern,
 * it facilitates operations such as creating, deleting, viewing, modifying,
 * and selling decks through the {@link ManageDeck} model manager and updates the
 * corresponding GUI components via {@link MainView}.
 *
 * <p>This class also collaborates with {@code CollectionController} to fetch
 * available cards from the user's collection.</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class DeckController {

    // Properties
    private final ManageDeck manageDeck;
    private MainView mainView;
    private MenuController menuController;
    private CollectionController collectionController;
    //Methods

    /**
     * Constructs a {@code DeckController} that manages interactions between the deck view,
     * main application view, and relevant controllers.
     *
     * @param manageDeck the panel or view responsible for managing decks
     * @param mainView the main application window or frame
     * @param menuController the controller responsible for menu navigation and actions
     * @param collectionController the controller that manages the user's card collection
     */
    public DeckController(ManageDeck manageDeck, MainView mainView, MenuController menuController, CollectionController collectionController){
        this.manageDeck = manageDeck;
        this.mainView = mainView;
        this.menuController = menuController;
        this.collectionController = collectionController;
    }

    /**
     * Initializes the deck management view by populating the left-side navigation
     * panel and setting the default center panel.
     */
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

    /**
     * Attempts to create a new deck with the specified name and type.
     *
     * @param deckName the desired name of the deck
     * @param deckType the type of the deck (must match {@link DeckType})
     * @return {@code true} if the deck was created successfully, {@code false} if it already exists
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

    /**
     * Attempts to delete a deck by its name.
     *
     * @param deckName the name of the deck to be deleted
     * @return {@code true} if deletion was successful, {@code false} otherwise
     */
    public boolean deleteDeck(String deckName){
        //Checks if deck already exists
        if(manageDeck.searchDeck(deckName)!= null){ //if it exists
            manageDeck.deleteDeck(deckName);
            return  true;
        }else {
            return false;
        }
    }

    /**
     * Adds a card from the collection to the specified deck.
     *
     * @param deckName the target deck name
     * @param cardName the name of the card to add
     * @return {@code true} if the operation was successful, {@code false} otherwise
     */
    public boolean addCardToDeck(String deckName, String cardName){
        return manageDeck.addCardToDeck(cardName, deckName);
    }

    /**
     * Removes a card from the specified deck.
     *
     * @param deckName the deck to remove the card from
     * @param cardName the card to remove
     * @return {@code true} if removal succeeded, {@code false} otherwise
     */
    public boolean removeCardFromDeck(String deckName, String cardName){
        return manageDeck.removeCardFromDeck(deckName, cardName);
    }

    /**
     * Retrieves all deck names currently managed.
     *
     * @return a list of deck names
     */
    public List<String> getAllDeckNames(){
        List<String> deckNames = new ArrayList<>();

        for(Deck deck: manageDeck.getDecks()){
            deckNames.add(deck.getName());
        }

        return deckNames;
    }

    /**
     * Retrieves the names of decks that are instances of {@link DeckSellable}.
     *
     * @return a list of sellable deck names
     */
    public List<String> getAllSellableDeckNames(){
        List<String> sellableDeckNames = new ArrayList<>();

        for(Deck deck: manageDeck.getDecks()){
            if(deck instanceof DeckSellable){
                sellableDeckNames.add(deck.getName());
            }
        }

        return sellableDeckNames;
    }

    /**
     * Retrieves the names of all cards within a specific deck.
     *
     * @param deckName the name of the deck
     * @return a list of card names, or {@code null} if the deck doesn't exist
     */
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

    /**
     * Retrieves names of cards from the user's collection that have a count > 0.
     *
     * @return a list of available card names
     */
    public List<String> getCollectionCardNames(){
        List<String> collectionCardNames = new ArrayList<>();
        for(String cardName: collectionController.getAllCardNames()){
            if(collectionController.getCollection().getCard(cardName).getCount() > 0){
                collectionCardNames.add(cardName);
            }
        }
        return collectionCardNames;
    }

    /**
     * Retrieves the list of {@code Card} objects within a given deck.
     *
     * @param deckName the name of the deck
     * @return a list of {@code Card} instances in the deck
     */
    public List<Card> getCards(String deckName){
        return manageDeck.getCards(deckName);
    }

    /**
     * Attempts to sell the specified deck.
     *
     * @param deckName the name of the deck to sell
     * @return {@code true} if the deck was sold successfully, {@code false} otherwise
     */
    public boolean sellDeck(String deckName){
        return manageDeck.sellDeck(deckName);
    }
}