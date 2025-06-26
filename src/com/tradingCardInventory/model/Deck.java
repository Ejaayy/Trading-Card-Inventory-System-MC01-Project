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
        System.out.println("─────────────────────────────────");
        System.out.printf("             Deck%n");
        System.out.println("─────────────────────────────────");

        if (this.cards.isEmpty()) {
            System.out.println("          Deck is empty");
        } else {
            System.out.printf("%-25s%n", "Name");
            for (int i = 0; i < this.cards.size(); i++) {
                Card card = this.cards.get(i);
                card.viewCardName();
                System.out.println();
            }
        }

        System.out.println("──────────────────────────────────");
    }

    /*
     * Displays a single card's details if it exists and has a count > 0.
     *
     * @param cardName the name of the card to display
     * @return true if the card was found and displayed, false otherwise
     */
    public boolean displayCard(String cardName){

        Card foundCard = this.searchCard(cardName);

        if(foundCard == null){
            return false;
        }
        else{
            System.out.println("──────────────────────────────────────────────────────────────────");
            System.out.printf("                      Viewing Card: %s\n", foundCard.getName());
            System.out.println("──────────────────────────────────────────────────────────────────");
            System.out.printf("%-25s %-12s %-12s %-10s%n", "Name", "Rarity", "Variant", "Value");
            foundCard.viewCardDetails();
            System.out.println("──────────────────────────────────────────────────────────────────");
        }
        return true;
    }

    public Card searchCard(String name){
        return this.cards.stream() // turn the list into a stream
                .filter(card -> card.getName().equalsIgnoreCase(name)) // keep cards that match the name
                .findFirst() // get the first matching card
                .orElse(null); // return it, or null if none found
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


