package com.tradingCardInventory.manager;

import com.tradingCardInventory.model.Binder;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.menu.TradeCardController;

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
    private final TradeCardController tradeCard;

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
        //Creates and adds new Binder to the list
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
        Card incomingCard = tradeCard.tradeCardMenu(this.collection.searchCard(cardName));

        if(incomingCard != null){
            //special case: if the incoming card exists in the collection
            if(this.collection.searchCard(incomingCard.getName()) != null){
                //shows the trade menu and if the user accepts the trade
                if(tradeCard.displayTradeMenu(incomingCard, this.collection.searchCard(cardName)).equals("1")){
                    //decrease outgoing card count and add incoming card to the binder
                    this.collection.changeCardCount(cardName, -1);
                    this.collection.changeCardCount(incomingCard.getName(), 1);
                    this.addCardToBinder(incomingCard.getName(), binderName);
                    System.out.println("Cards Traded! Added " + incomingCard.getName() + " to " + binderName + ".");
                } else {
                    //return outgoing card to binder
                    this.addCardToBinder(cardName, binderName);
                    System.out.println("Returning " + cardName + " to " + binderName + ".");
                }
            }else{
                //decrease outgoing card count and add incoming card to the binder
                this.collection.changeCardCount(cardName, -1);
                this.collection.addCard(incomingCard);
                this.addCardToBinder(incomingCard.getName(), binderName);
                System.out.println("Cards Traded! Added " + incomingCard.getName() + " to " + binderName + ".");
            }
        } else {
            //return outgoing card to binder
            this.addCardToBinder(cardName, binderName);
            System.out.println("Returning " + cardName + " to " + binderName + ".");
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
        //Loops through all binders
        for(int i=0; i< binders.size(); i++){
            if(binders.get(i).getName().equalsIgnoreCase(name)) return binders.get(i);
        }
        return null;
    }
}