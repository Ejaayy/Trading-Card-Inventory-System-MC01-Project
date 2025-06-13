package com.TradingCardInventoryClasses.utils;

import com.TradingCardInventoryClasses.model.Card;
import java.util.List;

public class CardUtils {

    // Properties / Attributes

    // Methods
    public Card searchCard(List<Card> cards, String name){
        for(int i=0; i< cards.size(); i++){
            if(cards.get(i).getName().equalsIgnoreCase(name)){
                return cards.get(i);
            }
        }
        return null;
    }

    public double calculatedAdjustedValue(Card card){
        //TODO hotdog
        return 1.0;
    }

    public boolean isTradeFair(Card card1, Card card2){
        //TODO
        return false;
    }

    public List<Card> sortCardsByName(){
        //TODO
        return this.collection;
    }

    public void viewCard(Card card){

        System.out.println("───────────────────────────────");
        System.out.printf("Name         : %s%n", card.getName());
        //System.out.printf("Rarity       : %s%n", card.getRarity());
        //System.out.printf("Variant      : %s%n", card.getVariant());
        //System.out.printf("Base Value   : $%.2f%n", card.getBaseValue());
        //System.out.printf("Actual Value : $%.2f%n", card.getActualValue());
        System.out.printf("Count        : %d%n", card.getCount());
        System.out.println("───────────────────────────────\n");

    }


}
