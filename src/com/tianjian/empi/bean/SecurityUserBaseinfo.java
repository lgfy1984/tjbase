package com.tianjian.empi.bean;

import java.util.Date;

/**
 * SecurityUserBaseinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SecurityUserBaseinfo implements java.io.Serializable {

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
	private String commConfigSexId;
	private Date dateOfBirth;
	private String commConfigCountryId;
	private String commConfigNationalityId;
	private String commConfigIdTypeId;
	private String idNo;
	private String sscid;
	private String commConfigAboId;
	private String commConfigRhId;
	private String commConfigDegreeId;
	private String commConfigMaritalStatusId;
	private String commConfigLocationId1;
	private String commConfigLocationId2;
	private String commConfigLocationId3;
	private String zipcode;
	private String phone;
	private String EMail;
	private String photoPath;
	private String commConfigRelationshipId;
	private String contactPersonName;
	private String contactPersonPhone;
	private String comments;
	private Long recordFlag;
	private Date createDate;
	private String createUserId;
	private String createUserName;
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
	private String doorNo;//居住地址
	private String censusDoorNo;//户籍地址
	private String birthDoorNo;//出生地址
	private String commConfigRelationshipName;
	private String commConfigLocationVillageid;
	private String mobileTel;
	private String commConfigLocationGroupId;
	private String cclgName;
	
	private String deceasedInd;
	private String deceasedIndName;
	private Date deceasedTime;
	private String motherPID;
	private String motherName;
	private String birthSequence;
	private String multipleBirthInd;
	private String multipleBirthindName;
	private String multipleBirthOrderNumber;
	private String occupationCodeId;
	private String occupationCodeName;
	
	private String birthLocationId1;
	private String birthLocationId2;
	private String birthLocationId3;
	private String birthLocationName1;
	private String birthLocationName2;
	private String birthLocationName3;
	private String birthLocationTownId;
	private String birthLocationTownName;
	private String birthLocationVillageId;
	private String birthLocationVillageName;
	private String birthLocationGroupId;
	private String birthLocationGroupName;
	private String censusLocationId1;
	private String censusLocationId2;
	private String censusLocationId3;
	private String censusLocationName1;
	private String censusLocationName2;
	private String censusLocationName3;
	private String censusLocationTownId;
	private String censusLocationTownName;
	private String censusLocationVillageId;
	private String censusLocationVillageName;
	private String censusLocationGroupId;
	private String censusLocationGroupName;
	
	private String languageId;
	private String languageName;
	private String religionId;
	private String religionName;
	private String deiverLicense;
	private String citizenShipId;
	private String citizenShipName;
	private String retiredStatus;
	private String userAccount;
	
	private String birthPlace;
	private String mailingAddress;
	
	
	// Constructors

	/** default constructor */
	public SecurityUserBaseinfo() {
	}

	/** minimal constructor */
	public SecurityUserBaseinfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public SecurityUserBaseinfo(String id, String pmi, String name,
			String nameEn, String inputCode, String commConfigSexId, Date dateOfBirth, String commConfigCountryId,
			String commConfigNationalityId, String commConfigIdTypeId,
			String idNo, String sscid, String commConfigAboId,
			String commConfigRhId, String commConfigDegreeId,
			String commConfigMaritalStatusId, String commConfigLocationId1,
			String commConfigLocationId2, String commConfigLocationId3,String zipcode, String phone, String EMail,
			String photoPath, String commConfigRelationshipId,
			String contactPersonName, String contactPersonPhone,
			String comments, Long recordFlag, Date createDate,
			String createUserId, String createUserName,
			String commConfigSexName, String commConfigCountryName,
			String commConfigNationalityName, String commConfigIdTypeName,
			String commConfigAboName, String commConfigRhName,
			String commConfigDegreeName, String ccmsName,
			String commConfigLocationName1, String commConfigLocationName2,
			String commConfigLocationName3, String commConfigLocationTownId,
			String ccltName, String cclvName,
			String commConfigRelationshipName,String censusDoorNo,String birthDoorNo,
			String commConfigLocationVillageid, String mobileTel,
			String commConfigLocationGroupId, String cclgName,String deceasedInd,
			Date deceasedTime, String motherPID, String motherName,
			String birthSequence,String multipleBirthInd,String multipleBirthOrderNumber,
			String occupationCodeId,String occupationCodeName,String multipleBirthindName,String deceasedIndName,
			String birthLocationId1,
			String birthLocationId2, String birthLocationId3,
			String birthLocationName1, String birthLocationName2,
			String birthLocationName3, String birthLocationTownId,
			String birthLocationTownName, String birthLocationVillageId,
			String birthLocationVillageName, String birthLocationGroupId,
			String birthLocationGroupName, String censusLocationId1,
			String censusLocationId2, String censusLocationId3,
			String censusLocationName1, String censusLocationName2,
			String censusLocationName3, String censusLocationTownId,
			String censusLocationTownName, String censusLocationVillageId,
			String censusLocationVillageName, String censusLocationGroupId,
			String censusLocationGroupName,String languageId,String languageName,String religionId,String religionName,
			String deiverLicense,String citizenShipId,String citizenShipName,String retiredStatus) {
		this.id = id;
		this.pmi = pmi;
		this.name = name;
		this.nameEn = nameEn;
		this.inputCode = inputCode;
		this.commConfigSexId = commConfigSexId;
		this.dateOfBirth = dateOfBirth;
		this.commConfigCountryId = commConfigCountryId;
		this.commConfigNationalityId = commConfigNationalityId;
		this.commConfigIdTypeId = commConfigIdTypeId;
		this.idNo = idNo;
		this.sscid = sscid;
		this.commConfigAboId = commConfigAboId;
		this.commConfigRhId = commConfigRhId;
		this.commConfigDegreeId = commConfigDegreeId;
		this.commConfigMaritalStatusId = commConfigMaritalStatusId;
		this.commConfigLocationId1 = commConfigLocationId1;
		this.commConfigLocationId2 = commConfigLocationId2;
		this.commConfigLocationId3 = commConfigLocationId3;
		this.zipcode = zipcode;
		this.phone = phone;
		this.EMail = EMail;
		this.photoPath = photoPath;
		this.commConfigRelationshipId = commConfigRelationshipId;
		this.contactPersonName = contactPersonName;
		this.contactPersonPhone = contactPersonPhone;
		this.comments = comments;
		this.recordFlag = recordFlag;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
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
		this.doorNo = doorNo;
		this.censusDoorNo = censusDoorNo;
		this.birthDoorNo = birthDoorNo;
		this.commConfigRelationshipName = commConfigRelationshipName;
		this.commConfigLocationVillageid = commConfigLocationVillageid;
		this.mobileTel = mobileTel;
		this.commConfigLocationGroupId = commConfigLocationGroupId;
		this.cclgName = cclgName;
		this.deceasedInd = deceasedInd;
		this.deceasedTime = deceasedTime;
		this.motherPID = motherPID;
		this.motherName = motherName;
		this.birthSequence = birthSequence;
		this.multipleBirthInd = multipleBirthInd;
		this.multipleBirthOrderNumber = multipleBirthOrderNumber; 
		this.occupationCodeId = occupationCodeId;
		this.occupationCodeName = occupationCodeName;
		this.multipleBirthindName = multipleBirthindName;
		this.deceasedIndName = deceasedIndName;
		this.birthLocationId1 = birthLocationId1;
		this.birthLocationId2 = birthLocationId2;
		this.birthLocationId3 = birthLocationId3;
		this.birthLocationName1 = birthLocationName1;
		this.birthLocationName2 = birthLocationName2;
		this.birthLocationName3 = birthLocationName3;
		this.birthLocationTownId = birthLocationTownId;
		this.birthLocationTownName = birthLocationTownName;
		this.birthLocationVillageId = birthLocationVillageId;
		this.birthLocationVillageName = birthLocationVillageName;
		this.birthLocationGroupId = birthLocationGroupId;
		this.birthLocationGroupName = birthLocationGroupName;
		this.censusLocationId1 = censusLocationId1;
		this.censusLocationId2 = censusLocationId2;
		this.censusLocationId3 = censusLocationId3;
		this.censusLocationName1 = censusLocationName1;
		this.censusLocationName2 = censusLocationName2;
		this.censusLocationName3 = censusLocationName3;
		this.censusLocationTownId = censusLocationTownId;
		this.censusLocationTownName = censusLocationTownName;
		this.censusLocationVillageId = censusLocationVillageId;
		this.censusLocationVillageName = censusLocationVillageName;
		this.censusLocationGroupId = censusLocationGroupId;
		this.censusLocationGroupName = censusLocationGroupName;
		this.languageId = languageId;
		this.languageName = languageName;
		this.religionId = religionId;
		this.religionName = religionName;
		this.deiverLicense = deiverLicense;
		this.citizenShipId = citizenShipId;
		this.citizenShipName = citizenShipName;
		this.retiredStatus = retiredStatus;
	}

	
	// Property accessors
	
	
	

	public String getId() {
		return this.id;
	}

	public SecurityUserBaseinfo(String id, String pmi, String name,
			String nameEn, String inputCode, String commConfigSexId,
			Date dateOfBirth, String commConfigCountryId,
			String commConfigNationalityId, String commConfigIdTypeId,
			String idNo, String sscid, String commConfigAboId,
			String commConfigRhId, String commConfigDegreeId,
			String commConfigMaritalStatusId, String commConfigLocationId1,
			String commConfigLocationId2, String commConfigLocationId3,
			String zipcode, String phone, String eMail, String photoPath,
			String commConfigRelationshipId, String contactPersonName,
			String contactPersonPhone, String comments, Long recordFlag,
			Date createDate, String createUserId, String createUserName,
			String commConfigSexName, String commConfigCountryName,
			String commConfigNationalityName, String commConfigIdTypeName,
			String commConfigAboName, String commConfigRhName,
			String commConfigDegreeName, String ccmsName,
			String commConfigLocationName1, String commConfigLocationName2,
			String commConfigLocationName3, String commConfigLocationTownId,
			String ccltName, String cclvName, String doorNo,
			String censusDoorNo, String birthDoorNo,
			String commConfigRelationshipName,
			String commConfigLocationVillageid, String mobileTel,
			String commConfigLocationGroupId, String cclgName,
			String deceasedInd, String deceasedIndName, Date deceasedTime,
			String motherPID, String motherName, String birthSequence,
			String multipleBirthInd, String multipleBirthindName,
			String multipleBirthOrderNumber, String occupationCodeId,
			String occupationCodeName, String birthLocationId1,
			String birthLocationId2, String birthLocationId3,
			String birthLocationName1, String birthLocationName2,
			String birthLocationName3, String birthLocationTownId,
			String birthLocationTownName, String birthLocationVillageId,
			String birthLocationVillageName, String birthLocationGroupId,
			String birthLocationGroupName, String censusLocationId1,
			String censusLocationId2, String censusLocationId3,
			String censusLocationName1, String censusLocationName2,
			String censusLocationName3, String censusLocationTownId,
			String censusLocationTownName, String censusLocationVillageId,
			String censusLocationVillageName, String censusLocationGroupId,
			String censusLocationGroupName, String languageId,
			String languageName, String religionId, String religionName,
			String deiverLicense, String citizenShipId, String citizenShipName,
			String retiredStatus, String userAccount, String birthPlace,
			String mailingAddress) {
		super();
		this.id = id;
		this.pmi = pmi;
		this.name = name;
		this.nameEn = nameEn;
		this.inputCode = inputCode;
		this.commConfigSexId = commConfigSexId;
		this.dateOfBirth = dateOfBirth;
		this.commConfigCountryId = commConfigCountryId;
		this.commConfigNationalityId = commConfigNationalityId;
		this.commConfigIdTypeId = commConfigIdTypeId;
		this.idNo = idNo;
		this.sscid = sscid;
		this.commConfigAboId = commConfigAboId;
		this.commConfigRhId = commConfigRhId;
		this.commConfigDegreeId = commConfigDegreeId;
		this.commConfigMaritalStatusId = commConfigMaritalStatusId;
		this.commConfigLocationId1 = commConfigLocationId1;
		this.commConfigLocationId2 = commConfigLocationId2;
		this.commConfigLocationId3 = commConfigLocationId3;
		this.zipcode = zipcode;
		this.phone = phone;
		EMail = eMail;
		this.photoPath = photoPath;
		this.commConfigRelationshipId = commConfigRelationshipId;
		this.contactPersonName = contactPersonName;
		this.contactPersonPhone = contactPersonPhone;
		this.comments = comments;
		this.recordFlag = recordFlag;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
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
		this.doorNo = doorNo;
		this.censusDoorNo = censusDoorNo;
		this.birthDoorNo = birthDoorNo;
		this.commConfigRelationshipName = commConfigRelationshipName;
		this.commConfigLocationVillageid = commConfigLocationVillageid;
		this.mobileTel = mobileTel;
		this.commConfigLocationGroupId = commConfigLocationGroupId;
		this.cclgName = cclgName;
		this.deceasedInd = deceasedInd;
		this.deceasedIndName = deceasedIndName;
		this.deceasedTime = deceasedTime;
		this.motherPID = motherPID;
		this.motherName = motherName;
		this.birthSequence = birthSequence;
		this.multipleBirthInd = multipleBirthInd;
		this.multipleBirthindName = multipleBirthindName;
		this.multipleBirthOrderNumber = multipleBirthOrderNumber;
		this.occupationCodeId = occupationCodeId;
		this.occupationCodeName = occupationCodeName;
		this.birthLocationId1 = birthLocationId1;
		this.birthLocationId2 = birthLocationId2;
		this.birthLocationId3 = birthLocationId3;
		this.birthLocationName1 = birthLocationName1;
		this.birthLocationName2 = birthLocationName2;
		this.birthLocationName3 = birthLocationName3;
		this.birthLocationTownId = birthLocationTownId;
		this.birthLocationTownName = birthLocationTownName;
		this.birthLocationVillageId = birthLocationVillageId;
		this.birthLocationVillageName = birthLocationVillageName;
		this.birthLocationGroupId = birthLocationGroupId;
		this.birthLocationGroupName = birthLocationGroupName;
		this.censusLocationId1 = censusLocationId1;
		this.censusLocationId2 = censusLocationId2;
		this.censusLocationId3 = censusLocationId3;
		this.censusLocationName1 = censusLocationName1;
		this.censusLocationName2 = censusLocationName2;
		this.censusLocationName3 = censusLocationName3;
		this.censusLocationTownId = censusLocationTownId;
		this.censusLocationTownName = censusLocationTownName;
		this.censusLocationVillageId = censusLocationVillageId;
		this.censusLocationVillageName = censusLocationVillageName;
		this.censusLocationGroupId = censusLocationGroupId;
		this.censusLocationGroupName = censusLocationGroupName;
		this.languageId = languageId;
		this.languageName = languageName;
		this.religionId = religionId;
		this.religionName = religionName;
		this.deiverLicense = deiverLicense;
		this.citizenShipId = citizenShipId;
		this.citizenShipName = citizenShipName;
		this.retiredStatus = retiredStatus;
		this.userAccount = userAccount;
		this.birthPlace = birthPlace;
		this.mailingAddress = mailingAddress;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getCommConfigCountryId() {
		return this.commConfigCountryId;
	}

	public void setCommConfigCountryId(String commConfigCountryId) {
		this.commConfigCountryId = commConfigCountryId;
	}

	public String getCommConfigNationalityId() {
		return this.commConfigNationalityId;
	}

	public void setCommConfigNationalityId(String commConfigNationalityId) {
		this.commConfigNationalityId = commConfigNationalityId;
	}

	public String getCommConfigIdTypeId() {
		return this.commConfigIdTypeId;
	}

	public void setCommConfigIdTypeId(String commConfigIdTypeId) {
		this.commConfigIdTypeId = commConfigIdTypeId;
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

	public String getCommConfigAboId() {
		return this.commConfigAboId;
	}

	public void setCommConfigAboId(String commConfigAboId) {
		this.commConfigAboId = commConfigAboId;
	}

	public String getCommConfigRhId() {
		return this.commConfigRhId;
	}

	public void setCommConfigRhId(String commConfigRhId) {
		this.commConfigRhId = commConfigRhId;
	}

	public String getCommConfigDegreeId() {
		return this.commConfigDegreeId;
	}

	public void setCommConfigDegreeId(String commConfigDegreeId) {
		this.commConfigDegreeId = commConfigDegreeId;
	}

	public String getCommConfigMaritalStatusId() {
		return this.commConfigMaritalStatusId;
	}

	public void setCommConfigMaritalStatusId(String commConfigMaritalStatusId) {
		this.commConfigMaritalStatusId = commConfigMaritalStatusId;
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

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getCommConfigRelationshipId() {
		return this.commConfigRelationshipId;
	}

	public void setCommConfigRelationshipId(String commConfigRelationshipId) {
		this.commConfigRelationshipId = commConfigRelationshipId;
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



	public String getCensusDoorNo() {
		return censusDoorNo;
	}

	public void setCensusDoorNo(String censusDoorNo) {
		this.censusDoorNo = censusDoorNo;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getBirthDoorNo() {
		return birthDoorNo;
	}

	public void setBirthDoorNo(String birthDoorNo) {
		this.birthDoorNo = birthDoorNo;
	}

	public String getCommConfigRelationshipName() {
		return this.commConfigRelationshipName;
	}

	public void setCommConfigRelationshipName(String commConfigRelationshipName) {
		this.commConfigRelationshipName = commConfigRelationshipName;
	}

	public String getCommConfigLocationVillageid() {
		return this.commConfigLocationVillageid;
	}

	public void setCommConfigLocationVillageid(
			String commConfigLocationVillageid) {
		this.commConfigLocationVillageid = commConfigLocationVillageid;
	}

	public String getMobileTel() {
		return this.mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getCommConfigLocationGroupId() {
		return this.commConfigLocationGroupId;
	}

	public void setCommConfigLocationGroupId(String commConfigLocationGroupId) {
		this.commConfigLocationGroupId = commConfigLocationGroupId;
	}

	public String getCclgName() {
		return this.cclgName;
	}

	public void setCclgName(String cclgName) {
		this.cclgName = cclgName;
	}

	public String getDeceasedInd() {
		return deceasedInd;
	}

	public void setDeceasedInd(String deceasedInd) {
		this.deceasedInd = deceasedInd;
	}

	public Date getDeceasedTime() {
		return deceasedTime;
	}

	public void setDeceasedTime(Date deceasedTime) {
		this.deceasedTime = deceasedTime;
	}

	public String getMotherPID() {
		return motherPID;
	}

	public void setMotherPID(String motherPID) {
		this.motherPID = motherPID;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getBirthSequence() {
		return birthSequence;
	}

	public void setBirthSequence(String birthSequence) {
		this.birthSequence = birthSequence;
	}

	public String getMultipleBirthInd() {
		return multipleBirthInd;
	}

	public void setMultipleBirthInd(String multipleBirthInd) {
		this.multipleBirthInd = multipleBirthInd;
	}

	public String getMultipleBirthOrderNumber() {
		return multipleBirthOrderNumber;
	}

	public void setMultipleBirthOrderNumber(String multipleBirthOrderNumber) {
		this.multipleBirthOrderNumber = multipleBirthOrderNumber;
	}

	public String getOccupationCodeId() {
		return occupationCodeId;
	}

	public void setOccupationCodeId(String occupationCodeId) {
		this.occupationCodeId = occupationCodeId;
	}

	public String getOccupationCodeName() {
		return occupationCodeName;
	}

	public void setOccupationCodeName(String occupationCodeName) {
		this.occupationCodeName = occupationCodeName;
	}

	public String getDeceasedIndName() {
		return deceasedIndName;
	}

	public void setDeceasedIndName(String deceasedIndName) {
		this.deceasedIndName = deceasedIndName;
	}

	public String getMultipleBirthindName() {
		return multipleBirthindName;
	}

	public void setMultipleBirthindName(String multipleBirthindName) {
		this.multipleBirthindName = multipleBirthindName;
	}

	public String getBirthLocationId1() {
		return birthLocationId1;
	}

	public void setBirthLocationId1(String birthLocationId1) {
		this.birthLocationId1 = birthLocationId1;
	}

	public String getBirthLocationId2() {
		return birthLocationId2;
	}

	public void setBirthLocationId2(String birthLocationId2) {
		this.birthLocationId2 = birthLocationId2;
	}

	public String getBirthLocationId3() {
		return birthLocationId3;
	}

	public void setBirthLocationId3(String birthLocationId3) {
		this.birthLocationId3 = birthLocationId3;
	}

	public String getBirthLocationName1() {
		return birthLocationName1;
	}

	public void setBirthLocationName1(String birthLocationName1) {
		this.birthLocationName1 = birthLocationName1;
	}

	public String getBirthLocationName2() {
		return birthLocationName2;
	}

	public void setBirthLocationName2(String birthLocationName2) {
		this.birthLocationName2 = birthLocationName2;
	}

	public String getBirthLocationName3() {
		return birthLocationName3;
	}

	public void setBirthLocationName3(String birthLocationName3) {
		this.birthLocationName3 = birthLocationName3;
	}

	public String getBirthLocationTownId() {
		return birthLocationTownId;
	}

	public void setBirthLocationTownId(String birthLocationTownId) {
		this.birthLocationTownId = birthLocationTownId;
	}

	public String getBirthLocationTownName() {
		return birthLocationTownName;
	}

	public void setBirthLocationTownName(String birthLocationTownName) {
		this.birthLocationTownName = birthLocationTownName;
	}

	public String getBirthLocationVillageId() {
		return birthLocationVillageId;
	}

	public void setBirthLocationVillageId(String birthLocationVillageId) {
		this.birthLocationVillageId = birthLocationVillageId;
	}

	public String getBirthLocationVillageName() {
		return birthLocationVillageName;
	}

	public void setBirthLocationVillageName(String birthLocationVillageName) {
		this.birthLocationVillageName = birthLocationVillageName;
	}

	public String getBirthLocationGroupId() {
		return birthLocationGroupId;
	}

	public void setBirthLocationGroupId(String birthLocationGroupId) {
		this.birthLocationGroupId = birthLocationGroupId;
	}

	public String getBirthLocationGroupName() {
		return birthLocationGroupName;
	}

	public void setBirthLocationGroupName(String birthLocationGroupName) {
		this.birthLocationGroupName = birthLocationGroupName;
	}

	public String getCensusLocationId1() {
		return censusLocationId1;
	}

	public void setCensusLocationId1(String censusLocationId1) {
		this.censusLocationId1 = censusLocationId1;
	}

	public String getCensusLocationId2() {
		return censusLocationId2;
	}

	public void setCensusLocationId2(String censusLocationId2) {
		this.censusLocationId2 = censusLocationId2;
	}

	public String getCensusLocationId3() {
		return censusLocationId3;
	}

	public void setCensusLocationId3(String censusLocationId3) {
		this.censusLocationId3 = censusLocationId3;
	}

	public String getCensusLocationName1() {
		return censusLocationName1;
	}

	public void setCensusLocationName1(String censusLocationName1) {
		this.censusLocationName1 = censusLocationName1;
	}

	public String getCensusLocationName2() {
		return censusLocationName2;
	}

	public void setCensusLocationName2(String censusLocationName2) {
		this.censusLocationName2 = censusLocationName2;
	}

	public String getCensusLocationName3() {
		return censusLocationName3;
	}

	public void setCensusLocationName3(String censusLocationName3) {
		this.censusLocationName3 = censusLocationName3;
	}

	public String getCensusLocationTownId() {
		return censusLocationTownId;
	}

	public void setCensusLocationTownId(String censusLocationTownId) {
		this.censusLocationTownId = censusLocationTownId;
	}

	public String getCensusLocationTownName() {
		return censusLocationTownName;
	}

	public void setCensusLocationTownName(String censusLocationTownName) {
		this.censusLocationTownName = censusLocationTownName;
	}

	public String getCensusLocationVillageId() {
		return censusLocationVillageId;
	}

	public void setCensusLocationVillageId(String censusLocationVillageId) {
		this.censusLocationVillageId = censusLocationVillageId;
	}

	public String getCensusLocationVillageName() {
		return censusLocationVillageName;
	}

	public void setCensusLocationVillageName(String censusLocationVillageName) {
		this.censusLocationVillageName = censusLocationVillageName;
	}

	public String getCensusLocationGroupId() {
		return censusLocationGroupId;
	}

	public void setCensusLocationGroupId(String censusLocationGroupId) {
		this.censusLocationGroupId = censusLocationGroupId;
	}

	public String getCensusLocationGroupName() {
		return censusLocationGroupName;
	}

	public void setCensusLocationGroupName(String censusLocationGroupName) {
		this.censusLocationGroupName = censusLocationGroupName;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getReligionId() {
		return religionId;
	}

	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}

	public String getReligionName() {
		return religionName;
	}

	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}

	public String getDeiverLicense() {
		return deiverLicense;
	}

	public void setDeiverLicense(String deiverLicense) {
		this.deiverLicense = deiverLicense;
	}

	public String getCitizenShipId() {
		return citizenShipId;
	}

	public void setCitizenShipId(String citizenShipId) {
		this.citizenShipId = citizenShipId;
	}

	public String getCitizenShipName() {
		return citizenShipName;
	}

	public void setCitizenShipName(String citizenShipName) {
		this.citizenShipName = citizenShipName;
	}

	public String getRetiredStatus() {
		return retiredStatus;
	}

	public void setRetiredStatus(String retiredStatus) {
		this.retiredStatus = retiredStatus;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	
}