package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Product;
<<<<<<< HEAD
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao{

    private JdbcTemplate jdbcTemplate;

=======
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcProductDao implements ProductDao {
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
    public JdbcProductDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

<<<<<<< HEAD
    @Override
    public Product getProduct(int productId) {
        Product product = null;
        String sql = "SELECT * " +
                "FROM product " +
                "WHERE product_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        if(results.next()) {
            product = mapRowToProduct(results);
        }
        return product;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> productsList = new ArrayList<>();
        String sql = "SELECT product_id, name, description, price, image_name " +
                "FROM product ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            productsList.add(mapRowToProduct(results));
        }
        return productsList;
    }

    @Override
    public List<Product> getProductsWithNoSales() {
        List<Product> productsWithNoSales = new ArrayList<>();
        String sql = "select * from product WHERE product_id not in (select product_id from line_item)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            productsWithNoSales.add(mapRowToProduct(results));
        }
        return productsWithNoSales;
    }

    @Override
    public Product createProduct(Product newProduct) {
        String sql = "INSERT INTO product " +
                "(name, description, price, image_name) " +
                "VALUES (?, ?, ?, ?) " +
                "RETURNING product_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newProduct.getName(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getImageName());
        if(results.next()) {
            int newId = results.getInt("product_id");
            newProduct.setProductId(newId);
        }
        return newProduct;
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        String sql = "UPDATE product " +
                "SET name = ?, description = ?, price = ?, image_name = ? " +
                "WHERE product_id = ?;";
        jdbcTemplate.update(sql, updatedProduct.getName(), updatedProduct.getDescription(), updatedProduct.getPrice(), updatedProduct.getImageName(), updatedProduct.getProductId());
    }

    @Override
    public void deleteProduct(int productId) {
        jdbcTemplate.update(
                "DELETE FROM line_item WHERE product_id = ?;",
                productId
        );
        jdbcTemplate.update(
                "DELETE FROM product WHERE product_id = ?;",
                productId
        );
    }

    private Product mapRowToProduct(SqlRowSet results) {
        Product product = new Product();
        product.setProductId(results.getInt("product_id"));
        product.setName(results.getString("name"));
        product.setDescription(results.getString("description"));
        product.setPrice(results.getBigDecimal("price"));
        product.setImageName(results.getString("image_name"));
        return product;
=======
    /**
     * Get a product from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param productId The id of the product.
     * @return A filled out Product object, or null if the id is invalid.
     */
    @Override
    public Product getProduct(int productId) {
        return null;
    }

    /**
     * Get all products from the datastore, ordered by product_id.
     *
     * @return All products as Product objects in a List.
     */
    @Override
    public List<Product> getProducts() {
        return null;
    }

    /**
     * Get a list of product for which there are no sales yet, ordered by product_id. Only these products may
     * be deleted form the datastore.
     *
     * @return All products as Product objects in a List.
     */
    @Override
    public List<Product> getProductsWithNoSales() {
        return null;
    }

    /**
     * Add a new product into the datastore.
     *
     * @param newProduct The Product object to add.
     * @return The added Product object with its new id filled in.
     */
    @Override
    public Product createProduct(Product newProduct) {
        return null;
    }

    /**
     * Update a product in the datastore. Only called on products that
     * are already in the datastore.
     *
     * @param updatedProduct The Product object to update.
     */
    @Override
    public void updateProduct(Product updatedProduct) {

    }

    /**
     * Remove a product from the datastore.
     *
     * @param productId The id of the product to remove. If the id doesn't exist, no error will occur.
     */
    @Override
    public void deleteProduct(int productId) {

>>>>>>> 2c1bd90d86d220b22132a5e5800c9ce5932f9795
    }
}
