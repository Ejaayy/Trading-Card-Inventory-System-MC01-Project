package com.TradingCardInventoryClasses.model;
import com.TradingCardInventoryClasses.options.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Collection {

    // Properties / Attributes
    private List<Card> collection;

    //Methods
    public Collection() {
        this.collection = new ArrayList<>(); // initialize it
    }

    //Main logic of adding a new Card to the collection list of cards
    public void addCard(String cardName, Rarity rarity, Variant variant, Double value){

        Card card = new Card(cardName, rarity, variant, value);
        this.collection.add(card);

        //Says that you're comparing Card Objects via their getName methods
        this.collection.sort(Comparator.comparing(Card::getName)); // add reversed if you want it descending

    }

    //Method override from adding Card from Binders and Deck
    public void addCard(Card card) {
        this.collection.add(card);
        this.collection.sort(Comparator.comparing(Card::getName));
    }

    //Logic for looping through the whole collection to display all Cards in it
    public void displayCollection(){
        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("                    Collection\n");
        System.out.println("────────────────────────────────────────────────────");
        System.out.printf("%-25s %-6s%n", "Name", "Count");
        for (int i = 0; i < this.collection.size(); i++){
            Card card = this.collection.get(i);
            card.viewCardAndCount();
        }
        System.out.println("────────────────────────────────────────────────────");

    }

    public boolean displayCard(String cardName){

        Card foundCard = this.searchCard(cardName);

        if(foundCard == null){
            return false;
        }
        else{
            foundCard.viewCardDetails();
            return true;
        }
    }


    //Logic for incrementing a Card by 1
    public void increaseCardCount(String name){
        Card foundCard = this.searchCard(name);
        if (foundCard == null){
            System.out.println("Card not found.");
        }
        else{
            foundCard.incrementCount(1);
            System.out.println("Increment Successful!\n");
        }
    }

    // Method Over Riding Option for incrementing a Card by more than 1
    public void increaseCardCount(String name, int count){
        Card foundCard = this.searchCard(name);
        if (foundCard == null){
            System.out.println("Card not found.");
        }
        else{
            foundCard.incrementCount(count);
            System.out.println("Increment Successful!\n");
        }
    }

    //Logic for decrementing a Card by 1
    public void decreaseCardCount(String name){
        Card foundCard = this.searchCard(name);
        if (foundCard == null){
            System.out.println("Card not found.");
        }
        else{
            if(foundCard.getCount() > 0){
                foundCard.incrementCount(-1);
                System.out.println();
            }
        }
    }

    // Method Over Riding Option for decrementing a Card by more than 1
    public void decreaseCardCount(String name, int count){
        Card foundCard = this.searchCard(name);
        if (foundCard == null){
            System.out.println("Card not found.");
            System.out.println();
        }
        else{
            if(foundCard.getCount() >= count){
                foundCard.incrementCount(-count);
            }
        }
    }

    //Method for looping through the whole collection and find a Card
    public Card searchCard(String name){
        return this.collection.stream() // turn the list into a stream
                .filter(card -> card.getName().equalsIgnoreCase(name)) // keep cards that match the name
                .findFirst() // get the first matching card
                .orElse(null); // return it, or null if none found
    }

    //Getters and Setters

    public List<Card> getAllCards(){
        return this.collection;
    }

    public Card getCard(String name){
        return this.searchCard(name);
    }

}
