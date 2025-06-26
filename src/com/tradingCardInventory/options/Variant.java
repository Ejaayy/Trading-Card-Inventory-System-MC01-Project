package com.tradingCardInventory.options;

/*
 * Represents the options for Variants
 * and their corresponding multiplicity values
 */
public enum Variant {
    NORMAL(1.0),
    EXTENDED_ART(1.5),
    FULL_ART(2.0),
    ALT_ART(3.0);

    private final double multiplier;

    /*
     * Constructor to assign the multiplier to the variant.
     *
     * @param multiplier the value multiplier for the variant
     */
    Variant(double multiplier) {
        this.multiplier = multiplier;
    }

    /*
     * Returns the multiplier associated with the variant.
     *
     * @return the multiplier value
     */
    public double getMultiplier() {
        return multiplier;
    }
}