/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.model;

/**
 * This is a Java Bean based class,used to hold the DB Details of KSFE
 *
 * @author RNarendran
 * @since 1.0,
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "KSFE_Question")
public class Question implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionID;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private Integer questionnaireID;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String questionDescription;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String responseDataType;
    private String questionRemarks;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<Response> responseList = new ArrayList<Response>();


    public Question() {
    }

    public Question(@NotNull(message = "required field") Integer questionnaireID, @NotNull(message = "required field") String questionDescription, @NotNull(message = "required field") String responseDataType, String questionRemarks) {
        this.questionnaireID = questionnaireID;
        this.questionDescription = questionDescription;
        this.responseDataType = responseDataType;
        this.questionRemarks = questionRemarks;
        }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", questionnaireID=" + questionnaireID +
                ", questionDescription='" + questionDescription + '\'' +
                ", questionRemarks='" + questionRemarks + '\'' +
                ", responseList=" + responseList +
               '}';
    }

    public String getResponseDataType() {
        return responseDataType;
    }

    public void setResponseDataType(String responseDataType) {
        this.responseDataType = responseDataType;
    }

    public Collection<Response> getResponseList() {
        return responseList;
    }

    public void setResponseList(Collection<Response> responseList) {
        this.responseList = responseList;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public Integer getQuestionnaireID() {
        return questionnaireID;
    }

    public void setQuestionnaireID(Integer questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getQuestionRemarks() {
        return questionRemarks;
    }

    public void setQuestionRemarks(String questionRemarks) {
        this.questionRemarks = questionRemarks;
    }

}
