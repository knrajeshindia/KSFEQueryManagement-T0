/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Unit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This is a Spring Repository class - DAO
 *
 * @author RNarendran
 * @since 1.0,
 */
@Repository
public class UnitDAOImpl implements UnitDAO {
	@Autowired
	 private SessionFactory sessionFactory;
	 
    //Insert unit
    @Override
    public void insertUnit(Unit unit) {
    	System.out.println(getClass());
        sessionFactory.getCurrentSession().save(unit);
        System.out.println("Inserted Unit: "+ unit);

    }
}
