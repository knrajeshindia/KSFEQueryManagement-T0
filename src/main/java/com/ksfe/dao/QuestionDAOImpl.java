/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Question;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a Spring Repository bean class - DAO
 *
 * @author RNarendran
 * @since 1.0,
 */
@Repository
public class QuestionDAOImpl implements QuestionDAO {
    @Autowired
    private SessionFactory sessionFactory;
    List<Question> questionList=new ArrayList<Question>();
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Question> query;
    Root<Question> root;


    //Insert question
    @Override
    public void insertQuestion(Question question) {
        System.out.println(getClass());
        sessionFactory.getCurrentSession().save(question);
        System.out.println("Inserted Question: " + question);

    }

    @Override
    public List<Question> getAllQuestions() {
        System.out.println(getClass());
        criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        query = criteriaBuilder.createQuery(Question.class);
        root = query.from(Question.class);
        questionList = sessionFactory.getCurrentSession().createQuery(query).getResultList();
        System.out.println("Questionlist-Complete records: " + questionList);
        return questionList;
    }
}
