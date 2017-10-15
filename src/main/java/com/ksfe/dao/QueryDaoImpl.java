package com.ksfe.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksfe.model.Query;


@Repository
public class QueryDaoImpl implements QueryDao {
	@Autowired
	 private SessionFactory sessionFactory;
	 
    //Insert query
    @Override
    public void insertQuery(Query query) {
    	System.out.println(getClass());
        sessionFactory.getCurrentSession().save(query);
        System.out.println("Inserted Query: "+query);

    }
}
