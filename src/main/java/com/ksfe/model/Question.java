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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="KSFE_Question_List")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer questionID;
    @NotNull
    @Column(nullable = false)
	private Integer questionnaireID;
    @NotNull
    @Column(nullable = false)
	private String questionDescription;
    private String questionRemarks;
    /*@OneToMany
    private Collection<Response> responseList=new ArrayList<Response>();*/
    //Delete - not required-recheck later
    @NotNull
    @Column(nullable = false)
	private Integer unitID;
    @NotNull
    @Column(nullable = false)
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

    public Question(Integer questionID, @NotNull Integer questionnaireID, @NotNull String questionDescription, String questionRemarks, @NotNull Integer unitID, @NotNull String questionStatus) {
        this.questionID = questionID;
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
                ", unitID=" + unitID +
                ", questionStatus='" + questionStatus + '\'' +
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
