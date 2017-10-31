/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.AnswerDAO;
import com.ksfe.model.Answer;
import com.ksfe.model.JsonData;
import com.ksfe.util.JsonUtil;
import com.ksfe.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This is a Spring Service class - for implementing Service requirements
 *
 * @author RNarendran
 * @since 1.0,
 */

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerDAO answerDAO;
    ArrayList<Answer> answerList;
    String jsonResponse;
    static JsonData jsonData;

    @Transactional
    public void insertAnswer(Answer answer) {
        System.out.println(getClass());
        answerDAO.insertAnswer(answer);
    }

    //Save AnswerList for QuestionList
    @Override
    @Transactional
    public String insertAnswer(ArrayList<Answer> answerList) {
        System.out.println(getClass());
        jsonData = setJsonData();
        System.out.println("Json Data :" + jsonData);

        try {
            this.answerList = answerDAO.saveAnswerList(answerList);
            System.out.println(answerList);
            jsonResponse = JsonUtil.convertJavaToJson(this.answerList);
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

    //Update response ID in answers
    @Override
    @Transactional
    public void updateAnswerList(Collection<Integer> answerIDList, Integer responseID) {
        answerDAO.updateAnswerList(answerIDList,responseID);
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
