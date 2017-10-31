/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Response;

import com.ksfe.util.ResponseCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * This is a Spring Repository bean class - DAO
 *
 * @author RNarendran
 * @since 1.0,
 */
@Repository
public class ResponseDAOImpl implements ResponseDAO {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Response> query;
    Root<Response> root;
    String responseStatus;

    // Insert object
    @Override
    public Response insertResponse(Response response) {
        System.out.println(getClass());
        sessionFactory.getCurrentSession().save(response);
        System.out.println("Inserted Response: " + response);
        return response;
    }

    //Verify response status for questionnaireID
    @Override
    public String verifyResponse(Integer questionnaireID) {
        System.out.println(getClass());
        bindDB();
        Serializable id = new Integer(questionnaireID);
        query.where(criteriaBuilder.equal(root.get("questionnaireID"), id));
        List<Response> responseList = session.createQuery(query).getResultList();

        if (responseList.size() > 0) {
            for (Response response : responseList) {
                if (response.getResponseStatus().equalsIgnoreCase("PUBLISHED")) {
                    responseStatus = ResponseCode.STATUS_PUBLISHED;
                    return responseStatus;
                } else {
                    responseStatus = ResponseCode.STATUS_DRAFT;
                    return responseStatus;
                }
            }
        }
        responseStatus = ResponseCode.STATUS_NOT_RESPONDED;
       /* Response response = session.createQuery(query).getSingleResult();
        System.out.println("Response object"+response);
        if(response !=null){
            if(response.getResponseStatus()=="PUBLISHED"){
                return true;
            }
        }*/
        return responseStatus;
    }

    //Critieria builder instantiation
    void bindDB() {
        session = sessionFactory.getCurrentSession();
        criteriaBuilder = session.getCriteriaBuilder();
        query = criteriaBuilder.createQuery(Response.class);
        root = query.from(Response.class);
        query.select(root);


    }
}
