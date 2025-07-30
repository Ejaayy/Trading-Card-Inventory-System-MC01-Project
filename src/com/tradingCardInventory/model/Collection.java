package com.tradingCardInventory.model;
import com.tradingCardInventory.options.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a user's card collection. Handles operations such as
 * adding, searching, displaying, and modifying card quantities.
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class Collection {

    // Properties / Attributes
    private final List<Card> collection;
    private double currentAmount = 0;

    //Methods

    /**
     * Constructs a new, empty {@code Collection}.
     */
    public Collection() {
        this.collection = new ArrayList<>(); // initialize it
    }

    /**
     * Modifies the current amount by the specified value.
     *
     * @param amount the value to add (positive or negative)
     * @return {@code true} if the resulting amount is non-negative; {@code false} otherwise
     */
    public boolean changeAmount(double amount) {
        if (this.currentAmount + amount >= 0) {
            this.currentAmount += amount;
            return true;
        }
        return false;
    }

    /**
     * Returns the current amount.
     *
     * @return the current amount
     */
    public double getAmount() {
        return this.currentAmount;
    }

    /**
     * Adds a new card to the collection based on its details.
     * Creates a new {@code Card} object and adds it to the list.
     *
     * @param cardName the name of the card
     * @param rarity   the rarity of the card
     * @param variant  the variant of the card
     * @param value    the base value of the card
     *
     * Postcondition:
     * - The new card is added and the collection is sorted alphabetically by name.
     */
    public void addCard(String cardName, Rarity rarity, Variant variant, Double value){

        //Make a new card given inputs
        Card card = new Card(cardName, rarity, variant, value);
        this.collection.add(card);

        //Says that you're comparing Card Objects via their getName methods
        this.collection.sort(Comparator.comparing(Card::getName)); // add reversed if you want it descending

    }

    /**
     * Adds an existing {@code Card} object to the collection.
     *
     * @param card the card to add
     *
     * Postcondition:
     * - The card is added and the collection is sorted by name.
     */
    public void addCard(Card card) {
        this.collection.add(card);
        this.collection.sort(Comparator.comparing(Card::getName));
    }

    /**
     * Changes the count of a card in the collection.
     * Increments or decrements based on the {@code count} parameter.
     *
     * @param name  the name of the card
     * @param count the number to add or subtract (e.g., -1 to decrease by one)
     * @return {@code true} if the change was successful; {@code false} otherwise
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

    /**
     * Searches for a card in the collection by name (case-insensitive).
     *
     * @param name the name of the card to search for
     * @return the matching {@code Card} object if found, or {@code null} if not found
     */
    public Card searchCard(String name){
        return this.collection.stream() // turn the list into a stream
                .filter(card -> card.getName().equalsIgnoreCase(name)) // keep cards that match the name
                .findFirst() // get the first matching card
                .orElse(null); // return it, or null if none found
    }

    /**
     * Checks if the collection has no cards with a positive count.
     *
     * @return {@code true} if all cards have a count of 0; {@code false} otherwise
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

    /**
     * Returns the full list of cards in the collection.
     *
     * @return a list of {@code Card} objects
     */
    public List<Card> getAllCards(){
        return this.collection;
    }

    /**
     * Retrieves a specific card by name (case-insensitive).
     *
     * @param name the name of the card
     * @return the {@code Card} object if found; {@code null} otherwise
     */
    public Card getCard(String name){
        return this.searchCard(name);
    }

}