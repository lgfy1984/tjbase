package com.tianjian.empi.bean;

import java.util.Date;


/**
 * CommConfigLocation entity. @author MyEclipse Persistence Tools
 */

public class SecurityIheMergeLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2193610868672922151L;
	private String id;
	private String oldPid;
	private String newPid;
	private String universalId;
	private String oldSecurityUserBaseinfoId;
	private String newSecurityUserBaseinfoId;
	private Date createDate;
	private String createUserId;
	private String createUserName;

	// Constructors

	/** default constructor */
	public SecurityIheMergeLog() {
	}

	/** minimal constructor */
	public SecurityIheMergeLog(String id) {
		this.id = id;
	}

	/** full constructor */
	public SecurityIheMergeLog(String id, String oldPid, String newPid,
			String universalId, String oldSecurityUserBaseinfoId, String newSecurityUserBaseinfoId,
			Date createDate, String createUserId,String createUserName) {
		this.id = id;
		this.oldPid = oldPid;
		this.newPid = newPid;
		this.universalId = universalId;
		this.oldSecurityUserBaseinfoId = oldSecurityUserBaseinfoId;
		this.newSecurityUserBaseinfoId = newSecurityUserBaseinfoId;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldPid() {
		return oldPid;
	}

	public void setOldPid(String oldPid) {
		this.oldPid = oldPid;
	}

	public String getNewPid() {
		return newPid;
	}

	public void setNewPid(String newPid) {
		this.newPid = newPid;
	}

	public String getUniversalId() {
		return universalId;
	}

	public void setUniversalId(String universalId) {
		this.universalId = universalId;
	}

	public String getOldSecurityUserBaseinfoId() {
		return oldSecurityUserBaseinfoId;
	}

	public void setOldSecurityUserBaseinfoId(String oldSecurityUserBaseinfoId) {
		this.oldSecurityUserBaseinfoId = oldSecurityUserBaseinfoId;
	}

	public String getNewSecurityUserBaseinfoId() {
		return newSecurityUserBaseinfoId;
	}

	public void setNewSecurityUserBaseinfoId(String newSecurityUserBaseinfoId) {
		this.newSecurityUserBaseinfoId = newSecurityUserBaseinfoId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	// Property accessors

	
}