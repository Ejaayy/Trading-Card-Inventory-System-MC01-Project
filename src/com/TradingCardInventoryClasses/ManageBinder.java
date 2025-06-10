package com.TradingCardInventoryClasses;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageBinder {

    // Properties / Attributes
    private ArrayList<Binder> binders;

    // Methods
    public int manageBinderMenuTemplate(){
        int input = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("MCO1 - Manage Binder Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Create New Binder");
        System.out.println("2. Delete a Binder");
        System.out.println("3. Add card to a Binder");
        System.out.println("4. Remove card from a Binder");
        System.out.println("5. Trade Card");
        System.out.println("6. View Binder");
        System.out.println("7. Exit");
        System.out.print("Enter Choice: ");
        input = scanner.nextInt();
        System.out.println("\n-------------------------------------------");
        return input;
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

    public void deleteBinder(Binder binder){

    }

    public void createBinder(String name){

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
