package com.seniordesigndbgt.dashboard.dao;

import com.seniordesigndbgt.dashboard.model.Twitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by thomaspatterson on 2/14/16.
 */
@Repository
@Transactional
public class TwitterDAO {
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public void save(Twitter tweet) {
        getSession().save(tweet);
    }

    @SuppressWarnings("unchecked")
    public List<Twitter> getAll() {
        return getSession().createQuery("from Twitter").list();
    }
}
