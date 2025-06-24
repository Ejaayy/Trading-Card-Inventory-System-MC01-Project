package com.tradingCardInventory.manager;

import com.tradingCardInventory.model.Binder;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ManageBinders {

    // Properties / Attributes
    private List<Binder> binders;
    private Collection collection;

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
            for(int i=0; i< binderContent.size(); i++){
                binderContent.get(i).incrementCount(1);
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

        //Search if Card exists in collection
        Card card = this.collection.searchCard(cardName);

        //Search if Binder exists in list
        Binder binder = this.searchBinder(binderName);

        //Return false if can't find specific card or binder
        if (card == null || binder == null) {
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
        Scanner scanner = new Scanner(System.in);

        //Input Card Name
        System.out.print("Enter Card name: ");
        scanner.nextLine(); //Input buffer
        String name = scanner.nextLine();

        //Search loop to find if the card exists already
        Card foundCard = this.collection.searchCard(name);

        if (foundCard != null) {
            System.out.println("Card already Exists! Would you like to increase the card count? [Y/N]: ");
            String response = scanner.nextLine();
            if (response.equals("Y")) {
                foundCard.incrementCount(1);
            }
        } else {
            // Input Rarity
            Rarity rarity = null;
            while (rarity == null) {
                System.out.print("Enter Rarity (common, uncommon, rare, legendary): ");
                String rarityInput = scanner.nextLine().trim().toUpperCase();
                try {
                    rarity = Rarity.valueOf(rarityInput);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid rarity. Try again.");
                }
            }

            // Input Variant (only if rare or legendary)
            Variant variant = Variant.NORMAL;  // default
            if (rarity == Rarity.RARE || rarity == Rarity.LEGENDARY) {
                while (true) {
                    System.out.print("Enter Variant (normal, extended_art, full_art, alt_art): ");
                    String variantInput = scanner.nextLine().trim().toUpperCase();
                    try {
                        variant = Variant.valueOf(variantInput);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid variant. Try again.");
                    }
                }
            }

            //Input Value
            System.out.print("Enter Value: ");
            double value = scanner.nextDouble();

            Card incomingCard = new Card(name, rarity, variant, value);

            System.out.println("Trade Menu");
            System.out.println("───────────────────────────────");
            System.out.println("Incoming Card");
            System.out.println("───────────────────────────────");
            System.out.printf("Name  : %s%n", incomingCard.getName());
            System.out.printf("Value : $%.2f%n", incomingCard.getActualValue());
            System.out.println("───────────────────────────────");
            System.out.println("Outgoing Card");
            System.out.println("───────────────────────────────");
            System.out.printf("Name  : %s%n", this.collection.searchCard(cardName).getName());
            System.out.printf("Value : $%.2f%n", this.collection.searchCard(cardName).getActualValue());
            System.out.println("───────────────────────────────\n");

            if(incomingCard.getActualValue() - this.collection.searchCard(cardName).getActualValue() > -1 && incomingCard.getActualValue() - this.collection.searchCard(cardName).getActualValue() < 1){
                System.out.println("Value difference is less than $1.");
            }

            System.out.println("Proceed with the trade?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Input: ");

            if(scanner.nextInt() == 1){
                this.collection.decreaseCardCount(cardName, 1);
                this.collection.addCard(incomingCard);
                this.addCardToBinder(incomingCard.getName(), binderName);
            } else {
                this.addCardToBinder(cardName, binderName);
            }
        }
    }

    /*
     * Dummy placeholder for future card comparison feature.
     * Currently just returns the first card.
     */
    public Card compareCard(Card card1, Card card2){
        return card1;
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
            if(binders.get(i).getName().equalsIgnoreCase(name)){
                return binders.get(i);
            }
        }

        return null;
    }
}
