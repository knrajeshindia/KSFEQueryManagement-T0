/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.TargetDAO;
import com.ksfe.model.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * This is a Spring Service class - for implementating Service requirements
 *
 * @author RNarendran
 * @since 1.0,
 */

@Service
public class TargetServiceImpl implements TargetService {
    @Autowired
	private TargetDAO targetDAO;

    @Transactional
    public void insertTarget(Target target) {
        System.out.println(getClass());
        targetDAO.insertTarget(target);
    }
}
