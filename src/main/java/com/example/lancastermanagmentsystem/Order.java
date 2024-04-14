package com.example.lancastermanagmentsystem;

public class Order {
    private int orderID;
    private String ingredient_name;
    private int quantity;
    private float total_price;
    private String type;
    private boolean delivered;

    public Order(int orderID, String ingredient_name, int quantity, float total_price, String type, boolean delivered) {
        this.orderID = orderID;
        this.ingredient_name = ingredient_name;
        this.quantity = quantity;
        this.total_price = total_price;
        this.type = type;
        this.delivered = delivered;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
