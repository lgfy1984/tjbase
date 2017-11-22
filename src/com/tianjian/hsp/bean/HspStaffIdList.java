package com.tianjian.hsp.bean;

import java.util.Date;

/**
 * HspStaffIdList entity. @author MyEclipse Persistence Tools
 */

public class HspStaffIdList implements java.io.Serializable {

	// Fields

	private String id;
	private String hspStaffIdOfAuthority;
	private String hspStaffBaseinfoId;
	private Long seqNo;
	private String authorityId;
	private String authorityName;
	private String typeCode;
	private String facilityUniversalId;
	private Date effectiveDate;
	private Date expirationDate;
	private Date createDate;
	private String createUserId;
	private String createUserName;
	// 租户id
	private String tenantId;

	// Constructors

	/** default constructor */
	public HspStaffIdList() {
	}

	/** minimal constructor */
	public HspStaffIdList(String id, String hspStaffIdOfAuthority,
			String hspStaffBaseinfoId, String authorityId) {
		this.id = id;
		this.hspStaffIdOfAuthority = hspStaffIdOfAuthority;
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
		this.authorityId = authorityId;
	}

	/** full constructor */
	public HspStaffIdList(String id, String hspStaffIdOfAuthority,
			String hspStaffBaseinfoId, Long seqNo, String authorityId,
			String authorityName, String typeCode, String facilityUniversalId,
			Date effectiveDate, Date expirationDate, Date createDate,
			String createUserId, String createUserName) {
		this.id = id;
		this.hspStaffIdOfAuthority = hspStaffIdOfAuthority;
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
		this.seqNo = seqNo;
		this.authorityId = authorityId;
		this.authorityName = authorityName;
		this.typeCode = typeCode;
		this.facilityUniversalId = facilityUniversalId;
		this.effectiveDate = effectiveDate;
		this.expirationDate = expirationDate;
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

	public String getHspStaffIdOfAuthority() {
		return this.hspStaffIdOfAuthority;
	}

	public void setHspStaffIdOfAuthority(String hspStaffIdOfAuthority) {
		this.hspStaffIdOfAuthority = hspStaffIdOfAuthority;
	}

	public String getHspStaffBaseinfoId() {
		return this.hspStaffBaseinfoId;
	}

	public void setHspStaffBaseinfoId(String hspStaffBaseinfoId) {
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
	}

	public Long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public String getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return this.authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getFacilityUniversalId() {
		return this.facilityUniversalId;
	}

	public void setFacilityUniversalId(String facilityUniversalId) {
		this.facilityUniversalId = facilityUniversalId;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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