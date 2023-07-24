package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDaoTests extends BaseDaoTests {

    private static final Product PRODUCT_1 = new Product(1,"Product 1", "Description 1",
            new BigDecimal("9.99"), "product-1.png");
    private static final Product PRODUCT_2 = new Product(2,"Product 2", "Description 2",
            new BigDecimal("19.00"), "product-2.png");
    private static final Product PRODUCT_3 = new Product(3,"Product 3", "Description 3",
            new BigDecimal("123.45"), "product-3.png");
    private static final Product PRODUCT_4 = new Product(4,"Product 4", "Description 4",
            new BigDecimal("0.99"), "product-4.png");



    private JdbcProductDao productDao;

    @Before
    public void setup() {

        productDao = new JdbcProductDao(dataSource);
    }

    @Test
    public void getProduct_returns_correct_products_with_id(){
        assertProductsMatch(PRODUCT_1, productDao.getProduct(1));
        assertProductsMatch(PRODUCT_2, productDao.getProduct(2));
        assertProductsMatch(PRODUCT_3, productDao.getProduct(3));
        assertProductsMatch(PRODUCT_4, productDao.getProduct(4));
    }

    @Test
    public void getProduct_returns_null_for_products_with_id_not_found(){
        Product product = productDao.getProduct(7);
        Assert.assertNull(product);
    }

    @Test
    public void getProducts_gets_all_products(){
        List<Product> productList = productDao.getProducts();
        Assert.assertEquals(4, productList.size());
    }

    @Test
    public void getProducts_returns_products_with_no_sales(){
        List<Product> productListWithNoSales = productDao.getProductsWithNoSales();
        Assert.assertEquals(1, productListWithNoSales.size());
    }

    @Test
    public void createProduct_returns_expected_values_when_retrieved(){
        Product product = new Product();
        product.setName("Product 5");
        product.setDescription("description 5");
        product.setPrice(BigDecimal.valueOf(11.99));
        product.setImageName("product-5.png");

        Product productReturned = productDao.createProduct(product);
        Assert.assertEquals("Product 5", productReturned.getName());
        Assert.assertEquals("description 5", productReturned.getDescription());
        Assert.assertEquals(BigDecimal.valueOf(11.99), productReturned.getPrice());
        Assert.assertEquals("product-5.png", productReturned.getImageName());
    }

    @Test
    public void createdProduct_has_expected_values_when_retrieved(){
        Product product = new Product();
        product.setName("Product 5");
        product.setDescription("description 5");
        product.setPrice(BigDecimal.valueOf(11.99));
        product.setImageName("product-5.png");

        Product productReturned = productDao.createProduct(product);
        Product createdProduct = productDao.getProduct(productReturned.getProductId());
        assertProductsMatch(productReturned, createdProduct);
    }


    @Test
    public void updateProduct_has_expected_values_when_retrieved(){
        Product product = productDao.getProduct(2);
        product.setName("Product 7");
        product.setDescription("Description 7");
        product.setPrice(BigDecimal.valueOf(14.99));
        product.setImageName("product-7.png");

        productDao.updateProduct(product);
        Product updatedProduct = productDao.getProduct(2);
        assertProductsMatch(product, updatedProduct);
    }

    @Test
    public void deletedProduct_has_expected_values_when_retrieved(){
        Product product = productDao.getProduct(4);
        productDao.deleteProduct(product.getProductId());
        Product updatedProduct = productDao.getProduct(4);
        Assert.assertNull("Delete Product failed to remove product from database",updatedProduct);
    }

    private void assertProductsMatch(Product expected, Product actual){
        Assert.assertEquals(expected.getProductId(), actual.getProductId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getImageName(), actual.getImageName());
    }



}
