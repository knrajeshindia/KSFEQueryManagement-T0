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
@Table(name="KSFE_Unit_Type")
public class UnitType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer unitTypeID;
    @NotNull
    @Column(nullable = false)
    private String unitTypeName;
    @NotNull
    @Column(nullable = false)
    private String unitQueryEligibility;

    public UnitType() {
    }

    public UnitType(Integer unitTypeID, String unitTypeName, @NotNull String unitQueryEligibility) {
        this.unitTypeID = unitTypeID;
        this.unitTypeName = unitTypeName;
        this.unitQueryEligibility = unitQueryEligibility;
    }

    @Override
    public String toString() {
        return "UnitType{" +
                "unitTypeID=" + unitTypeID +
                ", unitTypeName='" + unitTypeName + '\'' +
                ", unitQueryEligibility='" + unitQueryEligibility + '\'' +
                '}';
    }

    public Integer getUnitTypeID() {
        return unitTypeID;
    }

    public void setUnitTypeID(Integer unitTypeID) {
        this.unitTypeID = unitTypeID;
    }

    public String getUnitTypeName() {
        return unitTypeName;
    }

    public void setUnitTypeName(String unitTypeName) {
        this.unitTypeName = unitTypeName;
    }

    public String getUnitQueryEligibility() {
        return unitQueryEligibility;
    }

    public void setUnitQueryEligibility(String unitQueryEligibility) {
        this.unitQueryEligibility = unitQueryEligibility;
    }
}
