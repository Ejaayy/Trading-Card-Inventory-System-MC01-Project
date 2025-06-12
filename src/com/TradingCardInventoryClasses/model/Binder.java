package com.TradingCardInventoryClasses.model;

public class Binder {

    // Properties / Attributes

    private String name;
    private Card[] cards; //20 cards max in 1 binder

    // Method
    public Binder(String name){
        this.cards = new Card[20];
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Card[] getCards() {
        return cards;
    }

}
