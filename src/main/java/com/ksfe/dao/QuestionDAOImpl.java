/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Question;
import com.ksfe.model.Questionnaire;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Session session;
    Question question;
    List<Question> questionList;
    Set<Question> questionListSet;
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Question> query;
    Root<Question> root;
    Query<Question> q;

    // Insert object object
    @Override
    public Question insertQuestion(Question question) {
        System.out.println(getClass());
        session = sessionFactory.getCurrentSession();
        session.save(question);
        System.out.println("Inserted Question: " + question);

        return question;
    }

    //Retrieve All Question
    @Override
    public List<Question> getAllQuestions() {
        System.out.println(getClass());
        bindDB();
        questionList = session.createQuery(query).getResultList();
        System.out.println("Questionlist-Complete records: " + questionList);
        return questionList;
    }

    //Retrieve one Question
    @Override
    public Question getQuestion(int pk) {
//    	question=new Question();
        System.out.println(getClass());
        bindDB();
        query.where(criteriaBuilder.equal(root.get("questionID"), pk));
        q = session.createQuery(query);
        question = q.getSingleResult();
        System.out.println("Question- ID: " + question);
        return question;
    }

    //Retrieve Multiple Question based on condition
    @Override
    public List<Question> getMultipleQuestions(int pk) {
        System.out.println(getClass());
        bindDB();
        query.where(criteriaBuilder.gt(root.get("questionID"), pk));
        q = session.createQuery(query);
        questionList = q.getResultList();
        System.out.println("Question- Multiple: " + questionList);
        return questionList;
    }

    //Update Question
    @Override
    public Question updateQuestion(String questionDescription, Integer pk) {
        System.out.println(getClass());
        question = getQuestion(pk);
        question.setQuestionDescription(questionDescription);
        session.update(question);
        System.out.println("Question- Updated Description: " + question.getQuestionDescription());
        return question;
    }

    //Delete Question
    @Override
    public Question deleteQuestion(Integer pk) {
        System.out.println(getClass());
        bindDB();
        Serializable id = new Integer(pk);
        Question question = session.load(Question.class, id);
        if (question != null) {
            session.delete(question);
            System.out.println("Question- Updated Description: " + question.getQuestionDescription());
        }
        return question;
    }

    //Retrieve List<Question> from List of questionID
    @Override
    public List<Question> getMultipleQuestions(List<Integer> questionIDList) {
        questionListSet = new HashSet<Question>();
        for (Integer qID : questionIDList) {
            question = new Question();
            question = (Question) getQuestion(qID);
            questionListSet.add(question);
        }
        questionList = new ArrayList<>(questionListSet);
        System.out.println("Question List retrieved : " + questionList);
        return questionList;
    }

    //Delete Questionnaire-TEMPORARY -MOVE TO QUESIONNAIREDAO

    public Questionnaire deleteQuestionnaire(Integer pk) {
        System.out.println(getClass());
        bindDB();
        Serializable id = new Integer(pk);
        Questionnaire questionnaire = session.load(Questionnaire.class, id);
        if (questionnaire != null) {
            session.delete(questionnaire);
            System.out.println("DELETED : " + questionnaire.getQuestionnaireTitle());
        }
        return questionnaire;
    }

    //Critieria builder instantiation
    void bindDB() {
        session = sessionFactory.getCurrentSession();
        criteriaBuilder = session.getCriteriaBuilder();
        query = criteriaBuilder.createQuery(Question.class);
        root = query.from(Question.class);
        query.select(root);
        q = null;
        question = null;
        questionList = null;
    }


}