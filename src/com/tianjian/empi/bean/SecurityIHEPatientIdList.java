package com.tianjian.empi.bean;

import java.util.Date;

/**
 * ArchiveEmpiCompare entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SecurityIHEPatientIdList implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4581903582630005713L;
	// Fields

	private String id;
	private String pid;
	private String securityUserBaseinfoId;
	private Long seqNo;
	private Long checkDigit;
	private String checkDigitScheme;
	private String authorityId;
	private String authorityName;
	private String facilityId;
	private String facilityName;
	private String typeCode;
	private Date effectiveDate;
	private Date expirationDate;
	private Date createDate;  
	private String createUserId;   
	private String createUserName;  

	// Constructors

	/** default constructor */
	public SecurityIHEPatientIdList() {
		  id = "";
		  pid = "";
		  securityUserBaseinfoId = "";
		  checkDigitScheme = "";
		  authorityId = "";
		  authorityName = "";
		  facilityId = "";
		  facilityName = "";
		  typeCode = "";
		  createUserId="";
		  createUserName="";
	}
	
	/** full constructor */
	public SecurityIHEPatientIdList(String id,String pid, String securityUserBaseinfoId, 
			Long seqNo,Long checkDigit,String checkDigitScheme,String authorityId,
			String facilityId,String typeCode,Date effectiveDate,Date expirationDate,
			String authorityName,String facilityName,Date createDate,String createUserId,String createUserName) {
		this.id = id;
		this.pid = pid;
		this.securityUserBaseinfoId = securityUserBaseinfoId;
		this.seqNo = seqNo;
		this.checkDigit = checkDigit;
		this.checkDigitScheme = checkDigitScheme;
		this.authorityId = authorityId;
		this.authorityName = authorityName;
		this.facilityId = facilityId;
		this.facilityName = facilityName;
		this.typeCode = typeCode;
		this.effectiveDate = effectiveDate;
		this.expirationDate = expirationDate;
		this.createDate= createDate;
		this.createUserId=createUserId;
		this.createUserName=createUserName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	

	public String getSecurityUserBaseinfoId() {
		return securityUserBaseinfoId;
	}

	public void setSecurityUserBaseinfoId(String securityUserBaseinfoId) {
		this.securityUserBaseinfoId = securityUserBaseinfoId;
	}

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}
	public Long getCheckDigit() {
		return checkDigit;
	}

	public void setCheckDigit(Long checkDigit) {
		this.checkDigit = checkDigit;
	}

	public String getCheckDigitScheme() {
		return checkDigitScheme;
	}

	public void setCheckDigitScheme(String checkDigitScheme) {
		this.checkDigitScheme = checkDigitScheme;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
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
	
	
}