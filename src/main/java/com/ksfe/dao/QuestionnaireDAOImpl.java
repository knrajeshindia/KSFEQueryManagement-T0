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

import com.ksfe.model.Response;
import com.ksfe.service.ResponseService;
import com.ksfe.service.UnitService;
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
import java.util.Date;
import java.util.List;


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
    @Autowired
    private ResponseService responseService;
    @Autowired
    private UnitService unitService;
    private Questionnaire questionnaire;
    private Session session;
    List<Questionnaire> questionnaireList = new ArrayList<Questionnaire>();
    List<Questionnaire> questionnaireListFiltered = new ArrayList<Questionnaire>();
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Questionnaire> query;
    Root<Questionnaire> root;
    Query<Questionnaire> q;
    Date today = new Date();
    private Response response;
    private String responseStatus;


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
    public Questionnaire updateQuestionnaire(List<Integer> questionIDList, Integer pk) {
        System.out.println(getClass());
        this.questionnaire = getQuestionnaire(pk);
        System.out.println("Retrieved fr DB questionnaire : " + this.questionnaire);
        this.questionnaire.setQuestionIDList(questionIDList);
        this.questionnaire.setPostedDate(new Date());
        this.questionnaire.setQuestionnairePhase(ResponseCode.STATUS_PUBLISHED);
        session.update(this.questionnaire);
        System.out.println("Questionnaire- Updated Description: " + this.questionnaire);
        return questionnaire;
    }

    //Retrieve all pending questionnaireList for userID
    @Override
    public List<Questionnaire> viewPendingQuestionnaireList(Integer unitID) {
        System.out.println(getClass() + "UNIT ID:" + unitID);
        bindDB();
        Predicate filter = criteriaBuilder.and(
                criteriaBuilder.isMember(unitID, root.get("targetRespondentIDList")),
                criteriaBuilder.greaterThanOrEqualTo(root.get("dueDate"), today),
                criteriaBuilder.equal(root.get("questionnairePhase"), "PUBLISHED"));
        query.where(criteriaBuilder.and(filter));
        //ORDER BY CONDITION
        query.orderBy(criteriaBuilder.asc(root.get("dueDate")));
        questionnaireList = session.createQuery(query).getResultList();
        questionnaireListFiltered.clear();
        //Filter-out answered questionnaire
        for (Questionnaire questionnaire : questionnaireList) {
            response = responseService.verifyResponse(questionnaire.getQuestionnaireID(),unitID);
            responseStatus=response.getResponseStatus();
            questionnaire.setResponseStatus(responseStatus);
            questionnaire.setResponseDate(response.getResponseDate());
            questionnaire.setResponseID(response.getResponseID());

            if (!responseStatus.equalsIgnoreCase(ResponseCode.STATUS_PUBLISHED)) {
                //Set flag for questionnaire OPEN/MODIFY button view
                if (responseStatus.equalsIgnoreCase(ResponseCode.STATUS_NOT_RESPONDED)) {
                    questionnaire.setResponseFlag(false);
                } else {
                    questionnaire.setResponseFlag(true);
                }
                questionnaireListFiltered.add(questionnaire);
            }
        }
        insertUnitName(questionnaireList);
        System.out.println("Questionnairelist-Complete records for userID: " + questionnaireListFiltered);
        return questionnaireListFiltered;
    }

    //Retrieve all Questionnaire List for unit ID
    @Override
    public List<Questionnaire> viewCompleteQuestionnaireList(Integer unitID) {
        System.out.println(getClass() + " UNIT ID : " + unitID);
        bindDB();
        System.out.println("bindDB invoked");
        Predicate filter = criteriaBuilder.and(
                criteriaBuilder.isMember(unitID, root.get("targetRespondentIDList")));
        query.where(criteriaBuilder.and(filter));
        //ORDER BY
        query.orderBy(criteriaBuilder.desc(root.get("questionnaireID")));
        questionnaireList = session.createQuery(query).getResultList();
         //Add response details
        for (Questionnaire questionnaire : questionnaireList) {
            response = responseService.verifyResponse(questionnaire.getQuestionnaireID(), unitID);
            System.out.println("RESPONSE "+response);
            responseStatus=response.getResponseStatus();
            questionnaire.setResponseStatus(responseStatus);
            questionnaire.setResponseDate(response.getResponseDate());
            questionnaire.setResponseID(response.getResponseID());}
        insertUnitName(questionnaireList);
        System.out.println("Questionnairelist-Complete records for userID: " + questionnaireList);
        return questionnaireList;
    }

    //Retrieve MY Questionnaire -SELF CREATED
    @Override
    public List<Questionnaire> viewMyQuestionnaireList(Integer unitID) {
        System.out.println(getClass() + " UNIT ID : " + unitID);
        bindDB();
        System.out.println("bindDB invoked");
        Predicate filter = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("unitID"), unitID));
        query.where(criteriaBuilder.and(filter));
        //ORDER BY
        query.orderBy(criteriaBuilder.desc(root.get("questionnaireID")));
        questionnaireList = session.createQuery(query).getResultList();
        insertUnitName(questionnaireList);
        getResponsePercentage(questionnaireList);
        return questionnaireList;
    }

    //Insert UnitName from unitID
    private List<Questionnaire> insertUnitName(List<Questionnaire> questionnaireList){
        for(Questionnaire q:questionnaireList){
            q.setUnitIDName(unitService.getUnitName(q.getUnitID()));}
        return questionnaireList;
    }

    //View Response Percentage for Questionnaire
    private List<Questionnaire> getResponsePercentage(List<Questionnaire> questionnaireList){
        for(Questionnaire q:questionnaireList){
            int targetRespondents = q.getTargetRespondentIDList().size();
            double actualResponse=(double)responseService.getResponsePercentage(q.getQuestionnaireID());
            System.out.println("actual response : "+actualResponse+"/"+targetRespondents);
            Double responsePercentage=actualResponse/targetRespondents*100;
            System.out.println("responsePercentage : "+responsePercentage.intValue());
            q.setResponsePercentage(responsePercentage.intValue());
        }
        return questionnaireList;
    }
    //Critieria builder instantiation
    void bindDB() {
        session = sessionFactory.getCurrentSession();
        criteriaBuilder = session.getCriteriaBuilder();
        query = criteriaBuilder.createQuery(Questionnaire.class);
        root = query.from(Questionnaire.class);
        query.select(root);
        q = null;
        //questionnaire = null;
        questionnaireList = null;
    }


}
