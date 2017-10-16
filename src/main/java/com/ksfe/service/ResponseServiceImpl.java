/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.ResponseDAO;
import com.ksfe.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public void insertResponse(Response response) {
        System.out.println(getClass());
        responseDAO.insertResponse(response);
    }
}
