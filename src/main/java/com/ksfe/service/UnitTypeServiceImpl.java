/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.UnitTypeDAO;
import com.ksfe.model.UnitType;
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
public class UnitTypeServiceImpl implements UnitTypeService {
    @Autowired
	private UnitTypeDAO unitTypeDAO;

    @Transactional
    public void insertUnitType(UnitType unitType) {
        System.out.println(getClass());
        unitTypeDAO.insertUnitType(unitType);
    }
}
