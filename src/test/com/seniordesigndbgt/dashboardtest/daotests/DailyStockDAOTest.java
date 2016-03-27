package com.seniordesigndbgt.dashboardtest.daotests;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.seniordesigndbgt.dashboard.model.DailyStock;
import com.seniordesigndbgt.dashboard.dao.DailyStockDAO;

import java.util.List;
/**
 * Created by kamehardy on 3/21/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class DailyStockDAOTest {
    @Autowired
    private SessionFactory _sessionFactory;

    @Autowired
    private DailyStockDAO dailystockDAO;

    @Test
    public void testSave() throws Exception {
        DailyStock stock = new DailyStock();
        dailystockDAO.save(stock);

        List<DailyStock> stocks = dailystockDAO.getAll();
        assertEquals(stock, stocks.get(0));

    }

    @Test
    public void testDelete() throws Exception {
        DailyStock stock = new DailyStock();
        dailystockDAO.save(stock);
        dailystockDAO.delete(stock);

        List<DailyStock> stocks = dailystockDAO.getAll();
        assertNull(stocks);

    }

    @Test
    public void testGetAll() throws Exception {
        DailyStock stock = new DailyStock();
        DailyStock stock2 = new DailyStock();
        dailystockDAO.save(stock);
        dailystockDAO.save(stock2);

    }

    @Test
    public void testClearDaily() throws Exception {

    }
}