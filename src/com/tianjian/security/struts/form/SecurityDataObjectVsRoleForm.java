package com.tianjian.security.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class SecurityDataObjectVsRoleForm extends ActionForm {
	private String id;
	private String idid;
	private String sdotId;
	private String sdoValue;
	private String sdoValueName="";
	private String securityStaffBaseInfo;
	private String staffCode="";
	private String hspConfigBaseinfoName = "";
	private String hspConfigBaseinfoId = "";
	
	private String securityStaffBaseInfoName="";
	private String sdoIdName="";
	private String inputCode="";
	
	private String parentId;
	private String parentName;
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
	
	private String verbId;
	private String message;
	private String orderNo;
	private String asc;
	private String itemCodeHidden; 
	private List<?> securityStaffBaseInfoList=new ArrayList();
	private List<?> sdotIdList=new ArrayList();
	private List<?> sdoValueList=new ArrayList();
	
	private String[] staffCodes;
	private String[] hspConfigs;
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
	
	private String[] selectId;
	private String[] ids;
	private String[] sdotIds;
	private String[] sdoValues;
	private String[] checks;
	private String[] securityStaffBaseInfos;
	public SecurityDataObjectVsRoleForm() {
		idid="";
		id = "";
		sdotId = "";
		sdoValue = "";
		securityStaffBaseInfo = "";
		commConfigLocationId1="";
		commConfigLocationId2="";
		commConfigLocationId3="";
		commConfigLocationTownId="";
		commConfigLocationVillageId="";

		verbId = "";
		message = "";
		orderNo = "";
		asc = "";
		itemCodeHidden = "";

		ids = null;
		sdotIds = null;
		sdoValues = null;
		securityStaffBaseInfos = null;
	}
	public SecurityDataObjectVsRoleForm(String id, String sdotId,
			String sdoValue, String securityStaffBaseInfo, String verbId,
			String message, String orderNo, String asc, String itemCodeHidden,
			String[] ids, String[] sdotIds, String[] sdoValues,
			String[] securityStaffBaseInfos) {
		this.id = id;
		this.sdotId = sdotId;
		this.sdoValue = sdoValue;
		this.securityStaffBaseInfo = securityStaffBaseInfo;
		this.verbId = verbId;
		this.message = message;
		this.orderNo = orderNo;
		this.asc = asc;
		this.itemCodeHidden = itemCodeHidden;
		this.ids = ids;
		this.sdotIds = sdotIds;
		this.sdoValues = sdoValues;
		this.securityStaffBaseInfos = securityStaffBaseInfos;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSdotId() {
		return sdotId;
	}
	public void setSdotId(String sdotId) {
		this.sdotId = sdotId;
	}
	public String getSdoValue() {
		return sdoValue;
	}
	public void setSdoValue(String sdoValue) {
		this.sdoValue = sdoValue;
	}
	public String getSecurityStaffBaseInfo() {
		return securityStaffBaseInfo;
	}
	public void setSecurityStaffBaseInfo(String securityStaffBaseInfo) {
		this.securityStaffBaseInfo = securityStaffBaseInfo;
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
	public String getItemCodeHidden() {
		return itemCodeHidden;
	}
	public void setItemCodeHidden(String itemCodeHidden) {
		this.itemCodeHidden = itemCodeHidden;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String[] getSdotIds() {
		return sdotIds;
	}
	public void setSdotIds(String[] sdotIds) {
		this.sdotIds = sdotIds;
	}
	public String[] getSdoValues() {
		return sdoValues;
	}
	public void setSdoValues(String[] sdoValues) {
		this.sdoValues = sdoValues;
	}
	public String[] getSecurityStaffBaseInfos() {
		return securityStaffBaseInfos;
	}
	public void setSecurityStaffBaseInfos(String[] securityStaffBaseInfos) {
		this.securityStaffBaseInfos = securityStaffBaseInfos;
	}
	public String getSecurityStaffBaseInfoName() {
		return securityStaffBaseInfoName;
	}
	public void setSecurityStaffBaseInfoName(String securityStaffBaseInfoName) {
		this.securityStaffBaseInfoName = securityStaffBaseInfoName;
	}
	public String getSdoIdName() {
		return sdoIdName;
	}
	public void setSdoIdName(String sdoIdName) {
		this.sdoIdName = sdoIdName;
	}
	public String getIdid() {
		return idid;
	}
	public void setIdid(String idid) {
		this.idid = idid;
	}
	public List<?> getSecurityStaffBaseInfoList() {
		return securityStaffBaseInfoList;
	}
	public void setSecurityStaffBaseInfoList(List<?> securityStaffBaseInfoList) {
		this.securityStaffBaseInfoList = securityStaffBaseInfoList;
	}
	public List<?> getSdotIdList() {
		return sdotIdList;
	}
	public void setSdotIdList(List<?> sdotIdList) {
		this.sdotIdList = sdotIdList;
	}
	public List<?> getSdoValueList() {
		return sdoValueList;
	}
	public void setSdoValueList(List<?> sdoValueList) {
		this.sdoValueList = sdoValueList;
	}
	public String getSdoValueName() {
		return sdoValueName;
	}
	public void setSdoValueName(String sdoValueName) {
		this.sdoValueName = sdoValueName;
	}
	public String getInputCode() {
		return inputCode;
	}
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
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
	public String[] getChecks() {
		return checks;
	}
	public void setChecks(String[] checks) {
		this.checks = checks;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String[] getSelectId() {
		return selectId;
	}
	public void setSelectId(String[] selectId) {
		this.selectId = selectId;
	}
	public String[] getStaffCodes() {
		return staffCodes;
	}
	public void setStaffCodes(String[] staffCodes) {
		this.staffCodes = staffCodes;
	}
	public String[] getHspConfigs() {
		return hspConfigs;
	}
	public void setHspConfigs(String[] hspConfigs) {
		this.hspConfigs = hspConfigs;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getHspConfigBaseinfoName() {
		return hspConfigBaseinfoName;
	}
	public void setHspConfigBaseinfoName(String hspConfigBaseinfoName) {
		this.hspConfigBaseinfoName = hspConfigBaseinfoName;
	}
	public String getHspConfigBaseinfoId() {
		return hspConfigBaseinfoId;
	}
	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}
}
