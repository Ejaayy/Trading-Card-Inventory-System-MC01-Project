package com.TradingCardInventoryClasses.manager;

import com.TradingCardInventoryClasses.model.Binder;
import com.TradingCardInventoryClasses.model.Card;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageBinder {

    // Properties / Attributes
    private ArrayList<Binder> binders;

    // Methods

    public ManageBinder(){
        this.binders = new ArrayList<>();
    }

    public void createBinder(){

        System.out.print("Input Binder Name: ");
    }

    public void deleteBinder(Binder binder){

    }

    public void addCard(Card card){

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
}
