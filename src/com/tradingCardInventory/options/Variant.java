package com.tradingCardInventory.options;

/**
 * Represents the available artwork variants for a trading card,
 * each with a corresponding value multiplier.
 */
public enum Variant {
    NORMAL(1.0),
    EXTENDED_ART(1.5),
    FULL_ART(2.0),
    ALT_ART(3.0);

    private final double multiplier;

    /**
     * Constructs a {@code Variant} with the given multiplier.
     *
     * @param multiplier the value multiplier for the variant
     */
    Variant(double multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Returns the value multiplier associated with this variant.
     *
     * @return the multiplier value
     */
    public double getMultiplier() {
        return multiplier;
    }
}