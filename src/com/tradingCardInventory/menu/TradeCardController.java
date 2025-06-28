package com.tradingCardInventory.menu;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * TradeCardController handles the UI logic for facilitating a card trade.
 * It prompts the user to input details about the incoming card and compares it
 * with the outgoing card from the binder.
 */
public class TradeCardController {

    // Properties / Attributes
    private Scanner scanner;
    private final Collection collection;

    // Methods

    /*
     * Constructs the TradeCardController with the provided collection.
     *
     * @param collection the collection to interact with for trading
     */
    public TradeCardController(Collection collection) {
        this.scanner = new Scanner(System.in);
        this.collection = collection;
    }

    /*
     * Displays a trade menu, gets the new (incoming) card details, and compares it
     * with the outgoing card. Returns the incoming card if trade is accepted.
     *
     * @param cardName the name of the card being offered (outgoing)
     * @return the Card object to be traded in, or null if trade is canceled
     */
    public Card tradeCardMenu(Card outgoingCard){
        //Input for Incoming Card Name
        System.out.print("Enter Card name: ");
        String name = scanner.nextLine();
        Card incomingCard = null;

        if(!name.equals("0")) {
            //Search loop to find if the card exists already
            Card foundCard = this.collection.searchCard(name);
            if(foundCard != null) {
                //card exists in collection
                System.out.println("Card already exists in collection!");
                return foundCard;
            }else{
                //card doesnt exist in collection
                Rarity rarity = null;
                boolean flag = true;

                while(rarity == null && flag){
                    System.out.print("Enter Rarity (common, uncommon, rare, legendary): ");
                    String rarityInput = this.scanner.nextLine().trim().toUpperCase();

                    //Error Handling
                    try {
                        if(!rarityInput.equals("0"))
                            rarity = Rarity.valueOf(rarityInput);
                        else
                            flag = false;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid rarity. Try again.");
                    }

                    if(flag){
                        // Input Variant (only if rare or legendary)
                        Variant variant = Variant.NORMAL;  // default
                        if (rarity == Rarity.RARE || rarity == Rarity.LEGENDARY) {
                            while (true) {
                                System.out.print("Enter Variant (normal, extended_art, full_art, alt_art): ");
                                String variantInput = this.scanner.nextLine().trim().toUpperCase();

                                //Error Handling
                                try {
                                    if(!variantInput.equals("0"))
                                        variant = Variant.valueOf(variantInput);
                                    else
                                        flag = false;
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid variant. Try again.");
                                }
                            }
                        }

                        if(flag){
                            double value = 0;
                            boolean valid = false;

                            while(!valid){
                                System.out.print("Enter Value: ");

                                //Error Handling
                                try {
                                    value = this.scanner.nextDouble();
                                    scanner.nextLine(); // consume newline
                                    valid = true;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Please enter a numeric value.");
                                    scanner.nextLine(); // clear invalid input
                                }
                            }

                            if(value == 0){
                                System.out.println("Is the input (0) to cancel or the value of the card?");
                                System.out.println("0. Cancel");
                                System.out.println("1. Value");
                                System.out.print("Input: ");

                                double input = scanner.nextDouble();
                                scanner.nextLine();

                                if(input==0){
                                    flag = false;
                                }
                            }
                            if(flag){
                                // After a valid value is entered
                                incomingCard = new Card(name, rarity, variant, value);
                                System.out.println("Input Card Success!");

                                // If not 1, cancel the trade
                                if(!displayTradeMenu(incomingCard, outgoingCard).equals("1")) {
                                    incomingCard = null;
                                }
                            }
                        }
                    }
                }
            }
        }
        return incomingCard;
    }

    public String displayTradeMenu(Card incomingCard, Card outgoingCard){
        //Showing card details from input
        System.out.println("Trade Menu");
        System.out.println("───────────────────────────────");
        System.out.println("Incoming Card");
        System.out.println("───────────────────────────────");
        System.out.printf("Name  : %s%n", incomingCard.getName());
        System.out.printf("Value : $%.2f%n", incomingCard.getActualValue());
        System.out.println("───────────────────────────────");
        System.out.println("Outgoing Card");
        System.out.println("───────────────────────────────");
        System.out.printf("Name  : %s%n", outgoingCard.getName());
        System.out.printf("Value : $%.2f%n", outgoingCard.getActualValue());
        System.out.println("───────────────────────────────\n");

        // Check if the trade difference is more than a dollar
        if(incomingCard.getActualValue() - outgoingCard.getActualValue() <= -1 || incomingCard.getActualValue() - outgoingCard.getActualValue() >= 1){
            System.out.println("Value difference is greater than or equal to $1.");
        }

        // Confirm trade
        System.out.println("Proceed with the trade?");
        System.out.println("0. Cancel");
        System.out.println("1. Proceed");
        System.out.print("Input: ");
        
        boolean valid = false;
        String input = "";

        while(!valid){
            input = scanner.nextLine();
            
            if(input.equals("0") || input.equals("1")){
                valid = true;
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
        
        return input;
    }
}