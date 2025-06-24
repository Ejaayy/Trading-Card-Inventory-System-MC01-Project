package com.tradingCardInventory.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    // Properties / Attributes
    private String name;
    private List<Card> cards; //10 cards max in 1 deck

    // Methods

    /*
     * Constructs a Deck with the given name.
     *
     * @param name the name of the deck
     *
     * Pre-condition:
     * - 'name' must be a valid, non-null string
     * Post-condition:
     * - An empty deck with the given name is initialized
     */
    public Deck(String name){
        this.name = name;
        this.cards = new ArrayList<>();
    }

    /*
     * Adds a Card to the deck.
     *
     * @param card the Card object to add
     *
     * Pre-condition:
     * - 'card' must not be null
     * Post-condition:
     * - Card is added to the deck's list
     */
    public void addCard(Card card){
        this.cards.add(card);
    }

    /*
     * Removes a Card from the deck.
     *
     * @param card the Card object to remove
     *
     * Pre-condition:
     * - 'card' must exist in the deck
     * Post-condition:
     * - Card is removed from the list
     */
    public void removeCard(Card card){
        this.cards.remove(card);
    }

    /*
     * Checks if the deck has reached its maximum capacity.
     *
     * @return true if the deck has 20 or more cards, false otherwise
     */
    public boolean isFull(){
        return this.cards.size() >= 20;
    }

    /*
     * Displays the contents of the deck, including card details,
     * or a message if the deck is empty.
     *
     * Pre-condition:
     * - Cards must be viewable using viewCardDetails()
     * Post-condition:
     * - Deck information is printed to the console
     */
    public void viewDeck(){
        System.out.println("──────────────────────────────────────────────────────────────────");
        System.out.printf("                 Deck: %s\n", this.name);
        System.out.println("──────────────────────────────────────────────────────────────────");
        if(this.cards.size()>0){
            System.out.printf("%-25s %-12s %-12s %-10s%n", "Name", "Rarity", "Variant", "Value");
            for(int i=0; i<cards.size(); i++){
                cards.get(i).viewCardDetails();
            }
        }
        else{
            System.out.println("                  THIS DECK IS EMPTY");
        }
        System.out.println("──────────────────────────────────────────────────────────────────");
    }

    // GETTERS AND SETTERS

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


