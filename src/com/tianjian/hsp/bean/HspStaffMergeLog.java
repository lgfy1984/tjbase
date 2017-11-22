package com.tianjian.hsp.bean;

import java.util.Date;

/**
 * HspStaffMergeLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HspStaffMergeLog implements java.io.Serializable {

	// Fields

	private String id;
	private String oldStaffId;
	private String newStaffId;
	private String universalId;
	private String oldHspStaffBaseinfoId;
	private String newHspStaffBaseinfoId;
	private Date createDate;
	private String createUserId;
	private String createUserName;
	// 租户id
	private String tenantId;

	// Constructors

	/** default constructor */
	public HspStaffMergeLog() {
	}

	/** minimal constructor */
	public HspStaffMergeLog(String oldStaffId, String newStaffId) {
		this.oldStaffId = oldStaffId;
		this.newStaffId = newStaffId;
	}

	/** full constructor */
	public HspStaffMergeLog(String oldStaffId, String newStaffId,
			String universalId, String oldHspStaffBaseinfoId,
			String newHspStaffBaseinfoId, Date createDate, String createUserId,
			String createUserName) {
		this.oldStaffId = oldStaffId;
		this.newStaffId = newStaffId;
		this.universalId = universalId;
		this.oldHspStaffBaseinfoId = oldHspStaffBaseinfoId;
		this.newHspStaffBaseinfoId = newHspStaffBaseinfoId;
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

	public String getOldStaffId() {
		return this.oldStaffId;
	}

	public void setOldStaffId(String oldStaffId) {
		this.oldStaffId = oldStaffId;
	}

	public String getNewStaffId() {
		return this.newStaffId;
	}

	public void setNewStaffId(String newStaffId) {
		this.newStaffId = newStaffId;
	}

	public String getUniversalId() {
		return this.universalId;
	}

	public void setUniversalId(String universalId) {
		this.universalId = universalId;
	}

	public String getOldHspStaffBaseinfoId() {
		return this.oldHspStaffBaseinfoId;
	}

	public void setOldHspStaffBaseinfoId(String oldHspStaffBaseinfoId) {
		this.oldHspStaffBaseinfoId = oldHspStaffBaseinfoId;
	}

	public String getNewHspStaffBaseinfoId() {
		return this.newHspStaffBaseinfoId;
	}

	public void setNewHspStaffBaseinfoId(String newHspStaffBaseinfoId) {
		this.newHspStaffBaseinfoId = newHspStaffBaseinfoId;
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