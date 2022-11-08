package com.travelcompany.eshop.model;

import com.travelcompany.eshop.enums.Category;


public class Customer extends PersistenceClass{


    private String name;
    private String email;
    private String address;
    private String nationality;
    private Category category;

    public Customer(){

    }
    public Customer(String name, String email, String address, String nationality, Category category) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

