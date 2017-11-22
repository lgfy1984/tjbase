package com.tianjian.comm.struts.form;

import org.apache.struts.action.ActionForm;

public class UserSelectForm extends ActionForm {
	private static final long serialVersionUID = -6840390011168550885L;
	
	//----------
	private String selectFlag;//1query.居民健康档案查询 3manage.居民健康档案管理 2check.体检登记
	private String pmiQuery;
	private String personNameQuery;
	private String personInputCodeQuery;
	private String idNoQuery;
	private String fmiQuery;
	private String nameOfHouseholderQuery;
	private String provinceQuery;
	private String cityQuery;
	private String countyQuery;
	private String townQuery;
	private String villageQuery;
	private String groupQuery;
	private String displayFlag;
	
	//----------
	private String selecttype;
	private String flag;
	private String page;
	
	private String radio;
	
	private String content;
	
	private String securityUserBaseinfoId;
	private String cihaFamilyBaseinfoId;
	
	private String pmi;
	private String name;
	private String personInputCode;
	
	private String idNo;
	private String fmi;
	private String nameOfHouseholder;
	
	private String cmi;
	private String communityName;
	private String communityInputCode;
	
	private String province;
	private String city;
	private String county;
	private String town;
	private String village;
	private String group;
	//--------
	
	
	//处理省市县乡村组
	private String commProvinceId;//����ʡ所属省Id
	private String commProvinceName;//所属省名称
	private String commCityId;//������所属市Id
	private String commCityName;//所属市名称
	private String commCountyId;//������所属县Id
	private String commCountyName;//所属县名称
	private String commCltId;//��������所属乡镇Id
	private String commCltName;//所属乡镇名称
	private String commClvId;//所属村Id
	private String commClvName;//所属村名称
	private String commGroupId;//所属组Id
	private String commGroupName;//所属组名称
	private String cihaCommunityBaseinfoName;
	
	//填充高级查询时用？
	private String[] commProvinceIds;//����ʡ所属省	
	private String[] commProvinceNames;
	private String[] commCityIds;//������所属市
	private String[] commCityNames;
	private String[] commCountyIds;//������所属县
	private String[] commCountyNames;
	private String[] commCltIds;//��������所属乡镇
	private String[] commCltNames;
	private String[] commClvIds;//所属村
	private String[] commClvNames;
	private String[] commGroupIds;//所属组
	private String[] commGroupNames;
	
	//list页面列表使用
	private String[] commProvinceIdList;//����ʡ所属省
	private String[] commProvinceNameList;
	private String[] commCityIdList;//������所属市
	private String[] commCityNameList;
	private String[] commCountyIdList;//������所属县
	private String[] commCountyNameList;
	private String[] commCltIdList;//��������所属乡镇
	private String[] commCltNameList;
	private String[] commClvIdList;//所属村
	private String[] commClvNameList;
	private String[] commGroupIdList;//所属组
	private String[] commGroupNameList;
	
	
	private String[] pmiList;//个人编号
	private String[] personNameList;//居民姓名	
	private String[] personInputCodeList;//居民拼音码
	private String[] idNoList;//身份证	
	private String[] fmiList;//家庭编号
	private String[] nameOfHouseholderList;//户主姓名
	private String[] birthdayList;
	private String[] ageList;
	private String[] relationTelList;
	private String[] familyPersonRelationList;
	
	private String[] securityUserBaseinfoIdList;//
	private String[] sexIdList;
	
	
	
	public UserSelectForm(){
		selectFlag = "";//1query.居民健康档案查询 2manage.居民健康档案管理 3check.体检登记
		pmiQuery = "";
		personNameQuery = "";
		personInputCodeQuery = "";
		idNoQuery = "";
		fmiQuery = "";
		nameOfHouseholderQuery = "";
		provinceQuery = "";
		cityQuery = "";
		countyQuery = "";
		townQuery = "";
		villageQuery = "";
		groupQuery = "";
		displayFlag = "";
		
		//----------
		selecttype = "";
		flag = "";
		page = "";
		
		radio = "";
		
		content = "";
		
		securityUserBaseinfoId = "";
		cihaFamilyBaseinfoId = "";
		
		pmi = "";
		name = "";
		personInputCode = "";
		
		idNo = "";
		fmi = "";
		nameOfHouseholder = "";
		
		cmi = "";
		communityName = "";
		communityInputCode = "";
		
		province = "";
		city = "";
		county = "";
		town = "";
		village = "";
		group = "";
		//--------
		
		
		//处理省市县乡村组
		commProvinceId = "";//����ʡ所属省Id
		commProvinceName = "";//所属省名称
		commCityId = "";//������所属市Id
		commCityName = "";//所属市名称
		commCountyId = "";//������所属县Id
		commCountyName = "";//所属县名称
		commCltId = "";//��������所属乡镇Id
		commCltName = "";//所属乡镇名称
		commClvId = "";//所属村Id
		commClvName = "";//所属村名称
		commGroupId = "";//所属组Id
		commGroupName = "";//所属组名称
		cihaCommunityBaseinfoName = "";
	}

