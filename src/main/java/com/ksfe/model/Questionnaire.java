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
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

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
    private Collection<Integer> targetRespondentIDList = new HashSet<Integer>();
    @ElementCollection
    private Collection<Integer> questionIDList = new HashSet<Integer>();
    @ElementCollection
    private Collection<Integer> responseIDList = new HashSet<Integer>();


    @NotNull(message = "required field")
    @Column(nullable = false)
    private String senderName;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String senderJobTitle;
    private Integer questionnaireStatus;

    public Questionnaire() {
    }

    public Questionnaire(@NotNull String questionnaireTitle, @NotNull(message = "required field") String questionnaireDescription, String questionnaireRemarks, @NotNull(message = "required field") String senderName, @NotNull(message = "required field") String senderJobTitle) {
        this.questionnaireTitle = questionnaireTitle;
        this.questionnaireDescription = questionnaireDescription;
        this.questionnaireRemarks = questionnaireRemarks;
        this.senderName = senderName;
        this.senderJobTitle = senderJobTitle;
        }

    /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */

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
                ", senderName='" + senderName + '\'' +
                ", senderJobTitle='" + senderJobTitle + '\'' +
                ", questionnaireStatus=" + questionnaireStatus +
                '}';
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

    public Collection<Integer> getTargetRespondentIDList() {
        return targetRespondentIDList;
    }

    public void setTargetRespondentIDList(Collection<Integer> targetRespondentIDList) {
        this.targetRespondentIDList = targetRespondentIDList;
    }

    public Collection<Integer> getQuestionIDList() {
        return questionIDList;
    }

    public void setQuestionIDList(Collection<Integer> questionIDList) {
        this.questionIDList = questionIDList;
    }

    public Collection<Integer> getResponseIDList() {
        return responseIDList;
    }

    public void setResponseIDList(Collection<Integer> responseIDList) {
        this.responseIDList = responseIDList;
    }
}
