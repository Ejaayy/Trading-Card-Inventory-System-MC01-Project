package com.tradingCardInventory.manager;

import com.tradingCardInventory.model.Decks.DeckSellable;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.model.Decks.Deck;
import com.tradingCardInventory.options.DeckType;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ManageDeck} class provides core logic for managing decks of trading cards.
 * It supports operations such as creating decks, adding/removing cards, deleting decks,
 * and selling decks if applicable.
 *
 * <p>Decks are created based on a shared {@link Collection} instance, ensuring consistent card counts
 * across the system. This class supports both standard and {@code Sellable} decks through {@link DeckType}.</p>
 *
 * <p>Each deck has its own rules, such as preventing duplicate cards and maximum size limits.</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class ManageDeck {
    // Properties / Attributes
    private final ArrayList<Deck> decks; //since no limit
    private final Collection collection;

    // Methods

    /**
     * Constructs a {@code ManageDeck} instance linked to a shared card collection.
     *
     * @param collection the {@link Collection} from which cards are drawn and returned
     */
    public ManageDeck(Collection collection){
        this.decks = new ArrayList<>();
        this.collection = collection;
    }

    /**
     * Creates a new deck and adds it to the managed list.
     *
     * @param name the name of the deck
     * @param deckType the type of the deck (e.g., Normal or Sellable)
     */
    public void createDeck(String name, DeckType deckType){
        Deck deck = null;
        switch(deckType){
            case Normal -> deck = new Deck(name, deckType);
            case Sellable -> deck = new DeckSellable(name, deckType);
        }
        this.decks.add(deck);
    }

    /**
     * Deletes a deck by name and restores its cards back to the collection.
     *
     * @param deckName the name of the deck to delete
     * @return true if the deck existed and was deleted; false otherwise
     */
    public boolean deleteDeck(String deckName){
        Deck foundDeck = searchDeck(deckName);
        if(foundDeck == null){
            return false;
        }
        else{
            List<Card> binderContent = foundDeck.getCards();
            for (Card card : binderContent) {
                card.incrementCount(1);
            }
            this.decks.remove(foundDeck);
            return true;
        }
    }

    /**
     * Searches for a deck by its name (case-insensitive).
     *
     * @param name the name of the deck
     * @return the {@link Deck} object if found; {@code null} otherwise
     */
    public Deck searchDeck(String name){
        for (Deck deck : this.decks) {
            if (deck.getName().equalsIgnoreCase(name)) {
                return deck;
            }
        }
        return null;
    }

    /**
     * Adds a card from the collection into a specified deck.
     * The card must exist in the collection, not already exist in the deck,
     * and the deck must not be full.
     *
     * @param cardName the name of the card
     * @param deckName the name of the deck
     * @return true if the card was successfully added; false otherwise
     */
    public boolean addCardToDeck(String cardName, String deckName) {

        //Search if Card exists in collection
        Card card = this.collection.searchCard(cardName);

        //Search if Binder exists in list
        Deck deck = this.searchDeck(deckName);

        //Return false if you can't find specific card or binder
        if (card == null || deck == null) {
            return false;
        }

        //Return false if card Count is 0 or the binder is full already
        if (card.getCount() <= 0 || deck.isFull()) {
            return false;
        }

        //Checks if that card exists in that deck
        Card checkCardinDeck = deck.searchCard(cardName);

        //If it finds an existing copy in deck
        if(checkCardinDeck != null) {
            System.out.println("No duplicate cards allowed in deck");
            return false;
        }

        deck.addCard(card);
        card.incrementCount(-1); //decrement in collection because it was moved to binder
        return true;
    }

    /**
     * Removes a card from a specified deck and returns it to the collection.
     *
     * @param cardName the name of the card
     * @param deckName the name of the deck
     * @return true if the card was found and removed; false otherwise
     */
    public boolean removeCardFromDeck(String cardName, String deckName) {

        // Search if Card exists in collection
        Card card = this.collection.searchCard(cardName);

        // Search if Deck exists
        Deck deck = this.searchDeck(deckName);

        // Check for nulls before using them
        if (card == null || deck == null) {
            System.out.println("card or deck does not exist");
            return false;
        }

        //checks if the card is in the deck
        Card foundCard = deck.searchCard(cardName);
        if (foundCard == null) {
            System.out.println("card not in deck");
            return false;
        }

        deck.removeCard(card);
        card.incrementCount(1);

        return true;
    }

    /**
     * Sells a deck that implements {@link DeckSellable}.
     * Its value is added to the collection’s money, and it is removed from the system.
     *
     * @param deckName the name of the deck
     * @return true if the deck was sellable and sold; false otherwise
     */
    public boolean sellDeck(String deckName){
        DeckSellable foundDeck = (DeckSellable) searchDeck(deckName);
        if(foundDeck != null){
            this.collection.changeAmount(foundDeck.getDeckValue());
            this.decks.remove(foundDeck);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the list of all currently managed decks.
     *
     * @return list of {@link Deck} objects
     */
    public List<Deck> getDecks(){
        return this.decks;
    }

    /**
     * Retrieves all cards within a specific deck.
     *
     * @param deckName the name of the deck
     * @return list of {@link Card} objects if the deck exists; {@code null} otherwise
     */
    public List<Card> getCards(String deckName){

        Deck deck = this.searchDeck(deckName);
        if(deck != null){ //binder exists
            return deck.getCards();
        }
        return null;
    }
}