package com.TradingCardInventoryClasses.model;
import com.TradingCardInventoryClasses.utils.CardUtils;
import com.TradingCardInventoryClasses.options.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Collection {

    // Properties / Attributes
    private List<Card> collection;
    CardUtils cardUtils = new CardUtils();

    //Methods
    public Collection() {
        this.collection = new ArrayList<>(); // initialize it
    }

    //Main logic of adding a new Card to the collection list of cards
    public void addCard(String cardName, Rarity rarity, Variant variant, Double value){

        Card card = new Card(cardName, rarity, variant, value);
        this.collection.add(card);

        //Says that you're comparing Card Objects via their getName methods
        this.collection.sort(Comparator.comparing(Card::getName)); // add .reversed if you want it descending

    }

    //Logic for looping through the whole collection to display all Cards in it
    public void displayCollection(){

        for (int i = 0; i < this.collection.size(); i++){
            Card card = this.collection.get(i);
            cardUtils.viewCard(card);
        }

    }

    //Logic for incrementing a Card by 1
    public void increaseCardCount(String name){
        Card foundCard = cardUtils.searchCard(this.collection, name);
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
        Card foundCard = cardUtils.searchCard(this.collection, name);
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
        Card foundCard = cardUtils.searchCard(this.collection, name);
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
        Card foundCard = cardUtils.searchCard(this.collection, name);
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
        return cardUtils.searchCard(this.collection, name);
    }

    //Getters and Setters

    public List<Card> getAllCards(){
        return this.collection;
    }

    public Card getCard(String name){
        return cardUtils.searchCard(this.collection, name);
    }

}
