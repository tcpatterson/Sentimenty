package com.seniordesigndbgt.dashboardtest.daotests;


import com.seniordesigndbgt.dashboard.Application;
import com.seniordesigndbgt.dashboard.dao.DailyStockDAO;
import com.seniordesigndbgt.dashboard.model.DailyStock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DailyStockDAOTest{

    SessionFactory sessionFactory;
    Session session;

    DailyStockDAO _dailystockDAO;

    @Before
    public void before() {
        // setup the session factory
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.addAnnotatedClass(DailyStock.class);
        configuration.setProperty("hibernate.dialect",
                "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class",
                "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @Test
    public void testSave() throws Exception {
        DailyStock stock = new DailyStock();
        _dailystockDAO.save(stock);

        List<DailyStock> stocks = _dailystockDAO.getAll();
        assertEquals(stock, stocks.get(0));
    }

    @Test
    public void testDelete() throws Exception {
        DailyStock stock = new DailyStock();
        _dailystockDAO.save(stock);
        _dailystockDAO.delete(stock);

        List<DailyStock> stocks = _dailystockDAO.getAll();
        assertNull(stocks);

    }

    @Test
    public void testGetAll() throws Exception {
        DailyStock stock = new DailyStock();
        DailyStock stock2 = new DailyStock();
        _dailystockDAO.save(stock);
        _dailystockDAO.save(stock2);

    }

    @Test
    public void testClearDaily() throws Exception {

    }
}