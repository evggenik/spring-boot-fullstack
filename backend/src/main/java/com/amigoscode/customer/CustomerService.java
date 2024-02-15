package com.amigoscode.customer;

import com.amigoscode.exception.DuplicateResourseException;
import com.amigoscode.exception.RequestValidationException;
import com.amigoscode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jdbc") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomer(Long customerId) {
        return customerDao.selectCustomerById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer with id [%s] not found"
                        .formatted(customerId)));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        if(customerDao.existsCustomerWithEmail(customerRegistrationRequest.email())) {
            throw new DuplicateResourseException("Email already taken");
        }
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDao.insertCustomer(customer);
    }

    public void deleteCustomer(Long id) {
        if(customerDao.selectCustomerById(id).isEmpty()) {
            throw new ResourceNotFoundException("Customer with id [%s] not found"
                    .formatted(id));
        }
        customerDao.removeCustomer(id);
    }

    public void updateCustomer(Long customerId, CustomerUpdateRequest updateRequest) {
        Customer customer = getCustomer(customerId);

        boolean changes = false;

        if(updateRequest.name()!=null && !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            changes = true;
        }
        if(updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())) {
            customer.setAge(updateRequest.age());
            changes = true;
        }
        if(updateRequest.email()!=null && !updateRequest.email().equals(customer.getEmail())) {
            if(customerDao.existsCustomerWithEmail(updateRequest.email())) {
                throw new DuplicateResourseException("Email already taken");
            }
            customer.setEmail(updateRequest.email());
            changes = true;
        }
        if(!changes) {
            throw new RequestValidationException("no data changes found");
        }
        customerDao.updateCustomer(customer);
    }

}
