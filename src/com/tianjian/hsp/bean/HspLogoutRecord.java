package com.tianjian.hsp.bean;

import java.util.Date;

/**
 * HspLogoutRecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HspLogoutRecord implements java.io.Serializable {

	// Fields

	private String id;
	private String hspConfigBaseinfoId;
	private String itemCode;
	private String itemName;
	private Date dateCreated;
	private String creatorName;
	private Date logoutTime;
	private String logoutReason;
	private String createUserId;
	private String createUserName;
	// 租户id
	private String tenantId;

	// Constructors

	/** default constructor */
	public HspLogoutRecord() {
	}

	/** full constructor */
	public HspLogoutRecord(String hspConfigBaseinfoId, String itemCode,
			String itemName, Date dateCreated, String creatorName,
			Date logoutTime, String logoutReason, String createUserId,
			String createUserName) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.dateCreated = dateCreated;
		this.creatorName = creatorName;
		this.logoutTime = logoutTime;
		this.logoutReason = logoutReason;
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

	public String getHspConfigBaseinfoId() {
		return this.hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLogoutReason() {
		return this.logoutReason;
	}

	public void setLogoutReason(String logoutReason) {
		this.logoutReason = logoutReason;
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