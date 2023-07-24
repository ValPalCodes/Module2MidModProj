package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Customer;
<<<<<<< HEAD
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcCustomerDaoTests extends BaseDaoTests {

    private static final Customer CUSTOMER_1 = new Customer(1,"Customer 1", "Addr 1-1", "Addr 1-2",
            "City 1", "S1", "11111");
    private static final Customer CUSTOMER_2 = new Customer(2,"Customer 2", "Addr 2-1", "Addr 2-2",
            "City 2", "S2", "22222");
    private static final Customer CUSTOMER_3 = new Customer(3,"Customer 3", "Addr 3-1", null,
            "City 3", "S3", "33333");
    private static final Customer CUSTOMER_4 = new Customer(4,"Customer 4", "Addr 4-1", null,
            "City 4", "S4", "44444");

=======
import org.junit.Before;
import org.junit.Test;

public class JdbcCustomerDaoTests extends BaseDaoTests {

    private static final Customer CUSTOMER_1 = new Customer(1, "Customer 1", "Addr 1-1", "Addr 1-2", "City 1", "S1", "11111");
    private static final Customer CUSTOMER_2 = new Customer(2,"Customer 2", "Addr 2-1", "Addr 2-2", "City 2", "S2", "22222");
    private static final Customer CUSTOMER_3 = new Customer(3,"Customer 3", "Addr 3-1", null, "City 3", "S3", "33333");
    private static final Customer CUSTOMER_4 = new Customer(4,"Customer 4", "Addr 4-1", null, "City 4", "S4", "44444");
>>>>>>> 2c1bd90d86d220b22132a5e5800c9ce5932f9795

    private JdbcCustomerDao customerDao;

    @Before
    public void setup() {
        customerDao = new JdbcCustomerDao(dataSource);
    }
<<<<<<< HEAD

    @Test
    public void getCustomer_returns_correct_customer_for_id() {
        assertCustomersMatch(CUSTOMER_1, customerDao.getCustomer(1));
        assertCustomersMatch(CUSTOMER_2, customerDao.getCustomer(2));
        assertCustomersMatch(CUSTOMER_3, customerDao.getCustomer(3));
        assertCustomersMatch(CUSTOMER_4, customerDao.getCustomer(4));
    }

    @Test
    public void getCustomer_returns_null_when_id_not_found() {
        Customer customer = customerDao.getCustomer(5);
        Assert.assertNull(customer);
    }

    @Test
    public void getCustomers_gets_all_customers() {
        List<Customer> customersList = customerDao.getCustomers();
        Assert.assertEquals(4, customersList.size());

        assertCustomersMatch(CUSTOMER_1, customersList.get(0));
        assertCustomersMatch(CUSTOMER_2, customersList.get(1));
        assertCustomersMatch(CUSTOMER_3, customersList.get(2));
        assertCustomersMatch(CUSTOMER_4, customersList.get(3));
    }

    @Test
    public void createCustomer_returns_new_customer() {
        Customer customer = new Customer();
        customer.setCustomerId(5);
        customer.setName("Customer 5");
        customer.setStreetAddress1("Addr 5-1");
        customer.setStreetAddress2("Addr 5-2");
        customer.setCity("City 5");
        customer.setState("S5");
        customer.setZipCode("55555");

        Customer customerReturned = customerDao.createCustomer(customer);
        Assert.assertEquals(5, customerReturned.getCustomerId());
        Assert.assertEquals("Customer 5", customerReturned.getName());
        Assert.assertEquals("Addr 5-1", customerReturned.getStreetAddress1());
        Assert.assertEquals("Addr 5-2", customerReturned.getStreetAddress2());
        Assert.assertEquals("City 5", customerReturned.getCity());
        Assert.assertEquals("S5", customerReturned.getState());
        Assert.assertEquals("55555", customerReturned.getZipCode());

    }

    @Test
    public void created_customer_has_expected_values_when_retrieved(){
        Customer customer = new Customer();
        customer.setCustomerId(5);
        customer.setName("Customer 5");
        customer.setStreetAddress1("Addr 5-1");
        customer.setStreetAddress2("Addr 5-2");
        customer.setCity("City 5");
        customer.setState("S5");
        customer.setZipCode("55555");

        Customer customerReturned = customerDao.createCustomer(customer);
        Customer createdCustomer = customerDao.getCustomer(customerReturned.getCustomerId());
        assertCustomersMatch(customerReturned, createdCustomer);

    }

    @Test
    public void updated_customer_has_expected_values_when_retrieved(){
        Customer customer = customerDao.getCustomer(1);
        customer.setName("Customer 6");
        customer.setStreetAddress1("Addr 6-1");
        customer.setStreetAddress2(null);
        customer.setCity("City 6");
        customer.setState("S6");
        customer.setZipCode("66666");

        customerDao.updateCustomer(customer);
        Customer updatedCustomer = customerDao.getCustomer(1);
        assertCustomersMatch(customer, updatedCustomer);
    }


    private void assertCustomersMatch(Customer expected, Customer actual){
        Assert.assertEquals(expected.getCustomerId(), actual.getCustomerId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getStreetAddress1(), actual.getStreetAddress1());
        Assert.assertEquals(expected.getStreetAddress2(), actual.getStreetAddress2());
        Assert.assertEquals(expected.getCity(), actual.getCity());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getZipCode(), actual.getZipCode());

    }
=======
>>>>>>> 2c1bd90d86d220b22132a5e5800c9ce5932f9795
}
