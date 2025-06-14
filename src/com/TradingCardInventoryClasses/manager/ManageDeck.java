package com.TradingCardInventoryClasses.manager;

import com.TradingCardInventoryClasses.model.Deck;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageDeck {
    // Properties / Attributes
    private ArrayList<Deck> decks; //since no limit

    // Methods
    public ManageDeck(){
        this.decks = new ArrayList<>();
    }

    public void createDeck(){

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
