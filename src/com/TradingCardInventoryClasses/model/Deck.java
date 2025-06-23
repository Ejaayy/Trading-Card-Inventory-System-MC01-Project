package com.TradingCardInventoryClasses.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    // Properties / Attributes
    private String name;
    private List<Card> cards; //10 cards max in 1 deck

    // Methods
    public Deck(String name){
        this.name = name;
        this.cards = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public void removeCard(Card card){
        this.cards.remove(card);
    }

    public boolean isFull(){
        return this.cards.size() >= 20;
    }

    public void viewDeck(){
        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("                 Deck: %s\n", this.name);
        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("%-25s %-12s %-12s %-10s %-6s%n", "Name", "Rarity", "Variant", "Value", "Count");
        for(int i=0; i<cards.size(); i++){
            cards.get(i).viewCardDetails();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}


