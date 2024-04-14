package com.example.lancastermanagmentsystem;

import java.util.ArrayList;

public class Staff {
    private final int ID;
    private String Name;
    private String Surname;
    private String PhoneNumber;
    private String Email;
    private String DateOfBirth;
    private int holiday;
    private int remainingHoliday;
    private ArrayList<String> roles;
    private String password;

    public Staff(int ID, String name, String surname, String phoneNumber, String email,String dateOfBirth, ArrayList<String> roles, int holiday, int remainingHoliday) {
        this.ID = ID;
        this.Name = name;
        this.Surname = surname;
        this.PhoneNumber = phoneNumber;
        this.Email = email;
        this.DateOfBirth = dateOfBirth;
        this.holiday = holiday;
        this.remainingHoliday = remainingHoliday;
        this.roles = roles;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public int getHoliday() {
        return holiday;
    }

    public void setHoliday(int holiday) {
        this.holiday = holiday;
    }

    public int getRemainingHoliday() {
        return remainingHoliday;
    }

    public void setRemainingHoliday(int remainingHoliday) {
        this.remainingHoliday = remainingHoliday;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public void addRole(String role) {
        roles.add(role);
    }

    public void removeRole(String role) {
        roles.remove(role);
    }
}
