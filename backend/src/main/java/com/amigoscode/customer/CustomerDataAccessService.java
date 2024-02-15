package com.amigoscode.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("list")
public class CustomerDataAccessService implements CustomerDao {

    private static List<Customer> customers;
    static {
        customers = new ArrayList<>();
        Customer alex = new Customer(1L, "Alex", "alex@mail.com", 21);
        Customer jamila = new Customer(2L, "Jamila", "jamila@mail.com", 21);
        customers.add(alex);
        customers.add(jamila);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Long customerId) {

        return customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void removeCustomer(Long id) {
        customers.stream().filter(customer -> customer.getId().equals(id))
                .findFirst()
                .ifPresent(o->customers.remove(o));
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return customers.stream()
                .anyMatch(customer -> customer.getEmail().equals(email));
    }
}
