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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionID;
    @NotNull
    @Column(nullable = false)
    private Integer questionnaireID;
    @NotNull
    @Column(nullable = false)
    private String questionDescription;
    private String questionRemarks;
    @OneToMany
    private Collection<Response> responseList = new ArrayList<Response>();
    //Delete - not required-review
    @NotNull
    @Column(nullable = false)
    private Integer unitID;
    //Optional-review
    private String questionStatus;

    public Question() {
    }

    public Question(@NotNull Integer questionnaireID, @NotNull String questionDescription, String questionRemarks, @NotNull Integer unitID, @NotNull String questionStatus) {
        this.questionnaireID = questionnaireID;
        this.questionDescription = questionDescription;
        this.questionRemarks = questionRemarks;
        this.unitID = unitID;
        this.questionStatus = questionStatus;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", questionnaireID=" + questionnaireID +
                ", questionDescription='" + questionDescription + '\'' +
                ", questionRemarks='" + questionRemarks + '\'' +
                ", responseList=" + responseList +
                ", unitID=" + unitID +
                ", questionStatus='" + questionStatus + '\'' +
                '}';
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

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }
}
