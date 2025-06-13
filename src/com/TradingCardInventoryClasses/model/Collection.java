package com.TradingCardInventoryClasses.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.TradingCardInventoryClasses.utils.CardUtils;
import com.TradingCardInventoryClasses.options.*;

import static java.lang.constant.ConstantDescs.NULL;

public class Collection {

    // Properties / Attributes
    private List<Card> collection;
    CardUtils cardUtils = new CardUtils();

    //Methods
    public Collection() {
        this.collection = new ArrayList<>();
    }

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
                    addCard();
                    break;
                case 2:
                    //Increase / Decrease Card Count
                    increaseDecrease();
                    break;
                case 3:
                    //Display Card / Collection
                    display();
                    break;
                case 4:
                    running = false;
                    break;
            }
        }
    }

    public void addCard(){
        //TODO
        Scanner scanner = new Scanner(System.in);

        //Input Card Name
        System.out.print("Enter Card name: ");
        String cardName = scanner.nextLine();

        //Add here a search loop to find if the card exists already
        Card foundCard = cardUtils.searchCard(collection, cardName);

        if(foundCard != null){
            System.out.println("Card already Exists! Would you like to increase the card count? [Y/N]: ");
            String response = scanner.nextLine();
            if (response.equals("Y")){
                foundCard.incrementCount(1);
            }
        }
        else{
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

            Card card = new Card(cardName, rarity, variant, value);
            this.collection.add(card);

            //Says that you're comparing Card Objects via their getName methods
            this.collection.sort(Comparator.comparing(Card::getName)); // add .reversed if you want it descending
        }

    }

    public void display(){

        int input = 0;
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {

            System.out.println("View Card and View Collection Menu");
            System.out.println("-------------------------------------------");
            System.out.println("1. View Card");
            System.out.println("2. View Collection");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            input = scanner.nextInt(); //Didn't take the \n
            scanner.nextLine(); //Takes input Buffer
            System.out.println("\n-------------------------------------------");

            switch(input) {
                case 1:
                    // View Card

                    System.out.print("Enter Card Name: ");
                    String cardName = scanner.nextLine();
                    Card foundCard = cardUtils.searchCard(collection, cardName);

                    if(foundCard == null){
                        System.out.println("Card not found.");
                    }
                    else{
                        cardUtils.viewCard(foundCard);
                    }

                    break;
                case 2:
                    //View Collection
                    displayCollection();
                    break;
                case 3:
                    running = false;
                    break;
            }

        }

    }

    public void displayCollection(){

        for (int i = 0; i < collection.size(); i++){
            Card card = this.collection.get(i);
            cardUtils.viewCard(card);
        }

    }

    public void increaseCardCount(String name){
        Card foundCard = cardUtils.searchCard(this.collection, name);
        if (foundCard == null){
            System.out.println("Card not found.");
        }
        else{
            foundCard.incrementCount(1);
            System.out.println("Increment Successful!\n");
        }
    }

    // Method Over Riding Option
    public void increaseCardCount(String name, int count){
        Card foundCard = cardUtils.searchCard(this.collection, name);
        if (foundCard == null){
            System.out.println("Card not found.");
        }
        else{
            foundCard.incrementCount(count);
            System.out.println("Increment Successful!\n");
        }
    }

    public void decreaseCardCount(String name){
        Card foundCard = cardUtils.searchCard(this.collection, name);
        if (foundCard == null){
            System.out.println("Card not found.");
        }
        else{
            if(foundCard.getCount() > 0){
                foundCard.incrementCount(-1);
                System.out.println();
            }
        }
    }

    // Method Over Riding Option
    public void decreaseCardCount(String name, int count){
        Card foundCard = cardUtils.searchCard(this.collection, name);
        if (foundCard == null){
            System.out.println("Card not found.");
            System.out.println();
        }
        else{
            if(foundCard.getCount() >= count){
                foundCard.incrementCount(-count);
            }
        }
    }

    public void increaseDecrease(){
        int input = 0;
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {

            System.out.println("Increase or Decrease Card Count");
            System.out.println("-------------------------------------------");
            System.out.println("1. Increase Card Count");
            System.out.println("2. Decrease Card Count");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            input = scanner.nextInt(); //Didn't take the \n
            scanner.nextLine(); //Takes input Buffer
            System.out.println("\n-------------------------------------------");

            switch(input) {
                case 1:
                    // Increase Card
                    System.out.print("Enter Card Name: ");
                    String increaseCardName = scanner.nextLine();
                    increaseCardCount(increaseCardName);
                    break;
                case 2:
                    // Increase Card
                    System.out.print("Enter Card Name: ");
                    String decreaseCardName = scanner.nextLine();
                    decreaseCardCount(decreaseCardName);
                    break;
                case 3:
                    running = false;
                    break;
            }

        }
    }

    public List<Card> getAllCards(){
        return this.collection;
    }

    public Card getCard(String name){
        return cardUtils.searchCard(this.collection, name);
    }

}
