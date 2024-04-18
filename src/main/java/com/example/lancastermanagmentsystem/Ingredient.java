package com.example.lancastermanagmentsystem;

public class Ingredient {

    private final int ID;
    private String name;
    private int Quantity;
    private float price;
    private boolean vegan;
    private String allergen;
    private String type;

    private int ordered;
    private float totalPrice;


    public Ingredient(int ID, String ingredientName, int quantity, float price, boolean vegan, String allergen, String type) {
        this.ID = ID;
        name = ingredientName;
        Quantity = quantity;
        this.price = price;
        this.vegan = vegan;
        this.allergen = allergen;
        this.type = type;
    }


    public int getID() {
        return ID;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String ingredientName) {
        name = ingredientName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public String isAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }


}
