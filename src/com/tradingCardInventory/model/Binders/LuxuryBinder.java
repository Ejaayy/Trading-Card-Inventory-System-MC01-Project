package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;

import javax.swing.*;
import java.util.Comparator;

public class LuxuryBinder extends SellableBinder {
    private double customValue;

    public LuxuryBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    public boolean addCard(Card card){
        if(card.getVariant() != Variant.NORMAL) {
            super.addCard(card);
            if(this.customValue<this.binderValue){
                this.customValue = this.binderValue;
            }
            JOptionPane.showMessageDialog(null, "Success! Card Added", "Notification", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Invalid card type!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean setCustomValue(double customValue){
        if(customValue>=this.binderValue) {
            this.customValue = customValue;
            return true;
        }
        return false;
    }

    public double getBinderValue(){
        return this.customValue*1.1;
    }


}
