package com.seniordesigndbgt.dashboard.dao;

import com.seniordesigndbgt.dashboard.model.DailyStock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by neel on 2/15/16.
 */
public class DailyStockDAO {
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public void save(DailyStock stock){ getSession().save(stock); }

    public void delete(DailyStock stock){ getSession().delete(stock); }

    public List<DailyStock> getAll() { return getSession().createQuery("from DailyStock").list(); }

    public void clearDaily() {
        for (DailyStock stock : getAll()){
            delete(stock);
        }
    }
}
