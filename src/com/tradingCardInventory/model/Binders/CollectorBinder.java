package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;

import java.util.Comparator;

public class CollectorBinder extends Binder {

    public CollectorBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    public void addCard(Card card){
        if(card.getRarity() == Rarity.RARE && card.getRarity() == Rarity.LEGENDARY && card.getVariant()!= Variant.NORMAL) {
            this.cards.add(card);
            this.cards.sort(Comparator.comparing(Card::getName));
        }
    }
}
