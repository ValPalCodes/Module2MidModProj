package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Sale;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class JdbcSaleDaoTests extends BaseDaoTests {

    private static final Sale SALE_1 = new Sale(1, 1, LocalDate.parse("2022-01-01"), null, "Customer 1");
    private static final Sale SALE_2 = new Sale(2, 1, LocalDate.parse("2022-02-01"), LocalDate.parse("2022-02-02"), "Customer 1");
    private static final Sale SALE_3 = new Sale(3, 2, LocalDate.parse("2022-03-01"), null, "Customer 2");
    private static final Sale SALE_4 = new Sale(4, 2, LocalDate.parse("2022-01-01"), LocalDate.parse("2022-01-02"), "Customer 2");




    private JdbcSaleDao saleDao;

    @Before
    public void setup() {

        saleDao = new JdbcSaleDao(dataSource);
    }


    @Test
    public void getSale_returns_correct_sale_with_id(){
        assertSalesMatch(SALE_1, saleDao.getSale(1));
        assertSalesMatch(SALE_2, saleDao.getSale(2));
        assertSalesMatch(SALE_3, saleDao.getSale(3));
        assertSalesMatch(SALE_4, saleDao.getSale(4));
    }

    @Test
    public void getSale_returns_null_for_sales_without_id(){
        Sale sale = saleDao.getSale(8);
        Assert.assertNull(sale);
    }

    @Test
    public void getSalesUnshipped_returns_correct_values(){
        List<Sale> salesUnshipped = saleDao.getSalesUnshipped();
        Assert.assertEquals(2, salesUnshipped.size());
    }

    @Test
    public void getSales_with_customer_id(){
        List<Sale> salesWithCustomerId = saleDao.getSalesByCustomerId(1);
        Assert.assertEquals(2, salesWithCustomerId.size());

    }

    @Test
    public void getSales_with_product_id(){
        List<Sale> salesWithProductId = saleDao.getSalesByProductId(2);
        Assert.assertEquals(1, salesWithProductId.size());
    }

    @Test
    public void createSale_returns_expected_values_when_retrieved(){
        Sale sale = new Sale();
        sale.setCustomerId(4);
        sale.setSaleDate(LocalDate.parse("2022-03-02"));
        sale.setShipDate(null);

        Sale saleReturned = saleDao.createSale(sale);
        Assert.assertEquals(4, saleReturned.getCustomerId());
        Assert.assertEquals(LocalDate.parse("2022-03-02"), saleReturned.getSaleDate());
        Assert.assertEquals(null, saleReturned.getShipDate());
    }

    @Test
    public void createSale_has_expected_values_when_retrieved(){
        Sale sale = new Sale();
        sale.setCustomerId(4);
        sale.setSaleDate(LocalDate.parse("2022-03-02"));
        sale.setShipDate(null);

        Sale saleReturned = saleDao.createSale(sale);
        Sale createdSale = saleDao.getSale(saleReturned.getSaleId());
        assertSalesMatch(saleReturned, createdSale);

    }

    @Test
    public void updateSale_has_expected_values_when_retrieved(){
        Sale sale = saleDao.getSale(1);
        sale.setCustomerId(1);
        sale.setSaleDate(LocalDate.parse("2023-02-01"));
        sale.setShipDate(LocalDate.parse("2023-02-02"));

        saleDao.updateSale(sale);
        Sale updatedSale = saleDao.getSale(1);
        assertSalesMatch(sale, updatedSale);
    }

    @Test
    public void deletedSale_has_expected_values_when_retrieved(){
        Sale sale = saleDao.getSale(1);
        saleDao.deleteSale(sale.getSaleId());
        Sale updatedSale = saleDao.getSale(1);
        Assert.assertNull("Delete Sale failed to remove sale from database", updatedSale);
    }

    public void assertSalesMatch(Sale expected, Sale actual) {
        Assert.assertEquals(expected.getSaleId(), actual.getSaleId());
        Assert.assertEquals(expected.getCustomerId(), actual.getCustomerId());
        Assert.assertEquals(expected.getSaleDate(), actual.getSaleDate());
        Assert.assertEquals(expected.getShipDate(), actual.getShipDate());
        Assert.assertEquals(expected.getCustomerName(), actual.getCustomerName());
    }
}
