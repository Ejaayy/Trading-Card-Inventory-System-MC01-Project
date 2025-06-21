package com.TradingCardInventoryClasses.manager;

import com.TradingCardInventoryClasses.model.Binder;
import com.TradingCardInventoryClasses.model.Card;
import com.TradingCardInventoryClasses.model.Collection;
import com.TradingCardInventoryClasses.model.Deck;
import java.util.ArrayList;
import java.util.List;

public class ManageDeck {
    // Properties / Attributes
    private ArrayList<Deck> decks; //since no limit
    private Collection collection;

    // Methods
    public ManageDeck(Collection collection){
        this.decks = new ArrayList<>();
        this.collection = collection;
    }

    public void createDeck(String name){
      Deck deck = new Deck(name);
      this.decks.add(deck);
    }

    public boolean deleteDeck(String deckName){
        Deck foundDeck = searchDeck(deckName);
        if(foundDeck == null){
            return false;
        }
        else{
            List<Card> binderContent = foundDeck.getCards();
            for(int i=0; i< binderContent.size(); i++){
                binderContent.get(i).incrementCount(1);
            }
            this.decks.remove(foundDeck);
            return true;
        }
    }

    public Deck searchDeck(String name){
        for(int i=0; i< this.decks.size(); i++){
            if(this.decks.get(i).getName().equalsIgnoreCase(name)){
                return this.decks.get(i);
            }
        }

        return null;
    }

    public boolean addCardToDeck(String cardName, String binderName) {

        //Search if Card exists in collection
        Card card = this.collection.searchCard(cardName);

        //Search if Binder exists in list
        Deck deck = this.searchDeck(binderName);

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

    public boolean removeCardFromDeck(String cardName, String deckName) {

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

        deck.removeCard(card);
        card.incrementCount(1);

        return true;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    public boolean viewSpecificDeck(String deckName){

        Deck foundDeck = searchDeck(deckName);
        if(foundDeck == null){
            return false;
        }
        else{
            foundDeck.viewDeck();
        }
        return true;
    }
}
