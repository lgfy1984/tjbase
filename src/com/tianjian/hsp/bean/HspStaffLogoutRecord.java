package com.tianjian.hsp.bean;

import java.util.Date;

/**
 * HspStaffLogoutRecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HspStaffLogoutRecord implements java.io.Serializable {

	// Fields

	private String id;
	private String hspStaffBaseinfoId;
	private String empNo;
	private String name;
	private String idNo;
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
	public HspStaffLogoutRecord() {
	}

	/** minimal constructor */
	public HspStaffLogoutRecord(String empNo) {
		this.empNo = empNo;
	}

	/** full constructor */
	public HspStaffLogoutRecord(String hspStaffBaseinfoId, String empNo,
			String name, String idNo, Date dateCreated, String creatorName,
			Date logoutTime, String logoutReason, String createUserId,
			String createUserName) {
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
		this.empNo = empNo;
		this.name = name;
		this.idNo = idNo;
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

	public String getHspStaffBaseinfoId() {
		return this.hspStaffBaseinfoId;
	}

	public void setHspStaffBaseinfoId(String hspStaffBaseinfoId) {
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
	}

	public String getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
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