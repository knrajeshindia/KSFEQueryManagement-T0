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
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "KSFE_Question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionID;

    //Review
    private Integer questionnaireID;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String questionDescription;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String responseDataType;
    //Review
    private String questionStatus;
    @ElementCollection
    private Collection<Integer> answerIDList = new HashSet<Integer>();


    public Question() {
    }

    public Question(@NotNull(message = "required field") String questionDescription, @NotNull(message = "required field") String responseDataType) {
        this.questionDescription = questionDescription;
        this.responseDataType = responseDataType;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", questionnaireID=" + questionnaireID +
                ", questionDescription='" + questionDescription + '\'' +
                ", responseDataType='" + responseDataType + '\'' +
                ", questionStatus='" + questionStatus + '\'' +
                ", answerIDList=" + answerIDList +
                '}';
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

    public String getResponseDataType() {
        return responseDataType;
    }

    public void setResponseDataType(String responseDataType) {
        this.responseDataType = responseDataType;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Collection<Integer> getAnswerIDList() {
        return answerIDList;
    }

    public void setAnswerIDList(Collection<Integer> answerIDList) {
        this.answerIDList = answerIDList;
    }
}
