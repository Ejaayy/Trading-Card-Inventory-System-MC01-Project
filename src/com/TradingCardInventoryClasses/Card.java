package com.TradingCardInventoryClasses;

public class Card {

    // Properties / Attributes
    private String name;
    private int count;
    private String rarity;
    private String variant;
    private float value;

    // Methods
    public void displayCard(){
        System.out.println("Name: " + this.name);
        System.out.println("Count: " + this.count);
        System.out.println("Rarity: " + this.rarity);
        System.out.println("Variant: " + this.variant);
        System.out.println("Value: " + this.value);
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public int getCount() {
        return this.count;
    }

    public String getRarity() {
        return this.rarity;
    }

    public String getVariant() {
        return this.variant;
    }

    public float getValue() {
        return this.value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
