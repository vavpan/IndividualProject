package com.travelcompany.eshop.model;

import com.travelcompany.eshop.enums.Category;


/**
 *  Category of customer
 */
public class Business extends Customer{

    public Business() {
    }

    public Business(String name, String email, String address, String nationality, Category category) {
        super(name, email, address, nationality, category);
    }


}
