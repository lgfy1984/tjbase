package com.tianjian.empi.bean;

import java.util.Date;

/**
 * ArchiveEmpiCompare entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ArchiveEmpiCompare implements java.io.Serializable {

	// Fields

	private ArchiveEmpiCompareId id;
	private Date createDate;
	private String createUserId;
	private String createUserName;

	// Constructors

	/** default constructor */
	public ArchiveEmpiCompare() {
	}

	/** minimal constructor */
	public ArchiveEmpiCompare(ArchiveEmpiCompareId id) {
		this.id = id;
	}

	/** full constructor */
	public ArchiveEmpiCompare(ArchiveEmpiCompareId id, Date createDate,
			String createUserId, String createUserName) {
		this.id = id;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
	}

	// Property accessors

	public ArchiveEmpiCompareId getId() {
		return this.id;
	}

	public void setId(ArchiveEmpiCompareId id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

}