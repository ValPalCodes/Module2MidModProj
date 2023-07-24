package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
<<<<<<< HEAD
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

=======

import javax.print.DocFlavor;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao {
    /**
     * JDBC template used to access database throughout this DAO
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor
     *
     * @param dataSource Data source from main application
     */
>>>>>>> 2c1bd90d86d220b22132a5e5800c9ce5932f9795
    public JdbcCustomerDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

<<<<<<< HEAD
    @Override
    public Customer getCustomer(int customerId) {
        Customer customer = null;
        String sql = "SELECT * " +
                "FROM customer " +
                "WHERE customer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        if (results.next()) {
            customer = mapRowToCustomer(results);
        }
        return customer;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM customer ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            customerList.add(mapRowToCustomer(results));
        }
        return customerList;
    }

    @Override
    public Customer createCustomer(Customer newCustomer) {
        String sql = "INSERT INTO customer " +
                    "(name, street_address1, street_address2, city, state, " +
                    "zip_code) " +
                    "VALUES (?, ?, ?, ?, ?, ?) " +
                    "RETURNING customer_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newCustomer.getName(), newCustomer.getStreetAddress1(),
                            newCustomer.getStreetAddress2(), newCustomer.getCity(), newCustomer.getState(),
                            newCustomer.getZipCode());
        if (results.next()) {
            int newId = results.getInt("customer_id");
            newCustomer.setCustomerId(newId);
        }
        return newCustomer;
    }

    @Override
    public void updateCustomer(Customer updatedCustomer) {
        String sql = "UPDATE customer " +
                    "SET name = ?, street_address1 = ?, street_address2 = ?, city = ?, state = ?, zip_code = ? " +
                    "WHERE customer_id = ?;";
        jdbcTemplate.update(sql, updatedCustomer.getName(), updatedCustomer.getStreetAddress1(),
                updatedCustomer.getStreetAddress2(), updatedCustomer.getCity(), updatedCustomer.getState(),
                updatedCustomer.getZipCode(), updatedCustomer.getCustomerId());

    }

    private Customer mapRowToCustomer(SqlRowSet results) {
        Customer customer = new Customer();
        customer.setCustomerId(results.getInt("customer_id"));
        customer.setName(results.getString("name"));
        customer.setStreetAddress1(results.getString("street_address1"));
        customer.setStreetAddress2(results.getString("street_address2"));
        customer.setCity(results.getString("city"));
        customer.setState(results.getString("state"));
        customer.setZipCode(results.getString("zip_code"));
        return customer;
    }
=======
    /**
     * Get a customer from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param customerId The id of the customer.
     * @return A filled out Customer object, null if the customerId isn't in the database.
     */
    @Override
    public Customer getCustomer(int customerId) {
        return jdbcTemplate.queryForObject(
            "select * from customer where customer_id=?",
            new Object[] { customerId },
            this::mapRowToCustomer
        );
    }

    /**
     * Get all customers from the datastore, ordered by customer_id.
     *
     * @return All customers as Customer objects in a List.
     */
    @Override
    public List<Customer> getCustomers() {
        return jdbcTemplate.query(
            "select * from customer",
            new Object[] {},
            this::mapRowToCustomer
        );
    }

    /**
     * Add a new customer into the datastore.
     *
     * @param newCustomer the Customer object to add.
     * @return The added Customer object with its new id filled in.
     */
    @Override
    public Customer createCustomer(Customer newCustomer) {
        Integer newId = jdbcTemplate.queryForObject(
            "insert into customer (name, street_address1, street_address2, city, state, zip_code) values (?, ?, ?, ?, ?, ?) return customer_id",
            Integer.class,
            newCustomer.getName(),
            newCustomer.getStreetAddress1(),
            newCustomer.getStreetAddress2(),
            newCustomer.getCity(),
            newCustomer.getState(),
            newCustomer.getZipCode()
        );

        if (newId != null) {
            return getCustomer(newId);
        } else {
            return null;
        };
    }

    /**
     * Update a customer to the datastore. Only called on customers that
     * are already in the datastore.
     *
     * @param updatedCustomer The Customer object to update.
     */
    @Override
    public void updateCustomer(Customer updatedCustomer) {

    }

    /**
     * Maps a row to a customer
     *
     * @param row Result set at row to map
     * @param rowNumber Row number
     * @return Customer object for row
     */
    public Customer mapRowToCustomer(ResultSet row, int rowNumber) throws SQLException {
        return new Customer(
            row.getInt("customer_id"),
            row.getString("name"),
            row.getString("street_address1"),
            row.getString("street_address2"),
            row.getString("city"),
            row.getString("state"),
            row.getString("zip_code")
        );
    }

>>>>>>> 2c1bd90d86d220b22132a5e5800c9ce5932f9795
}
