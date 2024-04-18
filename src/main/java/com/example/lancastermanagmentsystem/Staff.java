package com.example.lancastermanagmentsystem;

import java.util.ArrayList;
/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
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

    /**
     * Constructor.
     * @param ID StaffID.
     * @param name Staff name.
     * @param surname Staff surname.
     * @param phoneNumber Staff phone number.
     * @param email Staff email address.
     * @param dateOfBirth Staff date of birth.
     * @param roles Array of all staff's roles.
     * @param holiday Binary to check whether staff is on holiday.
     * @param remainingHoliday Counter for remaining staff holiday.
     */
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

    /**
     * Set ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * @return staff's name.
     */
    public String getName() {
        return Name;
    }

    /**
     * Set staff's name.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * @return Staff's surname.
     */
    public String getSurname() {
        return Surname;
    }

    /**
     * Set staff's surname.
     */
    public void setSurname(String surname) {
        Surname = surname;
    }

    /**
     * Set staff's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Staff's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return Staff's surname.
     */
    public String getDateOfBirth() {
        return DateOfBirth;
    }

    /**
     * @return Staff's surname.
     */
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
