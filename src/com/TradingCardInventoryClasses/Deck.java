package com.TradingCardInventoryClasses;

public class Deck {

    // Properties / Attributes

    private String name;
    private Card[] cards; //10 cards max in 1 deck

    // Methods
    public Deck(String name){
        this.name = name;
        this.cards = new Card[10];
    }

    public void addCard(Card card){

    }

    public void removeCard(Card card){

    }

    public void viewDeck(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }
}


