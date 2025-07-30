package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The {@code Binder} class serves as the abstract base class for different types of card binders.
 * Each binder can contain up to 20 cards and supports basic operations like adding,
 * removing, and searching for cards. Subclasses can enforce additional rules for
 * card insertion based on binder type (e.g., rarity restrictions).
 *
 * <p>Binders are automatically sorted alphabetically by card name when a card is added.</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public abstract class Binder {

    // Properties / Attributes

    protected final String name;
    protected final BinderType type;
    protected final List<Card> cards; //20 cards max in 1 binder

    // Method

    /**
     * Constructs a new {@code Binder} with the given name and type.
     *
     * @param name       the name of the binder
     * @param binderType the {@link BinderType} representing the type of this binder
     */
    public Binder(String name, BinderType binderType) {
        //creates the space for Card objects
        this.cards = new ArrayList<>();
        this.type = binderType;
        this.name = name;
    }

    /**
     * Adds a card to the binder and sorts the list alphabetically by card name.
     *
     * @param card the {@link Card} to be added
     * @return {@code true} if the card was added successfully
     *
     * <p>Preconditions:</p>
     * <ul>
     *   <li>The card is not null</li>
     * </ul>
     *
     * <p>Postconditions:</p>
     * <ul>
     *   <li>The card is added to the binder</li>
     *   <li>The card list is sorted by name</li>
     * </ul>
     */
    public boolean addCard(Card card){
        this.cards.add(card);
        this.cards.sort(Comparator.comparing(Card::getName));
        return true;
    }


    /**
     * Removes the specified card from the binder.
     *
     * @param card the {@link Card} to remove from the binder
     *
     * <p>Preconditions:</p>
     * <ul>
     *   <li>The card exists in the binder</li>
     * </ul>
     *
     * <p>Postconditions:</p>
     * <ul>
     *   <li>The card is removed from the binder</li>
     * </ul>
     */
    public void removeCard(Card card){
        this.cards.remove(card);
    }

    /**
     * Checks whether the binder has reached its maximum capacity of 20 cards.
     *
     * @return {@code true} if the binder is full, {@code false} otherwise
     */
    public boolean isFull(){
        return this.cards.size() >= 20;
    }

    /**
     * Searches for a card in the binder by name (case-insensitive).
     *
     * @param name the name of the card to search for
     * @return the {@link Card} if found; {@code null} otherwise
     */
    public Card searchCard(String name){
        return this.cards.stream() // turn the list into a stream
                .filter(card -> card.getName().equalsIgnoreCase(name)) // keep cards that match the name
                .findFirst() // get the first matching card
                .orElse(null); // return it, or null if none found
    }

    /**
     * Returns the name of the binder.
     *
     * @return the binder's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of cards currently in the binder.
     *
     * @return a {@link List} of {@link Card} objects
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Returns the type of the binder as a string.
     *
     * @return the binder's type
     */
    public String getType() {
        return type.toString();
    }

}