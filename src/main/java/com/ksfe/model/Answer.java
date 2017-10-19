/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.model;

/**
 * This is a Java Bean based class,used to hold the DB Details of KSFE
 *
 * Answer class maps reply for individual Questions. Response class holds reply for Questionnaire.
 *
 * @author RNarendran
 * @since 1.0,
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="KSFE_Answer")
public class Answer implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer answerID;

    //Review
	private Integer responseID;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private Integer questionID;
    @NotNull(message = "required field")
    @Column(nullable = false)
	private String answerDescription;

    private String answerStatus;

    public Answer() {
    }

    public Answer(@NotNull(message = "required field") Integer questionID, @NotNull(message = "required field") String answerDescription) {
        this.questionID = questionID;
        this.answerDescription = answerDescription;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerID=" + answerID +
                ", responseID=" + responseID +
                ", questionID=" + questionID +
                ", answerDescription='" + answerDescription + '\'' +
                ", answerStatus='" + answerStatus + '\'' +
                '}';
    }

    public Integer getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Integer answerID) {
        this.answerID = answerID;
    }

    public Integer getResponseID() {
        return responseID;
    }

    public void setResponseID(Integer responseID) {
        this.responseID = responseID;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public void setAnswerDescription(String answerDescription) {
        this.answerDescription = answerDescription;
    }

    public String getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus;
    }
}
