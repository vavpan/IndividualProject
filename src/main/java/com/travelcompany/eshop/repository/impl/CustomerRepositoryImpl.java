package com.travelcompany.eshop.repository.impl;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.repository.CustomerRepository;

public class CustomerRepositoryImpl extends RepositoryImpl<Customer> implements CustomerRepository {


    @Override
    public void update(int customerId, String email) {
        Customer customer =  read(customerId);
        if(customer !=null){
            customer.setEmail(email);
        }
    }

    @Override
    public void updateAll(int customerId, Customer newData) {
        Customer customer =  read(customerId);
        if(customer !=null){

            customer.setName(newData.getName());
            customer.setAddress( newData.getAddress());
            customer.setEmail(newData.getEmail());
        }

    }
}
