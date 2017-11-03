/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Unit;

import java.util.List;
import java.util.Set;

/**
 * This is a Java interface for defining DAO operations
 *
 * @author RNarendran
 * @since 1.0,
 */
public interface UnitDAO {
    void insertUnit(Unit unit);

    List<Integer> getUnitIDList(Integer unitTypeID);

    //Retrieve one Unit
    Unit getUnit(int pk);

    Unit verifyUnit(Integer unitID, String password);

    //Add all unitID as HASHSET - for entire organisation
    Set<Integer> getUnitIDSet();

    //Add all unitID as HASHSET - for REGIONAL OFFICES
    Set<Integer> getUnitIDSet(Integer regionID);
}
