/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.UnitDAO;
import com.ksfe.model.JsonData;
import com.ksfe.model.Login;
import com.ksfe.model.Unit;
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
public class UnitServiceImpl implements UnitService {
    @Autowired
	private UnitDAO unitDAO;
    String jsonResponse;
    static JsonData jsonData;
    private Unit unit;


    @Transactional
    @Override
    public void insertUnit(Unit unit) {
        System.out.println(getClass());
        unitDAO.insertUnit(unit);
    }

    @Override
    @Transactional
    public List<Integer> getUnitIDList(Integer unitTypeID) {
        return unitDAO.getUnitIDList(unitTypeID);
    }

    //Verify USER -LOGIN
    @Override
    @Transactional
    public String verifyUnit(Integer unitID, String password) {
        System.out.println(getClass());
        jsonData = setJsonData();
        System.out.println("Json Data :" + jsonData);
        try {
            this.unit = unitDAO.verifyUnit(unitID,password);
            System.out.println(this.unit);
            jsonResponse = JsonUtil.convertJavaToJson(this.unit);
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

    //Retrieve Unit Name
    @Override
    @Transactional
    public String getUnitName(Integer unitID) {
        return unitDAO.getUnitName(unitID);
    }

    //Set default message data
    public static JsonData setJsonData() {
        jsonData = new JsonData();
        jsonData.setStatus(ResponseCode.STATUS_FAILURE);
        jsonData.setMessage(ResponseCode.MESSAGE_INITIALISED);
        return jsonData;}


}
