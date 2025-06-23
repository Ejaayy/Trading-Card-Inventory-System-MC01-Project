package com.TradingCardInventoryClasses.model;

import java.util.ArrayList;
import java.util.Comparator;
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
    }
    public void addCard(Card card){
        this.cards.add(card);
        this.cards.sort(Comparator.comparing(Card::getName));
    }

    public void removeCard(Card card){
        this.cards.remove(card);
    }

    public boolean isFull(){
        return this.cards.size() >= 20;
    }

    public void viewBinder(){
        System.out.println("──────────────────────────────────────────────────────────────────");
        System.out.printf("                       Binder: %s\n", this.name);
        System.out.println("──────────────────────────────────────────────────────────────────");
        if(this.cards.size()>0){
            System.out.printf("%-25s %-12s %-12s %-10s%n", "Name", "Rarity", "Variant", "Value");
            for(int i=0; i<cards.size(); i++){
                cards.get(i).viewCardDetails();
            }
        }
        else{
            System.out.println("                  THIS BINDER IS EMPTY");
        }
        System.out.println("──────────────────────────────────────────────────────────────────");
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
