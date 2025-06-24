package com.tradingCardInventory.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Binder {

    // Properties / Attributes

    private String name;
    private List<Card> cards; //20 cards max in 1 binder

    // Method

    /*
     * Constructs a Binder with the specified name.
     *
     * @param name the name of the binder
     *
     * Pre-condition:
     * - Name should be a valid string.
     * Post-condition:
     * - A binder is initialized with an empty list of cards.
     */
    public Binder(String name){
        //creates the space for Card objects
        this.cards = new ArrayList<>();
        this.name = name;
    }

    /*
     * Adds a card to the binder and sorts the binder alphabetically by card name.
     *
     * @param card the Card object to add
     *
     * Pre-condition:
     * - card is a valid Card object (not null)
     * Post-condition:
     * - card is added to the list and the list is sorted
     */
    public void addCard(Card card){
        this.cards.add(card);
        this.cards.sort(Comparator.comparing(Card::getName));
    }

    /*
     * Removes the specified card from the binder.
     *
     * @param card the Card object to remove
     *
     * Pre-condition:
     * - card exists in the list
     * Post-condition:
     * - card is removed from the binder
     */
    public void removeCard(Card card){
        this.cards.remove(card);
    }

    /*
     * Checks if the binder has reached its maximum capacity.
     *
     * @return true if the binder has 20 or more cards, false otherwise
     */
    public boolean isFull(){
        return this.cards.size() >= 20;
    }

    /*
     * Displays the contents of the binder, including card details or a message if it's empty.
     *
     * Pre-condition:
     * - cards must be viewable using viewCardDetails()
     * Post-condition:
     * - Binder information is printed to the console
     */
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

    // GETTERS AND SETTERS

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
