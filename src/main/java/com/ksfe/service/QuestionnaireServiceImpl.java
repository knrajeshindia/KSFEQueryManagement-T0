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
    Questionnaire questionnaire;
    String jsonResponse;
    static JsonData jsonData;

    @Transactional
    public String insertQuestionnaire(Questionnaire questionnaire) {
        System.out.println(getClass());
        jsonData = setJsonData();
        System.out.println("Json Data :"+jsonData);
        System.out.println("Questionnaire @ ServiceImpl :"+questionnaire);
        try {
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

    //Set default message data
    public static JsonData setJsonData() {
        if (jsonData == null) {
            jsonData = new JsonData();
        }
        jsonData.setStatus(ResponseCode.STATUS_FAILURE);
        jsonData.setMessage(ResponseCode.MESSAGE_INITIALISED);
        return jsonData;
    }


}
