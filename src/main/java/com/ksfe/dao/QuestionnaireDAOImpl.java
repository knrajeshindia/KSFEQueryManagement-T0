/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Questionnaire;

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
public class QuestionnaireDAOImpl implements QuestionnaireDAO {
	@Autowired
	 private SessionFactory sessionFactory;
	 
    //Insert question
    @Override
    public void insertQuestionnaire(Questionnaire questionnaire) {
    	System.out.println(getClass());
        sessionFactory.getCurrentSession().save(questionnaire);
        System.out.println("Inserted Question: "+ questionnaire);

    }
}
