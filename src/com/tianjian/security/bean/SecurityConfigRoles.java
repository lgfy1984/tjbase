package com.tianjian.security.bean;

/**
 * SecurityConfigRoles generated by MyEclipse Persistence Tools
 */

public class SecurityConfigRoles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7729905415057362573L;
	private String id;
	/** 角色ID */
	private String roleCode;
	/** 角色描述 */
	private String roleDetail;
	/** 输入码 */
	private String inputCode;
	/** 备注 */
	private String comments;
	/** 序号 */
	private Long serialNo;
	/** 角色类型（默认=0;0-普通角色，1-系统角色 */
	private Long roleType;
	/** 1县级应用系统2市级应用系统 */
	private Long roleFlag;
	/** 租户Id */
	private String tenantId;

	// Constructors

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	/** default constructor */
	public SecurityConfigRoles() {
	}

	/** minimal constructor */
	public SecurityConfigRoles(String roleCode) {
		this.roleCode = roleCode;
	}

	/** full constructor */
	public SecurityConfigRoles(String roleCode, String roleDetail,
			String inputCode, String comments, Long serialno, Long roleType,
			Long roleFlag) {
		this.roleCode = roleCode;
		this.roleDetail = roleDetail;
		this.inputCode = inputCode;
		this.comments = comments;
		this.serialNo = serialno;
		this.roleType = roleType;
		this.roleFlag = roleFlag;
	}

	// Property accessors

	public Long getRoleType() {
		return roleType;
	}

	public void setRoleType(Long roleType) {
		this.roleType = roleType;
	}

	public Long getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(Long roleFlag) {
		this.roleFlag = roleFlag;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDetail() {
		return this.roleDetail;
	}

	public void setRoleDetail(String roleDetail) {
		this.roleDetail = roleDetail;
	}

	public String getInputCode() {
		return this.inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}