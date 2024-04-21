package com.example.lancastermanagmentsystem;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class Order {
    private int ID;
    private String ingredient_name;
    private int quantity;
    private float total_price;
    private String type;
    private boolean delivered;

    /**
     * Constructor.
     * @param orderID Order ID.
     * @param ingredient_name Ingredient name.
     * @param quantity Ingredient quantity.
     * @param total_price Total price of ingredient.
     * @param type Type of ingredient.
     * @param delivered has ingredient been delivered.
     */
    public Order(int orderID, String ingredient_name, int quantity, float total_price, String type, boolean delivered) {
        this.ID = orderID;
        this.ingredient_name = ingredient_name;
        this.quantity = quantity;
        this.total_price = total_price;
        this.type = type;
        this.delivered = delivered;
    }

    /**
     * @return order ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Set order ID.
     * @param orderID orderID.
     */
    public void setID(int orderID) {
        this.ID = orderID;
    }

    /**
     * @return ingredient name.
     */
    public String getIngredient_name() {
        return ingredient_name;
    }

    /**
     * Set ingredient name.
     * @param ingredient_name name.
     */
    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    /**
     * @return ingredient quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set order quantity.
     * @param quantity order quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return total ingredient price.
     */
    public float getTotal_price() {
        return total_price;
    }

    /**
     * Set Ingredient price.
     * @param total_price total price.
     */
    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    /**
     * @return ingredient type.
     */
    public String getType() {
        return type;
    }

    /**
     * Set ingredient type.
     * @param type ingredient type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return has ingredient been delivered.
     */
    public boolean isDelivered() {
        return delivered;
    }

    /**
     * Set has ingredient been delivered.
     * @param delivered delivered status.
     */
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
