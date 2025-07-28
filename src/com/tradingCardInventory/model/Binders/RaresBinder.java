package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Rarity;

import java.util.Comparator;

public class RaresBinder extends SellableBinder {
    public RaresBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    public void addCard(Card card){
        if(card.getRarity() == Rarity.RARE && card.getRarity() == Rarity.LEGENDARY) {
            super.addCard(card);
        }
    }

    public double getBinderValue(){
        return this.binderValue*1.1;
    }
}
