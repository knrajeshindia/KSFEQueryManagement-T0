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
@Table(name="KSFE_Target_List")
public class Target implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer targetListID;
    @NotNull
    @Column(nullable = false)
	private Integer unitID;
    @NotNull
    @Column(nullable = false)
    private String questionnaireStatus;

    public Target() {
    }

    public Target(@NotNull Integer unitID, @NotNull String questionnaireStatus) {
        this.unitID = unitID;
        this.questionnaireStatus = questionnaireStatus;
    }

    public Integer getTargetListID() {
        return targetListID;
    }

    public void setTargetListID(Integer targetListID) {
        this.targetListID = targetListID;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getQuestionnaireStatus() {
        return questionnaireStatus;
    }

    public void setQuestionnaireStatus(String questionnaireStatus) {
        this.questionnaireStatus = questionnaireStatus;
    }
}
