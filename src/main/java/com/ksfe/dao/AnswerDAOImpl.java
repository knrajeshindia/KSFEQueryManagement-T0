/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Answer;
import com.ksfe.util.SessionUtil;
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
public class AnswerDAOImpl implements AnswerDAO {
	@Autowired
	 private SessionFactory sessionFactory;
	 
    //Insert Answer
    @Override
    public void insertAnswer(Answer answer) {
    	System.out.println(getClass());
        sessionFactory.getCurrentSession().save(answer);
        System.out.println("Inserted Answer: "+ answer);

    }
}
