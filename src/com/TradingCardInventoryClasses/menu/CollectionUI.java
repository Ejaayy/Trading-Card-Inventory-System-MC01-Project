package com.TradingCardInventoryClasses.menu;
import com.TradingCardInventoryClasses.model.Card;
import com.TradingCardInventoryClasses.model.Collection;
import com.TradingCardInventoryClasses.options.Rarity;
import com.TradingCardInventoryClasses.options.Variant;
import com.TradingCardInventoryClasses.utils.CardUtils;

import java.util.Comparator;
import java.util.Scanner;

//Directly calls Collection Methods
public class CollectionUI {

    public Collection collection;
    public CardUtils cardUtils;
    public Scanner scanner;

    public CollectionUI(Collection collection, Scanner scanner){
        this.collection = collection;
        cardUtils = new CardUtils();
        this.scanner = scanner;
    }

    public int collectionMenuTemplate(){
        int input = 0;

        System.out.println("MCO1 - Collection Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Add Card");
        System.out.println("2. Increase / Decrease Card Count");
        System.out.println("3. Display Card / Collection");
        System.out.println("4. Exit");
        System.out.print("Enter Choice: ");
        input = this.scanner.nextInt();
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
                    this.addInputCard();
                    break;
                case 2:
                    //Increase / Decrease Card Count
                    this.increaseDecrease();
                    break;
                case 3:
                    //Display Card / Collection
                    this.display();
                    break;
                case 4:
                    running = false;
                    break;
            }
        }
    }

    public void increaseDecrease(){
        int input = 0;
        boolean running = true;

        while(running) {

            System.out.println("Increase or Decrease Card Count");
            System.out.println("-------------------------------------------");
            System.out.println("1. Increase Card Count");
            System.out.println("2. Decrease Card Count");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            input = this.scanner.nextInt(); //Didn't take the \n
            this.scanner.nextLine(); //Takes input Buffer
            System.out.println("\n-------------------------------------------");

            switch(input) {
                case 1:
                    // Increase Card
                    System.out.print("Enter Card Name: ");
                    String increaseCardName = this.scanner.nextLine();
                    this.collection.increaseCardCount(increaseCardName);
                    break;
                case 2:
                    // Increase Card
                    System.out.print("Enter Card Name: ");
                    String decreaseCardName = this.scanner.nextLine();
                    this.collection.decreaseCardCount(decreaseCardName);
                    break;
                case 3:
                    running = false;
                    break;
            }

        }
    }

    public void display(){

        int input = 0;
        boolean running = true;

        while(running) {

            System.out.println("View Card and View Collection Menu");
            System.out.println("-------------------------------------------");
            System.out.println("1. View Card");
            System.out.println("2. View Collection");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            input = this.scanner.nextInt(); //Didn't take the \n
            this.scanner.nextLine(); //Takes input Buffer
            System.out.println("\n-------------------------------------------");

            switch(input) {
                case 1:
                    // View Card

                    System.out.print("Enter Card Name: ");
                    String cardName = this.scanner.nextLine();
                    Card foundCard = cardUtils.searchCard(this.collection.getAllCards(), cardName);

                    if(foundCard == null){
                        System.out.println("Card not found.");
                    }
                    else{
                        cardUtils.viewCard(foundCard);
                    }

                    break;
                case 2:
                    //View Collection
                    this.collection.displayCollection();
                    break;
                case 3:
                    running = false;
                    break;
            }

        }

    }

    public void addInputCard(){

        //Input Card Name
        System.out.print("Enter Card name: ");
        this.scanner.nextLine(); //Input buffer
        String cardName = this.scanner.nextLine();

        //Add here a search loop to find if the card exists already
        Card foundCard = cardUtils.searchCard(this.collection.getAllCards(), cardName);

        if(foundCard != null){
            System.out.println("Card already Exists! Would you like to increase the card count? [Y/N]: ");
            String response = this.scanner.nextLine();
            if (response.equals("Y")){
                foundCard.incrementCount(1);
            }
        }
        else{
            // Input Rarity
            Rarity rarity = null;
            while (rarity == null) {
                System.out.print("Enter Rarity (common, uncommon, rare, legendary): ");
                String rarityInput = this.scanner.nextLine().trim().toUpperCase();
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
                    String variantInput = this.scanner.nextLine().trim().toUpperCase();
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
            double value = this.scanner.nextDouble();

            this.collection.addCard(cardName, rarity, variant, value);

        }

    }
}
