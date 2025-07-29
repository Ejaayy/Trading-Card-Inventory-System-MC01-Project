package com.tradingCardInventory.model;
import com.tradingCardInventory.options.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Represents a user's card collection. Handles operations such as
 * adding, searching, displaying, and modifying card quantities.
 */
public class Collection {

    // Properties / Attributes
    private final List<Card> collection;
    private double currentAmount = 0;

    //Methods

    /*
     * Constructs a new, empty Collection.
     */
    public Collection() {
        this.collection = new ArrayList<>(); // initialize it
    }

    public boolean changeAmount(double amount) {
        if (this.currentAmount + amount >= 0) {
            this.currentAmount += amount;
            return true;
        }
        return false;
    }

    public double getAmount() {
        return this.currentAmount;
    }

    /*
     * Adds a new Card to the collection using card details.
     *
     * @param cardName the name of the card
     * @param rarity the rarity level of the card
     * @param variant the variant type of the card
     * @param value the base value of the card
     *
     * Post-condition:
     * - Adds a new Card and sorts the collection by card name.
     */
    public void addCard(String cardName, Rarity rarity, Variant variant, Double value){

        //Make a new card given inputs
        Card card = new Card(cardName, rarity, variant, value);
        this.collection.add(card);

        //Says that you're comparing Card Objects via their getName methods
        this.collection.sort(Comparator.comparing(Card::getName)); // add reversed if you want it descending

    }

    /*
     * Adds an existing Card object to the collection.
     *
     * @param card the Card object to add
     *
     * Post-condition:
     * - Adds and sorts the card collection.
     */
    public void addCard(Card card) {
        this.collection.add(card);
        this.collection.sort(Comparator.comparing(Card::getName));
    }

    /*
     * Increments the count of a card by a specific amount.
     *
     * @param name the name of the card
     * @param count the number to add
     */
    public boolean changeCardCount(String name, int count){

        //Searches if card exists and increments if it is
        Card foundCard = this.searchCard(name);
        if (foundCard == null){
            System.out.println("Card not found.");
            return false;
        }
        else{
            if(count==-1){ //means decrement
                //Check if foundCard count is not 0
                if(foundCard.getCount() > 0){
                    foundCard.incrementCount(count);
                    return true;
                }else{
                    return false;
                }
            }else{ //means increment
                foundCard.incrementCount(count);
                return true;
            }
        }
    }

    /*
     * Searches for a card in the collection by name (case-insensitive).
     *
     * @param name the name of the card to search
     * @return the Card object if found, otherwise null
     */
    public Card searchCard(String name){
        return this.collection.stream() // turn the list into a stream
                .filter(card -> card.getName().equalsIgnoreCase(name)) // keep cards that match the name
                .findFirst() // get the first matching card
                .orElse(null); // return it, or null if none found
    }

    /*
     * Checks if the collection is logically empty (no cards with count > 0).
     *
     * @return true if no card has count > 0, otherwise false
     */
    public boolean isEmpty() {
        for (Card card : this.collection) {
            if (card.getCount() > 0) {
                return false; // Found a card with count > 0, so it's not empty
            }
        }
        return true; // All cards had 0 count
    }

    //Getters and Setters

    public List<Card> getAllCards(){
        return this.collection;
    }


    public Card getCard(String name){
        return this.searchCard(name);
    }

}