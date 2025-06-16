package com.TradingCardInventoryClasses.manager;

import com.TradingCardInventoryClasses.model.Binder;
import com.TradingCardInventoryClasses.model.Card;
import com.TradingCardInventoryClasses.model.Collection;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteBinder(String binderName){
        Binder foundBinder = searchBinder(binderName);
        if(foundBinder == null){
            System.out.println("Binder not found");
        }
        else{
            List<Card> binderContent = foundBinder.getCards();
            for(int i=0; i< binderContent.size(); i++){
                collection.addCard(binderContent.get(i));
            }
            this.binders.remove(foundBinder);
        }

    }

    public boolean addCardFromBinder(String cardName, String binderName) {

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

        //Return false if card Count is 0 or the binder is full already
        if (card.getCount() <= 0 || binder.isFull()) {
            return false;
        }

        binder.removeCard(card);
        card.incrementCount(1);

        return true;
    }


    public int getCount(){
        return this.binders.size();
    }

    public void tradeCard(Card outgoing, Card incoming){

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
