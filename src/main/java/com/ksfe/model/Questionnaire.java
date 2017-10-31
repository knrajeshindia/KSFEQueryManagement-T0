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

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "KSFE_Questionaire")
public class Questionnaire implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionnaireID;
    @NotNull
    @Column(nullable = false)
    private String questionnaireTitle;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String questionnaireDescription;
    private String questionnaireRemarks;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date postedDate;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date dueDate;
    @ElementCollection
    private List<Integer> targetRespondentIDList = new ArrayList<Integer>();
    @ElementCollection
    private List<Integer> questionIDList = new ArrayList<Integer>();
    @ElementCollection
    private List<Integer> responseIDList = new ArrayList<Integer>();

    //to hold responseStatus for each Questionnaire for a userID - to fileter answered questionnaire
    @Transient
    private String responseStatus;
    @Transient
    private boolean responseFlag;
    @Transient
    private Integer responseID;


    @NotNull(message = "required field")
    @Column(nullable = false)
    private String senderName;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String senderJobTitle;
    private Integer questionnaireStatus;
    private String questionnairePhase;

    public Questionnaire() {
    }

    public Questionnaire(@NotNull String questionnaireTitle, @NotNull(message = "required field") String questionnaireDescription, String questionnaireRemarks, @NotNull(message = "required field") String senderName, @NotNull(message = "required field") String senderJobTitle) {
        this.questionnaireTitle = questionnaireTitle;
        this.questionnaireDescription = questionnaireDescription;
        this.questionnaireRemarks = questionnaireRemarks;
        this.senderName = senderName;
        this.senderJobTitle = senderJobTitle;
        }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "questionnaireID=" + questionnaireID +
                ", questionnaireTitle='" + questionnaireTitle + '\'' +
                ", questionnaireDescription='" + questionnaireDescription + '\'' +
                ", questionnaireRemarks='" + questionnaireRemarks + '\'' +
                ", postedDate=" + postedDate +
                ", dueDate=" + dueDate +
                ", targetRespondentIDList=" + targetRespondentIDList +
                ", questionIDList=" + questionIDList +
                ", responseIDList=" + responseIDList +
                ", responseStatus='" + responseStatus + '\'' +
                ", responseFlag=" + responseFlag +
                ", responseID=" + responseID +
                ", senderName='" + senderName + '\'' +
                ", senderJobTitle='" + senderJobTitle + '\'' +
                ", questionnaireStatus=" + questionnaireStatus +
                ", questionnairePhase='" + questionnairePhase + '\'' +
                '}';
    }

    public String getQuestionnairePhase() {
        return questionnairePhase;
    }

    public void setQuestionnairePhase(String questionnairePhase) {
        this.questionnairePhase = questionnairePhase;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getQuestionnaireID() {
        return questionnaireID;
    }

    public void setQuestionnaireID(Integer questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    public String getQuestionnaireTitle() {
        return questionnaireTitle;
    }

    public void setQuestionnaireTitle(String questionnaireTitle) {
        this.questionnaireTitle = questionnaireTitle;
    }

    public String getQuestionnaireDescription() {
        return questionnaireDescription;
    }

    public void setQuestionnaireDescription(String questionnaireDescription) {
        this.questionnaireDescription = questionnaireDescription;
    }

    public String getQuestionnaireRemarks() {
        return questionnaireRemarks;
    }

    public void setQuestionnaireRemarks(String questionnaireRemarks) {
        this.questionnaireRemarks = questionnaireRemarks;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderJobTitle() {
        return senderJobTitle;
    }

    public void setSenderJobTitle(String senderJobTitle) {
        this.senderJobTitle = senderJobTitle;
    }

    public Integer getQuestionnaireStatus() {
        return questionnaireStatus;
    }

    public void setQuestionnaireStatus(Integer questionnaireStatus) {
        this.questionnaireStatus = questionnaireStatus;
    }

    public List<Integer> getTargetRespondentIDList() {
        return targetRespondentIDList;
    }

    public void setTargetRespondentIDList(List<Integer> targetRespondentIDList) {
        this.targetRespondentIDList = targetRespondentIDList;
    }

    public List<Integer> getQuestionIDList() {
        return questionIDList;
    }

    public void setQuestionIDList(List<Integer> questionIDList) {
        this.questionIDList = questionIDList;
    }

    public List<Integer> getResponseIDList() {
        return responseIDList;
    }

    public void setResponseIDList(List<Integer> responseIDList) {
        this.responseIDList = responseIDList;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public boolean isResponseFlag() {
        return responseFlag;
    }

    public void setResponseFlag(boolean responseFlag) {
        this.responseFlag = responseFlag;
    }

    public Integer getResponseID() {
        return responseID;
    }

    public void setResponseID(Integer responseID) {
        this.responseID = responseID;
    }
}
