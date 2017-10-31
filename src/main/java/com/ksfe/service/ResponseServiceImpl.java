/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.ResponseDAO;
import com.ksfe.model.JsonData;
import com.ksfe.model.Response;
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
public class ResponseServiceImpl implements ResponseService {
    @Autowired
    private ResponseDAO responseDAO;
    Response response;
    String jsonResponse;
    static JsonData jsonData;

    @Transactional
    public String insertResponse(Response response) {
        System.out.println(getClass());
        jsonData = setJsonData();
        System.out.println("Json Data :" + jsonData);

        try {
            this.response = responseDAO.insertResponse(response);
            System.out.println(response);
            jsonResponse = JsonUtil.convertJavaToJson(this.response);
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

    //Method to verify if a response exist for questionnaireID
    @Override
    @Transactional
    public boolean verifyResponse(Integer questionnaireID) {
        return responseDAO.verifyResponse(questionnaireID);
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
