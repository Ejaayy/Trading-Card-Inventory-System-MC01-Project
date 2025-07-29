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

    public void addCard(Card card){
        if(card.getRarity() == Rarity.COMMON && card.getRarity() == Rarity.UNCOMMON) {
            JOptionPane.showMessageDialog(null, "Success! Card Added", "Notification", JOptionPane.ERROR_MESSAGE);
            super.addCard(card);
        }else{
            JOptionPane.showMessageDialog(null, "Invalid card type!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
