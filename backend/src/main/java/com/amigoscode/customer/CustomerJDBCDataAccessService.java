package com.amigoscode.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class CustomerJDBCDataAccessService implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate, CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        String sql = "SELECT id, name, email, age FROM customer";
        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public Optional<Customer> selectCustomerById(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return jdbcTemplate.query(sql, customerRowMapper, id).stream().findFirst();
    }


    @Override
    public void insertCustomer(Customer customer) {
        var sql = """
                INSERT INTO customer (name, email, age)
                VALUES (?, ?, ?)
                """;
        int result = jdbcTemplate.update(
                sql, customer.getName(),
                customer.getEmail(),
                customer.getAge()
        );
        System.out.println("jdbcTemplate.update = " + result);
    }

    @Override
    public void removeCustomer(Long id) {
        String sql = """
                DELETE FROM customer
                WHERE id = ?
                """;
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void updateCustomer(Customer update) {
        if(update.getName()!=null) {
            String sql_name_update = "UPDATE customer SET name = ? WHERE id = ?";
            jdbcTemplate.update(sql_name_update, update.getName(), update.getId());
        }

        if(update.getAge()!=null) {
            String sql_age_update = "UPDATE customer SET age = ? WHERE id = ?";
            jdbcTemplate.update(sql_age_update, update.getAge(), update.getId());
        }
        String sql_email_update = """
                UPDATE customer
                SET email = ?
                WHERE id = ?
                """;
        jdbcTemplate.update(sql_email_update, update.getEmail(), update.getId());
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        String sql = """
                SELECT count(id) FROM customer
                WHERE email = ?
                """;
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return result!=null && result>0;
    }

    public boolean existsCustomerWithId(Long id) {
        String sql = """
                SELECT count(id) FROM customer
                WHERE id = ?
                """;
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return result!=null && result>0;
    }
}
