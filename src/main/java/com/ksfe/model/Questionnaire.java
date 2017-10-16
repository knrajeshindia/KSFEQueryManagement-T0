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
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(name = "KSFE_Questionaire_List")
public class Questionnaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionnaireID;
    @NotNull
    @Column(nullable = false)
    private String questionnaireTitle;
    @NotNull
    @Column(nullable = false)
    private String questionnaireDescription;
    private String questionnaireRemarks;
    @Temporal(TemporalType.DATE)
    private Date postedDate;
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @OneToMany
    private Collection<Target> targetRespondentList = new HashSet<Target>();
    @OneToMany
    private Collection<Question> questionList = new ArrayList<Question>();
    @NotNull
    @Column(nullable = false)
    private String senderName;
    @NotNull
    @Column(nullable = false)
    private String senderJobTitle;
    @NotNull
    @Column(nullable = false)
    private Integer questionnaireStatus;

    public Questionnaire() {
    }

    public Questionnaire(@NotNull String questionnaireTitle, @NotNull String questionnaireDescription, String questionnaireRemarks, @NotNull String senderName, @NotNull String senderJobTitle, @NotNull Integer questionnaireStatus) {
        this.questionnaireTitle = questionnaireTitle;
        this.questionnaireDescription = questionnaireDescription;
        this.questionnaireRemarks = questionnaireRemarks;
        this.senderName = senderName;
        this.senderJobTitle = senderJobTitle;
        this.questionnaireStatus = questionnaireStatus;
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
                ", targetRespondentList=" + targetRespondentList +
                ", questionList=" + questionList +
                ", senderName='" + senderName + '\'' +
                ", senderJobTitle='" + senderJobTitle + '\'' +
                ", questionnaireStatus=" + questionnaireStatus +
                '}';
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

    public Collection<Target> getTargetRespondentList() {
        return targetRespondentList;
    }

    public void setTargetRespondentList(Collection<Target> targetRespondentList) {
        this.targetRespondentList = targetRespondentList;
    }

    public Collection<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(Collection<Question> questionList) {
        this.questionList = questionList;
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
}
