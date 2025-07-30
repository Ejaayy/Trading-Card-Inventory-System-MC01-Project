package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Rarity;

import javax.swing.*;
import java.util.Comparator;

/**
 * Represents a binder that only accepts rare and legendary cards.
 * This binder type slightly increases its value by 10% due to the rarity of the cards it holds.
 * It inherits from {@link SellableBinder}, which allows it to be sold.
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class RaresBinder extends SellableBinder {

    /**
     * Constructs a RaresBinder with the specified name and binder type.
     *
     * @param name       the name of the binder
     * @param binderType the type of the binder
     */
    public RaresBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    /**
     * Attempts to add a card to the binder.
     * Only cards with {@link Rarity#RARE} or {@link Rarity#LEGENDARY} are accepted.
     *
     * @param card the card to be added
     * @return true if the card was successfully added; false otherwise
     */
    public boolean addCard(Card card){
        if(card.getRarity() == Rarity.RARE || card.getRarity() == Rarity.LEGENDARY) {
            super.addCard(card);
            JOptionPane.showMessageDialog(null, "Success! Card Added", "Notification", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Invalid card type!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    /**
     * Returns the total value of the binder with a 10% premium due to rare cards.
     *
     * @return the adjusted binder value
     */
    public double getBinderValue(){
        return this.binderValue*1.1;
    }
}
