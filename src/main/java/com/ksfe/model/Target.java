/*
 * Copyright (c) 2017, 2018, KSFE and/or its affiliates. All rights reserved.
 * KSFE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ksfe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * This is a Java Bean based class,used to hold the DB Details of KSFE
 *
 * @author RNarendran
 * @since 1.0,
 */


@Entity
@Table(name="KSFE_Target")
public class Target implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer targetRespondentID;
	@NotNull(message = "required field")
	@Column(nullable = false)
	private Integer unitID;


	public Target() {
	}

	@Override
	public String toString() {
		return "Target{" +
				"targetRespondentID=" + targetRespondentID +
				", unitID=" + unitID +
				'}';
	}

	public Integer getTargetRespondentID() {
		return targetRespondentID;
	}

	public void setTargetRespondentID(Integer targetRespondentID) {
		this.targetRespondentID = targetRespondentID;
	}

	public Integer getUnitID() {
		return unitID;
	}

	public void setUnitID(Integer unitID) {
		this.unitID = unitID;
	}
}
