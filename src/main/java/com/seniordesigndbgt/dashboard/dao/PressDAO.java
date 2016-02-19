package com.seniordesigndbgt.dashboard.dao;

import com.seniordesigndbgt.dashboard.model.Press;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public List getAll() {
        return getSession().createQuery("from User").list();
    }

    public Press getByUsername(String username) {
        return (Press) getSession().createQuery(
                "from User where username = :username")
                .setParameter("username", username)
                .uniqueResult();
    }

    public Press getById(long id) {
        return (Press) getSession().load(Press.class, id);
    }

    public void update(Press user) {
        getSession().update(user);
    }
}
