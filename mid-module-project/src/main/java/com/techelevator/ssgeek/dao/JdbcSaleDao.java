package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Sale;
<<<<<<< HEAD
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcSaleDao implements SaleDao {
    private JdbcTemplate jdbcTemplate;

=======
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcSaleDao implements SaleDao {
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
    public JdbcSaleDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

<<<<<<< HEAD

    @Override
    public Sale getSale(int saleId) {
        Sale sale = null;
        String sql = "SELECT sale_id, customer.customer_id, sale_date, ship_date, customer.name AS customer_name " +
                    "FROM sale " +
                    "JOIN customer ON customer.customer_id = sale.customer_id " +
                    "WHERE sale_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
        if(results.next()) {
            sale = mapRowToSale(results);
        }
        return sale;
    }

    @Override
    public List<Sale> getSalesUnshipped() {
        List<Sale> salesUnshipped = new ArrayList<>();
        String sql = "SELECT sale_id, customer.customer_id, sale_date, ship_date, customer.name AS customer_name " +
                "FROM sale " +
                "JOIN customer ON customer.customer_id = sale.customer_id " +
                "WHERE ship_date IS null ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            salesUnshipped.add(mapRowToSale(results));
        }
        return salesUnshipped;
    }

    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        List<Sale> salesByCustomer = new ArrayList<>();
        String sql = "SELECT sale_id, customer.customer_id, sale_date, ship_date, customer.name AS customer_name " +
                "FROM sale " +
                "JOIN customer ON customer.customer_id = sale.customer_id " +
                "WHERE sale.customer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        while (results.next()) {
            salesByCustomer.add(mapRowToSale(results));
        }
        return salesByCustomer;
    }

    @Override
    public List<Sale> getSalesByProductId(int productId) {
        List<Sale> salesByProductId = new ArrayList<>();
        String sql = "SELECT sale_id, customer.customer_id, sale_date, ship_date, customer.name AS customer_name " +
                "FROM sale " +
                "JOIN customer ON customer.customer_id = sale.customer_id " +
                "WHERE sale_id IN " +
                "(SELECT distinct sale_id FROM line_item WHERE product_id = ?) ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        while(results.next()) {
            salesByProductId.add(mapRowToSale(results));
        }
        return salesByProductId;
    }

    @Override
    public Sale createSale(Sale newSale) {
        String sql = "INSERT INTO sale " +
                "(customer_id, sale_date, ship_date) " +
                "VALUES (?, ?, ?) " +
                "RETURNING sale_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newSale.getCustomerId(), newSale.getSaleDate(), newSale.getShipDate());
        if(results.next()) {
            int newId = results.getInt("sale_id");
            newSale.setSaleId(newId);

        }
        return newSale;
    }

    @Override
    public void updateSale(Sale updatedSale) {
        String sql = "UPDATE sale " +
                "SET customer_id = ?, sale_date = ?, ship_date = ? " +
                "WHERE sale_id = ?;";
        jdbcTemplate.update(sql, updatedSale.getCustomerId(), updatedSale.getSaleDate(), updatedSale.getShipDate(), updatedSale.getSaleId());
    }

    @Override
    public void deleteSale(int saleId) {
        jdbcTemplate.update(
                "DELETE FROM line_item WHERE sale_id = ?;",
                saleId
        );
        jdbcTemplate.update(
                "DELETE FROM sale where sale_id = ?;",
                saleId
        );

    }


    private Sale mapRowToSale(SqlRowSet results) {
        Sale sale = new Sale();
        sale.setSaleId(results.getInt("sale_id"));
        sale.setCustomerId(results.getInt("customer_id"));
        Date saleDate = results.getDate("sale_date");
        sale.setSaleDate(saleDate.toLocalDate());
        if (results.getDate("ship_date") != null) {
            sale.setShipDate(results.getDate("ship_date").toLocalDate());
        }
        sale.setCustomerName(results.getString("customer_name"));
        return sale;
=======
    /**
     * Get a sale from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param saleId The id of the sale.
     * @return A filled out Sale object, null if the id is not found in the database.
     */
    @Override
    public Sale getSale(int saleId) {
        return null;
    }

    /**
     * Get all sales from the datastore that have not been shipped (ship date is null),
     * ordered by sale_id. Only unshipped orders may be shipped or deleted.
     *
     * @return All unshipped sales as Sale objects in a List.
     */
    @Override
    public List<Sale> getSalesUnshipped() {
        return null;
    }

    /**
     * Get all sales from the datastore for a given customer, ordered by sale_id.
     *
     * @param customerId The id of the customer.
     * @return All sales as Sale objects in a List.
     */
    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        return null;
    }

    /**
     * Get all sales from the datastore for a given product, ordered by sale_id.
     *
     * @param productId The id of the product.
     * @return All sales as Sale objects in a List.
     */
    @Override
    public List<Sale> getSalesByProductId(int productId) {
        return null;
    }

    /**
     * Add a new sale into the datastore.
     *
     * @param newSale The Sale object to add.
     * @return The added Sale object with its new id filled in.
     */
    @Override
    public Sale createSale(Sale newSale) {
        return null;
    }

    /**
     * Update a sale to the datastore. Only called on sales that
     * are already in the datastore.
     *
     * @param updatedSale The Sale object to update.
     */
    @Override
    public void updateSale(Sale updatedSale) {

    }

    /**
     * Remove a sale from the datastore.
     *
     * @param saleId The id of the sale to remove. If the id doesn't exist, no error will occur.
     */
    @Override
    public void deleteSale(int saleId) {

>>>>>>> 2c1bd90d86d220b22132a5e5800c9ce5932f9795
    }
}
