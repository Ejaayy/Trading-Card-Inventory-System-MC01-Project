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

    public void manageBinderMenu(){

        boolean running = true;
        int input = 0;
        while(running) {
            input = manageBinderMenuTemplate();
            switch (input) {
                case 1:
                    //Create New Binder
                    break;
                case 2:
                    //Delete a Binder
                    break;
                case 3:
                    //Add card to a Binder
                    break;
                case 4:
                    //Remove card from a Binder
                    break;
                case 5:
                    //Trade Card
                    break;
                case 6:
                    //View Binder
                    break;
                case 7:
                    //exit
                    running = false;
                    break;
            }
        }
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
