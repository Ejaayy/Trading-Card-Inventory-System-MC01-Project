package com.tradingCardInventory.manager;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.model.Deck;
import java.util.ArrayList;
import java.util.List;

public class ManageDeck {
    // Properties / Attributes
    private ArrayList<Deck> decks; //since no limit
    private Collection collection;

    // Methods

    /*
     * Constructor that initializes the deck list and sets the collection reference.
     *
     * @param collection the shared Collection object
     */
    public ManageDeck(Collection collection){
        this.decks = new ArrayList<>();
        this.collection = collection;
    }

    /*
     * Creates and adds a new deck with the given name.
     *
     * @param name the name of the new deck
     */
    public void createDeck(String name){
      Deck deck = new Deck(name);
      this.decks.add(deck);
    }

    /*
     * Deletes a deck and returns all its cards back to the collection.
     *
     * @param deckName the name of the deck to be deleted
     * @return true if deleted successfully, false otherwise
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

    /*
     * Searches for a deck by name (case-insensitive).
     *
     * @param name the name of the deck
     * @return the matching Deck object, or null if not found
     */
    public Deck searchDeck(String name){
        for (Deck deck : this.decks) {
            if (deck.getName().equalsIgnoreCase(name)) {
                return deck;
            }
        }
        return null;
    }

    /*
     * Adds a card from the collection into a deck.
     * Reduces the count of the card in the collection.
     *
     * @param cardName the card to add
     * @param deckName the deck to add the card to
     * @return true if successful, false if card or deck is invalid or full
     */
    public boolean addCardToDeck(String cardName, String deckName) {

        //Search if Card exists in collection
        Card card = this.collection.searchCard(cardName);

        //Search if Binder exists in list
        Deck deck = this.searchDeck(deckName);

        //Return false if can't find specific card or binder
        if (card == null || deck == null) {
            return false;
        }

        //Return false if card Count is 0 or the binder is full already
        if (card.getCount() <= 0 || deck.isFull()) {
            return false;
        }

        deck.addCard(card);
        card.incrementCount(-1); //decrement in collection because it was moved to binder
        return true;
    }

    /*
     * Removes a card from a deck and returns it to the collection.
     *
     * @param cardName the card to remove
     * @param deckName the deck to remove the card from
     * @return true if successful, false if not valid or conditions unmet
     */
    public boolean removeCardFromDeck(String cardName, String deckName) {

        // Search if Card exists in collection
        Card card = this.collection.searchCard(cardName);

        // Search if Deck exists
        Deck deck = this.searchDeck(deckName);

        // Check for nulls before using them
        if (card == null || deck == null) {
            return false;
        }

        //checks if the card is in the deck
        Card foundCard = deck.searchCard(cardName);
        if (foundCard == null) {
            return false;
        }

        deck.removeCard(card);
        card.incrementCount(1);

        return true;
    }

    /*
     * Gets the list of all current decks.
     *
     * @return the deck list
     */
    public ArrayList<Deck> getDecks() {
        return decks;
    }

    /*
     * Displays the contents of a specific deck, if it exists.
     *
     * @param deckName name of the deck to view
     * @return true if deck was found and shown, false otherwise
     */
    public Deck viewSpecificDeck(String deckName){

        Deck foundDeck = searchDeck(deckName);
        if(foundDeck == null){
            return foundDeck;
        }
        else{
            foundDeck.viewDeck();
        }
        return foundDeck;
    }

    public boolean viewSpecificCardinDeck(String cardName, Deck deckName){

        if(deckName.displayCard(cardName)){
            return true;
        }
        else{
            return false;
        }
    }

    //SETTERS AND GETTERS
    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }
}
