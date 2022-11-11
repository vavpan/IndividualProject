package com.travelcompany.eshop.model;

import com.travelcompany.eshop.enums.Category;


/**
 *  Category of customer
 */
public class Individual extends Customer{

    // Default Constructor
    public Individual() {
    }

    public Individual(String name, String email, String address, String nationality, Category category) {
        super(name, email, address, nationality, category);
    }
}
