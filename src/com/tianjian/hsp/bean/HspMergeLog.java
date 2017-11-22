package com.tianjian.hsp.bean;

import java.util.Date;

/**
 * HspMergeLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HspMergeLog implements java.io.Serializable {

	// Fields

	private String id;
	private String oldHspId;
	private String newHspId;
	private String universalId;
	private String oldHspConfigBaseinfoId;
	private String newHspConfigBaseinfoId;
	private Date createDate;
	private String createUserId;
	private String createUserName;
	//租户id
	private String tenantId;
	// Constructors

	/** default constructor */
	public HspMergeLog() {
	}

	/** minimal constructor */
	public HspMergeLog(String oldHspId, String newHspId) {
		this.oldHspId = oldHspId;
		this.newHspId = newHspId;
	}

	/** full constructor */
	public HspMergeLog(String oldHspId, String newHspId, String universalId, String oldHspConfigBaseinfoId,
			String newHspConfigBaseinfoId, Date createDate, String createUserId, String createUserName) {
		this.oldHspId = oldHspId;
		this.newHspId = newHspId;
		this.universalId = universalId;
		this.oldHspConfigBaseinfoId = oldHspConfigBaseinfoId;
		this.newHspConfigBaseinfoId = newHspConfigBaseinfoId;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldHspId() {
		return this.oldHspId;
	}

	public void setOldHspId(String oldHspId) {
		this.oldHspId = oldHspId;
	}

	public String getNewHspId() {
		return this.newHspId;
	}

	public void setNewHspId(String newHspId) {
		this.newHspId = newHspId;
	}

	public String getUniversalId() {
		return this.universalId;
	}

	public void setUniversalId(String universalId) {
		this.universalId = universalId;
	}

	public String getOldHspConfigBaseinfoId() {
		return this.oldHspConfigBaseinfoId;
	}

	public void setOldHspConfigBaseinfoId(String oldHspConfigBaseinfoId) {
		this.oldHspConfigBaseinfoId = oldHspConfigBaseinfoId;
	}

	public String getNewHspConfigBaseinfoId() {
		return this.newHspConfigBaseinfoId;
	}

	public void setNewHspConfigBaseinfoId(String newHspConfigBaseinfoId) {
		this.newHspConfigBaseinfoId = newHspConfigBaseinfoId;
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

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}