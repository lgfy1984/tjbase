package com.tianjian.empi.bean;

import java.util.Date;

/**
 * SecurityUserBaseinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SameUserBaseinfo implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 6365255994802715816L;
	private String id;
	private String pmi;
	private String name;
	private String nameEn;
	private String inputCode;
	private String dateOfBirth;
	private String idNo;
	private String sscid;
	private String zipcode;
	private String phone;
	private String EMail;
	private String contactPersonName;
	private String contactPersonPhone;
	private String commConfigSexName;
	private String commConfigCountryName;
	private String commConfigNationalityName;
	private String commConfigIdTypeName;
	private String commConfigAboName;
	private String commConfigRhName;
	private String commConfigDegreeName;
	private String ccmsName;
	private String commConfigLocationName1;
	private String commConfigLocationName2;
	private String commConfigLocationName3;
	private String commConfigLocationTownId;
	private String ccltName;
	private String cclvName;
	private String houseNo;
	private String commConfigRelationshipName;
	private String mobileTel;
	private String cclgName;
	
	// Constructors

	/** default constructor */
	public SameUserBaseinfo() {
	}

	/** minimal constructor */
	public SameUserBaseinfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public SameUserBaseinfo(String id, String pmi, String name,
			String nameEn, String inputCode, String dateOfBirth, 
			String idNo, String sscid,String zipcode, String phone, String EMail, String commConfigRelationshipId,
			String contactPersonName, String contactPersonPhone,
			String commConfigSexName, String commConfigCountryName,
			String commConfigNationalityName, String commConfigIdTypeName,
			String commConfigAboName, String commConfigRhName,
			String commConfigDegreeName, String ccmsName,
			String commConfigLocationName1, String commConfigLocationName2,
			String commConfigLocationName3, String commConfigLocationTownId,
			String ccltName, String cclvName, String houseNo,
			String commConfigRelationshipName, String mobileTel, String cclgName) {
		this.id = id;
		this.pmi = pmi;
		this.name = name;
		this.nameEn = nameEn;
		this.inputCode = inputCode;
		this.idNo = idNo;
		this.sscid = sscid;
		this.zipcode = zipcode;
		this.phone = phone;
		this.EMail = EMail;
		this.contactPersonName = contactPersonName;
		this.contactPersonPhone = contactPersonPhone;
		this.commConfigSexName = commConfigSexName;
		this.commConfigCountryName = commConfigCountryName;
		this.commConfigNationalityName = commConfigNationalityName;
		this.commConfigIdTypeName = commConfigIdTypeName;
		this.commConfigAboName = commConfigAboName;
		this.commConfigRhName = commConfigRhName;
		this.commConfigDegreeName = commConfigDegreeName;
		this.ccmsName = ccmsName;
		this.commConfigLocationName1 = commConfigLocationName1;
		this.commConfigLocationName2 = commConfigLocationName2;
		this.commConfigLocationName3 = commConfigLocationName3;
		this.commConfigLocationTownId = commConfigLocationTownId;
		this.ccltName = ccltName;
		this.cclvName = cclvName;
		this.houseNo = houseNo;
		this.commConfigRelationshipName = commConfigRelationshipName;
		this.mobileTel = mobileTel;
		this.cclgName = cclgName;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPmi() {
		return this.pmi;
	}

	public void setPmi(String pmi) {
		this.pmi = pmi;
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

	public String getInputCode() {
		return this.inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getSscid() {
		return this.sscid;
	}

	public void setSscid(String sscid) {
		this.sscid = sscid;
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

	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonPhone() {
		return this.contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

	public String getCommConfigSexName() {
		return this.commConfigSexName;
	}

	public void setCommConfigSexName(String commConfigSexName) {
		this.commConfigSexName = commConfigSexName;
	}

	public String getCommConfigCountryName() {
		return this.commConfigCountryName;
	}

	public void setCommConfigCountryName(String commConfigCountryName) {
		this.commConfigCountryName = commConfigCountryName;
	}

	public String getCommConfigNationalityName() {
		return this.commConfigNationalityName;
	}

	public void setCommConfigNationalityName(String commConfigNationalityName) {
		this.commConfigNationalityName = commConfigNationalityName;
	}

	public String getCommConfigIdTypeName() {
		return this.commConfigIdTypeName;
	}

	public void setCommConfigIdTypeName(String commConfigIdTypeName) {
		this.commConfigIdTypeName = commConfigIdTypeName;
	}

	public String getCommConfigAboName() {
		return this.commConfigAboName;
	}

	public void setCommConfigAboName(String commConfigAboName) {
		this.commConfigAboName = commConfigAboName;
	}

	public String getCommConfigRhName() {
		return this.commConfigRhName;
	}

	public void setCommConfigRhName(String commConfigRhName) {
		this.commConfigRhName = commConfigRhName;
	}

	public String getCommConfigDegreeName() {
		return this.commConfigDegreeName;
	}

	public void setCommConfigDegreeName(String commConfigDegreeName) {
		this.commConfigDegreeName = commConfigDegreeName;
	}

	public String getCcmsName() {
		return this.ccmsName;
	}

	public void setCcmsName(String ccmsName) {
		this.ccmsName = ccmsName;
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

	public String getCommConfigLocationTownId() {
		return this.commConfigLocationTownId;
	}

	public void setCommConfigLocationTownId(String commConfigLocationTownId) {
		this.commConfigLocationTownId = commConfigLocationTownId;
	}

	public String getCcltName() {
		return this.ccltName;
	}

	public void setCcltName(String ccltName) {
		this.ccltName = ccltName;
	}

	public String getCclvName() {
		return this.cclvName;
	}

	public void setCclvName(String cclvName) {
		this.cclvName = cclvName;
	}


	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getCommConfigRelationshipName() {
		return this.commConfigRelationshipName;
	}

	public void setCommConfigRelationshipName(String commConfigRelationshipName) {
		this.commConfigRelationshipName = commConfigRelationshipName;
	}


	public String getMobileTel() {
		return this.mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}


	public String getCclgName() {
		return this.cclgName;
	}

	public void setCclgName(String cclgName) {
		this.cclgName = cclgName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}