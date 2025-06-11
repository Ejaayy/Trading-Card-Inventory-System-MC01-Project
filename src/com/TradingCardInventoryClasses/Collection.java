package com.TradingCardInventoryClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.TradingCardInventoryClasses.Options.*;

public class Collection {

    // Properties / Attributes
    private List<Card> collection;

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

    public void addCard(){
        //TODO
        Scanner scanner = new Scanner(System.in);

        //Input Card Name
        System.out.print("Enter Card name: ");
        String cardName = scanner.nextLine();

        //Add here a search loop to find if the card exists already
        //TODO

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
