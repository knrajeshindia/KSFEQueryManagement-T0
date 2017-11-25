/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.dao;

import com.ksfe.model.Response;

import com.ksfe.service.AnswerService;
import com.ksfe.util.ResponseCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    @Autowired
    private AnswerService answerService;
    private Session session;
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Response> query;
    Root<Response> root;
    Query<Response> q;
    String responseStatus;
    private Collection<Integer> answerIDList = new ArrayList<Integer>();
    private Response responseDummy;
    private Response response;
    private int responseID;

    // Insert object
    @Override
    public Response insertResponse(Response response) {
        System.out.println(getClass());
        sessionFactory.getCurrentSession().save(response);
        System.out.println("Inserted Response: " + response);
        answerIDList = response.getAnswerIDList();
        answerService.updateAnswerList(answerIDList, response.getResponseID());
        return response;
    }

    //Verify response status for questionnaireID
    //Revise to select only response - ID and status only
    @Override
    public Response verifyResponse(Integer questionnaireID, Integer unitID) {
        System.out.println(getClass());
        bindDB();
        Predicate filter = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("questionnaireID"), questionnaireID),
                criteriaBuilder.equal(root.get("unitID"), unitID));
        query.where(criteriaBuilder.and(filter));
        List<Response> responseList = session.createQuery(query).getResultList();
        System.out.println(responseList);
        if (responseList.size() > 0) {
            for (Response response : responseList) {
            	responseDummy = new Response();
                responseDummy.setResponseID(response.getResponseID());
                responseDummy.setResponseDate(response.getResponseDate());
                if (response.getResponseStatus().equalsIgnoreCase("PUBLISHED")) {
                    responseDummy.setResponseStatus(ResponseCode.STATUS_PUBLISHED);
                    return responseDummy;
                } else {
                    responseDummy.setResponseStatus(ResponseCode.STATUS_DRAFT);
                    return responseDummy;
                }
            }
        }
        responseDummy = new Response();
        responseDummy.setResponseStatus(ResponseCode.STATUS_NOT_RESPONDED);
        return responseDummy;
    }

    //Get single Response object
    @Override
    public Response getResponse(int responseID) {
        System.out.println(getClass().getName());
        bindDB();
        query.where(criteriaBuilder.equal(root.get("responseID"), responseID));
        q = session.createQuery(query);
        this.response = q.getSingleResult();
        System.out.println("Response: " + this.response);
        return this.response;
    }

    //Update response-PUBLISH
    @Override
    public Response updateResponse(Response response) {
        System.out.println(getClass()+"|"+response);
        responseDummy=getResponse(response.getResponseID());
        bindDB();
            responseDummy.setResponseRemarks(response.getResponseRemarks());
            /*responseDummy.setAttachmentFile(response.getAttachmentFile());*/
            responseDummy.setAttachmentDescription(response.getAttachmentDescription());
            responseDummy.setRespondentName(response.getRespondentName());
            responseDummy.setRespondentJobTitle(response.getRespondentJobTitle());
            responseDummy.setResponseDate(new Date());
            responseDummy.setResponseStatus(ResponseCode.STATUS_PUBLISHED);
            session.update(responseDummy);
            System.out.println("Response :"+responseDummy);        
        return responseDummy;
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
