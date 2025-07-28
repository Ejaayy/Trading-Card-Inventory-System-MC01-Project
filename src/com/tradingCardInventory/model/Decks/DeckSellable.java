package com.tradingCardInventory.model.Decks;

import com.tradingCardInventory.model.Card;

public class DeckSellable extends Deck{
    private double deckValue;

    public DeckSellable(String name){
        super(name);
    }

    public void addCard(Card card) {
        super.addCard(card);
        updateDeckValue();
    }

    public void removeCard(Card card) {
        super.removeCard(card);
        updateDeckValue();
    }

    public int sell(){
        return 0;
    }

    public double getDeckValue(){
        return deckValue;
    }

    private void setDeckValue(double deckValue) {
        this.deckValue = deckValue;
    }

    private void updateDeckValue(){
        double cardValueSum = 0;

        for(Card card : this.cards){
            cardValueSum += card.getActualValue();
        }

        setDeckValue(cardValueSum);
    }
}
