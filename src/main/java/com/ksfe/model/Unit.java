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
    @ManyToOne
    private UnitType unitType;
    //Duplicate unitID for binding branches to region-where applicable
    private Integer regionID;
    @NotNull
    @Column(nullable = false)
	private String password;
	@NotNull
	@Column(nullable = false)
	private String unitName;
    @NotNull
    @Column(nullable = false)
    private String unitCode;
    @NotNull
    @Column(nullable = false)
    private String unitAddress;
    @NotNull
    @Column(nullable = false)
    private String unitDistrict;
    @NotNull
    @Column(nullable = false)
    private String unitManager;
    @NotNull
    @Column(nullable = false)
    private String unitEmail;
    @NotNull
    @Column(nullable = false)
    private String unitMobile;
    @NotNull
    @Column(nullable = false)
    private String unitTelephone;
    @NotNull
    @Column(nullable = false)
	private String unitStatus;

    public Unit() {
    }

	public Unit(Integer regionID, @NotNull String password, @NotNull String unitName, @NotNull String unitCode, @NotNull String unitAddress, @NotNull String unitDistrict, @NotNull String unitManager, @NotNull String unitEmail, @NotNull String unitMobile, @NotNull String unitTelephone, @NotNull String unitStatus) {
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
				", unitType=" + unitType +
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

	/**
	 * @return the unitID
	 */
	public Integer getUnitID() {
		return unitID;
	}


	/**
	 * @param unitID the unitID to set
	 */
	public void setUnitID(Integer unitID) {
		this.unitID = unitID;
	}


	/**
	 * @return the unitType
	 */
	public UnitType getUnitType() {
		return unitType;
	}


	/**
	 * @param unitType the unitType to set
	 */
	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}


	/**
	 * @return the regionID
	 */
	public Integer getRegionID() {
		return regionID;
	}


	/**
	 * @param regionID the regionID to set
	 */
	public void setRegionID(Integer regionID) {
		this.regionID = regionID;
	}


	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}


	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	/**
	 * @return the unitCode
	 */
	public String getUnitCode() {
		return unitCode;
	}


	/**
	 * @param unitCode the unitCode to set
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}


	/**
	 * @return the unitAddress
	 */
	public String getUnitAddress() {
		return unitAddress;
	}


	/**
	 * @param unitAddress the unitAddress to set
	 */
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}


	/**
	 * @return the unitDistrict
	 */
	public String getUnitDistrict() {
		return unitDistrict;
	}


	/**
	 * @param unitDistrict the unitDistrict to set
	 */
	public void setUnitDistrict(String unitDistrict) {
		this.unitDistrict = unitDistrict;
	}


	/**
	 * @return the unitManager
	 */
	public String getUnitManager() {
		return unitManager;
	}


	/**
	 * @param unitManager the unitManager to set
	 */
	public void setUnitManager(String unitManager) {
		this.unitManager = unitManager;
	}


	/**
	 * @return the unitEmail
	 */
	public String getUnitEmail() {
		return unitEmail;
	}


	/**
	 * @param unitEmail the unitEmail to set
	 */
	public void setUnitEmail(String unitEmail) {
		this.unitEmail = unitEmail;
	}


	/**
	 * @return the unitMobile
	 */
	public String getUnitMobile() {
		return unitMobile;
	}


	/**
	 * @param unitMobile the unitMobile to set
	 */
	public void setUnitMobile(String unitMobile) {
		this.unitMobile = unitMobile;
	}


	/**
	 * @return the unitTelephone
	 */
	public String getUnitTelephone() {
		return unitTelephone;
	}


	/**
	 * @param unitTelephone the unitTelephone to set
	 */
	public void setUnitTelephone(String unitTelephone) {
		this.unitTelephone = unitTelephone;
	}


	/**
	 * @return the unitStatus
	 */
	public String getUnitStatus() {
		return unitStatus;
	}


	/**
	 * @param unitStatus the unitStatus to set
	 */
	public void setUnitStatus(String unitStatus) {
		this.unitStatus = unitStatus;
	}


}
