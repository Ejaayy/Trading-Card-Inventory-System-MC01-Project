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

    public void addCard(Card card){
        //Add card from collection
        //remove from collection
        //add to list in binders
    }

    public int getCount(){
        return this.binders.size();
    }

    public void tradeCard(Card outgoing, Card incoming){

    }

    public Card compareCard(Card card1, Card card2){
        return card1;
    }

    public void viewBinder(){

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
