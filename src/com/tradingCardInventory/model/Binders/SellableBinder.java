package com.tradingCardInventory.model.Binders;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;

public abstract class SellableBinder extends Binder {
    protected double binderValue;

    public SellableBinder(String name, BinderType binderType) {
        super(name, binderType);
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

    public double getBinderValue(){
        return binderValue;
    }

    private void setBinderValue(double binderValue) {
        this.binderValue = binderValue;
    }

    protected void updateBinderValue(){
        double cardValueSum = 0;

        for(Card card : this.cards){
            cardValueSum += card.getActualValue();
        }

        setBinderValue(cardValueSum);
    }
}
