package com.seniordesigndbgt.dashboard.dao;

import com.seniordesigndbgt.dashboard.model.Jive;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class JiveDAO {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public void save(Jive jive) {
        getSession().save(jive);
    }

    public void delete(Jive jive) {
        getSession().delete(jive);
    }

    @SuppressWarnings("unchecked")
    public List getAll() {
        return getSession().createQuery("from Jive").list();
    }

    public void update(Jive jive){
        getSession().update(jive);
    }

}
