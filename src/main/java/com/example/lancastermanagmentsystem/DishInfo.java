package com.example.lancastermanagmentsystem;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class DishInfo {
    private final String name;

    private float price;

    private String description;
    private String recommendedWine;

    /**
     * Constructor.
     * @param name dish name.
     * @param dishPrice dish price.
     * @param description dish description
     * @param recommendedWine dish recommended wine.
     */
    public DishInfo(String name, float dishPrice, String description, String recommendedWine) {
        this.name = name;
        this.price = dishPrice;
        this.description = description;
        this.recommendedWine = recommendedWine;
    }

    /**
     * Set dish description.
     * @param description dish description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set dish recommended wine.
     * @param recommendedWine dish recommended wine.
     */
    public void setRecommendedWine(String recommendedWine) {
        this.recommendedWine = recommendedWine;
    }

    /**
     * @return dish name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return dish price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return dish description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return dish recommended wine.
     */
    public String getRecommendedWine() {
        return recommendedWine;
    }


}