	public String getSelecttype() {
		return selecttype;
	}

	public void setSelecttype(String selecttype) {
		this.selecttype = selecttype;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSecurityUserBaseinfoId() {
		return securityUserBaseinfoId;
	}

	public void setSecurityUserBaseinfoId(String securityUserBaseinfoId) {
		this.securityUserBaseinfoId = securityUserBaseinfoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getCihaFamilyBaseinfoId() {
		return cihaFamilyBaseinfoId;
	}

	public void setCihaFamilyBaseinfoId(String cihaFamilyBaseinfoId) {
		this.cihaFamilyBaseinfoId = cihaFamilyBaseinfoId;
	}

	public String getFmi() {
		return fmi;
	}

	public void setFmi(String fmi) {
		this.fmi = fmi;
	}

	public String getPmi() {
		return pmi;
	}

	public void setPmi(String pmi) {
		this.pmi = pmi;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getPmiQuery() {
		return pmiQuery;
	}

	public void setPmiQuery(String pmiQuery) {
		this.pmiQuery = pmiQuery;
	}

	public String getPersonNameQuery() {
		return personNameQuery;
	}

	public void setPersonNameQuery(String personNameQuery) {
		this.personNameQuery = personNameQuery;
	}

	public String getPersonInputCodeQuery() {
		return personInputCodeQuery;
	}

	public void setPersonInputCodeQuery(String personInputCodeQuery) {
		this.personInputCodeQuery = personInputCodeQuery;
	}

	public String getIdNoQuery() {
		return idNoQuery;
	}

	public void setIdNoQuery(String idNoQuery) {
		this.idNoQuery = idNoQuery;
	}

	public String getFmiQuery() {
		return fmiQuery;
	}

	public void setFmiQuery(String fmiQuery) {
		this.fmiQuery = fmiQuery;
	}

	public String getNameOfHouseholderQuery() {
		return nameOfHouseholderQuery;
	}

	public void setNameOfHouseholderQuery(String nameOfHouseholderQuery) {
		this.nameOfHouseholderQuery = nameOfHouseholderQuery;
	}

	public String getProvinceQuery() {
		return provinceQuery;
	}

	public void setProvinceQuery(String provinceQuery) {
		this.provinceQuery = provinceQuery;
	}

	public String getCityQuery() {
		return cityQuery;
	}

	public void setCityQuery(String cityQuery) {
		this.cityQuery = cityQuery;
	}

	public String getCountyQuery() {
		return countyQuery;
	}

	public void setCountyQuery(String countyQuery) {
		this.countyQuery = countyQuery;
	}

	public String getTownQuery() {
		return townQuery;
	}

	public void setTownQuery(String townQuery) {
		this.townQuery = townQuery;
	}

	public String getVillageQuery() {
		return villageQuery;
	}

	public void setVillageQuery(String villageQuery) {
		this.villageQuery = villageQuery;
	}

	public String getGroupQuery() {
		return groupQuery;
	}

	public void setGroupQuery(String groupQuery) {
		this.groupQuery = groupQuery;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCommProvinceId() {
		return commProvinceId;
	}

	public void setCommProvinceId(String commProvinceId) {
		this.commProvinceId = commProvinceId;
	}

	public String getCommProvinceName() {
		return commProvinceName;
	}

	public void setCommProvinceName(String commProvinceName) {
		this.commProvinceName = commProvinceName;
	}

	public String getCommCityId() {
		return commCityId;
	}

	public void setCommCityId(String commCityId) {
		this.commCityId = commCityId;
	}

	public String getCommCityName() {
		return commCityName;
	}

	public void setCommCityName(String commCityName) {
		this.commCityName = commCityName;
	}

	public String getCommCountyId() {
		return commCountyId;
	}

	public void setCommCountyId(String commCountyId) {
		this.commCountyId = commCountyId;
	}

	public String getCommCountyName() {
		return commCountyName;
	}

	public void setCommCountyName(String commCountyName) {
		this.commCountyName = commCountyName;
	}

	public String getCommCltId() {
		return commCltId;
	}

	public void setCommCltId(String commCltId) {
		this.commCltId = commCltId;
	}

	public String getCommCltName() {
		return commCltName;
	}

	public void setCommCltName(String commCltName) {
		this.commCltName = commCltName;
	}

	public String getCommClvId() {
		return commClvId;
	}

	public void setCommClvId(String commClvId) {
		this.commClvId = commClvId;
	}

	public String getCommClvName() {
		return commClvName;
	}

	public void setCommClvName(String commClvName) {
		this.commClvName = commClvName;
	}

	public String getCommGroupId() {
		return commGroupId;
	}

	public void setCommGroupId(String commGroupId) {
		this.commGroupId = commGroupId;
	}

	public String getCommGroupName() {
		return commGroupName;
	}

	public void setCommGroupName(String commGroupName) {
		this.commGroupName = commGroupName;
	}

	public String getCihaCommunityBaseinfoName() {
		return cihaCommunityBaseinfoName;
	}

	public void setCihaCommunityBaseinfoName(String cihaCommunityBaseinfoName) {
		this.cihaCommunityBaseinfoName = cihaCommunityBaseinfoName;
	}

	public String[] getCommProvinceIds() {
		return commProvinceIds;
	}

	public void setCommProvinceIds(String[] commProvinceIds) {
		this.commProvinceIds = commProvinceIds;
	}

	public String[] getCommProvinceNames() {
		return commProvinceNames;
	}

	public void setCommProvinceNames(String[] commProvinceNames) {
		this.commProvinceNames = commProvinceNames;
	}

	public String[] getCommCityIds() {
		return commCityIds;
	}

	public void setCommCityIds(String[] commCityIds) {
		this.commCityIds = commCityIds;
	}

	public String[] getCommCityNames() {
		return commCityNames;
	}

	public void setCommCityNames(String[] commCityNames) {
		this.commCityNames = commCityNames;
	}

	public String[] getCommCountyIds() {
		return commCountyIds;
	}

	public void setCommCountyIds(String[] commCountyIds) {
		this.commCountyIds = commCountyIds;
	}

	public String[] getCommCountyNames() {
		return commCountyNames;
	}

	public void setCommCountyNames(String[] commCountyNames) {
		this.commCountyNames = commCountyNames;
	}

	public String[] getCommCltIds() {
		return commCltIds;
	}

	public void setCommCltIds(String[] commCltIds) {
		this.commCltIds = commCltIds;
	}

	public String[] getCommCltNames() {
		return commCltNames;
	}

	public void setCommCltNames(String[] commCltNames) {
		this.commCltNames = commCltNames;
	}

	public String[] getCommClvIds() {
		return commClvIds;
	}

	public void setCommClvIds(String[] commClvIds) {
		this.commClvIds = commClvIds;
	}

	public String[] getCommClvNames() {
		return commClvNames;
	}

	public void setCommClvNames(String[] commClvNames) {
		this.commClvNames = commClvNames;
	}

	public String[] getCommGroupIds() {
		return commGroupIds;
	}

	public void setCommGroupIds(String[] commGroupIds) {
		this.commGroupIds = commGroupIds;
	}

	public String[] getCommGroupNames() {
		return commGroupNames;
	}

	public void setCommGroupNames(String[] commGroupNames) {
		this.commGroupNames = commGroupNames;
	}

	public String[] getCommProvinceIdList() {
		return commProvinceIdList;
	}

	public void setCommProvinceIdList(String[] commProvinceIdList) {
		this.commProvinceIdList = commProvinceIdList;
	}

	public String[] getCommProvinceNameList() {
		return commProvinceNameList;
	}

	public void setCommProvinceNameList(String[] commProvinceNameList) {
		this.commProvinceNameList = commProvinceNameList;
	}

	public String[] getCommCityIdList() {
		return commCityIdList;
	}

	public void setCommCityIdList(String[] commCityIdList) {
		this.commCityIdList = commCityIdList;
	}

	public String[] getCommCityNameList() {
		return commCityNameList;
	}

	public void setCommCityNameList(String[] commCityNameList) {
		this.commCityNameList = commCityNameList;
	}

	public String[] getCommCountyIdList() {
		return commCountyIdList;
	}

	public void setCommCountyIdList(String[] commCountyIdList) {
		this.commCountyIdList = commCountyIdList;
	}

	public String[] getCommCountyNameList() {
		return commCountyNameList;
	}

	public void setCommCountyNameList(String[] commCountyNameList) {
		this.commCountyNameList = commCountyNameList;
	}

	public String[] getCommCltIdList() {
		return commCltIdList;
	}

	public void setCommCltIdList(String[] commCltIdList) {
		this.commCltIdList = commCltIdList;
	}

	public String[] getCommCltNameList() {
		return commCltNameList;
	}

	public void setCommCltNameList(String[] commCltNameList) {
		this.commCltNameList = commCltNameList;
	}

	public String[] getCommClvIdList() {
		return commClvIdList;
	}

	public void setCommClvIdList(String[] commClvIdList) {
		this.commClvIdList = commClvIdList;
	}

	public String[] getCommClvNameList() {
		return commClvNameList;
	}

	public void setCommClvNameList(String[] commClvNameList) {
		this.commClvNameList = commClvNameList;
	}

	public String[] getCommGroupIdList() {
		return commGroupIdList;
	}

	public void setCommGroupIdList(String[] commGroupIdList) {
		this.commGroupIdList = commGroupIdList;
	}

	public String[] getCommGroupNameList() {
		return commGroupNameList;
	}

	public void setCommGroupNameList(String[] commGroupNameList) {
		this.commGroupNameList = commGroupNameList;
	}

	public String getPersonInputCode() {
		return personInputCode;
	}

	public void setPersonInputCode(String personInputCode) {
		this.personInputCode = personInputCode;
	}

	public String getNameOfHouseholder() {
		return nameOfHouseholder;
	}

	public void setNameOfHouseholder(String nameOfHouseholder) {
		this.nameOfHouseholder = nameOfHouseholder;
	}

	public String getCmi() {
		return cmi;
	}

	public void setCmi(String cmi) {
		this.cmi = cmi;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getCommunityInputCode() {
		return communityInputCode;
	}

	public void setCommunityInputCode(String communityInputCode) {
		this.communityInputCode = communityInputCode;
	}

	public String[] getPmiList() {
		return pmiList;
	}

	public void setPmiList(String[] pmiList) {
		this.pmiList = pmiList;
	}

	public String[] getPersonNameList() {
		return personNameList;
	}

	public void setPersonNameList(String[] personNameList) {
		this.personNameList = personNameList;
	}

	public String[] getPersonInputCodeList() {
		return personInputCodeList;
	}

	public void setPersonInputCodeList(String[] personInputCodeList) {
		this.personInputCodeList = personInputCodeList;
	}

	public String[] getIdNoList() {
		return idNoList;
	}

	public void setIdNoList(String[] idNoList) {
		this.idNoList = idNoList;
	}

	public String[] getFmiList() {
		return fmiList;
	}

	public void setFmiList(String[] fmiList) {
		this.fmiList = fmiList;
	}

	public String[] getNameOfHouseholderList() {
		return nameOfHouseholderList;
	}

	public void setNameOfHouseholderList(String[] nameOfHouseholderList) {
		this.nameOfHouseholderList = nameOfHouseholderList;
	}

	public String[] getSecurityUserBaseinfoIdList() {
		return securityUserBaseinfoIdList;
	}

	public void setSecurityUserBaseinfoIdList(String[] securityUserBaseinfoIdList) {
		this.securityUserBaseinfoIdList = securityUserBaseinfoIdList;
	}

	public String[] getSexIdList() {
		return sexIdList;
	}

	public void setSexIdList(String[] sexIdList) {
		this.sexIdList = sexIdList;
	}

	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	public String[] getBirthdayList() {
		return birthdayList;
	}

	public void setBirthdayList(String[] birthdayList) {
		this.birthdayList = birthdayList;
	}

	public String[] getAgeList() {
		return ageList;
	}

	public void setAgeList(String[] ageList) {
		this.ageList = ageList;
	}

	public String[] getRelationTelList() {
		return relationTelList;
	}

	public void setRelationTelList(String[] relationTelList) {
		this.relationTelList = relationTelList;
	}

	public String[] getFamilyPersonRelationList() {
		return familyPersonRelationList;
	}

	public void setFamilyPersonRelationList(String[] familyPersonRelationList) {
		this.familyPersonRelationList = familyPersonRelationList;
	}

}
