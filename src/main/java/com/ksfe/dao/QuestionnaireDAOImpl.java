/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Questionnaire;
import com.ksfe.model.Questionnaire;

import com.ksfe.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static antlr.build.ANTLR.root;

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
    private Questionnaire questionnaire;
    private Session session;
    List<Questionnaire> questionList = new ArrayList<Questionnaire>();
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Questionnaire> query;
    Root<Questionnaire> root;
    Query<Questionnaire> q;

    // Insert object
    @Override
    public Questionnaire insertQuestionnaire(Questionnaire questionnaire) {
        System.out.println(getClass());
        sessionFactory.getCurrentSession().save(questionnaire);
        System.out.println("Inserted Questionnaire: " + questionnaire);
        return questionnaire;
    }

    //Retrieve one Questionnaire
    @Override
    public Questionnaire getQuestionnaire(int pk) {
        System.out.println(getClass());
        bindDB();
        query.where(criteriaBuilder.equal(root.get("questionnaireID"), pk));
        q = session.createQuery(query);
        questionnaire = q.getSingleResult();
        System.out.println("Questionnaire- ID: " + questionnaire);
        return questionnaire;
    }


    @Override
    public Questionnaire updateQuestionnaire(Questionnaire questionnaire, Integer pk) {
        System.out.println(getClass());
        System.out.println("@updateQuestionnaire : " + questionnaire);
        this.questionnaire = getQuestionnaire(pk);
        System.out.println("Retrieved fr DB questionnaire : " + this.questionnaire);
        this.questionnaire.setPostedDate(questionnaire.getPostedDate());
        this.questionnaire.setQuestionIDList(questionnaire.getQuestionIDList());
        session.update(this.questionnaire);
        System.out.println("Questionnaire- Updated Description: " + this.questionnaire);
        return questionnaire;
    }


    //Critieria builder instantiation
    void bindDB() {
        session = sessionFactory.getCurrentSession();
        criteriaBuilder = session.getCriteriaBuilder();
        query = criteriaBuilder.createQuery(Questionnaire.class);
        root = query.from(Questionnaire.class);
        query.select(root);
        q = null;
        questionnaire = null;
        questionList = null;
    }


}
