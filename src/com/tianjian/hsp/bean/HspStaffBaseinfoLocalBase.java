package com.tianjian.hsp.bean;

import java.util.Date;

/**
 * HspStaffBaseinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HspStaffBaseinfoLocalBase implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8451639408662590380L;
	private String id;
	private String hspConfigBaseinfoId;
	private String empNo;
	private String name;
	private String idNo;
	private Date birthday;
	private String commConfigSexId;
	private String commConfigNationalityId;
	private Date startWorkDate;
	private String officeTel;
	private String mobileTel;
	private Long islocation;
	private Date createDate;
	private String createUserId;
	private String createUserName;
	private String nameEn;
	private String inputCode;
	private String securityUserBaseinfoId;
	// 租户id
	private String tenantId;

	// Constructors

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/** default constructor */
	public HspStaffBaseinfoLocalBase() {
	}

	/** minimal constructor */
	public HspStaffBaseinfoLocalBase(String id) {
		this.id = id;
	}

	/** full constructor */
	public HspStaffBaseinfoLocalBase(String id, String hspConfigBaseinfoId,
			String empNo, String name, String idNo, Date birthday,
			String commConfigSexId, String commConfigNationalityId,
			Date startWorkDate, String officeTel, String mobileTel,
			Long islocation, Date createDate, String createUserId,
			String createUserName, String nameEn, String inputCode,
			String securityUserBaseinfoId) {
		this.id = id;
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
		this.empNo = empNo;
		this.name = name;
		this.idNo = idNo;
		this.birthday = birthday;
		this.commConfigSexId = commConfigSexId;
		this.commConfigNationalityId = commConfigNationalityId;
		this.startWorkDate = startWorkDate;
		this.officeTel = officeTel;
		this.mobileTel = mobileTel;

		this.islocation = islocation;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.nameEn = nameEn;
		this.inputCode = inputCode;
		this.securityUserBaseinfoId = securityUserBaseinfoId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCommConfigSexId() {
		return this.commConfigSexId;
	}

	public void setCommConfigSexId(String commConfigSexId) {
		this.commConfigSexId = commConfigSexId;
	}

	public String getCommConfigNationalityId() {
		return this.commConfigNationalityId;
	}

	public void setCommConfigNationalityId(String commConfigNationalityId) {
		this.commConfigNationalityId = commConfigNationalityId;
	}

	public Date getStartWorkDate() {
		return this.startWorkDate;
	}

	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public String getOfficeTel() {
		return this.officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getMobileTel() {
		return this.mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public Long getIslocation() {
		return this.islocation;
	}

	public void setIslocation(Long islocation) {
		this.islocation = islocation;
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

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getInputCode() {
		return this.inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getSecurityUserBaseinfoId() {
		return this.securityUserBaseinfoId;
	}

	public void setSecurityUserBaseinfoId(String securityUserBaseinfoId) {
		this.securityUserBaseinfoId = securityUserBaseinfoId;
	}

	public String getHspConfigBaseinfoId() {
		return hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

}