package com.example.lancastermanagmentsystem;

public class DishInfo {
    private final String name;

    private float price;

    private String description;
    private String recommendedWine;
    public DishInfo(String name, float dishPrice, String description, String recommendedWine) {
        this.name = name;
        this.price = dishPrice;
        this.description = description;
        this.recommendedWine = recommendedWine;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecommendedWine(String recommendedWine) {
        this.recommendedWine = recommendedWine;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getRecommendedWine() {
        return recommendedWine;
    }


}
