/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.QuestionDAO;
import com.ksfe.model.JsonData;
import com.ksfe.model.Question;
import com.ksfe.util.JsonUtil;
import com.ksfe.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This is a Spring Service class - for implementating Service requirements
 *
 * @author RNarendran
 * @since 1.0,
 */

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
	private QuestionDAO questionDAO;
    Question question;
    String jsonResponse;
    static JsonData jsonData;

    @Transactional
    public String insertQuestion(Question question) {
        System.out.println(getClass());
        jsonData = setJsonData();
        System.out.println("Json Data :"+jsonData);
        System.out.println("@ ServiceImpl :"+question);
        try {
        System.out.println(getClass());
        question=questionDAO.insertQuestion(question);
            System.out.println(question);
            jsonResponse = JsonUtil.convertJavaToJson(question);
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
    public List<Question> getAllQuestions() {
        return questionDAO.getAllQuestions();
    }

    @Override
    @Transactional
    public Question getQuestion(int pk) {
        return questionDAO.getQuestion(pk);
    }

    @Override
    @Transactional
    public List<Question> getMultipleQuestions(int pk) {
        return questionDAO.getMultipleQuestions(pk);
    }

    //Update Question
    @Override
    @Transactional
    public Question updateQuestion(String questionDescription, Integer pk){
        return questionDAO.updateQuestion(questionDescription,pk);
    }

    //Delete Question
    @Override
    @Transactional
    public Question deleteQuestion(Integer pk) {
        return questionDAO.deleteQuestion(pk);
    }

    //Set default JSON message data
    public static JsonData setJsonData() {
        if (jsonData == null) {
            jsonData = new JsonData();
        }
        jsonData.setStatus(ResponseCode.STATUS_FAILURE);
        jsonData.setMessage(ResponseCode.MESSAGE_INITIALISED);
        return jsonData;
    }




}
