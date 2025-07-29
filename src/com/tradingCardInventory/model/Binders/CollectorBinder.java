package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;

import javax.swing.*;
import java.util.Comparator;

public class CollectorBinder extends Binder {

    public CollectorBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    public boolean addCard(Card card){
        if((card.getRarity() == Rarity.RARE || card.getRarity() == Rarity.LEGENDARY) && card.getVariant()!= Variant.NORMAL) {
            this.cards.add(card);
            this.cards.sort(Comparator.comparing(Card::getName));
            JOptionPane.showMessageDialog(null, "Success! Card Added", "Notification", JOptionPane.ERROR_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Invalid card type!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
