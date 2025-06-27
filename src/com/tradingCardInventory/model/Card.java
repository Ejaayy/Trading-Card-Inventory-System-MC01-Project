package com.tradingCardInventory.model;
import com.tradingCardInventory.options.*;

/*
 * Represents a trading card with properties like name, rarity, variant, value, and count.
 * Actual value is calculated using a multiplier based on the variant.
 */
public class Card {

    // Properties / Attributes
    private final String name;
    private final Rarity rarity;
    private final Variant variant;
    private final double actualValue;
    private int count;

    // Methods

    /*
     * Constructs a new Card with the specified properties.
     *
     * @param name the name of the card
     * @param rarity the rarity level of the card
     * @param variant the variant style of the card
     * @param baseValue the base monetary value of the card
     *
     * Pre-condition:
     * - Rarity and Variant enums must be valid and have multipliers defined.
     * Post-condition:
     * - A card object is created with calculated actual value and count = 1.
     */
    public Card(String name, Rarity rarity, Variant variant, double baseValue) {
        this.name = name;
        this.rarity = rarity;
        this.variant = variant;
        this.actualValue = baseValue * variant.getMultiplier();
        this.count = 1;
    }

    public void incrementCount(int count) {
        this.count += count;
    }

    /*
     * Increments the count of this card by the specified amount.
     *
     * @param count number of copies to add
     */
    public void viewCardDetails(){

        System.out.printf("%-25s %-12s %-12s $%-9.2f%n",
                this.name,
                this.rarity,
                this.variant,
                this.actualValue);
    }

    // Getters and Setters

    public String getName() {
        return this.name;
    }

    public int getCount() {
        return this.count;
    }

    public double getActualValue() {
        return this.actualValue;
    }

    /*
    public Variant getVariant() {
        return this.variant;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public double getBaseValue(){
        return this.baseValue;
    }

    public void setActualValue(double actualValue) {
        this.actualValue = actualValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

     */

}
