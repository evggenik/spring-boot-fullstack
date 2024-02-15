package com.amigoscode.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    // get all customers
    List<Customer> selectAllCustomers();
    // get one customer by id
    Optional<Customer> selectCustomerById(Long id);
    // add one customer
    void insertCustomer(Customer customer);
    // delete one customer
    void removeCustomer(Long id);

    void updateCustomer(Customer update);

    boolean existsCustomerWithEmail(String email);
}
