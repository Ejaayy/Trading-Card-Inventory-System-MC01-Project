package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Variant;

import javax.swing.*;

/**
 * The {@code LuxuryBinder} class represents a special type of {@link SellableBinder}
 * that only accepts cards with variants other than {@code NORMAL}.
 * It allows setting a custom value for the binder, which must be at least as high
 * as its base value. The final binder value is calculated with a 10% premium.
 *
 * <p>This class enhances {@code SellableBinder} by supporting custom valuation
 * and stricter card acceptance rules.</p>
 *
 * <p>Cards are sorted alphabetically upon successful addition.</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class LuxuryBinder extends SellableBinder {
    private double customValue;

    /**
     * Constructs a {@code LuxuryBinder} with the specified name and binder type.
     *
     * @param name       the name of the binder
     * @param binderType the {@link BinderType} associated with this binder
     */
    public LuxuryBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    /**
     * Attempts to add a card to the binder. Only cards with non-{@code NORMAL} variants are allowed.
     * If successfully added, the binder's custom value is updated if necessary.
     *
     * @param card the {@link Card} to add
     * @return {@code true} if the card is added; {@code false} otherwise
     *
     * <p>A UI message is shown to indicate success or failure.</p>
     */
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

    /**
     * Sets a custom value for the binder, provided it's at least as large as the default binder value.
     *
     * @param customValue the new custom value to set
     * @return {@code true} if the value was updated; {@code false} if it's below the base value
     */
    public boolean setCustomValue(double customValue){
        if(customValue>=this.binderValue) {
            this.customValue = customValue;
            return true;
        }
        return false;
    }

    /**
     * Gets the total binder value with a 10% premium based on the custom value.
     *
     * @return the calculated binder value with the premium
     */
    public double getBinderValue(){
        return this.customValue*1.1;
    }

}
