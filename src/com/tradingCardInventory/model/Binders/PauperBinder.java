package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Rarity;

import java.util.Comparator;

public class PauperBinder extends SellableBinder{
    public PauperBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    public void addCard(Card card){
        if(card.getRarity() == Rarity.COMMON && card.getRarity() == Rarity.UNCOMMON) {
            super.addCard(card);
        }
    }
}
