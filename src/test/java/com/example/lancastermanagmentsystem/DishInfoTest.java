package com.example.lancastermanagmentsystem;

import static org.junit.Assert.assertEquals;

import com.example.lancastermanagmentsystem.DishInfo;
import org.junit.Before;
import org.junit.Test;

public class DishInfoTest {
    private DishInfo dish;

    @Before
    public void setUp() {
        dish = new DishInfo("Spaghetti Carbonara", 10.99f, "Delicious pasta with bacon and cheese sauce", "Chardonnay");
    }

    @Test
    public void testGetName() {
        assertEquals("Spaghetti Carbonara", dish.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10.99f, dish.getPrice(), 0.01);
    }

    @Test
    public void testGetDescription() {
        assertEquals("Delicious pasta with bacon and cheese sauce", dish.getDescription());
    }

    @Test
    public void testGetRecommendedWine() {
        assertEquals("Chardonnay", dish.getRecommendedWine());
    }

    @Test
    public void testSetDescription() {
        dish.setDescription("Classic Italian pasta dish with eggs, cheese, bacon, and black pepper.");
        assertEquals("Classic Italian pasta dish with eggs, cheese, bacon, and black pepper.", dish.getDescription());
    }

    @Test
    public void testSetRecommendedWine() {
        dish.setRecommendedWine("Pinot Grigio");
        assertEquals("Pinot Grigio", dish.getRecommendedWine());
    }
}
