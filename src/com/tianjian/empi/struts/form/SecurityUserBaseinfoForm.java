//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.tianjian.empi.struts.form;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/** 
 * MyEclipse Struts
 * Creation date: 04-26-2007
 * 
 * XDoclet definition:
 * @struts.form name="SecurityUserBaseinfoForm"
 */
public class SecurityUserBaseinfoForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8532657321704614390L;
	/**ID*/
	private String id;
	/**客户ID*/
	private String pmi;
	/**中文姓名*/
	private String name;
	/**英文姓名*/
	private String nameEn;
	/**输入码*/
	private String inputCode;
	/**性别*/
	private String commConfigSexId;
	/**出生地*/
	private String birthPlace;
	/**出生日期*/ 
	//-------------出生日期处理--------------------------------
    private String birth_date_year;
    private String birth_date_month;
    private String birth_date_day;
	//-----------------------------------------------------
    //-------------死亡日期处理--------------------------------
    private String death_date_year;
    private String death_date_month;
    private String death_date_day;
	//-----------------------------------------------------
	/**国籍*/
	private String commConfigCountryId;
	/**民族*/
	private String commConfigNationalityId;
	/**身份证件类别*/
	private String commConfigIdTypeId;
	/**证件号码*/
	private String idNo;
	/**社会保障卡号*/
	private String sscid;
	/**ABO血型*/
	private String commConfigAboId;
	/**RH血型*/
	private String commConfigRhId;
	/**文化程度*/
	private String commConfigDegreeId;
	/**婚姻状况*/
	private String commConfigMaritalStatusId;
	/**所属省*/
	private String commConfigLocationId1;
	/**所属市*/
	private String commConfigLocationId2;
	/**所属县*/
	private String commConfigLocationId3;
	/**所属乡镇*/
	private String commConfigLocationTownId;
	/**所属村*/
	private String commConfigLocationVillageId;
	/**所属组*/
	private String commConfigLocationGroupId;
	/**居住地址*/
	private String mailingAddress;
	/**邮政编码*/
	private String zipcode;
	/**居住电话*/
	private String phone;
	/**E_mail*/
	private String EMail;
	/**照片*/
	private String photoPath;
	/**照片*/
	private String imagePath;
	/**联系人关系*/
	private String commConfigRelationshipId;
	/**联系人姓名*/
	private String contactPersonName;
	/**联系人电话号码*/
	private String contactPersonPhone;
	/**备注*/
	private String comments;
	
	private String deceasedInd;//是否死亡代码
	private String deceasedIndName;//是否死亡名称
	private String deceasedTime;//死亡时间
	private String motherId;//母亲ID
	private String motherName;//母亲姓名
	private String birthSequence;//产次
	private String multipleBirthind;//多胞胎标识
	private String multipleBirthindName;//多胞胎名称
	private String multipleBirthorderNo;//出生顺序
	private String occupationCodeId;//职业类别代码
	private String occupationCodeName;//职业类别名称
	
	/**手机号码*/
	
	private String mobileTel;
	
	private String createUserId;
	
	private String cardType;
	
	private String cardNo;
	//-----------------处理公用部分------------------------------------------
	private String xflag = "";
    private String verbId;
    private String message;
    private String orderNo;
    private String picStatus;
    private String asc;
    private String card_nos;
    private String card_nos1;
    private String position;
    private String locationLevel;//默认的平台等级是市级还是县级
    //-------处理代码对应的字典名称------------------
    /**性别*/
	private String commConfigSexId_name;
	/**国籍*/
	private String commConfigCountryId_name;
	/**民族*/
	private String commConfigNationalityId_name;
	/**身份证件类别*/
	private String commConfigIdTypeId_name;
	/**ABO血型*/
	private String commConfigAboId_name;
	/**RH血型*/
	private String commConfigRhId_name;
	/**文化程度*/
	private String commConfigDegreeId_name;
	/**婚姻状况*/
	private String commConfigMaritalStatusId_name;
	/**所属省*/
	private String commConfigLocationId1_name;
	/**所属市*/
	private String commConfigLocationId2_name;
	/**所属县*/
	private String commConfigLocationId3_name;
	/**所属乡镇*/
	private String commConfigLocationTownId_name;
	/**所属村*/
	private String commConfigLocationVillageId_name;
	/**所属组*/
	private String commConfigLocationGroupId_name;
	/**联系人关系*/
	private String commConfigRelationshipId_name;
	private String passWord;
	
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
	
	private String doorNo;
	private String censusDoorNo;
	private String birthDoorNo;
	
    //-------------主键隐含处理------------------------------
    private String idHidden;
	
    private String hspConfigBaseinfoId;
	//--------所有字典都定义成数组---------------------------
	 /**性别*/
	private String[] commConfigSexIds;
	private String[] commConfigSexId_names;
	/**国籍*/
	private String[] commConfigCountryIds;
	private String[] commConfigCountryId_names;
	/**民族*/
	private String[] commConfigNationalityIds;
	private String[] commConfigNationalityId_names;
	/**身份证件类别*/
	private String[] commConfigIdTypeIds;
	private String[] commConfigIdTypeId_names;
	/**ABO血型*/
	private String[] commConfigAboIds;
	private String[] commConfigAboId_names;
	/**RH血型*/
	private String[] commConfigRhIds;
	private String[] commConfigRhId_names;
	/**职业*/
	private String[] occupationCodeIds;
	private String[] occupationCodeNames;
	/**是否死亡*/
	private String[] deceasedInds;
	private String[] deceasedIndNames;
	/**文化程度*/
	private String[] commConfigDegreeIds;
	private String[] commConfigDegreeId_names;
	/**婚姻状况*/
	private String[] commConfigMaritalStatusIds;
	private String[] commConfigMaritalStatusId_names;
	/**所属省*/
	private String[] commConfigLocationId1s;
	private String[] commConfigLocationId1_names;
	/**所属市*/
	private String[] commConfigLocationId2s;
	private String[] commConfigLocationId2_names;
	/**所属县*/
	private String[] commConfigLocationId3s;
	private String[] commConfigLocationId3_names;
	/**所属乡镇*/
	private String[] commConfigLocationTownIds;
	private String[] commConfigLocationTownId_names;
	/**所属村*/
	private String[] commConfigLocationVillageIds;
	private String[] commConfigLocationVillageId_names;
	/**所属组*/
	private String[] commConfigLocationGroupIds;
	private String[] commConfigLocationGroupId_names; 
	
	//户籍地址
	/**所属省*/
	private String[] censusLocationId1s;
	private String[] censusLocationId1_names;
	/**所属市*/
	private String[] censusLocationId2s;
	private String[] censusLocationId2_names;
	/**所属县*/
	private String[] censusLocationId3s;
	private String[] censusLocationId3_names;
	/**所属乡镇*/
	private String[] censusLocationTownIds;
	private String[] censusLocationTownId_names;
	/**所属村*/
	private String[] censusLocationVillageIds;
	private String[] censusLocationVillageId_names;
	/**所属组*/
	private String[] censusLocationGroupIds;
	private String[] censusLocationGroupId_names; 
	//出生地址
	/**所属省*/
	private String[] birthLocationId1s;
	private String[] birthLocationId1_names;
	/**所属市*/
	private String[] birthLocationId2s;
	private String[] birthLocationId2_names;
	/**所属县*/
	private String[] birthLocationId3s;
	private String[] birthLocationId3_names;
	/**所属乡镇*/
	private String[] birthLocationTownIds;
	private String[] birthLocationTownId_names;
	/**所属村*/
	private String[] birthLocationVillageIds;
	private String[] birthLocationVillageId_names;
	/**所属组*/
	private String[] birthLocationGroupIds;
	private String[] birthLocationGroupId_names; 
	/**联系人关系*/
	private String[] commConfigRelationshipIds;
	private String[] commConfigRelationshipId_names;
	/**卡类型*/
	private String[] commConfigCardTypeIds;
	private String[] commConfigCardTypeId_names;
	
	//-------------------------------------------------
    private String[] years;
    private String[] months;
    private String[] days;
	
	//-----定义query查询列表-------------------------------
    private String[] idList;
    private String[] pmiList;
    private String[] nameList;
    private String[] commConfigSexIdList;
    private String[] commConfigSexNameList;
    private String[] commconfigLocationId3List;
    private String[] commconfigLocationId3_nameList;
    private String[] commconfigLocationTownIdList;
    private String[] commconfigLocationTownId_nameList;
    private String[] commconfigLocationVillageIdList;
    private String[] commconfigLocationVillageId_nameList;
    private String[] dateOfBirthList;
    private String[] birthPlaceList;
    private String[] inputCodeList;
    private String[] cardNoList;
    private String[] idNoList;
    private String[] mobileTelList;
    private String[] mailingAddressList;
    private String[] commConfigIdTypeList;
    private String[] residentialAddress;
    private String[] fullAddress;
    private String[] cardType_list;
    private String[] cardTypeName_list;
    private String[] cardNo_list;
    private String[] cardId_list;
    private String[] cardType_list1;
    private String[] cardTypeName_list1;
    
    //userinfo并入内容
    private String[] seqNoList;
    private String[] hspConfigBaseinfoIdList;
    private String[] hspConfigBaseinfoNameList;
    private String[] commConfigCardtypeIdList;
    private String[] commConfigCardtypeNameList;
    private String[] commConfigIdTypeIdList;
    private String[] commConfigIdTypeNameList;
    
    private FormFile file;
	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		// TODO Auto-generated method stub
		return null;
	}

	public SecurityUserBaseinfoForm() {
		super();
		id ="";
		pmi ="";
		name ="";
		nameEn ="";
		inputCode ="";
		commConfigSexId ="";
		birthPlace ="";
	    birth_date_year ="";
	    birth_date_month ="";
	    birth_date_day ="";
		commConfigCountryId ="";
		commConfigNationalityId ="";
		commConfigIdTypeId ="";
		idNo ="";
		sscid ="";
		commConfigAboId ="";
		commConfigRhId ="";
		commConfigDegreeId ="";
		commConfigMaritalStatusId ="";
		hspConfigBaseinfoId="";
		commConfigLocationId1 ="";
		commConfigLocationId2 ="";
		commConfigLocationId3 ="";
		commConfigLocationTownId="";
		commConfigLocationVillageId="";
		commConfigLocationGroupId="";
		mailingAddress ="";
		zipcode ="";
		phone ="";
		doorNo="";
		censusDoorNo="";
		birthDoorNo="";
		EMail ="";
		photoPath ="";
		imagePath ="";
		mobileTel="";
		commConfigRelationshipId ="";
		contactPersonName ="";
		contactPersonPhone ="";
		comments ="";
	    verbId ="";
	    message ="";
	    orderNo ="";
	    asc ="";
	    picStatus="";
		commConfigSexId_name ="";
		commConfigCountryId_name ="";
		commConfigNationalityId_name ="";
		commConfigIdTypeId_name ="";
		commConfigAboId_name ="";
		commConfigRhId_name ="";
		commConfigDegreeId_name ="";
		commConfigMaritalStatusId_name ="";
		commConfigLocationId1_name ="";
		commConfigLocationId2_name ="";
		commConfigLocationId3_name ="";
		commConfigLocationTownId_name="";
		commConfigLocationVillageId_name="";
		commConfigLocationGroupId_name="";
		commConfigRelationshipId_name ="";
	    idHidden ="";
	    passWord="";
	    cardType="";
	    cardNo="";
	    deceasedInd = "";//是否死亡代码
		deceasedIndName = "";//是否死亡名称
		deceasedTime = "";//死亡时间
		motherId = "";//母亲ID
		motherName = "";//母亲姓名
		birthSequence = "";//产次
		multipleBirthind = "";//多胞胎标识
		multipleBirthorderNo = "";//出生顺序
		occupationCodeId = "";//职业类别代码
		occupationCodeName = "";//职业类别名称
		death_date_year = "";
		death_date_month = "";
	    death_date_day = "";
	    birthLocationId1 = "";
		birthLocationId2 = "";
		birthLocationId3 = "";
		birthLocationName1 = "";
		birthLocationName2 = "";
		birthLocationName3 = "";
		birthLocationTownId = "";
		birthLocationTownName = "";
		birthLocationVillageId = "";
		birthLocationVillageName = "";
		birthLocationGroupId = "";
		birthLocationGroupName = "";
		censusLocationId1 = "";
		censusLocationId2 = "";
		censusLocationId3 = "";
		censusLocationName1 = "";
		censusLocationName2 = "";
		censusLocationName3 = "";
		censusLocationTownId = "";
		censusLocationTownName = "";
		censusLocationVillageId = "";
		censusLocationVillageName = "";
		censusLocationGroupId = "";
		censusLocationGroupName = "";
	    //photoFile=null;
	}

	
	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	
	public String getPmi() {
		return pmi;
	}

	
	public void setPmi(String pmi) {
		this.pmi = pmi;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getNameEn() {
		return nameEn;
	}

	
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	
	public String getInputCode() {
		return inputCode;
	}

	
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	
	public String getCommConfigSexId() {
		return commConfigSexId;
	}

	
	public void setCommConfigSexId(String commConfigSexId) {
		this.commConfigSexId = commConfigSexId;
	}

	
	public String getBirthPlace() {
		return birthPlace;
	}

	
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	
	
	public String getCommConfigCountryId() {
		return commConfigCountryId;
	}

	
	public void setCommConfigCountryId(String commConfigCountryId) {
		this.commConfigCountryId = commConfigCountryId;
	}

	
	public String getCommConfigNationalityId() {
		return commConfigNationalityId;
	}

	
	public void setCommConfigNationalityId(String commConfigNationalityId) {
		this.commConfigNationalityId = commConfigNationalityId;
	}

	
	public String getCommConfigIdTypeId() {
		return commConfigIdTypeId;
	}

	
	public void setCommConfigIdTypeId(String commConfigIdTypeId) {
		this.commConfigIdTypeId = commConfigIdTypeId;
	}

	
	public String getIdNo() {
		return idNo;
	}

	
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	
	public String getSscid() {
		return sscid;
	}

	
	public void setSscid(String sscid) {
		this.sscid = sscid;
	}

	
	public String getCommConfigAboId() {
		return commConfigAboId;
	}

	
	public void setCommConfigAboId(String commConfigAboId) {
		this.commConfigAboId = commConfigAboId;
	}

	
	public String getCommConfigRhId() {
		return commConfigRhId;
	}

	
	public void setCommConfigRhId(String commConfigRhId) {
		this.commConfigRhId = commConfigRhId;
	}

	
	public String getCommConfigDegreeId() {
		return commConfigDegreeId;
	}

	
	public void setCommConfigDegreeId(String commConfigDegreeId) {
		this.commConfigDegreeId = commConfigDegreeId;
	}

	
	public String getCommConfigMaritalStatusId() {
		return commConfigMaritalStatusId;
	}

	
	public void setCommConfigMaritalStatusId(String commConfigMaritalStatusId) {
		this.commConfigMaritalStatusId = commConfigMaritalStatusId;
	}

	
	public String getCommConfigLocationId1() {
		return commConfigLocationId1;
	}

	
	public void setCommConfigLocationId1(String commConfigLocationId1) {
		this.commConfigLocationId1 = commConfigLocationId1;
	}

	
	public String getCommConfigLocationId2() {
		return commConfigLocationId2;
	}

	
	public void setCommConfigLocationId2(String commConfigLocationId2) {
		this.commConfigLocationId2 = commConfigLocationId2;
	}

	
	public String getCommConfigLocationId3() {
		return commConfigLocationId3;
	}

	
	public void setCommConfigLocationId3(String commConfigLocationId3) {
		this.commConfigLocationId3 = commConfigLocationId3;
	}

	
	public String getMailingAddress() {
		return mailingAddress;
	}

	
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	
	public String getZipcode() {
		return zipcode;
	}

	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	
	public String getPhone() {
		return phone;
	}

	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	public String getPhotoPath() {
		return photoPath;
	}

	
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	
	public String getCommConfigRelationshipId() {
		return commConfigRelationshipId;
	}

	
	public void setCommConfigRelationshipId(String commConfigRelationshipId) {
		this.commConfigRelationshipId = commConfigRelationshipId;
	}

	
	public String getContactPersonName() {
		return contactPersonName;
	}

	
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	
	public String getContactPersonPhone() {
		return contactPersonPhone;
	}

	
	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

	
	public String getComments() {
		return comments;
	}

	
	public void setComments(String comments) {
		this.comments = comments;
	}

	
	public String getVerbId() {
		return verbId;
	}

	
	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}

	
	public String getMessage() {
		return message;
	}

	
	public void setMessage(String message) {
		this.message = message;
	}

	
	public String getOrderNo() {
		return orderNo;
	}

	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	
	public String getAsc() {
		return asc;
	}

	
	public void setAsc(String asc) {
		this.asc = asc;
	}

	
	public String getCommConfigSexId_name() {
		return commConfigSexId_name;
	}

	
	public void setCommConfigSexId_name(String commConfigSexId_name) {
		this.commConfigSexId_name = commConfigSexId_name;
	}

	
	public String getCommConfigCountryId_name() {
		return commConfigCountryId_name;
	}

	
	public void setCommConfigCountryId_name(String commConfigCountryId_name) {
		this.commConfigCountryId_name = commConfigCountryId_name;
	}

	
	public String getCommConfigNationalityId_name() {
		return commConfigNationalityId_name;
	}

	
	public void setCommConfigNationalityId_name(String commConfigNationalityId_name) {
		this.commConfigNationalityId_name = commConfigNationalityId_name;
	}

	
	public String getCommConfigIdTypeId_name() {
		return commConfigIdTypeId_name;
	}

	
	public void setCommConfigIdTypeId_name(String commConfigIdTypeId_name) {
		this.commConfigIdTypeId_name = commConfigIdTypeId_name;
	}

	
	public String getCommConfigAboId_name() {
		return commConfigAboId_name;
	}

	
	public void setCommConfigAboId_name(String commConfigAboId_name) {
		this.commConfigAboId_name = commConfigAboId_name;
	}

	
	public String getCommConfigRhId_name() {
		return commConfigRhId_name;
	}

	
	public void setCommConfigRhId_name(String commConfigRhId_name) {
		this.commConfigRhId_name = commConfigRhId_name;
	}

	
	public String getCommConfigDegreeId_name() {
		return commConfigDegreeId_name;
	}

	
	public void setCommConfigDegreeId_name(String commConfigDegreeId_name) {
		this.commConfigDegreeId_name = commConfigDegreeId_name;
	}

	
	public String getCommConfigMaritalStatusId_name() {
		return commConfigMaritalStatusId_name;
	}

	
	public void setCommConfigMaritalStatusId_name(String commConfigMaritalStatusId_name) {
		this.commConfigMaritalStatusId_name = commConfigMaritalStatusId_name;
	}

	
	public String getCommConfigLocationId1_name() {
		return commConfigLocationId1_name;
	}

	
	public void setCommConfigLocationId1_name(String commConfigLocationId1_name) {
		this.commConfigLocationId1_name = commConfigLocationId1_name;
	}

	
	public String getCommConfigLocationId2_name() {
		return commConfigLocationId2_name;
	}

	
	public void setCommConfigLocationId2_name(String commConfigLocationId2_name) {
		this.commConfigLocationId2_name = commConfigLocationId2_name;
	}

	
	public String getCommConfigLocationId3_name() {
		return commConfigLocationId3_name;
	}

	
	public void setCommConfigLocationId3_name(String commConfigLocationId3_name) {
		this.commConfigLocationId3_name = commConfigLocationId3_name;
	}

	
	public String getCommConfigRelationshipId_name() {
		return commConfigRelationshipId_name;
	}

	
	public void setCommConfigRelationshipId_name(String commConfigRelationshipId_name) {
		this.commConfigRelationshipId_name = commConfigRelationshipId_name;
	}

	
	public String getIdHidden() {
		return idHidden;
	}

	
	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
	}

	
	public String[] getCommConfigSexIds() {
		return commConfigSexIds;
	}

	
	public void setCommConfigSexIds(String[] commConfigSexIds) {
		this.commConfigSexIds = commConfigSexIds;
	}

	
	public String[] getCommConfigSexId_names() {
		return commConfigSexId_names;
	}

	
	public void setCommConfigSexId_names(String[] commConfigSexId_names) {
		this.commConfigSexId_names = commConfigSexId_names;
	}

	
	public String[] getCommConfigCountryIds() {
		return commConfigCountryIds;
	}

	
	public void setCommConfigCountryIds(String[] commConfigCountryIds) {
		this.commConfigCountryIds = commConfigCountryIds;
	}

	
	public String[] getCommConfigCountryId_names() {
		return commConfigCountryId_names;
	}

	
	public void setCommConfigCountryId_names(String[] commConfigCountryId_names) {
		this.commConfigCountryId_names = commConfigCountryId_names;
	}

	
	public String[] getCommConfigNationalityIds() {
		return commConfigNationalityIds;
	}

	
	public void setCommConfigNationalityIds(String[] commConfigNationalityIds) {
		this.commConfigNationalityIds = commConfigNationalityIds;
	}

	
	public String[] getCommConfigNationalityId_names() {
		return commConfigNationalityId_names;
	}

	
	public void setCommConfigNationalityId_names(String[] commConfigNationalityId_names) {
		this.commConfigNationalityId_names = commConfigNationalityId_names;
	}

	
	public String[] getCommConfigIdTypeIds() {
		return commConfigIdTypeIds;
	}

	
	public void setCommConfigIdTypeIds(String[] commConfigIdTypeIds) {
		this.commConfigIdTypeIds = commConfigIdTypeIds;
	}

	
	public String[] getCommConfigIdTypeId_names() {
		return commConfigIdTypeId_names;
	}

	
	public void setCommConfigIdTypeId_names(String[] commConfigIdTypeId_names) {
		this.commConfigIdTypeId_names = commConfigIdTypeId_names;
	}

	
	public String[] getCommConfigAboIds() {
		return commConfigAboIds;
	}

	
	public void setCommConfigAboIds(String[] commConfigAboIds) {
		this.commConfigAboIds = commConfigAboIds;
	}

	
	public String[] getCommConfigAboId_names() {
		return commConfigAboId_names;
	}

	
	public void setCommConfigAboId_names(String[] commConfigAboId_names) {
		this.commConfigAboId_names = commConfigAboId_names;
	}

	
	public String[] getCommConfigRhIds() {
		return commConfigRhIds;
	}

	
	public void setCommConfigRhIds(String[] commConfigRhIds) {
		this.commConfigRhIds = commConfigRhIds;
	}

	
	public String[] getCommConfigRhId_names() {
		return commConfigRhId_names;
	}

	
	public void setCommConfigRhId_names(String[] commConfigRhId_names) {
		this.commConfigRhId_names = commConfigRhId_names;
	}

	
	public String[] getCommConfigDegreeIds() {
		return commConfigDegreeIds;
	}

	
	public void setCommConfigDegreeIds(String[] commConfigDegreeIds) {
		this.commConfigDegreeIds = commConfigDegreeIds;
	}

	
	public String[] getCommConfigDegreeId_names() {
		return commConfigDegreeId_names;
	}

	
	public void setCommConfigDegreeId_names(String[] commConfigDegreeId_names) {
		this.commConfigDegreeId_names = commConfigDegreeId_names;
	}

	
	public String[] getCommConfigMaritalStatusIds() {
		return commConfigMaritalStatusIds;
	}

	
	public void setCommConfigMaritalStatusIds(String[] commConfigMaritalStatusIds) {
		this.commConfigMaritalStatusIds = commConfigMaritalStatusIds;
	}

	
	public String[] getCommConfigMaritalStatusId_names() {
		return commConfigMaritalStatusId_names;
	}

	
	public void setCommConfigMaritalStatusId_names(String[] commConfigMaritalStatusId_names) {
		this.commConfigMaritalStatusId_names = commConfigMaritalStatusId_names;
	}

	
	public String[] getCommConfigLocationId1s() {
		return commConfigLocationId1s;
	}

	
	public void setCommConfigLocationId1s(String[] commConfigLocationId1s) {
		this.commConfigLocationId1s = commConfigLocationId1s;
	}

	
	public String[] getCommConfigLocationId1_names() {
		return commConfigLocationId1_names;
	}

	
	public void setCommConfigLocationId1_names(String[] commConfigLocationId1_names) {
		this.commConfigLocationId1_names = commConfigLocationId1_names;
	}

	
	public String[] getCommConfigLocationId2s() {
		return commConfigLocationId2s;
	}

	
	public void setCommConfigLocationId2s(String[] commConfigLocationId2s) {
		this.commConfigLocationId2s = commConfigLocationId2s;
	}

	
	public String[] getCommConfigLocationId2_names() {
		return commConfigLocationId2_names;
	}

	
	public void setCommConfigLocationId2_names(String[] commConfigLocationId2_names) {
		this.commConfigLocationId2_names = commConfigLocationId2_names;
	}

	
	public String[] getCommConfigLocationId3s() {
		return commConfigLocationId3s;
	}

	
	public void setCommConfigLocationId3s(String[] commConfigLocationId3s) {
		this.commConfigLocationId3s = commConfigLocationId3s;
	}

	
	public String[] getCommConfigLocationId3_names() {
		return commConfigLocationId3_names;
	}

	
	public void setCommConfigLocationId3_names(String[] commConfigLocationId3_names) {
		this.commConfigLocationId3_names = commConfigLocationId3_names;
	}

	
	public String[] getCommConfigRelationshipIds() {
		return commConfigRelationshipIds;
	}

	
	public void setCommConfigRelationshipIds(String[] commConfigRelationshipIds) {
		this.commConfigRelationshipIds = commConfigRelationshipIds;
	}

	
	public String[] getCommConfigRelationshipId_names() {
		return commConfigRelationshipId_names;
	}

	
	public void setCommConfigRelationshipId_names(String[] commConfigRelationshipId_names) {
		this.commConfigRelationshipId_names = commConfigRelationshipId_names;
	}

	
	public String[] getPmiList() {
		return pmiList;
	}

	
	public void setPmiList(String[] pmiList) {
		this.pmiList = pmiList;
	}

	
	public String[] getNameList() {
		return nameList;
	}

	
	public void setNameList(String[] nameList) {
		this.nameList = nameList;
	}

	
	public String[] getCommConfigSexIdList() {
		return commConfigSexIdList;
	}

	
	public void setCommConfigSexIdList(String[] commConfigSexIdList) {
		this.commConfigSexIdList = commConfigSexIdList;
	}

	
	public String[] getDateOfBirthList() {
		return dateOfBirthList;
	}

	
	public void setDateOfBirthList(String[] dateOfBirthList) {
		this.dateOfBirthList = dateOfBirthList;
	}

	
	public String[] getBirthPlaceList() {
		return birthPlaceList;
	}

	
	public void setBirthPlaceList(String[] birthPlaceList) {
		this.birthPlaceList = birthPlaceList;
	}

	
	public String[] getInputCodeList() {
		return inputCodeList;
	}

	
	public void setInputCodeList(String[] inputCodeList) {
		this.inputCodeList = inputCodeList;
	}

	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
	
	
	public String getBirth_date_year() {
		return birth_date_year;
	}

	
	public void setBirth_date_year(String birth_date_year) {
		this.birth_date_year = birth_date_year;
	}

	
	public String getBirth_date_month() {
		return birth_date_month;
	}

	
	public void setBirth_date_month(String birth_date_month) {
		this.birth_date_month = birth_date_month;
	}

	
	public String getBirth_date_day() {
		return birth_date_day;
	}

	
	public void setBirth_date_day(String birth_date_day) {
		this.birth_date_day = birth_date_day;
	}

	

	
	public String getEMail() {
		return EMail;
	}

	
	public void setEMail(String mail) {
		EMail = mail;
	}

	public String[] getYears() {
		return years;
	}

	
	public void setYears(String[] years) {
		this.years = years;
	}

	
	public String[] getMonths() {
		return months;
	}

	
	public void setMonths(String[] months) {
		this.months = months;
	}

	
	public String[] getDays() {
		return days;
	}

	
	public void setDays(String[] days) {
		this.days = days;
	}

	
	public String[] getIdList() {
		return idList;
	}

	
	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	
	public String[] getCommConfigSexNameList() {
		return commConfigSexNameList;
	}

	
	public void setCommConfigSexNameList(String[] commConfigSexNameList) {
		this.commConfigSexNameList = commConfigSexNameList;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getPicStatus() {
		return picStatus;
	}

	public void setPicStatus(String picStatus) {
		this.picStatus = picStatus;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String[] getCardNoList() {
		return cardNoList;
	}

	public void setCardNoList(String[] cardNoList) {
		this.cardNoList = cardNoList;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String[] getIdNoList() {
		return idNoList;
	}

	public void setIdNoList(String[] idNoList) {
		this.idNoList = idNoList;
	}

	public String[] getMailingAddressList() {
		return mailingAddressList;
	}

	public void setMailingAddressList(String[] mailingAddressList) {
		this.mailingAddressList = mailingAddressList;
	}

	public String[] getCommConfigCardTypeIds() {
		return commConfigCardTypeIds;
	}

	public void setCommConfigCardTypeIds(String[] commConfigCardTypeIds) {
		this.commConfigCardTypeIds = commConfigCardTypeIds;
	}

	public String[] getCommConfigCardTypeId_names() {
		return commConfigCardTypeId_names;
	}

	public void setCommConfigCardTypeId_names(String[] commConfigCardTypeId_names) {
		this.commConfigCardTypeId_names = commConfigCardTypeId_names;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCommConfigLocationTownId() {
		return commConfigLocationTownId;
	}

	public void setCommConfigLocationTownId(String commConfigLocationTownId) {
		this.commConfigLocationTownId = commConfigLocationTownId;
	}

	public String getCommConfigLocationVillageId() {
		return commConfigLocationVillageId;
	}

	public void setCommConfigLocationVillageId(String commConfigLocationVillageId) {
		this.commConfigLocationVillageId = commConfigLocationVillageId;
	}

	public String[] getCommConfigLocationTownIds() {
		return commConfigLocationTownIds;
	}

	public void setCommConfigLocationTownIds(String[] commConfigLocationTownIds) {
		this.commConfigLocationTownIds = commConfigLocationTownIds;
	}

	public String[] getCommConfigLocationTownId_names() {
		return commConfigLocationTownId_names;
	}

	public void setCommConfigLocationTownId_names(
			String[] commConfigLocationTownId_names) {
		this.commConfigLocationTownId_names = commConfigLocationTownId_names;
	}

	public String[] getCommConfigLocationVillageIds() {
		return commConfigLocationVillageIds;
	}

	public void setCommConfigLocationVillageIds(
			String[] commConfigLocationVillageIds) {
		this.commConfigLocationVillageIds = commConfigLocationVillageIds;
	}

	public String[] getCommConfigLocationVillageId_names() {
		return commConfigLocationVillageId_names;
	}

	public void setCommConfigLocationVillageId_names(
			String[] commConfigLocationVillageId_names) {
		this.commConfigLocationVillageId_names = commConfigLocationVillageId_names;
	}

	public String getCommConfigLocationTownId_name() {
		return commConfigLocationTownId_name;
	}

	public void setCommConfigLocationTownId_name(
			String commConfigLocationTownId_name) {
		this.commConfigLocationTownId_name = commConfigLocationTownId_name;
	}

	public String getCommConfigLocationVillageId_name() {
		return commConfigLocationVillageId_name;
	}

	public void setCommConfigLocationVillageId_name(
			String commConfigLocationVillageId_name) {
		this.commConfigLocationVillageId_name = commConfigLocationVillageId_name;
	}

	public String[] getCommconfigLocationId3List() {
		return commconfigLocationId3List;
	}

	public void setCommconfigLocationId3List(String[] commconfigLocationId3List) {
		this.commconfigLocationId3List = commconfigLocationId3List;
	}

	public String[] getCommconfigLocationId3_nameList() {
		return commconfigLocationId3_nameList;
	}

	public void setCommconfigLocationId3_nameList(
			String[] commconfigLocationId3_nameList) {
		this.commconfigLocationId3_nameList = commconfigLocationId3_nameList;
	}

	public String[] getCommconfigLocationTownIdList() {
		return commconfigLocationTownIdList;
	}

	public void setCommconfigLocationTownIdList(
			String[] commconfigLocationTownIdList) {
		this.commconfigLocationTownIdList = commconfigLocationTownIdList;
	}

	public String[] getCommconfigLocationTownId_nameList() {
		return commconfigLocationTownId_nameList;
	}

	public void setCommconfigLocationTownId_nameList(
			String[] commconfigLocationTownId_nameList) {
		this.commconfigLocationTownId_nameList = commconfigLocationTownId_nameList;
	}

	public String[] getCommconfigLocationVillageIdList() {
		return commconfigLocationVillageIdList;
	}

	public void setCommconfigLocationVillageIdList(
			String[] commconfigLocationVillageIdList) {
		this.commconfigLocationVillageIdList = commconfigLocationVillageIdList;
	}

	public String[] getCommconfigLocationVillageId_nameList() {
		return commconfigLocationVillageId_nameList;
	}

	public void setCommconfigLocationVillageId_nameList(
			String[] commconfigLocationVillageId_nameList) {
		this.commconfigLocationVillageId_nameList = commconfigLocationVillageId_nameList;
	}

	public String getCommConfigLocationGroupId() {
		return commConfigLocationGroupId;
	}

	public void setCommConfigLocationGroupId(String commConfigLocationGroupId) {
		this.commConfigLocationGroupId = commConfigLocationGroupId;
	}

	public String[] getCommConfigLocationGroupIds() {
		return commConfigLocationGroupIds;
	}

	public void setCommConfigLocationGroupIds(String[] commConfigLocationGroupIds) {
		this.commConfigLocationGroupIds = commConfigLocationGroupIds;
	}

	public String[] getCommConfigLocationGroupId_names() {
		return commConfigLocationGroupId_names;
	}

	public void setCommConfigLocationGroupId_names(
			String[] commConfigLocationGroupId_names) {
		this.commConfigLocationGroupId_names = commConfigLocationGroupId_names;
	}

	public String getCommConfigLocationGroupId_name() {
		return commConfigLocationGroupId_name;
	}

	public void setCommConfigLocationGroupId_name(
			String commConfigLocationGroupId_name) {
		this.commConfigLocationGroupId_name = commConfigLocationGroupId_name;
	}

	public String[] getCardType_list() {
		return cardType_list;
	}

	public void setCardType_list(String[] cardType_list) {
		this.cardType_list = cardType_list;
	}

	public String[] getCardTypeName_list() {
		return cardTypeName_list;
	}

	public void setCardTypeName_list(String[] cardTypeName_list) {
		this.cardTypeName_list = cardTypeName_list;
	}

	public String[] getCardNo_list() {
		return cardNo_list;
	}

	public void setCardNo_list(String[] cardNo_list) {
		this.cardNo_list = cardNo_list;
	}

	public String[] getCardId_list() {
		return cardId_list;
	}

	public void setCardId_list(String[] cardId_list) {
		this.cardId_list = cardId_list;
	}

	public String getCard_nos() {
		return card_nos;
	}

	public void setCard_nos(String card_nos) {
		this.card_nos = card_nos;
	}

	public String[] getCardType_list1() {
		return cardType_list1;
	}

	public void setCardType_list1(String[] cardType_list1) {
		this.cardType_list1 = cardType_list1;
	}

	public String[] getCardTypeName_list1() {
		return cardTypeName_list1;
	}

	public void setCardTypeName_list1(String[] cardTypeName_list1) {
		this.cardTypeName_list1 = cardTypeName_list1;
	}

	public String getCard_nos1() {
		return card_nos1;
	}

	public void setCard_nos1(String card_nos1) {
		this.card_nos1 = card_nos1;
	}

	public String getXflag() {
		return xflag;
	}

	public void setXflag(String xflag) {
		this.xflag = xflag;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDeceasedInd() {
		return deceasedInd;
	}

	public void setDeceasedInd(String deceasedInd) {
		this.deceasedInd = deceasedInd;
	}

	public String getDeceasedTime() {
		return deceasedTime;
	}

	public void setDeceasedTime(String deceasedTime) {
		this.deceasedTime = deceasedTime;
	}

	public String getMotherId() {
		return motherId;
	}

	public void setMotherId(String motherId) {
		this.motherId = motherId;
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

	public String getMultipleBirthind() {
		return multipleBirthind;
	}

	public void setMultipleBirthind(String multipleBirthind) {
		this.multipleBirthind = multipleBirthind;
	}

	public String getMultipleBirthorderNo() {
		return multipleBirthorderNo;
	}

	public void setMultipleBirthorderNo(String multipleBirthorderNo) {
		this.multipleBirthorderNo = multipleBirthorderNo;
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

	public String[] getOccupationCodeIds() {
		return occupationCodeIds;
	}

	public void setOccupationCodeIds(String[] occupationCodeIds) {
		this.occupationCodeIds = occupationCodeIds;
	}

	public String[] getOccupationCodeNames() {
		return occupationCodeNames;
	}

	public void setOccupationCodeNames(String[] occupationCodeNames) {
		this.occupationCodeNames = occupationCodeNames;
	}

	public String[] getDeceasedInds() {
		return deceasedInds;
	}

	public void setDeceasedInds(String[] deceasedInds) {
		this.deceasedInds = deceasedInds;
	}

	public String[] getDeceasedIndNames() {
		return deceasedIndNames;
	}

	public void setDeceasedIndNames(String[] deceasedIndNames) {
		this.deceasedIndNames = deceasedIndNames;
	}

	public String getMultipleBirthindName() {
		return multipleBirthindName;
	}

	public void setMultipleBirthindName(String multipleBirthindName) {
		this.multipleBirthindName = multipleBirthindName;
	}

	public String getDeath_date_year() {
		return death_date_year;
	}

	public void setDeath_date_year(String death_date_year) {
		this.death_date_year = death_date_year;
	}

	public String getDeath_date_month() {
		return death_date_month;
	}

	public void setDeath_date_month(String death_date_month) {
		this.death_date_month = death_date_month;
	}

	public String getDeath_date_day() {
		return death_date_day;
	}

	public void setDeath_date_day(String death_date_day) {
		this.death_date_day = death_date_day;
	}

	public String getHspConfigBaseinfoId() {
		return hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	public String[] getSeqNoList() {
		return seqNoList;
	}

	public void setSeqNoList(String[] seqNoList) {
		this.seqNoList = seqNoList;
	}

	public String[] getHspConfigBaseinfoIdList() {
		return hspConfigBaseinfoIdList;
	}

	public void setHspConfigBaseinfoIdList(String[] hspConfigBaseinfoIdList) {
		this.hspConfigBaseinfoIdList = hspConfigBaseinfoIdList;
	}

	public String[] getHspConfigBaseinfoNameList() {
		return hspConfigBaseinfoNameList;
	}

	public void setHspConfigBaseinfoNameList(String[] hspConfigBaseinfoNameList) {
		this.hspConfigBaseinfoNameList = hspConfigBaseinfoNameList;
	}

	public String[] getCommConfigCardtypeIdList() {
		return commConfigCardtypeIdList;
	}

	public void setCommConfigCardtypeIdList(String[] commConfigCardtypeIdList) {
		this.commConfigCardtypeIdList = commConfigCardtypeIdList;
	}

	public String[] getCommConfigCardtypeNameList() {
		return commConfigCardtypeNameList;
	}

	public void setCommConfigCardtypeNameList(String[] commConfigCardtypeNameList) {
		this.commConfigCardtypeNameList = commConfigCardtypeNameList;
	}

	public String[] getCommConfigIdTypeIdList() {
		return commConfigIdTypeIdList;
	}

	public void setCommConfigIdTypeIdList(String[] commConfigIdTypeIdList) {
		this.commConfigIdTypeIdList = commConfigIdTypeIdList;
	}

	public String[] getCommConfigIdTypeNameList() {
		return commConfigIdTypeNameList;
	}

	public void setCommConfigIdTypeNameList(String[] commConfigIdTypeNameList) {
		this.commConfigIdTypeNameList = commConfigIdTypeNameList;
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


	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getCensusDoorNo() {
		return censusDoorNo;
	}

	public void setCensusDoorNo(String censusDoorNo) {
		this.censusDoorNo = censusDoorNo;
	}

	public String getBirthDoorNo() {
		return birthDoorNo;
	}

	public void setBirthDoorNo(String birthDoorNo) {
		this.birthDoorNo = birthDoorNo;
	}

	public String[] getCensusLocationId1s() {
		return censusLocationId1s;
	}

	public void setCensusLocationId1s(String[] censusLocationId1s) {
		this.censusLocationId1s = censusLocationId1s;
	}

	public String[] getCensusLocationId1_names() {
		return censusLocationId1_names;
	}

	public void setCensusLocationId1_names(String[] censusLocationId1_names) {
		this.censusLocationId1_names = censusLocationId1_names;
	}

	public String[] getCensusLocationId2s() {
		return censusLocationId2s;
	}

	public void setCensusLocationId2s(String[] censusLocationId2s) {
		this.censusLocationId2s = censusLocationId2s;
	}

	public String[] getCensusLocationId2_names() {
		return censusLocationId2_names;
	}

	public void setCensusLocationId2_names(String[] censusLocationId2_names) {
		this.censusLocationId2_names = censusLocationId2_names;
	}

	public String[] getCensusLocationId3s() {
		return censusLocationId3s;
	}

	public void setCensusLocationId3s(String[] censusLocationId3s) {
		this.censusLocationId3s = censusLocationId3s;
	}

	public String[] getCensusLocationId3_names() {
		return censusLocationId3_names;
	}

	public void setCensusLocationId3_names(String[] censusLocationId3_names) {
		this.censusLocationId3_names = censusLocationId3_names;
	}

	public String[] getCensusLocationTownIds() {
		return censusLocationTownIds;
	}

	public void setCensusLocationTownIds(String[] censusLocationTownIds) {
		this.censusLocationTownIds = censusLocationTownIds;
	}

	public String[] getCensusLocationTownId_names() {
		return censusLocationTownId_names;
	}

	public void setCensusLocationTownId_names(String[] censusLocationTownId_names) {
		this.censusLocationTownId_names = censusLocationTownId_names;
	}

	public String[] getCensusLocationVillageIds() {
		return censusLocationVillageIds;
	}

	public void setCensusLocationVillageIds(String[] censusLocationVillageIds) {
		this.censusLocationVillageIds = censusLocationVillageIds;
	}

	public String[] getCensusLocationVillageId_names() {
		return censusLocationVillageId_names;
	}

	public void setCensusLocationVillageId_names(
			String[] censusLocationVillageId_names) {
		this.censusLocationVillageId_names = censusLocationVillageId_names;
	}

	public String[] getCensusLocationGroupIds() {
		return censusLocationGroupIds;
	}

	public void setCensusLocationGroupIds(String[] censusLocationGroupIds) {
		this.censusLocationGroupIds = censusLocationGroupIds;
	}

	public String[] getCensusLocationGroupId_names() {
		return censusLocationGroupId_names;
	}

	public void setCensusLocationGroupId_names(String[] censusLocationGroupId_names) {
		this.censusLocationGroupId_names = censusLocationGroupId_names;
	}

	public String[] getBirthLocationId1s() {
		return birthLocationId1s;
	}

	public void setBirthLocationId1s(String[] birthLocationId1s) {
		this.birthLocationId1s = birthLocationId1s;
	}

	public String[] getBirthLocationId1_names() {
		return birthLocationId1_names;
	}

	public void setBirthLocationId1_names(String[] birthLocationId1_names) {
		this.birthLocationId1_names = birthLocationId1_names;
	}

	public String[] getBirthLocationId2s() {
		return birthLocationId2s;
	}

	public void setBirthLocationId2s(String[] birthLocationId2s) {
		this.birthLocationId2s = birthLocationId2s;
	}

	public String[] getBirthLocationId2_names() {
		return birthLocationId2_names;
	}

	public void setBirthLocationId2_names(String[] birthLocationId2_names) {
		this.birthLocationId2_names = birthLocationId2_names;
	}

	public String[] getBirthLocationId3s() {
		return birthLocationId3s;
	}

	public void setBirthLocationId3s(String[] birthLocationId3s) {
		this.birthLocationId3s = birthLocationId3s;
	}

	public String[] getBirthLocationId3_names() {
		return birthLocationId3_names;
	}

	public void setBirthLocationId3_names(String[] birthLocationId3_names) {
		this.birthLocationId3_names = birthLocationId3_names;
	}

	public String[] getBirthLocationTownIds() {
		return birthLocationTownIds;
	}

	public void setBirthLocationTownIds(String[] birthLocationTownIds) {
		this.birthLocationTownIds = birthLocationTownIds;
	}

	public String[] getBirthLocationTownId_names() {
		return birthLocationTownId_names;
	}

	public void setBirthLocationTownId_names(String[] birthLocationTownId_names) {
		this.birthLocationTownId_names = birthLocationTownId_names;
	}

	public String[] getBirthLocationVillageIds() {
		return birthLocationVillageIds;
	}

	public void setBirthLocationVillageIds(String[] birthLocationVillageIds) {
		this.birthLocationVillageIds = birthLocationVillageIds;
	}

	public String[] getBirthLocationVillageId_names() {
		return birthLocationVillageId_names;
	}

	public void setBirthLocationVillageId_names(
			String[] birthLocationVillageId_names) {
		this.birthLocationVillageId_names = birthLocationVillageId_names;
	}

	public String[] getBirthLocationGroupIds() {
		return birthLocationGroupIds;
	}

	public void setBirthLocationGroupIds(String[] birthLocationGroupIds) {
		this.birthLocationGroupIds = birthLocationGroupIds;
	}

	public String[] getBirthLocationGroupId_names() {
		return birthLocationGroupId_names;
	}

	public void setBirthLocationGroupId_names(String[] birthLocationGroupId_names) {
		this.birthLocationGroupId_names = birthLocationGroupId_names;
	}

	public String[] getMobileTelList() {
		return mobileTelList;
	}

	public void setMobileTelList(String[] mobileTelList) {
		this.mobileTelList = mobileTelList;
	}

	public String getLocationLevel() {
		return locationLevel;
	}

	public void setLocationLevel(String locationLevel) {
		this.locationLevel = locationLevel;
	}

	public String[] getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String[] residentialAddress) {
		this.residentialAddress = residentialAddress;
	}


	public String[] getCommConfigIdTypeList() {
		return commConfigIdTypeList;
	}

	public void setCommConfigIdTypeList(String[] commConfigIdTypeList) {
		this.commConfigIdTypeList = commConfigIdTypeList;
	}

	public String[] getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String[] fullAddress) {
		this.fullAddress = fullAddress;
	}
	
	
}

