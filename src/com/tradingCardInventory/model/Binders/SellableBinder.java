package com.tradingCardInventory.model.Binders;
import com.tradingCardInventory.model.Card;

public abstract class SellableBinder extends Binder {
    private double binderValue;

    public SellableBinder(String name) {
        super(name);
        binderValue = 0.0;
    }

    public void addCard(Card card) {
        super.addCard(card);
        updateBinderValue();
    }

    public void removeCard(Card card) {
        super.removeCard(card);
        updateBinderValue();
    }

    public int sell(){
        return 0;
    }

    public double getBinderValue(){
        return binderValue;
    }

    private void setBinderValue(double binderValue) {
        this.binderValue = binderValue;
    }

    private void updateBinderValue(){
        double cardValueSum = 0;

        for(Card card : this.cards){
            cardValueSum += card.getActualValue();
        }

        setBinderValue(cardValueSum);
    }
}
