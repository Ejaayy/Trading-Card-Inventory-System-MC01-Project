package com.tradingCardInventory.manager;

import com.tradingCardInventory.model.Binder;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.menu.TradeCardController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ManageBinders {

    // Properties / Attributes
    private List<Binder> binders;
    private Collection collection;
    private TradeCardController tradeCard;

    // Methods

    /*
     * Constructor that initializes the binders list and binds the collection reference.
     *
     * @param collection reference to the shared Collection object
     */
    public ManageBinders(Collection collection){
        this.binders = new ArrayList<>();
        this.collection = collection;
        this.tradeCard = new TradeCardController(collection);
    }

    /*
     * Creates a new binder and adds it to the list.
     *
     * @param name the name of the new binder
     */
    public void createBinder(String name){
        Binder binder = new Binder(name);
        this.binders.add(binder);
    }

    /*
     * Deletes an existing binder and restores all its card counts back to the collection.
     *
     * @param binderName name of the binder to delete
     * @return true if deleted successfully, false if not found
     */
    public boolean deleteBinder(String binderName){
        Binder foundBinder = searchBinder(binderName);
        if(foundBinder == null){
            return false;
        }
        else{
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

        binder.addCard(card);
        card.incrementCount(-1); //decrement in collection because it was moved to binder
        return true;
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

        Card foundCard = binder.searchCard(cardName);

        if (foundCard == null) {
            return false;
        }

        binder.removeCard(card);
        card.incrementCount(1);

        return true;
    }

    /*
     * Gets the total number of binders.
     *
     * @return size of the binders list
     */
    public int getCount(){
        return this.binders.size();
    }


    /*
     * Facilitates a card trade from a binder. Adds a new card to the collection
     * and removes the traded one if values are within $1 range.
     *
     * @param cardName   name of the outgoing card
     * @param binderName name of the binder involved
     */
    public void tradeCard(String cardName, String binderName) {
        //runs the trade card menu and assigns the return value to incoming card
        //if incoming card exists, a trade was confirmed and incoming card will be the new card
        //if incoming card is null/doesn't exist, then the trade was rejected and the old card should be kept
        Card incomingCard = tradeCard.tradeCardMenu(cardName);
        if(incomingCard != null){
            this.collection.decreaseCardCount(cardName, 1);
            this.collection.addCard(incomingCard);
            this.addCardToBinder(incomingCard.getName(), binderName);
        } else {
            this.addCardToBinder(cardName, binderName);
        }
    }

    /*
     * Displays a specific binder if it exists.
     *
     * @param binderName name of the binder to view
     * @return true if the binder was found and viewed, false otherwise
     */
    public boolean viewSpecificBinder(String binderName){

        Binder foundBinder = searchBinder(binderName);
        if(foundBinder == null){
            return false;
        }
        else{
            foundBinder.viewBinder();
        }
        return true;
    }

    /*
     * Searches for a binder in the list by name (case-insensitive).
     *
     * @param name name of the binder
     * @return the Binder object if found, null otherwise
     */
    public Binder searchBinder(String name){
        for(int i=0; i< binders.size(); i++){
            if(binders.get(i).getName().equalsIgnoreCase(name)) return binders.get(i);
        }

        return null;
    }

}
