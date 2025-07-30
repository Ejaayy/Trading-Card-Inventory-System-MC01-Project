package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.options.Rarity;

import javax.swing.*;
import java.util.Comparator;

/**
 * The {@code PauperBinder} class is a specialized {@link SellableBinder}
 * that only accepts cards with {@link Rarity#COMMON} or {@link Rarity#UNCOMMON} rarity.
 *
 * <p>This binder is ideal for collections of lower rarity.
 * It enforces rarity restrictions during addition and displays a UI message
 * upon success or failure.</p>
 *
 * <p>Accepted cards are sorted alphabetically by name after being added.</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class PauperBinder extends SellableBinder{

    /**
     * Constructs a {@code PauperBinder} with the specified name and binder type.
     *
     * @param name       the name of the binder
     * @param binderType the {@link BinderType} to categorize this binder
     */
    public PauperBinder(String name, BinderType binderType) {
        super(name, binderType);
    }

    /**
     * Attempts to add a card to the binder. Only cards with
     * {@code COMMON} or {@code UNCOMMON} rarity are allowed.
     *
     * @param card the {@link Card} to add
     * @return {@code true} if the card was added; {@code false} otherwise
     *
     * <p>A UI notification is shown based on the result.</p>
     */
    public boolean addCard(Card card){
        if(card.getRarity() == Rarity.COMMON || card.getRarity() == Rarity.UNCOMMON) {
            super.addCard(card);
            JOptionPane.showMessageDialog(null, "Success! Card Added", "Notification", JOptionPane.ERROR_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Invalid card type!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}