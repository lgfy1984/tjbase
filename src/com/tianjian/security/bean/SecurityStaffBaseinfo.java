package com.tianjian.security.bean;

import java.util.Date;

/**
 * SecurityStaffBaseinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SecurityStaffBaseinfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5191217371970445259L;
	private String id;
	private String staffCode;
	private String hspConfigBaseinfoId;
	private String name;
	private String nameEn;
	private String commConfigSexId;
	private Date dateOfBirth;
	private String commConfigStafftypeId;
	private String idNo;
	private String phone;
	private Long islocation;
	private String comments;
	private String inputCode;
	private Date createDate;
	private String hspStaffBaseinfoId;
	private String createUserId;
	private String createUserName;
	private Long seqNo;
	private String email;
	private String pinyinInputCode;
	private String homePageType;
	private Long staffType;
	private String tenantId;

	// Constructors

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/** default constructor */
	public SecurityStaffBaseinfo() {
	}

	/** full constructor */
	public SecurityStaffBaseinfo(String staffCode, String hspConfigBaseinfoId,
			String name, String nameEn, String commConfigSexId,
			Date dateOfBirth, String commConfigStafftypeId, String idNo,
			String phone, Long islocation, String comments, String inputCode,
			Date createDate, String hspStaffBaseinfoId, String createUserId,
			String createUserName, Long seqNo, String homePageType,
			String email, Long staffType, String pinyinInputCode) {
		this.staffCode = staffCode;
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
		this.name = name;
		this.nameEn = nameEn;
		this.commConfigSexId = commConfigSexId;
		this.dateOfBirth = dateOfBirth;
		this.commConfigStafftypeId = commConfigStafftypeId;
		this.idNo = idNo;
		this.phone = phone;
		this.islocation = islocation;
		this.comments = comments;
		this.inputCode = inputCode;
		this.createDate = createDate;
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.seqNo = seqNo;
		this.homePageType = homePageType;
		this.email = email;
		this.staffType = staffType;
		this.pinyinInputCode = pinyinInputCode;
	}

	// Property accessors

	public String getPinyinInputCode() {
		return pinyinInputCode;
	}

	public void setPinyinInputCode(String pinyinInputCode) {
		this.pinyinInputCode = pinyinInputCode;
	}

	public Long getStaffType() {
		return staffType;
	}

	public void setStaffType(Long staffType) {
		this.staffType = staffType;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStaffCode() {
		return this.staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getHspConfigBaseinfoId() {
		return this.hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getCommConfigSexId() {
		return this.commConfigSexId;
	}

	public void setCommConfigSexId(String commConfigSexId) {
		this.commConfigSexId = commConfigSexId;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCommConfigStafftypeId() {
		return this.commConfigStafftypeId;
	}

	public void setCommConfigStafftypeId(String commConfigStafftypeId) {
		this.commConfigStafftypeId = commConfigStafftypeId;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getIslocation() {
		return this.islocation;
	}

	public void setIslocation(Long islocation) {
		this.islocation = islocation;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getInputCode() {
		return this.inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getHspStaffBaseinfoId() {
		return this.hspStaffBaseinfoId;
	}

	public void setHspStaffBaseinfoId(String hspStaffBaseinfoId) {
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
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

	public Long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public String getHomePageType() {
		return homePageType;
	}

	public void setHomePageType(String homePageType) {
		this.homePageType = homePageType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}