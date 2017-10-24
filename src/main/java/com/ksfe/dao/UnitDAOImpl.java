/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Unit;
import com.ksfe.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * This is a Spring Repository bean class - DAO
 *
 * @author RNarendran
 * @since 1.0,
 */
@Repository
public class UnitDAOImpl implements UnitDAO {
    @Autowired
    private SessionFactory sessionFactory;
    private List<Integer> unitIDList;

    //Insert unit
    @Override
    public void insertUnit(Unit unit) {
        System.out.println(getClass());
        sessionFactory.getCurrentSession().save(unit);
        System.out.println("Inserted Unit: " + unit);
    }

    // Retrieve all unitID for given unitType
    @Override
    public List<Integer> getUnitIDList(Integer unitTypeID) {
        System.out.println(getClass());
        System.out.println("Reached UnitDAOIMpl: "+unitTypeID);
        Session session = sessionFactory.getCurrentSession();
        System.out.println("session created: "+session);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        System.out.println("builder created: ");
        CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);
        System.out.println("queery created: "+criteriaQuery);
        Root<Unit> unitRoot = criteriaQuery.from(Unit.class);
        System.out.println("root created: ");
        criteriaQuery.select(unitRoot.get("unitID"));
        criteriaQuery.where(builder.equal(unitRoot.get("unitTypeID"), unitTypeID));
        unitIDList = session.createQuery(criteriaQuery).getResultList();
        System.out.println("UnitIDlist-Complete records: " + unitIDList);
        return unitIDList;
    }
}

