package com.tianjian.hsp.bean;

import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * HspConfigBaseinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HspConfigBaseinfo implements java.io.Serializable {

	// Fields

	private String id;
	private String itemCode;
	private String itemName;
	private String parentItemCode;
	private String commConfigUnittypeId;
	private String commConfigUnitgradeId;
	private String commConfigEconkindId;
	private String commConfigLocationId1;
	private String commConfigLocationId2;
	private String commConfigLocationId3;
	private String commConfigLocationTownId;
	private String commClvId;
	private String address;
	private String zipcode;
	private String phone;
	private Long dateClinicNum;
	private Long yearOuthospitalNum;
	private Long authorizedBedNum;
	private Long outspreadBedNum;
	private Long doctorNum;
	private Long nurseNum;
	private Long technicPersonNum;
	private Long sickroomNum;
	private Long operationroomNum;
	private String comments;
	private Long seqNo;
	private String inputCode;
	private String levelDesc;
	private String commConfigHospitalTypeId;
	private String commConfigFtManageId;
	private String EMail;
	private String domainName;
	private Date createDate;
	private String createUserId;
	private String createUserName;
	private String parentItemName;
	private String commConfigUnittypeName;
	private String commConfigUnitgradeName;
	private String cchtName;
	private String commConfigFtManageName;
	private String commConfigEconkindName;
	private String commConfigLocationName1;
	private String commConfigLocationName2;
	private String commConfigLocationName3;
	private String ccltName;
	private String commClvName;
	private String contactPersonName;
	private String commConfigSettypeId;
	private String commConfigGovaffrsId;
	private String hspConfigBaseinfoId1;
	private String hspConfigBaseinfoId2;
	private String hspConfigBaseinfoId3;
	private String hspConfigBaseinfoId4;
	private String hspConfigBaseinfoId5;
	private String referralOutFlag;
	private Long hspType;
	private Blob photo;
	private Set hspStaffBaseinfos = new HashSet(0);
 	private Long count;
	// Constructors
	
	private String tseqno;

 	private String dictValue;
	private String town;
	private String village;
	
	
	//租户id
	private String tenantId;
	/** default constructor */
	public HspConfigBaseinfo() {
	}

	/** minimal constructor */

	/** full constructor */
	public HspConfigBaseinfo(String itemCode, String itemName, String parentItemCode, String commConfigUnittypeId,
			String commConfigUnitgradeId, String commConfigEconkindId, String commConfigLocationId1,
			String commConfigLocationId2, String commConfigLocationId3, String commConfigLocationTownId, String commClvId,
			String address, String zipcode, String phone, Long dateClinicNum, Long yearOuthospitalNum, Long authorizedBedNum,
			Long outspreadBedNum, Long doctorNum, Long nurseNum, Long technicPersonNum, Long sickroomNum, Long operationroomNum,
			String comments, Long seqNo, String inputCode, String levelDesc, String commConfigHospitalTypeId,
			String commConfigFtManageId, String EMail, String domainName, Date createDate, String createUserId,
			String createUserName, String parentItemName, String commConfigUnittypeName, String commConfigUnitgradeName,
			String cchtName, String commConfigFtManageName, String commConfigEconkindName, String commConfigLocationName1,
			String commConfigLocationName2, String commConfigLocationName3, String ccltName, String commClvName,
			String contactPersonName, String commConfigSettypeId, String commConfigGovaffrsId, String hspConfigBaseinfoId1,
			String hspConfigBaseinfoId2, String hspConfigBaseinfoId3, String hspConfigBaseinfoId4, String hspConfigBaseinfoId5,
			String referralOutFlag, Long hspType, Blob photo) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.parentItemCode = parentItemCode;
		this.commConfigUnittypeId = commConfigUnittypeId;
		this.commConfigUnitgradeId = commConfigUnitgradeId;
		this.commConfigEconkindId = commConfigEconkindId;
		this.commConfigLocationId1 = commConfigLocationId1;
		this.commConfigLocationId2 = commConfigLocationId2;
		this.commConfigLocationId3 = commConfigLocationId3;
		this.commConfigLocationTownId = commConfigLocationTownId;
		this.commClvId = commClvId;
		this.address = address;
		this.zipcode = zipcode;
		this.phone = phone;
		this.dateClinicNum = dateClinicNum;
		this.yearOuthospitalNum = yearOuthospitalNum;
		this.authorizedBedNum = authorizedBedNum;
		this.outspreadBedNum = outspreadBedNum;
		this.doctorNum = doctorNum;
		this.nurseNum = nurseNum;
		this.technicPersonNum = technicPersonNum;
		this.sickroomNum = sickroomNum;
		this.operationroomNum = operationroomNum;
		this.comments = comments;
		this.seqNo = seqNo;
		this.inputCode = inputCode;
		this.levelDesc = levelDesc;
		this.commConfigHospitalTypeId = commConfigHospitalTypeId;
		this.commConfigFtManageId = commConfigFtManageId;
		this.EMail = EMail;
		this.domainName = domainName;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.parentItemName = parentItemName;
		this.commConfigUnittypeName = commConfigUnittypeName;
		this.commConfigUnitgradeName = commConfigUnitgradeName;
		this.cchtName = cchtName;
		this.commConfigFtManageName = commConfigFtManageName;
		this.commConfigEconkindName = commConfigEconkindName;
		this.commConfigLocationName1 = commConfigLocationName1;
		this.commConfigLocationName2 = commConfigLocationName2;
		this.commConfigLocationName3 = commConfigLocationName3;
		this.ccltName = ccltName;
		this.commClvName = commClvName;
		this.contactPersonName = contactPersonName;
		this.commConfigSettypeId = commConfigSettypeId;
		this.commConfigGovaffrsId = commConfigGovaffrsId;
		this.hspConfigBaseinfoId1 = hspConfigBaseinfoId1;
		this.hspConfigBaseinfoId2 = hspConfigBaseinfoId2;
		this.hspConfigBaseinfoId3 = hspConfigBaseinfoId3;
		this.hspConfigBaseinfoId4 = hspConfigBaseinfoId4;
		this.hspConfigBaseinfoId5 = hspConfigBaseinfoId5;
		this.referralOutFlag = referralOutFlag;
		this.hspType = hspType;
		this.photo = photo;
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

	public String getParentItemCode() {
		return this.parentItemCode;
	}

	public void setParentItemCode(String parentItemCode) {
		this.parentItemCode = parentItemCode;
	}

	public String getCommConfigUnittypeId() {
		return this.commConfigUnittypeId;
	}

	public void setCommConfigUnittypeId(String commConfigUnittypeId) {
		this.commConfigUnittypeId = commConfigUnittypeId;
	}

	public String getCommConfigUnitgradeId() {
		return this.commConfigUnitgradeId;
	}

	public void setCommConfigUnitgradeId(String commConfigUnitgradeId) {
		this.commConfigUnitgradeId = commConfigUnitgradeId;
	}

	public String getCommConfigEconkindId() {
		return this.commConfigEconkindId;
	}

	public void setCommConfigEconkindId(String commConfigEconkindId) {
		this.commConfigEconkindId = commConfigEconkindId;
	}

	public String getCommConfigLocationId1() {
		return this.commConfigLocationId1;
	}

	public void setCommConfigLocationId1(String commConfigLocationId1) {
		this.commConfigLocationId1 = commConfigLocationId1;
	}

	public String getCommConfigLocationId2() {
		return this.commConfigLocationId2;
	}

	public void setCommConfigLocationId2(String commConfigLocationId2) {
		this.commConfigLocationId2 = commConfigLocationId2;
	}

	public String getCommConfigLocationId3() {
		return this.commConfigLocationId3;
	}

	public void setCommConfigLocationId3(String commConfigLocationId3) {
		this.commConfigLocationId3 = commConfigLocationId3;
	}

	public String getCommConfigLocationTownId() {
		return this.commConfigLocationTownId;
	}

	public void setCommConfigLocationTownId(String commConfigLocationTownId) {
		this.commConfigLocationTownId = commConfigLocationTownId;
	}

	public String getCommClvId() {
		return this.commClvId;
	}

	public void setCommClvId(String commClvId) {
		this.commClvId = commClvId;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getDateClinicNum() {
		return this.dateClinicNum;
	}

	public void setDateClinicNum(Long dateClinicNum) {
		this.dateClinicNum = dateClinicNum;
	}

	public Long getYearOuthospitalNum() {
		return this.yearOuthospitalNum;
	}

	public void setYearOuthospitalNum(Long yearOuthospitalNum) {
		this.yearOuthospitalNum = yearOuthospitalNum;
	}

	public Long getAuthorizedBedNum() {
		return this.authorizedBedNum;
	}

	public void setAuthorizedBedNum(Long authorizedBedNum) {
		this.authorizedBedNum = authorizedBedNum;
	}

	public Long getOutspreadBedNum() {
		return this.outspreadBedNum;
	}

	public void setOutspreadBedNum(Long outspreadBedNum) {
		this.outspreadBedNum = outspreadBedNum;
	}

	public Long getDoctorNum() {
		return this.doctorNum;
	}

	public void setDoctorNum(Long doctorNum) {
		this.doctorNum = doctorNum;
	}

	public Long getNurseNum() {
		return this.nurseNum;
	}

	public void setNurseNum(Long nurseNum) {
		this.nurseNum = nurseNum;
	}

	public Long getTechnicPersonNum() {
		return this.technicPersonNum;
	}

	public void setTechnicPersonNum(Long technicPersonNum) {
		this.technicPersonNum = technicPersonNum;
	}

	public Long getSickroomNum() {
		return this.sickroomNum;
	}

	public void setSickroomNum(Long sickroomNum) {
		this.sickroomNum = sickroomNum;
	}

	public Long getOperationroomNum() {
		return this.operationroomNum;
	}

	public void setOperationroomNum(Long operationroomNum) {
		this.operationroomNum = operationroomNum;
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

	public String getInputCode() {
		return this.inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getLevelDesc() {
		return this.levelDesc;
	}

	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}

	public String getCommConfigHospitalTypeId() {
		return this.commConfigHospitalTypeId;
	}

	public void setCommConfigHospitalTypeId(String commConfigHospitalTypeId) {
		this.commConfigHospitalTypeId = commConfigHospitalTypeId;
	}

	public String getCommConfigFtManageId() {
		return this.commConfigFtManageId;
	}

	public void setCommConfigFtManageId(String commConfigFtManageId) {
		this.commConfigFtManageId = commConfigFtManageId;
	}

	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
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

	public String getParentItemName() {
		return this.parentItemName;
	}

	public void setParentItemName(String parentItemName) {
		this.parentItemName = parentItemName;
	}

	public String getCommConfigUnittypeName() {
		return this.commConfigUnittypeName;
	}

	public void setCommConfigUnittypeName(String commConfigUnittypeName) {
		this.commConfigUnittypeName = commConfigUnittypeName;
	}

	public String getCommConfigUnitgradeName() {
		return this.commConfigUnitgradeName;
	}

	public void setCommConfigUnitgradeName(String commConfigUnitgradeName) {
		this.commConfigUnitgradeName = commConfigUnitgradeName;
	}

	public String getCchtName() {
		return this.cchtName;
	}

	public void setCchtName(String cchtName) {
		this.cchtName = cchtName;
	}

	public String getCommConfigFtManageName() {
		return this.commConfigFtManageName;
	}

	public void setCommConfigFtManageName(String commConfigFtManageName) {
		this.commConfigFtManageName = commConfigFtManageName;
	}

	public String getCommConfigEconkindName() {
		return this.commConfigEconkindName;
	}

	public void setCommConfigEconkindName(String commConfigEconkindName) {
		this.commConfigEconkindName = commConfigEconkindName;
	}

	public String getCommConfigLocationName1() {
		return this.commConfigLocationName1;
	}

	public void setCommConfigLocationName1(String commConfigLocationName1) {
		this.commConfigLocationName1 = commConfigLocationName1;
	}

	public String getCommConfigLocationName2() {
		return this.commConfigLocationName2;
	}

	public void setCommConfigLocationName2(String commConfigLocationName2) {
		this.commConfigLocationName2 = commConfigLocationName2;
	}

	public String getCommConfigLocationName3() {
		return this.commConfigLocationName3;
	}

	public void setCommConfigLocationName3(String commConfigLocationName3) {
		this.commConfigLocationName3 = commConfigLocationName3;
	}

	public String getCcltName() {
		return this.ccltName;
	}

	public void setCcltName(String ccltName) {
		this.ccltName = ccltName;
	}

	public String getCommClvName() {
		return this.commClvName;
	}

	public void setCommClvName(String commClvName) {
		this.commClvName = commClvName;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getCommConfigSettypeId() {
		return this.commConfigSettypeId;
	}

	public void setCommConfigSettypeId(String commConfigSettypeId) {
		this.commConfigSettypeId = commConfigSettypeId;
	}

	public String getCommConfigGovaffrsId() {
		return this.commConfigGovaffrsId;
	}

	public void setCommConfigGovaffrsId(String commConfigGovaffrsId) {
		this.commConfigGovaffrsId = commConfigGovaffrsId;
	}

	public String getHspConfigBaseinfoId1() {
		return this.hspConfigBaseinfoId1;
	}

	public void setHspConfigBaseinfoId1(String hspConfigBaseinfoId1) {
		this.hspConfigBaseinfoId1 = hspConfigBaseinfoId1;
	}

	public String getHspConfigBaseinfoId2() {
		return this.hspConfigBaseinfoId2;
	}

	public void setHspConfigBaseinfoId2(String hspConfigBaseinfoId2) {
		this.hspConfigBaseinfoId2 = hspConfigBaseinfoId2;
	}

	public String getHspConfigBaseinfoId3() {
		return this.hspConfigBaseinfoId3;
	}

	public void setHspConfigBaseinfoId3(String hspConfigBaseinfoId3) {
		this.hspConfigBaseinfoId3 = hspConfigBaseinfoId3;
	}

	public String getHspConfigBaseinfoId4() {
		return this.hspConfigBaseinfoId4;
	}

	public void setHspConfigBaseinfoId4(String hspConfigBaseinfoId4) {
		this.hspConfigBaseinfoId4 = hspConfigBaseinfoId4;
	}

	public String getHspConfigBaseinfoId5() {
		return this.hspConfigBaseinfoId5;
	}

	public void setHspConfigBaseinfoId5(String hspConfigBaseinfoId5) {
		this.hspConfigBaseinfoId5 = hspConfigBaseinfoId5;
	}

	public String getReferralOutFlag() {
		return this.referralOutFlag;
	}

	public void setReferralOutFlag(String referralOutFlag) {
		this.referralOutFlag = referralOutFlag;
	}



	public Long getHspType() {
		return hspType;
	}

	public void setHspType(Long hspType) {
		this.hspType = hspType;
	}

	public Blob getPhoto() {
		return this.photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public Set getHspStaffBaseinfos() {
		return hspStaffBaseinfos;
	}

	public void setHspStaffBaseinfos(Set hspStaffBaseinfos) {
		this.hspStaffBaseinfos = hspStaffBaseinfos;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getTseqno() {
		return tseqno;
	}

	public void setTseqno(String tseqno) {
		this.tseqno = tseqno;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	

	
	
	
	
	
	

}