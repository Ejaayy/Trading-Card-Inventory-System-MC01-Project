package com.TradingCardInventoryClasses;
import com.TradingCardInventoryClasses.Options.*;

public class Card {

    // Properties / Attributes
    private String name;
    private int count;
    private Rarity rarity;
    private Variant variant;
    private double baseValue;
    private double actualValue;

    // Methods

    public Card(String name, Rarity rarity, Variant variant, double baseValue) {
        this.name = name;
        this.rarity = rarity;
        this.variant = variant;
        this.baseValue = baseValue;
        this.actualValue = baseValue * variant.getMultiplier();
        this.count = 1;
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

    public void setCount(int count) {
        this.count = count;
    }

    public void setActualValue(double actualValue) {
        this.actualValue = actualValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }
}
