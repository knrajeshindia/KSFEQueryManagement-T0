/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Unit;
import com.ksfe.model.UnitType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This is a Spring Repository bean class - DAO
 *
 * @author RNarendran
 * @since 1.0,
 */
@Repository
public class UnitTypeDAOImpl implements UnitTypeDAO {
	@Autowired
	 private SessionFactory sessionFactory;
	 
    //Insert
    @Override
    public void insertUnitType(UnitType unitType) {
    	System.out.println(getClass());
        sessionFactory.getCurrentSession().save(unitType);
        System.out.println("Inserted: "+ unitType);

    }
}
