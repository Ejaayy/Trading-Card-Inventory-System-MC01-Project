package com.tradingCardInventory.manager;

import com.tradingCardInventory.model.Binders.*;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.options.BinderType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The {@code ManageBinders} class handles all logic related to the management of binders,
 * including creation, deletion, card transfers, and selling of binders. It interacts with a shared
 * {@link Collection} instance to ensure synchronization of card counts and binder contents.
 *
 * <p>Supports different {@link BinderType}s and works with various binder implementations like
 * {@code NonCuratedBinder}, {@code RaresBinder}, {@code LuxuryBinder}, etc.</p>
 *
 * <p>Follows core principles of encapsulation and single responsibility.</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class ManageBinders {

    // Properties / Attributes
    private final List<Binder> binders;
    private final Collection collection;

    // Methods

    /**
     * Constructs a {@code ManageBinders} instance with a given collection.
     *
     * @param collection the shared {@code Collection} used for card availability and updates
     */
    public ManageBinders(Collection collection){
        this.binders = new ArrayList<>();
        this.collection = collection;
    }

    /**
     * Creates a new binder of the specified type and adds it to the binder list.
     *
     * @param name the name of the binder
     * @param binderType the type of binder to create
     */
    public void createBinder(String name, BinderType binderType){
        Binder binder = null;
        //Creates and adds new Binder to the list
        switch(binderType){
            case NonCurated -> binder = new NonCuratedBinder(name, binderType);
            case Pauper -> binder = new PauperBinder(name, binderType);
            case Luxury -> binder = new LuxuryBinder(name, binderType);
            case Rares -> binder = new RaresBinder(name, binderType);
            case Collector ->  binder = new CollectorBinder(name, binderType);
        }
        this.binders.add(binder);
    }

    /**
     * Deletes an existing binder by name and returns all its cards back to the collection.
     *
     * @param binderName the name of the binder to delete
     * @return true if the binder was found and deleted; false otherwise
     */
    public boolean deleteBinder(String binderName){
        //Checks if binder exists
        Binder foundBinder = searchBinder(binderName);
        if(foundBinder == null){
            return false;
        }
        else{
            //Loops through every card in binder
            List<Card> binderContent = foundBinder.getCards();
            for (Card card : binderContent) {
                card.incrementCount(1);
            }
            this.binders.remove(foundBinder);

            //Sort the collection again
            collection.getAllCards().sort(Comparator.comparing(Card::getName));
            return true;
        }

    }

    /**
     * Adds a card from the collection to a specified binder.
     * Decreases the card count in the collection if successful.
     *
     * @param cardName the name of the card to add
     * @param binderName the name of the binder to add the card into
     * @return true if the operation was successful; false otherwise
     */
    public boolean addCardToBinder(String cardName, String binderName) {

        //Search if Card exists in collection
        Card card = this.collection.searchCard(cardName);

        //Search if Binder exists in list
        Binder binder = this.searchBinder(binderName);

        //Return false if can't find specific card or binder
        if (card == null || binder == null) {
            return false;
        }

        //Return false if card Count is 0 or the binder is full already
        if (card.getCount() <= 0 || binder.isFull()) {
            return false;
        }

        //Check if Binder allows that specific card

        if(binder.addCard(card)) {
            card.incrementCount(-1); //decrement in collection because it was moved to binder
            return true;
        }
        return false;
    }

    /**
     * Removes a card from a binder and returns it to the collection.
     * Increases the card count in the collection.
     *
     * @param cardName the name of the card to remove
     * @param binderName the name of the binder to remove the card from
     * @return true if the card was found and removed; false otherwise
     */
    public boolean removeCardFromBinder(String cardName, String binderName) {

        // Search if Card exists in collection
        Card card = this.collection.searchCard(cardName);

        // Search if Binder exists
        Binder binder = this.searchBinder(binderName);

        // Check for nulls before using them
        if (card == null || binder == null) {
            return false;
        }

        //Finds Card
        Card foundCard = binder.searchCard(cardName);

        if (foundCard == null) {
            return false;
        }

        //Removes that specific card
        binder.removeCard(card);
        card.incrementCount(1);

        return true;
    }

    /**
     * Searches for a binder by name (case-insensitive).
     *
     * @param name the name of the binder to search for
     * @return the {@code Binder} object if found; {@code null} otherwise
     */
    public Binder searchBinder(String name){
        //Loops through all binders
        for (Binder binder : binders) {
            if (binder.getName().equalsIgnoreCase(name)) return binder;
        }
        return null;
    }

    /**
     * Sells a binder that implements the {@code SellableBinder} interface.
     * Adds the binder's value to the collection's monetary amount and removes it from the list.
     *
     * @param binderName the name of the binder to sell
     * @return true if the binder was found and sold; false otherwise
     */
    public boolean sellBinder(String binderName){
        //typecast returned binder into SellableBinder
        SellableBinder foundBinder = (SellableBinder) searchBinder(binderName);
        if(foundBinder != null){
            this.collection.changeAmount(foundBinder.getBinderValue());
            System.out.println(foundBinder.getBinderValue());
            this.binders.remove(foundBinder);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the list of all binders currently managed.
     *
     * @return list of all {@code Binder} instances
     */
    public List<Binder> getBinders(){
        return this.binders;
    }

    /**
     * Retrieves all cards contained within a specified binder.
     *
     * @param binderName the name of the binder
     * @return list of {@code Card} objects in the binder; {@code null} if binder is not found
     */
    public List<Card> getCards(String binderName){

        Binder binder = this.searchBinder(binderName);
        if(binder != null){ //binder exists
            return binder.getCards();
        }
        return null;
    }
}