package com.tianjian.hsp.bean;

import java.util.Date;

/**
 * HspStaffBaseinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HspStaffBaseinfo implements java.io.Serializable {

	// Fields

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
	private String commDictPublicCharId1;
	private String workCertificateNo;
	private String commDictPublicCharId2;
	private String commConfigPositiontitleId;
	private String commConfigEmptitleId;
	private String commDictPublicCharId3;
	private String commConfigDegreeId;
	private String commConfigDegreeLevelId;
	private String commConfigProfessionId;
	private Long islocation;
	private Date createDate;
	private String createUserId;
	private String createUserName;
	private String nameEn;
	private String inputCode;
	private String securityUserBaseinfoId;
	private Long isemployment;
	private Date retiredDate;
	private String innerFileNumber;
	private String professionalTittleName;
	private String postTittle;
	private String relatedDepartment;
	private String idType;
	private String bdeptCode;

	private Long count;
	private String itemName;

	// 租户id
	private String tenantId;

	// Constructors

	/** default constructor */
	public HspStaffBaseinfo() {
	}

	/** full constructor */
	public HspStaffBaseinfo(String hspConfigBaseinfoId, String empNo,
			String name, String idNo, Date birthday, String commConfigSexId,
			String commConfigNationalityId, Date startWorkDate,
			String officeTel, String mobileTel, String commDictPublicCharId1,
			String workCertificateNo, String commDictPublicCharId2,
			String commConfigPositiontitleId, String commConfigEmptitleId,
			String commDictPublicCharId3, String commConfigDegreeId,
			String commConfigDegreeLevelId, String commConfigProfessionId,
			Long islocation, Date createDate, String createUserId,
			String createUserName, String nameEn, String inputCode,
			String securityUserBaseinfoId, Long isemployment, Date retiredDate,
			String innerFileNumber, String professionalTittleName,
			String postTittle, String relatedDepartment, String idType,
			String bdeptCode) {
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
		this.commDictPublicCharId1 = commDictPublicCharId1;
		this.workCertificateNo = workCertificateNo;
		this.commDictPublicCharId2 = commDictPublicCharId2;
		this.commConfigPositiontitleId = commConfigPositiontitleId;
		this.commConfigEmptitleId = commConfigEmptitleId;
		this.commDictPublicCharId3 = commDictPublicCharId3;
		this.commConfigDegreeId = commConfigDegreeId;
		this.commConfigDegreeLevelId = commConfigDegreeLevelId;
		this.commConfigProfessionId = commConfigProfessionId;
		this.islocation = islocation;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.nameEn = nameEn;
		this.inputCode = inputCode;
		this.securityUserBaseinfoId = securityUserBaseinfoId;
		this.isemployment = isemployment;
		this.retiredDate = retiredDate;
		this.innerFileNumber = innerFileNumber;
		this.professionalTittleName = professionalTittleName;
		this.postTittle = postTittle;
		this.relatedDepartment = relatedDepartment;
		this.idType = idType;
		this.bdeptCode = bdeptCode;
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

	public String getCommDictPublicCharId1() {
		return this.commDictPublicCharId1;
	}

	public void setCommDictPublicCharId1(String commDictPublicCharId1) {
		this.commDictPublicCharId1 = commDictPublicCharId1;
	}

	public String getWorkCertificateNo() {
		return this.workCertificateNo;
	}

	public void setWorkCertificateNo(String workCertificateNo) {
		this.workCertificateNo = workCertificateNo;
	}

	public String getCommDictPublicCharId2() {
		return this.commDictPublicCharId2;
	}

	public void setCommDictPublicCharId2(String commDictPublicCharId2) {
		this.commDictPublicCharId2 = commDictPublicCharId2;
	}

	public String getCommConfigPositiontitleId() {
		return this.commConfigPositiontitleId;
	}

	public void setCommConfigPositiontitleId(String commConfigPositiontitleId) {
		this.commConfigPositiontitleId = commConfigPositiontitleId;
	}

	public String getCommConfigEmptitleId() {
		return this.commConfigEmptitleId;
	}

	public void setCommConfigEmptitleId(String commConfigEmptitleId) {
		this.commConfigEmptitleId = commConfigEmptitleId;
	}

	public String getCommDictPublicCharId3() {
		return this.commDictPublicCharId3;
	}

	public void setCommDictPublicCharId3(String commDictPublicCharId3) {
		this.commDictPublicCharId3 = commDictPublicCharId3;
	}

	public String getCommConfigDegreeId() {
		return this.commConfigDegreeId;
	}

	public void setCommConfigDegreeId(String commConfigDegreeId) {
		this.commConfigDegreeId = commConfigDegreeId;
	}

	public String getCommConfigDegreeLevelId() {
		return this.commConfigDegreeLevelId;
	}

	public void setCommConfigDegreeLevelId(String commConfigDegreeLevelId) {
		this.commConfigDegreeLevelId = commConfigDegreeLevelId;
	}

	public String getCommConfigProfessionId() {
		return this.commConfigProfessionId;
	}

	public void setCommConfigProfessionId(String commConfigProfessionId) {
		this.commConfigProfessionId = commConfigProfessionId;
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

	public Long getIsemployment() {
		return this.isemployment;
	}

	public void setIsemployment(Long isemployment) {
		this.isemployment = isemployment;
	}

	public Date getRetiredDate() {
		return this.retiredDate;
	}

	public void setRetiredDate(Date retiredDate) {
		this.retiredDate = retiredDate;
	}

	public String getInnerFileNumber() {
		return this.innerFileNumber;
	}

	public void setInnerFileNumber(String innerFileNumber) {
		this.innerFileNumber = innerFileNumber;
	}

	public String getProfessionalTittleName() {
		return professionalTittleName;
	}

	public void setProfessionalTittleName(String professionalTittleName) {
		this.professionalTittleName = professionalTittleName;
	}

	public String getPostTittle() {
		return postTittle;
	}

	public void setPostTittle(String postTittle) {
		this.postTittle = postTittle;
	}

	public String getRelatedDepartment() {
		return this.relatedDepartment;
	}

	public void setRelatedDepartment(String relatedDepartment) {
		this.relatedDepartment = relatedDepartment;
	}

	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getBdeptCode() {
		return this.bdeptCode;
	}

	public void setBdeptCode(String bdeptCode) {
		this.bdeptCode = bdeptCode;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}