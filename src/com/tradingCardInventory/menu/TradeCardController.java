package com.tradingCardInventory.menu;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;

import java.util.Scanner;

/*
 * TradeCardController handles the UI logic for facilitating a card trade.
 * It prompts the user to input details about the incoming card and compares it
 * with the outgoing card from the binder.
 */
public class TradeCardController {

    // Properties / Attributes
    private Scanner scanner;
    private Collection collection;

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
    public Card tradeCardMenu(String cardName){
        //Input for Incoming Card Name
        System.out.print("Enter Card name: ");
        String name = scanner.nextLine();
        Card incomingCard = null;

        //Search loop to find if the card exists already
        Card foundCard = this.collection.searchCard(name);

        if (foundCard != null) {
            System.out.println("Card already Exists! Would you like to increase the card count? [Y/N]: ");
            String response = scanner.nextLine();
            if (response.equals("Y")) {
                foundCard.incrementCount(1);
            }
        } else {
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

            incomingCard = new Card(name, rarity, variant, value);

            System.out.println("Trade Menu");
            System.out.println("───────────────────────────────");
            System.out.println("Incoming Card");
            System.out.println("───────────────────────────────");
            System.out.printf("Name  : %s%n", incomingCard.getName());
            System.out.printf("Value : $%.2f%n", incomingCard.getActualValue());
            System.out.println("───────────────────────────────");
            System.out.println("Outgoing Card");
            System.out.println("───────────────────────────────");
            System.out.printf("Name  : %s%n", this.collection.searchCard(cardName).getName());
            System.out.printf("Value : $%.2f%n", this.collection.searchCard(cardName).getActualValue());
            System.out.println("───────────────────────────────\n");

            if(incomingCard.getActualValue() - this.collection.searchCard(cardName).getActualValue() > -1 && incomingCard.getActualValue() - this.collection.searchCard(cardName).getActualValue() < 1){
                System.out.println("Value difference is less than $1.");
            }

            System.out.println("Proceed with the trade?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Input: ");

            if(scanner.nextInt() != 1) {
                scanner.nextLine(); //input buffer
                incomingCard = null;
            }
        }
        return incomingCard;
    }
}
