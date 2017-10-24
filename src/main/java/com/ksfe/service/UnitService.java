/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.service;

import com.ksfe.model.Unit;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This is a Java Interface declaring Service methods
 *
 * @author RNarendran
 * @since 1.0,
 */
public interface UnitService {
    public void insertUnit(Unit unit);
    List<Integer> getUnitIDList(Integer unitTypeID);
}
