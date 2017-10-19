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
@Table(name="KSFE_Unit")
public class Unit implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer unitID;

    private Integer unitTypeID;
    //Duplicate unitID for binding branches to region-where applicable
    private Integer regionID;
    @NotNull(message = "required field")
    @Column(nullable = false)
	private String password;
	@NotNull(message = "required field")
	@Column(nullable = false)
	private String unitName;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String unitCode;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String unitAddress;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String unitDistrict;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String unitManager;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String unitEmail;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String unitMobile;
    @NotNull(message = "required field")
    @Column(nullable = false)
    private String unitTelephone;
    @NotNull(message = "required field")
    @Column(nullable = false)
	private String unitStatus;

    public Unit() {
    }

	public Unit(Integer regionID, @NotNull(message = "required field") String password, @NotNull(message = "required field") String unitName, @NotNull(message = "required field") String unitCode, @NotNull(message = "required field") String unitAddress, @NotNull(message = "required field") String unitDistrict, @NotNull(message = "required field") String unitManager, @NotNull(message = "required field") String unitEmail, @NotNull(message = "required field") String unitMobile, @NotNull(message = "required field") String unitTelephone, @NotNull(message = "required field") String unitStatus) {
		this.regionID = regionID;
		this.password = password;
		this.unitName = unitName;
		this.unitCode = unitCode;
		this.unitAddress = unitAddress;
		this.unitDistrict = unitDistrict;
		this.unitManager = unitManager;
		this.unitEmail = unitEmail;
		this.unitMobile = unitMobile;
		this.unitTelephone = unitTelephone;
		this.unitStatus = unitStatus;
	}

	@Override
	public String toString() {
		return "Unit{" +
				"unitID=" + unitID +
				", unitTypeID=" + unitTypeID +
				", regionID=" + regionID +
				", password='" + password + '\'' +
				", unitName='" + unitName + '\'' +
				", unitCode='" + unitCode + '\'' +
				", unitAddress='" + unitAddress + '\'' +
				", unitDistrict='" + unitDistrict + '\'' +
				", unitManager='" + unitManager + '\'' +
				", unitEmail='" + unitEmail + '\'' +
				", unitMobile='" + unitMobile + '\'' +
				", unitTelephone='" + unitTelephone + '\'' +
				", unitStatus='" + unitStatus + '\'' +
				'}';
	}

	public Integer getUnitID() {
		return unitID;
	}

	public void setUnitID(Integer unitID) {
		this.unitID = unitID;
	}

	public Integer getUnitTypeID() {
		return unitTypeID;
	}

	public void setUnitTypeID(Integer unitTypeID) {
		this.unitTypeID = unitTypeID;
	}

	public Integer getRegionID() {
		return regionID;
	}

	public void setRegionID(Integer regionID) {
		this.regionID = regionID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public String getUnitDistrict() {
		return unitDistrict;
	}

	public void setUnitDistrict(String unitDistrict) {
		this.unitDistrict = unitDistrict;
	}

	public String getUnitManager() {
		return unitManager;
	}

	public void setUnitManager(String unitManager) {
		this.unitManager = unitManager;
	}

	public String getUnitEmail() {
		return unitEmail;
	}

	public void setUnitEmail(String unitEmail) {
		this.unitEmail = unitEmail;
	}

	public String getUnitMobile() {
		return unitMobile;
	}

	public void setUnitMobile(String unitMobile) {
		this.unitMobile = unitMobile;
	}

	public String getUnitTelephone() {
		return unitTelephone;
	}

	public void setUnitTelephone(String unitTelephone) {
		this.unitTelephone = unitTelephone;
	}

	public String getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(String unitStatus) {
		this.unitStatus = unitStatus;
	}
}
