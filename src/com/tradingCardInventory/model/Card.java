package com.tradingCardInventory.model;
import com.tradingCardInventory.options.*;

/*
 * Represents a trading card with properties like name, rarity, variant, value, and count.
 * Actual value is calculated using a multiplier based on the variant.
 */
public class Card {

    // Properties / Attributes
    private String name;
    private int count;
    private Rarity rarity;
    private Variant variant;
    private double baseValue;
    private double actualValue;

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
        this.baseValue = baseValue;
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

    public Rarity getRarity() {
        return this.rarity;
    }

    public Variant getVariant() {
        return this.variant;
    }

    public double getActualValue() {
        return this.actualValue;
    }

    public double getBaseValue(){
        return this.baseValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActualValue(double actualValue) {
        this.actualValue = actualValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

}
