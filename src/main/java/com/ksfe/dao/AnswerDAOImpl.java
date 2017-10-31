/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Answer;
import com.ksfe.util.ResponseCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

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
    private Session session;
    private ArrayList<Answer> answerList;
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Answer> query;
    Root<Answer> root;
    Query<Answer> q;
    Answer answer;


    //Insert Answer
    @Override
    public void insertAnswer(Answer answer) {
        System.out.println(getClass());
        sessionFactory.getCurrentSession().save(answer);
        System.out.println("Inserted Answer: " + answer);

    }

    @Override
    public ArrayList<Answer> saveAnswerList(ArrayList<Answer> answerList) {
        this.answerList = new ArrayList<>();
        for (Answer a : answerList) {
            a.setAnswerStatus(ResponseCode.STATUS_DRAFT);
            insertAnswer(a);
            this.answerList.add(a);
        }
        return this.answerList;
    }


    //Retrieve one Answer
    @Override
    public Answer getAnswer(int pk) {
        System.out.println(getClass());
        bindDB();
        query.where(criteriaBuilder.equal(root.get("answerID"), pk));
        q = session.createQuery(query);
        answer = q.getSingleResult();
        System.out.println("Answer- ID: " + answer);
        return answer;
    }

    @Override
    public void updateAnswerList(Collection<Integer> answerIDList, Integer responseID) {
        System.out.println(getClass());
        for (Integer answerID : answerIDList) {
            answer = getAnswer(answerID);
            answer.setResponseID(responseID);
            session.update(answer);
        }
    }

    //Critieria builder instantiation
    void bindDB() {
        session = sessionFactory.getCurrentSession();
        criteriaBuilder = session.getCriteriaBuilder();
        query = criteriaBuilder.createQuery(Answer.class);
        root = query.from(Answer.class);
        query.select(root);
        q = null;
        answer = null;
    }
}
