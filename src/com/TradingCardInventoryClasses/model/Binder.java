package com.TradingCardInventoryClasses.model;

import java.util.ArrayList;
import java.util.List;

public class Binder {

    // Properties / Attributes

    private String name;
    private List<Card> cards; //20 cards max in 1 binder

    // Method
    public Binder(String name){
        //creates the space for Card objects
        this.cards = new ArrayList<>();
        this.name = name;

        //for(int i = 0)
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

}
