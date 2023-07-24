package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.LineItem;
<<<<<<< HEAD
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcLineItemDao implements LineItemDao{
    private JdbcTemplate jdbcTemplate;

=======
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcLineItemDao implements LineItemDao {
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
    public JdbcLineItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

<<<<<<< HEAD

    @Override
    public List<LineItem> getLineItemsBySale(int saleId) {
        List<LineItem> lineItemsList =  new ArrayList<>();
        String sql = "SELECT line_item_id, sale_id, line_item.product_id, quantity, product.name AS product_name, price " +
                "FROM line_item " +
                "JOIN product ON product.product_id = line_item.product_id " +
                "WHERE sale_id = ? " +
                "ORDER BY line_item_id ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
        while(results.next()) {
            lineItemsList.add(mapRowToLineItems(results));
        }

        return lineItemsList;
    }

    private LineItem mapRowToLineItems(SqlRowSet results) {
        LineItem lineItem = new LineItem();
        lineItem.setLineItemId(results.getInt("line_item_id"));
        lineItem.setSaleId(results.getInt("sale_id"));
        lineItem.setProductId(results.getInt("product_id"));
        lineItem.setQuantity(results.getInt("quantity"));
        lineItem.setProductName(results.getString("product_name"));
        lineItem.setPrice(results.getBigDecimal("price"));
        return lineItem;
=======
    /**
     * Get all line items associated with a sale, ordered by line_item_id.
     *
     * @param saleId The id of the sale to get line items from.
     * @return All line items for a sale as LineItem objects in a List.
     */
    @Override
    public List<LineItem> getLineItemsBySale(int saleId) {
        return null;
>>>>>>> 2c1bd90d86d220b22132a5e5800c9ce5932f9795
    }
}
