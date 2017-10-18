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
import java.util.Date;

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
	private String responseDescription;
    private String responseRemarks;
    private String attachmentDescription;
    private byte[] attachmentFile;
    @NotNull(message = "required field")
    @Column(nullable = false)
	private String respondentName;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String respondentJobTitile;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private Date responseDate;
    @NotNull(message = "required field")
    @Column(nullable = false)
	private String responseStatus;

    public Response() {
    }

    public Response(@NotNull(message = "required field") Integer unitID, @NotNull(message = "required field") String responseDescription, String responseRemarks, @NotNull(message = "required field") String respondentName, @NotNull(message = "required field") String respondentJobTitile, @NotNull(message = "required field") String responseStatus) {
        this.unitID = unitID;
        this.responseDescription = responseDescription;
        this.responseRemarks = responseRemarks;
        this.respondentName = respondentName;
        this.respondentJobTitile = respondentJobTitile;
        this.responseStatus = responseStatus;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseID=" + responseID +
                ", unitID=" + unitID +
                ", responseDescription='" + responseDescription + '\'' +
                ", responseRemarks='" + responseRemarks + '\'' +
                ", respondentName='" + respondentName + '\'' +
                ", respondentJobTitile='" + respondentJobTitile + '\'' +
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

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getResponseRemarks() {
        return responseRemarks;
    }

    public void setResponseRemarks(String responseRemarks) {
        this.responseRemarks = responseRemarks;
    }

    public String getRespondentName() {
        return respondentName;
    }

    public void setRespondentName(String respondentName) {
        this.respondentName = respondentName;
    }

    public String getRespondentJobTitile() {
        return respondentJobTitile;
    }

    public void setRespondentJobTitile(String respondentJobTitile) {
        this.respondentJobTitile = respondentJobTitile;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
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

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
}
