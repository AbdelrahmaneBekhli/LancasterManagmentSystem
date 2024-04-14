package com.example.lancastermanagmentsystem;

public class Staff {
    private final String id;
    private String name;
    private String surname;
    private String role;
    private String phoneNumber;
    private String email;
    private String gender;
    private String dob;
    private String address;
    private String postcode;
    private String county;
    private float holiday;
    private float remainingHoliday;


    public Staff(String id, String name, String surname, String role, String phoneNumber, String email, String gender, String dob, String address, String postcode, String county, float holiday, float remainingHoliday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.postcode = postcode;
        this.county = county;
        this.holiday = holiday;
        this.remainingHoliday = remainingHoliday;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public float getHoliday() {
        return holiday;
    }

    public void setHoliday(float holiday) {
        this.holiday = holiday;
    }

    public float getRemainingHoliday() {
        return remainingHoliday;
    }

    public void setRemainingHoliday(float remainingHoliday) {
        this.remainingHoliday = remainingHoliday;
    }
}
