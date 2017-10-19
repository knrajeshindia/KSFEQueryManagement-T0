/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.model;

/**
 * This is a Java Bean based class,used to hold the DB Details of KSFE
 *
 * Answer class maps reply for individual Questions. Response class holds reply for Questionnaire.
 * *
 * @author RNarendran
 * @since 1.0,
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(name="KSFE_Response")
public class Response implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer responseID;
    @NotNull(message = "required field")
    @Column(nullable = false)
	private Integer unitID;

    @NotNull(message = "required field")
    @Column(nullable = false)
    private Integer questionnaireID;

    private String responseRemarks;
    private String attachmentDescription;
    private byte[] attachmentFile;
    @NotNull(message = "required field")
    @Column(nullable = false)
	private String respondentName;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String respondentJobTitle;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private Date responseDate;
    @ElementCollection
    private Collection<Integer> answerIDList = new HashSet<Integer>();

	private String responseStatus;

    public Response() {
    }

    public Response(@NotNull(message = "required field") Integer unitID, @NotNull(message = "required field") Integer questionnaireID, @NotNull(message = "required field") String respondentName, @NotNull(message = "required field") String respondentJobTitle) {
        this.unitID = unitID;
        this.questionnaireID = questionnaireID;
        this.respondentName = respondentName;
        this.respondentJobTitle = respondentJobTitle;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseID=" + responseID +
                ", unitID=" + unitID +
                ", questionnaireID=" + questionnaireID +
                ", responseRemarks='" + responseRemarks + '\'' +
                ", attachmentDescription='" + attachmentDescription + '\'' +
                ", attachmentFile=" + Arrays.toString(attachmentFile) +
                ", respondentName='" + respondentName + '\'' +
                ", respondentJobTitle='" + respondentJobTitle + '\'' +
                ", responseDate=" + responseDate +
                ", answerIDList=" + answerIDList +
                ", responseStatus='" + responseStatus + '\'' +
                '}';
    }

    public Integer getResponseID() {
        return responseID;
    }

    public void setResponseID(Integer responseID) {
        this.responseID = responseID;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public Integer getQuestionnaireID() {
        return questionnaireID;
    }

    public void setQuestionnaireID(Integer questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    public String getResponseRemarks() {
        return responseRemarks;
    }

    public void setResponseRemarks(String responseRemarks) {
        this.responseRemarks = responseRemarks;
    }

    public String getAttachmentDescription() {
        return attachmentDescription;
    }

    public void setAttachmentDescription(String attachmentDescription) {
        this.attachmentDescription = attachmentDescription;
    }

    public byte[] getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(byte[] attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public String getRespondentName() {
        return respondentName;
    }

    public void setRespondentName(String respondentName) {
        this.respondentName = respondentName;
    }

    public String getRespondentJobTitle() {
        return respondentJobTitle;
    }

    public void setRespondentJobTitle(String respondentJobTitle) {
        this.respondentJobTitle = respondentJobTitle;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Collection<Integer> getAnswerIDList() {
        return answerIDList;
    }

    public void setAnswerIDList(Collection<Integer> answerIDList) {
        this.answerIDList = answerIDList;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
