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

    public List <Press> getToday() {
        return getSession().createQuery("from Press WHERE timestamp between CURDATE() and CURDATE()+1").list();
    }

    public List <Press> getYesterday() {
        return getSession().createQuery("from Press WHERE timestamp between CURDATE() and CURDATE()-1").list();
    }

    public List <Press> search(String term) {
        return getSession().createQuery("from Press where title LIKE '%" + term + "%'").list();
    }

    public void update(Press press){
        getSession().update(press);
    }

    public List<Press> getByID(int id) {
        return getSession().createQuery("from Press WHERE id = "+id).list();
    }

}
