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
     * @param ID Staff ID.
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
     * @return staff's ID.
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
     * @param name name.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * @return staff's surname.
     */
    public String getSurname() {
        return Surname;
    }

    /**
     * Set staff's surname.
     * @param surname surname.
     */
    public void setSurname(String surname) {
        Surname = surname;
    }

    /**
     * Set staff's password.
     * @param password password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return staff's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return staff's surname.
     */
    public String getDateOfBirth() {
        return DateOfBirth;
    }

    /**
     * Set Staff's date of birth.
     * @param dateOfBirth date of birth.
     */
    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    /**
     * @return is staff on holiday.
     */
    public int getHoliday() {
        return holiday;
    }

    /**
     * Set staff to onHoliday/onDuty.
     * @param holiday 1/0 is holiday.
     */
    public void setHoliday(int holiday) {
        this.holiday = holiday;
    }

    /**
     * @return staff's remaining holidays.
     */
    public int getRemainingHoliday() {
        return remainingHoliday;
    }

    /**
     * Set staff's remaining holidays.
     * @param remainingHoliday how many holiday left
     */
    public void setRemainingHoliday(int remainingHoliday) {
        this.remainingHoliday = remainingHoliday;
    }

    /**
     * @return staff's email address.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Set staff's email address.
     * @param email email address.
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * @return staff's phone number.
     */
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    /**
     * Set staff's phone number.
     * @param phoneNumber phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    /**
     * @return staff's roles.
     */
    public ArrayList<String> getRoles() {
        return roles;
    }

    /**
     * Set staff's roles to a new set of roles.
     * @param roles roles.
     */
    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    /**
     * Add a staff role.
     * @param role role.
     */
    public void addRole(String role) {
        roles.add(role);
    }

    /**
     * Remove a staff's role.
     * @param role role.
     */
    public void removeRole(String role) {
        roles.remove(role);
    }
}
