package com.TradingCardInventoryClasses.model;

import com.TradingCardInventoryClasses.utils.CardUtils;

import java.util.ArrayList;
import java.util.List;

public class Binder {

    // Properties / Attributes

    private String name;
    private List<Card> cards; //20 cards max in 1 binder
    private CardUtils cardUtils;

    // Method
    public Binder(String name){
        //creates the space for Card objects
        this.cards = new ArrayList<>();
        this.name = name;
        this.cardUtils = new CardUtils();

        //for(int i = 0)
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

    public void viewBinder(){
        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("               Binder: %s\n", this.name);
        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("%-25s %-12s %-12s %-10s %-6s%n", "Name", "Rarity", "Variant", "Value", "Count");
        for(int i=0; i<cards.size(); i++){
            cards.get(i).viewCardDetails();
        }
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

    public int getBinderCardsSize(){
        return this.cards.size();
    }

}
