package com.seniordesigndbgt.dashboard.dao;

import com.seniordesigndbgt.dashboard.model.Trend;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TrendDAO {

    @Autowired
    private SessionFactory _sessionFactory;
    private static final int NUMBER_OF_TRENDS = 4;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public void save(Trend trend) {
        System.out.println("\nTrend title: "+trend.getTrendTitle()+" is this long: "+trend.getTrendTitle().length());
        getSession().save(trend);
    }

    public void delete(Trend trend) {
        getSession().delete(trend);
    }

    @SuppressWarnings("unchecked")
    public List<Trend> getAll() {
        return getSession().createQuery("from Trend").list();
    }

    public List<Trend> getMostRecent(){

        List<Trend> trendList = getSession().createQuery("from Trend").list();
        if (trendList.size() > NUMBER_OF_TRENDS)
                return trendList.subList(0, NUMBER_OF_TRENDS);
        else
            return trendList;
    }

    public void update(Trend trend){
        getSession().update(trend);
    }

}
