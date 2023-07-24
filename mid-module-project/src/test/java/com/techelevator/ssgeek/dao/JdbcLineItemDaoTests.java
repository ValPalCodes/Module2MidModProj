package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.LineItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcLineItemDaoTests extends BaseDaoTests {

    private static final LineItem LINE_ITEM_1 = new LineItem(1, 1 , 1, 1,
            "Product 1",
            BigDecimal.valueOf(9.99));
    private static final LineItem LINE_ITEM_2 = new LineItem(2, 1 , 2, 1,
            "Product 2",
            BigDecimal.valueOf(19.00));
    private static final LineItem LINE_ITEM_3 = new LineItem(3, 1 , 4, 1,
            "Product 4",
            BigDecimal.valueOf(0.99));
    private static final LineItem LINE_ITEM_4 = new LineItem(4, 2 , 4, 10,
            "Product 4",
            BigDecimal.valueOf(0.99));
    private static final LineItem LINE_ITEM_5 = new LineItem(5, 2 , 1, 10,
            "Product 1",
            BigDecimal.valueOf(9.99));
    private static final LineItem LINE_ITEM_6 = new LineItem(6, 3 , 1, 100,
            "Product 1",
            BigDecimal.valueOf(9.99));


    private JdbcLineItemDao lineItemDao;

    @Before
    public void setup() {
        lineItemDao = new JdbcLineItemDao(dataSource);
    }

    @Test
    public void getLineItems_returns_correct_lineItem_for_saleId(){
        List<LineItem> lineItemList = lineItemDao.getLineItemsBySale(1);
        Assert.assertEquals(3, lineItemList.size());

        assertLineItemsMatch(LINE_ITEM_1, lineItemList.get(0));
        assertLineItemsMatch(LINE_ITEM_2, lineItemList.get(1));
        assertLineItemsMatch(LINE_ITEM_3, lineItemList.get(2));


        List<LineItem> lineitemsList2 = lineItemDao.getLineItemsBySale(2);
        Assert.assertEquals(2, lineitemsList2.size());

        assertLineItemsMatch(LINE_ITEM_4, lineitemsList2.get(0));
        assertLineItemsMatch(LINE_ITEM_5, lineitemsList2.get(1));

        List<LineItem> lineItemsList3 = lineItemDao.getLineItemsBySale(3);
        Assert.assertEquals(1, lineItemsList3.size());

        assertLineItemsMatch(LINE_ITEM_6, lineItemsList3.get(0));


    }


    @Test
    public void getLineItemsBySale_returns_correct_values(){
        List<LineItem> lineItemList = lineItemDao.getLineItemsBySale(1);
        Assert.assertEquals(3, lineItemList.size());

        assertLineItemsMatch(LINE_ITEM_1, lineItemList.get(0));
        assertLineItemsMatch(LINE_ITEM_2, lineItemList.get(1));
        assertLineItemsMatch(LINE_ITEM_3, lineItemList.get(2));

        List<LineItem> lineItemsList2 = lineItemDao.getLineItemsBySale(2);
        Assert.assertEquals(2, lineItemsList2.size());

        assertLineItemsMatch(LINE_ITEM_4, lineItemsList2.get(0));
        assertLineItemsMatch(LINE_ITEM_5, lineItemsList2.get(1));

        List<LineItem> lineItemList1 = lineItemDao.getLineItemsBySale(3);
        Assert.assertEquals(1, lineItemList1.size());

        assertLineItemsMatch(LINE_ITEM_6, lineItemList1.get(0));
    }

    @Test
    public void getLineItemsBySale_returns_null_when_sale_id_not_found(){
        List<LineItem> lineItems = lineItemDao.getLineItemsBySale(4);
        Assert.assertEquals(0, lineItems.size());

    }


    private void assertLineItemsMatch(LineItem expected, LineItem actual){
        Assert.assertEquals(expected.getLineItemId(), actual.getLineItemId());
        Assert.assertEquals(expected.getSaleId(), actual.getSaleId());
        Assert.assertEquals(expected.getProductId(), actual.getProductId());
        Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
        Assert.assertEquals(expected.getProductName(), actual.getProductName());
        Assert.assertEquals(expected.getPrice().stripTrailingZeros(), actual.getPrice().stripTrailingZeros());

    }
}
