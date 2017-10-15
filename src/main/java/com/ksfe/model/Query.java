package com.ksfe.model;

/**
 * This is a Java Bean based class,used to hold the DB Details of KSFE
 *
 * @author RNarendran
 * @since 1.0,
 */
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="KSFE_Query_List")
public class Query implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer queryID;
    @NotNull
    @Column(nullable = false)
	private Integer questionnaireID;
    @NotNull
    @Column(nullable = false)
	private String queryDescription;
    @NotNull
    @Column(nullable = false)
	private Integer branchID;
    @NotNull
    @Column(nullable = false)
	private String queryStatus;

	public Integer getQueryID() {
		return queryID;
	}
	public void setQueryID(Integer queryID) {
		this.queryID = queryID;
	}
	public Integer getQuestionnaireID() {
		return questionnaireID;
	}
	public void setQuestionnaireID(Integer questionnaireID) {
		this.questionnaireID = questionnaireID;
	}
	public String getQueryDescription() {
		return queryDescription;
	}
	public void setQueryDescription(String queryDescription) {
		this.queryDescription = queryDescription;
	}
	public Integer getBranchID() {
		return branchID;
	}
	public void setBranchID(Integer branchID) {
		this.branchID = branchID;
	}
	public String getQueryStatus() {
		return queryStatus;
	}
	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	@Override
	public String toString() {
		return "Query [queryID=" + queryID + ", questionnaireID=" + questionnaireID + ", queryDescription="
				+ queryDescription + ", branchID=" + branchID + ", queryStatus=" + queryStatus + "]";
	}
	
	

}
