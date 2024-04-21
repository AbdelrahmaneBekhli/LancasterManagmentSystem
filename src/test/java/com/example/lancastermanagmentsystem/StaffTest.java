package com.example.lancastermanagmentsystem;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class StaffTest {
    private final Staff staff = new Staff(1, "John", "Doe", "123456789", "john.doe@example.com", "1990-01-01", new ArrayList<>(), 0, 20);

    @Test
    void getID() {
        assertEquals(1, staff.getID());
    }

    @Test
    void getName() {
        assertEquals("John", staff.getName());
    }

    @Test
    void setName() {
        staff.setName("Jane");
        assertEquals("Jane", staff.getName());
    }

    @Test
    void getSurname() {
        assertEquals("Doe", staff.getSurname());
    }

    @Test
    void setSurname() {
        staff.setSurname("Smith");
        assertEquals("Smith", staff.getSurname());
    }

    @Test
    void setPassword() {
        staff.setPassword("1234");
        assertEquals("1234", staff.getPassword());
    }

    @Test
    void getPassword() {
        staff.setPassword("1234");
        assertEquals("1234", staff.getPassword());
    }

    @Test
    void getDateOfBirth() {
        assertEquals("1990-01-01", staff.getDateOfBirth());
    }

    @Test
    void setDateOfBirth() {
        staff.setDateOfBirth("1985-05-15");
        assertEquals("1985-05-15", staff.getDateOfBirth());
    }

    @Test
    void getHoliday() {
        assertEquals(0, staff.getHoliday());
    }

    @Test
    void setHoliday() {
        staff.setHoliday(1);
        assertEquals(1, staff.getHoliday());
    }

    @Test
    void getRemainingHoliday() {
        assertEquals(20, staff.getRemainingHoliday());
    }

    @Test
    void setRemainingHoliday() {
        staff.setRemainingHoliday(15);
        assertEquals(15, staff.getRemainingHoliday());
    }

    @Test
    void getEmail() {
        assertEquals("john.doe@example.com", staff.getEmail());
    }

    @Test
    void setEmail() {
        staff.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", staff.getEmail());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("123456789", staff.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        staff.setPhoneNumber("987654321");
        assertEquals("987654321", staff.getPhoneNumber());
    }

    @Test
    void getRoles() {
        staff.addRole("Manager");
        assertEquals(1, staff.getRoles().size());
        assertTrue(staff.getRoles().contains("Manager"));
    }

    @Test
    void setRoles() {
        ArrayList<String> newRoles = new ArrayList<>();
        newRoles.add("Admin");
        staff.setRoles(newRoles);
        assertEquals(1, staff.getRoles().size());
        assertTrue(staff.getRoles().contains("Admin"));
    }
    @Test
    void addRole() {
        staff.addRole("Manager");
        staff.addRole("Admin");
        assertEquals(2, staff.getRoles().size());
        assertTrue(staff.getRoles().contains("Manager"));
    }

    @Test
    void removeRole() {
        staff.addRole("Manager");
        staff.addRole("Admin");
        staff.removeRole("Manager");
        assertFalse(staff.getRoles().contains("Manager"));
        assertTrue(staff.getRoles().contains("Admin"));
    }
}