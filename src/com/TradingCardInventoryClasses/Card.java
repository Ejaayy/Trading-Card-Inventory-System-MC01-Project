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
        this.count = 1;
    }

    public void displayCard(){
        System.out.println("Name: " + this.name);
        System.out.println("Count: " + this.count);
        System.out.println("Rarity: " + this.rarity);
        System.out.println("Variant: " + this.variant);
        System.out.println("Value: " + this.actualValue);
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

    public double getValue() {
        return this.actualValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
