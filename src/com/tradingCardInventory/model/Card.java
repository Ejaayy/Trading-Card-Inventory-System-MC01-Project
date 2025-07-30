package com.tradingCardInventory.model;
import com.tradingCardInventory.options.*;

/**
 * Represents a trading card with properties like name, rarity, variant, value, and count.
 * The actual value is calculated using a multiplier based on the variant.
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class Card {

    // Properties / Attributes
    private final String name;
    private final Rarity rarity;
    private final Variant variant;
    private final double actualValue;
    private int count;

    // Methods

    /**
     * Constructs a new {@code Card} with the specified name, rarity, variant, and base value.
     * The actual value is computed by multiplying the base value by the variant's multiplier.
     *
     * @param name      the name of the card
     * @param rarity    the rarity level of the card
     * @param variant   the variant style of the card
     * @param baseValue the base monetary value of the card
     *
     * Preconditions:
     * - {@code rarity} and {@code variant} must not be null and should have defined multipliers.
     *
     * Postconditions:
     * - Initializes {@code count} to 1.
     * - Calculates {@code actualValue} based on the variant.
     */
    public Card(String name, Rarity rarity, Variant variant, double baseValue) {

        this.name = name;
        this.rarity = rarity;
        this.variant = variant;
        this.actualValue = baseValue * variant.getMultiplier();
        this.count = 1;
    }

    /**
     * Increases the count of this card by the specified amount.
     *
     * @param count the number of additional copies to add
     */
    public void incrementCount(int count) {
        this.count += count;
    }

    // Getters and Setters

    /**
     * Returns the name of the card.
     *
     * @return the card's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the current number of copies of this card.
     *
     * @return the card count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Returns the computed actual value of the card.
     *
     * @return the actual value
     */
    public double getActualValue() {
        return this.actualValue;
    }

    /**
     * Returns the variant type of the card.
     *
     * @return the card's variant
     */
    public Variant getVariant() {
        return this.variant;
    }

    /**
     * Returns the rarity level of the card.
     *
     * @return the card's rarity
     */
    public Rarity getRarity() {
        return this.rarity;
    }

}