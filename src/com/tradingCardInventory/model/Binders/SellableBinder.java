package com.tradingCardInventory.model.Binders;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;

/**
 * An abstract class representing a binder that can be sold.
 * It maintains a value based on the cards it contains.
 * All classes that extend this must inherit and use the value logic.
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public abstract class SellableBinder extends Binder {
    protected double binderValue;

    /**
     * Constructs a SellableBinder with a given name and binder type.
     *
     * @param name       the name of the binder
     * @param binderType the type of the binder
     */
    public SellableBinder(String name, BinderType binderType) {
        super(name, binderType);
        binderValue = 0.0;
    }

    /**
     * Adds a card to the binder and updates the total value.
     *
     * @param card the card to be added
     * @return true after the card is added
     */
    public boolean addCard(Card card) {
        super.addCard(card);
        updateBinderValue();
        return true;
    }

    /**
     * Removes a card from the binder and updates the total value.
     *
     * @param card the card to be removed
     */
    public void removeCard(Card card) {
        super.removeCard(card);
        updateBinderValue();
    }

    /**
     * Returns the total value of the binder.
     *
     * @return the sum of values of all cards in the binder
     */
    public double getBinderValue(){
        return binderValue;
    }

    /**
     * Sets the binder value. Only accessible within this class.
     *
     * @param binderValue the new total binder value
     */
    private void setBinderValue(double binderValue) {
        this.binderValue = binderValue;
    }

    /**
     * Recalculates the binder's total value by summing all card values.
     */
    protected void updateBinderValue(){
        double cardValueSum = 0;

        for(Card card : this.cards){
            cardValueSum += card.getActualValue();
        }

        setBinderValue(cardValueSum);
    }
}
