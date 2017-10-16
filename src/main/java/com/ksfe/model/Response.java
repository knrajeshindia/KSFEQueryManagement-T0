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
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer responseID;
    @NotNull
    @Column(nullable = false)
	private Integer responsenaireID;
    @NotNull
    @Column(nullable = false)
	private String responseDescription;
    private String responseRemarks;
    //Delete - not required-recheck later
    @NotNull
    @Column(nullable = false)
	private Integer unitID;
    @NotNull
    @Column(nullable = false)
	private String responseStatus;

    public Response() {
    }

    public Response(@NotNull Integer responsenaireID, @NotNull String responseDescription, String responseRemarks, @NotNull Integer unitID, @NotNull String responseStatus) {
        this.responsenaireID = responsenaireID;
        this.responseDescription = responseDescription;
        this.responseRemarks = responseRemarks;
        this.unitID = unitID;
        this.responseStatus = responseStatus;
    }

    public Response(Integer responseID, @NotNull Integer responsenaireID, @NotNull String responseDescription, String responseRemarks, @NotNull Integer unitID, @NotNull String responseStatus) {
        this.responseID = responseID;
        this.responsenaireID = responsenaireID;
        this.responseDescription = responseDescription;
        this.responseRemarks = responseRemarks;
        this.unitID = unitID;
        this.responseStatus = responseStatus;
    }

    @Override
    public String toString() {
        return "Question{" +
                "responseID=" + responseID +
                ", responsenaireID=" + responsenaireID +
                ", responseDescription='" + responseDescription + '\'' +
                ", responseRemarks='" + responseRemarks + '\'' +
                ", unitID=" + unitID +
                ", responseStatus='" + responseStatus + '\'' +
                '}';
    }

    public Integer getQuestionID() {
        return responseID;
    }

    public void setQuestionID(Integer responseID) {
        this.responseID = responseID;
    }

    public Integer getQuestionnaireID() {
        return responsenaireID;
    }

    public void setQuestionnaireID(Integer responsenaireID) {
        this.responsenaireID = responsenaireID;
    }

    public String getQuestionDescription() {
        return responseDescription;
    }

    public void setQuestionDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getQuestionRemarks() {
        return responseRemarks;
    }

    public void setQuestionRemarks(String responseRemarks) {
        this.responseRemarks = responseRemarks;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getQuestionStatus() {
        return responseStatus;
    }

    public void setQuestionStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
