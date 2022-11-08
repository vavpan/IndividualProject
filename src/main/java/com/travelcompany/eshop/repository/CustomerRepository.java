package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.model.Customer;

public interface CustomerRepository extends Repository<Customer> {

    void update(int customerId, String email);

    void updateAll(int customerId, Customer newCustomer);

}
