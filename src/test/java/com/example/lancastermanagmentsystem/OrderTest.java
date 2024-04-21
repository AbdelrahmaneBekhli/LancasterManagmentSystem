package com.example.lancastermanagmentsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private final Order order =  new Order(1, "Coffee", 2, 10.0f, "Drink", false);


    @Test
    void testGetID() {
        assertEquals(1, order.getID());
    }

    @Test
    void testSetID() {
        order.setID(2);
        assertEquals(2, order.getID());
    }

    @Test
    void testGetIngredient_name() {
        assertEquals("Coffee", order.getIngredient_name());
    }

    @Test
    void testSetIngredient_name() {
        order.setIngredient_name("Tea");
        assertEquals("Tea", order.getIngredient_name());
    }

    @Test
    void testGetQuantity() {
        assertEquals(2, order.getQuantity());
    }

    @Test
    void testSetQuantity() {
        order.setQuantity(3);
        assertEquals(3, order.getQuantity());
    }

    @Test
    void testGetTotal_price() {
        assertEquals(10.0f, order.getTotal_price());
    }

    @Test
    void testSetTotal_price() {
        order.setTotal_price(15.0f);
        assertEquals(15.0, order.getTotal_price());
    }

    @Test
    void testGetType() {
        assertEquals("Drink", order.getType());
    }

    @Test
    void testSetType() {
        order.setType("Food");
        assertEquals("Food", order.getType());
    }

    @Test
    void testIsDelivered() {
        assertFalse(order.isDelivered());
    }

    @Test
    void testSetDelivered() {
        order.setDelivered(true);
        assertTrue(order.isDelivered());
    }
}
