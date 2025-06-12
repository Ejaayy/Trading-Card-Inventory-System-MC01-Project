package com.TradingCardInventoryClasses;

public class CardUtils {

    // Properties / Attributes

    // Methods
    public double calculatedAdjustedValue(Card card){
        //TODO hotdog
        return 1.0;
    }

    public boolean isTradeFair(Card card1, Card card2){
        //TODO
        return false;
    }

    public void viewCard(Card card){

        System.out.println("───────────────────────────────");
        System.out.printf("Name         : %s%n", card.getName());
        System.out.printf("Rarity       : %s%n", card.getRarity());
        System.out.printf("Variant      : %s%n", card.getVariant());
        System.out.printf("Base Value   : $%.2f%n", card.getBaseValue());
        System.out.printf("Actual Value : $%.2f%n", card.getActualValue());
        System.out.printf("Count        : %d%n", card.getCount());
        System.out.println("───────────────────────────────\n");

    }
}
