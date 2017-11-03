/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Unit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Session session;
    private List<Integer> unitIDList;
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Unit> query;
    List<Unit> answerListRev1 = new ArrayList<Unit>();
    Root<Unit> root;
    Query<Unit> q;
    private Unit unit;

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
        System.out.println("query created: "+criteriaQuery);
        Root<Unit> unitRoot = criteriaQuery.from(Unit.class);
        System.out.println("root created: ");
        criteriaQuery.select(unitRoot.get("unitID"));
        criteriaQuery.where(builder.equal(unitRoot.get("unitTypeID"), unitTypeID));
        unitIDList = session.createQuery(criteriaQuery).getResultList();
        System.out.println("UnitIDlist-Complete records: " + unitIDList);
        return unitIDList;
    }

    //Add all unitID as HASHSET - for entire organisation
    @Override
    public Set<Integer> getUnitIDSet() {
        System.out.println(getClass());
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);
        Root<Unit> unitRoot = criteriaQuery.from(Unit.class);
        criteriaQuery.select(unitRoot.get("unitID"));
        unitIDList = session.createQuery(criteriaQuery).getResultList();
        Set<Integer> unitIDSet=new HashSet<Integer>(unitIDList);
        System.out.println("unitIDList-Complete records: " + unitIDSet);
        return unitIDSet;
    }
    //Add all BRACHES - unitID as HASHSET - for specific region
    @Override
    public Set<Integer> getUnitIDSet(Integer regionID) {
        System.out.println(getClass());
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);
        Root<Unit> unitRoot = criteriaQuery.from(Unit.class);
        criteriaQuery.select(unitRoot.get("unitID"));
        criteriaQuery.where(builder.equal(unitRoot.get("regionID"), regionID));
        unitIDList = session.createQuery(criteriaQuery).getResultList();
        Set<Integer> unitIDSet=new HashSet<Integer>(unitIDList);
        System.out.println("unitIDSet-Complete records: " + unitIDSet);
        return unitIDSet;
       }

    //Retrieve one Unit
    @Override
    public Unit getUnit(int pk) {
        System.out.println(getClass());
        bindDB();
        query.where(criteriaBuilder.equal(root.get("unitID"), pk));
        q = session.createQuery(query);
        unit = q.getSingleResult();
        System.out.println("UNIT: " + unit);
        return unit;
    }

    //Verify User
    @Override
    public Unit verifyUnit(Integer unitID, String password) {
        this.unit=getUnit(unitID);
        if(this.unit!=null){
            if(this.unit.getPassword().equals(password)){
                return this.unit;
            }
        }
        return null;
    }



    //Critieria builder instantiation
    void bindDB() {
        session = sessionFactory.getCurrentSession();
        criteriaBuilder = session.getCriteriaBuilder();
        query = criteriaBuilder.createQuery(Unit.class);
        root = query.from(Unit.class);
        query.select(root);
        q = null;
        unit = null;
    }
}

