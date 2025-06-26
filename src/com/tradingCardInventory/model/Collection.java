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
    private List<Card> collection;

    //Methods

    /*
     * Constructs a new, empty Collection.
     */
    public Collection() {
        this.collection = new ArrayList<>(); // initialize it
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
     * Displays all cards in the collection that have a count > 0.
     *
     * Post-condition:
     * - Prints a table of card names and their counts, or a message if empty.
     */
    public void displayCollection() {
        System.out.println("──────────────────────────────────────────");
        System.out.printf("              Collection%n");
        System.out.println("──────────────────────────────────────────");

        if (this.isEmpty()) {
            System.out.println("           Collection is empty");
        } else {
            System.out.printf("%-25s %-6s%n", "Name", "Count");
            for (int i = 0; i < this.collection.size(); i++) {
                Card card = this.collection.get(i);
                if (card.getCount() > 0) {
                    System.out.printf("%-25s   %-6d%n", card.getName(), card.getCount());
                }
            }
        }

        System.out.println("─────────────────────────────────────────────");
    }

    /*
     * Displays a single card's details if it exists and has a count > 0.
     *
     * @param cardName the name of the card to display
     * @return true if the card was found and displayed, false otherwise
     */
    public boolean displayCard(String cardName){

        Card foundCard = this.searchCard(cardName);

        if(foundCard == null || foundCard.getCount() <= 0){
            return false;
        }
        else{
            System.out.println("──────────────────────────────────────────────────────────────────");
            System.out.printf("                      Viewing Card: %s\n", foundCard.getName());
            System.out.println("──────────────────────────────────────────────────────────────────");
            System.out.printf("%-25s %-12s %-12s %-10s%n", "Name", "Rarity", "Variant", "Value");
            foundCard.viewCardDetails();
            System.out.println("──────────────────────────────────────────────────────────────────");
            return true;
        }
    }

    /*
     * Increments the count of a card by 1.
     *
     * @param name the name of the card
     */
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

    /*
     * Increments the count of a card by a specific amount.
     *
     * @param name the name of the card
     * @param count the number to add
     */
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

    /*
     * Decrements the count of a card by 1, if it is greater than 0.
     *
     * @param name the name of the card
     */
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

    /*
     * Decrements the count of a card by a specific amount.
     *
     * @param name the name of the card
     * @param count the number to subtract
     */
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
