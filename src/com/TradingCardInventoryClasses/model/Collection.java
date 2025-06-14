package com.TradingCardInventoryClasses.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.TradingCardInventoryClasses.utils.CardUtils;
import com.TradingCardInventoryClasses.options.*;

public class Collection {

    // Properties / Attributes
    private List<Card> collection;
    CardUtils cardUtils = new CardUtils();

    //Methods
    public Collection() {
        this.collection = new ArrayList<>(); // initialize it
    }

    public void addCard(String cardName, Rarity rarity, Variant variant, Double value){

            Card card = new Card(cardName, rarity, variant, value);
            this.collection.add(card);

            //Says that you're comparing Card Objects via their getName methods
            this.collection.sort(Comparator.comparing(Card::getName)); // add .reversed if you want it descending

    }

    public void displayCollection(){

        for (int i = 0; i < this.collection.size(); i++){
            Card card = this.collection.get(i);
            cardUtils.viewCard(card);
        }

    }

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

    // Method Over Riding Option
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

    // Method Over Riding Option
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

    public List<Card> getAllCards(){
        return this.collection;
    }

    public Card getCard(String name){
        return cardUtils.searchCard(this.collection, name);
    }

}
