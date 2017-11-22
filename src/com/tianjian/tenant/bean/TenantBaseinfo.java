package com.tianjian.tenant.bean;

import java.util.Date;

/**
 * TenantBaseinfo entity. @author MyEclipse Persistence Tools
 */

public class TenantBaseinfo implements java.io.Serializable {

	private static final long serialVersionUID = -1349753774240380063L;
	// Fields
	private String id;
	private String itemCode;
	private String itemName;
	private String address;
	private String zipcode;
	private String contactPersonName;
	private String phone;
	private String comments;
	private Long seqNo;
	private String EMail;
	private String passwd;
	private Date createDate;
	private String createUserId;
	private String createUserName;

	// Constructors

	/** default constructor */
	public TenantBaseinfo() {
	}

	public TenantBaseinfo(String id, String itemName) {
		this.id = id;
		this.itemName = itemName;
	}


	/** full constructor */
	public TenantBaseinfo(String itemCode, String itemName, String address,
			String zipcode, String contactPersonName, String phone,
			String comments, Long seqNo, String EMail, String passwd,
			Date createDate, String createUserId, String createUserName) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.address = address;
		this.zipcode = zipcode;
		this.contactPersonName = contactPersonName;
		this.phone = phone;
		this.comments = comments;
		this.seqNo = seqNo;
		this.EMail = EMail;
		this.passwd = passwd;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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