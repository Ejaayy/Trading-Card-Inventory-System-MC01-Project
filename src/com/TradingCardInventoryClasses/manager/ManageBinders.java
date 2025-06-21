package com.TradingCardInventoryClasses.manager;

import com.TradingCardInventoryClasses.model.Binder;
import com.TradingCardInventoryClasses.model.Card;
import com.TradingCardInventoryClasses.model.Collection;
import com.TradingCardInventoryClasses.options.Rarity;
import com.TradingCardInventoryClasses.options.Variant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageBinders {

    // Properties / Attributes
    private List<Binder> binders;
    private Collection collection;
    // Methods

    public ManageBinders(Collection collection){
        this.binders = new ArrayList<>();
        this.collection = collection;
    }

    public void createBinder(String name){
        Binder binder = new Binder(name);
        this.binders.add(binder);
    }

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
            return true;
        }

    }

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


    public int getCount(){
        return this.binders.size();
    }


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

    public Card compareCard(Card card1, Card card2){
        return card1;
    }

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


    public Binder searchBinder(String name){
        for(int i=0; i< binders.size(); i++){
            if(binders.get(i).getName().equalsIgnoreCase(name)){
                return binders.get(i);
            }
        }

        return null;
    }
}
