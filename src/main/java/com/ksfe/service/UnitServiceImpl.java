/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.dao.UnitDAO;
import com.ksfe.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
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


}
