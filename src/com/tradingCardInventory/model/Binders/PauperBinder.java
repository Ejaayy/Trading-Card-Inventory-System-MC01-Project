package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Rarity;

import javax.swing.*;
import java.util.Comparator;

public class PauperBinder extends SellableBinder{
    public PauperBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    public boolean addCard(Card card){
        if(card.getRarity() == Rarity.COMMON || card.getRarity() == Rarity.UNCOMMON) {
            super.addCard(card);
            JOptionPane.showMessageDialog(null, "Success! Card Added", "Notification", JOptionPane.ERROR_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Invalid card type!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}