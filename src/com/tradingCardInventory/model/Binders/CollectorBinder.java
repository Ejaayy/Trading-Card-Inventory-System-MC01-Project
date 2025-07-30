package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;

import javax.swing.*;
import java.util.Comparator;

/**
 * The {@code CollectorBinder} class represents a specialized binder that only accepts
 * cards with either {@link Rarity#RARE} or {@link Rarity#LEGENDARY} rarity and
 * a variant other than {@link Variant#NORMAL}.
 *
 * <p>This class extends the abstract {@link Binder} class and enforces stricter rules
 * for which cards can be added.</p>
 *
 * <p>Cards added to this binder are automatically sorted alphabetically by name.</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class CollectorBinder extends Binder {

    /**
     * Constructs a {@code CollectorBinder} with the specified name and binder type.
     *
     * @param name       the name of the binder
     * @param binderType the {@link BinderType} of the binder
     */
    public CollectorBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    /**
     * Attempts to add a card to the binder. Only cards with {@code RARE} or {@code LEGENDARY} rarity
     * and a variant that is not {@code NORMAL} are accepted.
     *
     * @param card the {@link Card} to be added
     * @return {@code true} if the card meets the criteria and was added;
     *         {@code false} otherwise
     *
     * <p>UI dialogs notify the user of success or error.</p>
     */
    public boolean addCard(Card card){
        if((card.getRarity() == Rarity.RARE || card.getRarity() == Rarity.LEGENDARY) && card.getVariant()!= Variant.NORMAL) {
            this.cards.add(card);
            this.cards.sort(Comparator.comparing(Card::getName));
            JOptionPane.showMessageDialog(null, "Success! Card Added", "Notification", JOptionPane.ERROR_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Invalid card type!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
