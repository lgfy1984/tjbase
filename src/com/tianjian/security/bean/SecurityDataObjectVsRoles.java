package com.tianjian.security.bean;

/**
 * SecurityDataObjectVsRoles entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SecurityDataObjectVsRoles implements java.io.Serializable {

	// Fields

	private String id;
	private String securityStaffBaseinfoId;
	private String sdotId;
	private String sdoValue;

	// Constructors

	/** default constructor */
	public SecurityDataObjectVsRoles() {
	}

	/** minimal constructor */
	public SecurityDataObjectVsRoles(String securityStaffBaseinfoId) {
		this.securityStaffBaseinfoId = securityStaffBaseinfoId;
	}

	/** full constructor */
	public SecurityDataObjectVsRoles(String securityStaffBaseinfoId,
			String sdotId, String sdoValue) {
		this.securityStaffBaseinfoId = securityStaffBaseinfoId;
		this.sdotId = sdotId;
		this.sdoValue = sdoValue;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecurityStaffBaseinfoId() {
		return this.securityStaffBaseinfoId;
	}

	public void setSecurityStaffBaseinfoId(String securityStaffBaseinfoId) {
		this.securityStaffBaseinfoId = securityStaffBaseinfoId;
	}

	public String getSdotId() {
		return this.sdotId;
	}

	public void setSdotId(String sdotId) {
		this.sdotId = sdotId;
	}

	public String getSdoValue() {
		return this.sdoValue;
	}

	public void setSdoValue(String sdoValue) {
		this.sdoValue = sdoValue;
	}

}