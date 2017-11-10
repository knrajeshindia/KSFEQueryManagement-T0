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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    List<Answer> answerListRev1 = new ArrayList<Answer>();
    Root<Answer> root;
    Query<Answer> q;
    Answer answer;
    private Integer answerID;


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
        bindDB();
        for (Integer answerID : answerIDList) {
            answer = getAnswer(answerID);
            answer.setResponseID(responseID);
            session.update(answer);
        }
    }

    //Retrieve answer List for responseID
    @Override
    public List<Answer> getAnswerList(int responseID) {
        System.out.println(getClass() + "|" + responseID);
        bindDB();
        query.where(criteriaBuilder.equal(root.get("responseID"), responseID));
        q = session.createQuery(query);
        answerListRev1 = q.getResultList();
        System.out.println("Answer List: " + answerListRev1);
        return answerListRev1;
    }

    //Update Answer -PUBLISH
    @Override
    public List<Answer> updateAnswerList(List<Answer> answerList) {
        System.out.println(getClass());
        bindDB();
        answerListRev1.clear();
        for (Answer a : answerList) {
            answerID = a.getAnswerID();
            answer = getAnswer(answerID);
            System.out.println("Answer: "+answer);
            answer.setAnswerDescription(a.getAnswerDescription());
            answer.setAnswerStatus(ResponseCode.STATUS_PUBLISHED);
            session.update(answer);
            answerListRev1.add(answer);
        }
        System.out.println("AnswerList :"+answerListRev1);
        return answerListRev1;
    }


    //Retrieve answer for question ID + responseID (for sepcific Unit)
    @Override
    public String getAnswer(Integer qID, Integer responseID) {
        String answerString="Pl submit your answer";
        Predicate filter = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("responseID"), responseID),
                criteriaBuilder.equal(root.get("questionID"), qID));
        query.where(criteriaBuilder.and(filter));
        answer = session.createQuery(query).getSingleResult();
        if(answer!=null)answerString=answer.getAnswerDescription();
        System.out.println("Retrieved Answer : "+answerString);
        return answerString;
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
