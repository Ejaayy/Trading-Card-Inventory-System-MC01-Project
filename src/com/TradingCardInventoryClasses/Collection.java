package com.TradingCardInventoryClasses;

import java.util.List;
import java.util.Scanner;

public class Collection {

    // Properties / Attributes
    private List<Card> collection;

    //Methods

    public int collectionMenuTemplate(){
        int input = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("MCO1 - Collection Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Add Card");
        System.out.println("2. Increase / Decrease Card Count");
        System.out.println("3. Display Card / Collection");
        System.out.println("4. Exit");
        System.out.print("Enter Choice: ");
        input = scanner.nextInt();
        System.out.println("\n-------------------------------------------");
        return input;
    }

    public void collectionMenu(){

        boolean running = true;
        int input = 0;
        while(running) {
            input = collectionMenuTemplate();
            switch (input) {
                case 1:
                    //Add card method
                    break;
                case 2:
                    //Increase / Decrease Card Count
                    break;
                case 3:
                    //Display Card / Collection
                    break;
                case 4:
                    running = false;
                    break;
            }
        }
    }

    public void addCard(Card card){
        //TODO
    }

    public Card getCard(String name){
        //TODO
        return collection.get(0);
    }

    public void increaseCardCount(String name){
        //TODO
    }

    public void decreaseCardCount(String name){
        //TODO
    }

    public List<Card> getAllCards(){
        return this.collection;
    }

    public List<Card> sortCardsByName(){
        //TODO
        return this.collection;
    }

}
