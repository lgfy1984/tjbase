package com.tianjian.empi.bean;

import java.util.Date;

/**
 * SecurityConfigUsers entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SecurityConfigUsers implements java.io.Serializable {

	// Fields

	private String securityUserBaseinfoId;
	private String passwd;
	private Long tagc;
	private Long tag;
	private Long licenseTag;
	private Date forbidTime;
	private Long forbidFlag;
	private String comments;
	private Long recordFlag;
	private Date createDate;
	private String createUserId;
	private String createUserName;

	// Constructors

	/** default constructor */
	public SecurityConfigUsers() {
	}

	/** minimal constructor */
	public SecurityConfigUsers(String securityUserBaseinfoId) {
		this.securityUserBaseinfoId = securityUserBaseinfoId;
	}

	/** full constructor */
	public SecurityConfigUsers(String securityUserBaseinfoId, String passwd,
			Long tagc, Long tag, Long licenseTag, Date forbidTime,
			Long forbidFlag, String comments, Long recordFlag, Date createDate,
			String createUserId, String createUserName) {
		this.securityUserBaseinfoId = securityUserBaseinfoId;
		this.passwd = passwd;
		this.tagc = tagc;
		this.tag = tag;
		this.licenseTag = licenseTag;
		this.forbidTime = forbidTime;
		this.forbidFlag = forbidFlag;
		this.comments = comments;
		this.recordFlag = recordFlag;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
	}

	// Property accessors

	public String getSecurityUserBaseinfoId() {
		return this.securityUserBaseinfoId;
	}

	public void setSecurityUserBaseinfoId(String securityUserBaseinfoId) {
		this.securityUserBaseinfoId = securityUserBaseinfoId;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Long getTagc() {
		return this.tagc;
	}

	public void setTagc(Long tagc) {
		this.tagc = tagc;
	}

	public Long getTag() {
		return this.tag;
	}

	public void setTag(Long tag) {
		this.tag = tag;
	}

	public Long getLicenseTag() {
		return this.licenseTag;
	}

	public void setLicenseTag(Long licenseTag) {
		this.licenseTag = licenseTag;
	}

	public Date getForbidTime() {
		return this.forbidTime;
	}

	public void setForbidTime(Date forbidTime) {
		this.forbidTime = forbidTime;
	}

	public Long getForbidFlag() {
		return this.forbidFlag;
	}

	public void setForbidFlag(Long forbidFlag) {
		this.forbidFlag = forbidFlag;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getRecordFlag() {
		return this.recordFlag;
	}

	public void setRecordFlag(Long recordFlag) {
		this.recordFlag = recordFlag;
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