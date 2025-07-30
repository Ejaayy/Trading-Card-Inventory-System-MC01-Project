package com.tradingCardInventory.model.Binders;

import com.tradingCardInventory.options.BinderType;

/**
 * The {@code NonCuratedBinder} class represents a basic type of {@link Binder}
 * with no restrictions on the types of cards that can be added.
 *
 * <p>This binder behaves like a default, catch-all storage for cards,
 * where all card variants and rarities are accepted.</p>
 *
 * <p>All cards are sorted alphabetically upon addition (inherited behavior).</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class NonCuratedBinder extends Binder {
    /**
     * Constructs a {@code NonCuratedBinder} with the specified name and type.
     *
     * @param name       the name of the binder
     * @param binderType the {@link BinderType} used to categorize this binder
     */
    public NonCuratedBinder(String name, BinderType binderType) {
        super(name, binderType);
    }
}
