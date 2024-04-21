package com.example.lancastermanagmentsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    private final Ingredient ingredient = new Ingredient(1, "Chocolate", 10, 10.0f, true, "Flour", "Sweets");


    @Test
    void testGetID() {
        assertEquals(1, ingredient.getID());
    }

    @Test
    void testGetOrdered() {
        ingredient.setOrdered(1);
        assertEquals(1, ingredient.getOrdered());
    }

    @Test
    void testSetOrdered() {
        ingredient.setOrdered(0);
        assertEquals(0, ingredient.getOrdered());
    }

    @Test
    void testGetTotalPrice() {
        ingredient.setTotalPrice(200);
        assertEquals(200, ingredient.getTotalPrice());
    }

    @Test
    void testSetTotalPrice() {
        ingredient.setTotalPrice(15.0f);
        assertEquals(15.0, ingredient.getTotalPrice());
    }

    @Test
    void testGetType() {
        assertEquals("Sweets", ingredient.getType());
    }

    @Test
    void testSetType() {
        ingredient.setType("Drink");
        assertEquals("Drink", ingredient.getType());
    }

    @Test
    void testGetName() {
        assertEquals("Chocolate", ingredient.getName());
    }

    @Test
    void testSetName() {
        ingredient.setName("Sugar");
        assertEquals("Sugar", ingredient.getName());
    }

    @Test
    void testGetQuantity() {
        assertEquals(10, ingredient.getQuantity());
    }

    @Test
    void testSetQuantity() {
        ingredient.setQuantity(1000);
        assertEquals(1000, ingredient.getQuantity());
    }

    @Test
    void testGetPrice() {
        assertEquals(10.0f, ingredient.getPrice());
    }

    @Test
    void testSetPrice() {
        ingredient.setPrice(2.0f);
        assertEquals(2.0, ingredient.getPrice());
    }

    @Test
    void testIsVegan() {
        assertTrue(ingredient.isVegan());
    }

    @Test
    void testSetVegan() {
        ingredient.setVegan(false);
        assertFalse(ingredient.isVegan());
    }

    @Test
    void testSetAllergen() {
        ingredient.setAllergen("Peanuts");
        assertEquals("Peanuts", ingredient.isAllergen());
    }
}
