package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;

import java.util.Comparator;

public class LuxuryBinder extends SellableBinder {
    private double customValue;

    public LuxuryBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    public void addCard(Card card){
        if(card.getVariant() != Variant.NORMAL) {
            super.addCard(card);
            if(this.customValue<this.binderValue){
                this.customValue = this.binderValue;
            }
        }
    }

    public void setCustomValue(double customValue){
        if(this.customValue>=this.binderValue) {
            this.customValue = customValue;
        }
    }

    public double getBinderValue(){
        return this.customValue*1.1;
    }


}
