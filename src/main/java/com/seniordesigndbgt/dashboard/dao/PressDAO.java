package com.seniordesigndbgt.dashboard.dao;

import com.seniordesigndbgt.dashboard.model.Press;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PressDAO {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public void save(Press press) {
        getSession().save(press);
    }

    public void delete(Press press) {
        getSession().delete(press);
    }

    @SuppressWarnings("unchecked")
    public List <Press> getAll() {
        return getSession().createQuery("from Press").list();
    }

    public void update(Press press){
        getSession().update(press);
    }

}
