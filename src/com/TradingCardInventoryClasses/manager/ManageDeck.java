package com.TradingCardInventoryClasses.manager;

import com.TradingCardInventoryClasses.model.Binder;
import com.TradingCardInventoryClasses.model.Collection;
import com.TradingCardInventoryClasses.model.Deck;
import java.util.ArrayList;

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

    public void deleteDeck(Deck deck){

    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }
}
