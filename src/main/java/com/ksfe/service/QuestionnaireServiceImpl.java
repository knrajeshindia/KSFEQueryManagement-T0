/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.QuestionnaireDAO;
import com.ksfe.model.JsonData;
import com.ksfe.model.Questionnaire;

import com.ksfe.util.JsonUtil;
import com.ksfe.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a Spring Service class - for implementing Service requirements
 *
 * @author RNarendran
 * @since 1.0,
 */

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    private QuestionnaireDAO questionnaireDAO;
    @Autowired
    private RespondentService respondentService;
    Questionnaire questionnaire;
    List<Questionnaire> questionnaireList;
    List<Integer> unitIDList = new ArrayList<Integer>();
    String jsonResponse;
    static JsonData jsonData;

    @Transactional
    public String insertQuestionnaire(Questionnaire questionnaire) {
        System.out.println(getClass());
        jsonData = setJsonData();
        System.out.println("Json Data :" + jsonData);
        System.out.println("Questionnaire @ ServiceImpl :" + questionnaire);
        try {
            getAllRespondents(questionnaire);
            questionnaire.setQuestionnaireTitle(questionnaire.getQuestionnaireTitle().toUpperCase());
            questionnaire.setQuestionnaireDescription(questionnaire.getQuestionnaireDescription().toUpperCase());
            questionnaire.setQuestionnairePhase(ResponseCode.STATUS_DRAFT);
            questionnaire = questionnaireDAO.insertQuestionnaire(questionnaire);
            System.out.println(questionnaire);
            jsonResponse = JsonUtil.convertJavaToJson(questionnaire);
            jsonData.setData(jsonResponse);
            jsonData.setStatus(ResponseCode.STATUS_SUCCESS);
            jsonData.setMessage(ResponseCode.MESSAGE_UPDATED);
        } catch (EmptyResultDataAccessException e) {
            jsonData.setMessage(ResponseCode.MESSAGE_FAILURE);
        } catch (DataAccessException de) {
            jsonData.setMessage(ResponseCode.MESSAGE_NETWORK);
        }
        jsonResponse = JsonUtil.convertJavaToJson(jsonData);
        System.out.println(jsonResponse);
        return jsonResponse;
    }

    @Override
    @Transactional
    public Questionnaire updateQuestionnaire(List<Integer> questionIDList, Integer pk) {
    	System.out.println("@service - redirecting to DAO : "+questionnaire);
        return questionnaireDAO.updateQuestionnaire(questionIDList,pk);
    }

    //Get all pending questionnaire list
    @Override
    @Transactional
    public String viewPendingQuestionnaireList(Integer unitID) {
        System.out.println(getClass());
        jsonData = setJsonData();
        System.out.println("Json Data :" + jsonData);
        try {
            questionnaireList = questionnaireDAO.viewPendingQuestionnaireList(unitID);
            System.out.println("UNIT ID"+unitID);
            jsonResponse = JsonUtil.convertJavaToJson(questionnaireList);
            jsonData.setData(jsonResponse);
            jsonData.setStatus(ResponseCode.STATUS_SUCCESS);
            jsonData.setMessage(ResponseCode.MESSAGE_UPDATED);
        } catch (EmptyResultDataAccessException e) {
            jsonData.setMessage(ResponseCode.MESSAGE_FAILURE);
        } catch (DataAccessException de) {
            jsonData.setMessage(ResponseCode.MESSAGE_NETWORK);
        }
        jsonResponse = JsonUtil.convertJavaToJson(jsonData);
        System.out.println(jsonResponse);
        return jsonResponse;
    }

    //Retrieve complete Questionnaire List
    @Override
    @Transactional
    public String viewCompleteQuestionnaireList(Integer unitID) {
        System.out.println(getClass());
        jsonData = setJsonData();
        System.out.println("Json Data :" + jsonData);
        try {
            questionnaireList = questionnaireDAO.viewCompleteQuestionnaireList(unitID);
            System.out.println(questionnaireList);
            jsonResponse = JsonUtil.convertJavaToJson(questionnaireList);
            jsonData.setData(jsonResponse);
            jsonData.setStatus(ResponseCode.STATUS_SUCCESS);
            jsonData.setMessage(ResponseCode.MESSAGE_UPDATED);
        } catch (EmptyResultDataAccessException e) {
            jsonData.setMessage(ResponseCode.MESSAGE_FAILURE);
        } catch (DataAccessException de) {
            jsonData.setMessage(ResponseCode.MESSAGE_NETWORK);
        }
        jsonResponse = JsonUtil.convertJavaToJson(jsonData);
        System.out.println(jsonResponse);
        return jsonResponse;
    }

    //Retrieve MY Questionnaire List(Self created)
    @Override
    @Transactional
    public String viewMyQuestionnaireList(Integer unitID) {
        System.out.println(getClass());
        jsonData = setJsonData();
        System.out.println("Json Data :" + jsonData);
        try {
            questionnaireList = questionnaireDAO.viewMyQuestionnaireList(unitID);
            System.out.println("UNIT ID"+unitID);
            jsonResponse = JsonUtil.convertJavaToJson(questionnaireList);
            jsonData.setData(jsonResponse);
            jsonData.setStatus(ResponseCode.STATUS_SUCCESS);
            jsonData.setMessage(ResponseCode.MESSAGE_UPDATED);
        } catch (EmptyResultDataAccessException e) {
            jsonData.setMessage(ResponseCode.MESSAGE_FAILURE);
        } catch (DataAccessException de) {
            jsonData.setMessage(ResponseCode.MESSAGE_NETWORK);
        }
        jsonResponse = JsonUtil.convertJavaToJson(jsonData);
        System.out.println(jsonResponse);
        return jsonResponse;
    }

    //Get all UnitIDs for selected UnitTypeID
    @Transactional
    private Questionnaire getAllRespondents(Questionnaire questionnaire) {
        System.out.println(getClass()+" getRespondents() "+questionnaire.getQuestionnaireTitle());
        unitIDList = respondentService.getUnitIDList(questionnaire.getTargetRespondentIDList());
        System.out.println("@Questionnaire-service - unitIDs retrieved"+unitIDList);
        questionnaire.setTargetRespondentIDList(unitIDList);
        return questionnaire;
    }


    //Set default message data
    public static JsonData setJsonData() {
        jsonData = new JsonData();
        jsonData.setStatus(ResponseCode.STATUS_FAILURE);
        jsonData.setMessage(ResponseCode.MESSAGE_INITIALISED);
        return jsonData;
    }


}
