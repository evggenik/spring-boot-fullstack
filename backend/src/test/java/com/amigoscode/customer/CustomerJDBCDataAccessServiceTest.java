package com.amigoscode.customer;

import com.amigoscode.AbstractTestcontainers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class CustomerJDBCDataAccessServiceTest extends AbstractTestcontainers {

    private CustomerJDBCDataAccessService underTest;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        underTest = new CustomerJDBCDataAccessService(
                getJdbcTemplate(),
                customerRowMapper
        );
    }

    @Test
    void itShouldSelectAllCustomers() {

        // Given
        Customer customer = new Customer("Wasya", "wasya@mail.ru", 20);
        underTest.insertCustomer(customer);
        // When
        List<Customer> customers = underTest.selectAllCustomers();
        // Then
        assertThat(customers).isNotEmpty();
    }

    @Test
    void itShouldSelectCustomerById() {
        // Given
        String email = "wasya2@mail.ru";
        Customer customer = new Customer("Wasya", email, 20);
        underTest.insertCustomer(customer);
        Long id = underTest.selectAllCustomers().stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
        // When
        Optional<Customer> actual = underTest.selectCustomerById(id);
        // Then
        assertThat(actual).isPresent().hasValueSatisfying(c->{
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());

        });
    }

    @Test
    void itShouldReturnEmptyWhenSelectCustomerById() {
        // Given
        Long id = -1L;
        // When
       var actual = underTest.selectCustomerById(id);
        // Then
        assertThat(actual).isEmpty();
    }

    @Test
    void itShouldInsertCustomer() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldRemoveCustomer() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldUpdateCustomer() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldExistsCustomerWithEmail() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldExistsCustomerWithId() {
        // Given

        // When

        // Then

    }
}