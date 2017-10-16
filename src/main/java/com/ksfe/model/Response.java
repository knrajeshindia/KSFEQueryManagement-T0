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

@Entity
@Table(name="KSFE_Response_List")
public class Response implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer responseID;
    @NotNull
    @Column(nullable = false)
	private Integer unitID;
    @NotNull
    @Column(nullable = false)
	private String responseDescription;
    private String responseRemarks;
    @NotNull
    @Column(nullable = false)
	private String respondentName;
    @NotNull
    @Column(nullable = false)
    private String respondentJobTitile;
    @NotNull
    @Column(nullable = false)
	private String responseStatus;

    public Response() {
    }

    public Response(@NotNull Integer unitID, @NotNull String responseDescription, String responseRemarks, @NotNull String respondentName, @NotNull String respondentJobTitile, @NotNull String responseStatus) {
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
}
