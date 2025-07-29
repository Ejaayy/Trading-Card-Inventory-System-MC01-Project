package com.tradingCardInventory.manager;

import com.tradingCardInventory.model.Binders.*;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.options.BinderType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * ManageBinders handles all operations related to binder management,
 * including creating, deleting, modifying binders, and managing cards inside them.
 * It interacts with the shared Collection object and supports trading cards as well.
 */
public class ManageBinders {

    // Properties / Attributes
    private final List<Binder> binders;
    private final Collection collection;

    // Methods

    /*
     * Constructor that initializes the binders list and binds the collection reference.
     *
     * @param collection reference to the shared Collection object
     */
    public ManageBinders(Collection collection){
        this.binders = new ArrayList<>();
        this.collection = collection;
    }

    /*
     * Creates a new binder and adds it to the list.
     *
     * @param name the name of the new binder
     *
     * Notes:
     * Binder Constructors:
     * Non-curated Binder: NonCuratedBinder()
     * Pauper Binder:      PauperBinder()
     * Rares Binder:       RaresBinder()
     * Luxury Binder:      LuxuryBinder()
     * Collector Binder:   CollectorBinder()
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

    /*
     * Deletes an existing binder and restores all its card counts back to the collection.
     *
     * @param binderName name of the binder to delete
     * @return true if deleted successfully, false if not found
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

    /*
     * Transfers a card from the collection into a binder.
     * Decreases card count in collection.
     *
     * @param cardName   name of the card to move
     * @param binderName target binder name
     * @return true if successful, false if not possible
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

    /*
     * Removes a card from a binder and returns it to the collection (increases count).
     *
     * @param cardName   name of the card to remove
     * @param binderName name of the binder
     * @return true if operation was successful, false otherwise
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

    /*
     * Searches for a binder in the list by name (case-insensitive).
     *
     * @param name name of the binder
     * @return the Binder object if found, null otherwise
     */
    public Binder searchBinder(String name){
        //Loops through all binders
        for (Binder binder : binders) {
            if (binder.getName().equalsIgnoreCase(name)) return binder;
        }
        return null;
    }

    /*
     * Sells the binder with the given name
     *
     * @param name name of the binder
     * Preconditions: This function will not be called on non-sellable binders
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

    public List<Binder> getBinders(){
        return this.binders;
    }

    public List<Card> getCards(String binderName){

        Binder binder = this.searchBinder(binderName);
        if(binder != null){ //binder exists
            return binder.getCards();
        }
        return null;
    }
}