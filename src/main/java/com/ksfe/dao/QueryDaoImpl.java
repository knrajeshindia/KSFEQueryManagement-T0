/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksfe.model.Query;

/**
 * This is a Spring Repository class - DAO
 *
 * @author RNarendran
 * @since 1.0,
 */
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
