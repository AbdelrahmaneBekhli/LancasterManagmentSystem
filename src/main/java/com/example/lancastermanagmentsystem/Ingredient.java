package com.example.lancastermanagmentsystem;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
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

    /**
     * Constructor
     * @param ID ingredient ID.
     * @param ingredientName ingredient name.
     * @param quantity ingredient quantity.
     * @param price ingredient price.
     * @param vegan is ingredient vegan.
     * @param allergen ingredient allergen.
     * @param type ingredient type.
     */
    public Ingredient(int ID, String ingredientName, int quantity, float price, boolean vegan, String allergen, String type) {
        this.ID = ID;
        name = ingredientName;
        Quantity = quantity;
        this.price = price;
        this.vegan = vegan;
        this.allergen = allergen;
        this.type = type;
    }

    /**
     * @return ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * @return number of ordered ingredients.
     */
    public int getOrdered() {
        return ordered;
    }

    /**
     * Set number of ordered ingredients.
     * @param ordered number of ordered ingredients.
     */
    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    /**
     * @return total price of ingredients.
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * Set total price of ingredients.
     * @param totalPrice ingredient total price
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return ingredient type.
     */
    public String getType(){
        return type;
    }

    /**
     * Set ingredient type.
     * @param type ingredient type
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * @return ingredient name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set ingredient name.
     * @param ingredientName ingredient name.
     */
    public void setName(String ingredientName) {
        name = ingredientName;
    }

    /**
     * @return ingredient quantity.
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * Set quantity.
     * @param quantity ingredient quantity.
     */
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    /**
     * @return ingredient price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set ingredient price.
     * @param price ingredient price.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return is ingredient vegan.
     */
    public boolean isVegan() {
        return vegan;
    }

    /**
     * Set is ingredient vegan.
     * @param vegan vegan state.
     */
    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    /**
     * @return allergen type.
     */
    public String isAllergen() {
        return allergen;
    }

    /**
     * Set allergen type.
     * @param allergen ingredient allergen.
     */
    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }


}
