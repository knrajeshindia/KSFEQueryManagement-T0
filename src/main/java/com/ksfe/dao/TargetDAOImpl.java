/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Target;
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
public class TargetDAOImpl implements TargetDAO {
	@Autowired
	 private SessionFactory sessionFactory;
	 
    //Insert target
    @Override
    public void insertTarget(Target target) {
    	System.out.println(getClass());
        sessionFactory.getCurrentSession().save(target);
        System.out.println("Inserted Target: "+ target);

    }
}
