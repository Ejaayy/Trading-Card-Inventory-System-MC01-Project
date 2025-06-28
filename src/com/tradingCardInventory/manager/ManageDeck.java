package com.tradingCardInventory.manager;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.model.Deck;
import java.util.ArrayList;
import java.util.List;


/*
 * ManageDeck handles operations related to managing decks such as
 * creating, deleting, viewing, and modifying deck contents using cards from a shared collection.
 */
public class ManageDeck {
    // Properties / Attributes
    private final ArrayList<Deck> decks; //since no limit
    private final Collection collection;

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
     * Searches for a specific deck by name and displays its contents.
     *
     * @param deckName the name of the deck to search for and view
     * @return the Deck object if found, otherwise returns null
     *
     * Pre-condition:
     * - The deck name should match an existing deck (case-insensitive if implemented that way)
     * Post-condition:
     * - If the deck is found, its contents are printed via viewDeck()
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

    /*
     * Displays a specific card within a given deck.
     *
     * @param cardName the name of the card to search for in the deck
     * @param deckName the Deck object to search within
     * @return true if the card was found and displayed, false otherwise
     *
     */
    public boolean viewSpecificCardinDeck(String cardName, Deck deck){

        return deck.displayCard(cardName);
    }
}