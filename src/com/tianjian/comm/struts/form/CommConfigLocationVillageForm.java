package com.tianjian.comm.struts.form;

import org.apache.struts.action.ActionForm;
/**
 * COMM_CONFIG_LOCATION_VILLAGE村字典用ActionForm
 * @author Dzenall
 * @since 2008-9-18
 */
public class CommConfigLocationVillageForm extends ActionForm{

	private static final long serialVersionUID = 1L;

	private String id;
	private String seqNo;
	private String itemCode;
	private String itemName;
	private String inputCode;
	private String comments;
	private String commCltId;
	private String commCltName;
	
	private String commProvinceId;
	private String commCityId;
	private String commCountyId;
	private String commProvinceName;
	private String commCityName;
	private String commCountyName;

	private String verbId;
	private String message;
	private String orderNo;
	private String asc;
	private String idHidden;
	
	//添加字段的form 属性 总人数 总家庭数 联系人的名字 电话
	private String villagerNum;
	private String familyNum;
	private String contactPersonName;
	private String phohe;
	
	//添加字段的form 数组 属性 总人数 总家庭数 联系人的名字 电话
	private String[] villagerNums;
	private String[] familyNums;
	private String[] contactPersonNames;
	private String[] phones;
	
	private String[] commCltIds;
	private String[] commCltNames;
	private String[] commProvinceIds;
	private String[] commCityIds;
	private String[] commCountyIds;
	private String[] commProvinceNames;
	private String[] commCityNames;
	private String[] commCountyNames;

	private String[] idList;
	private String[] seqNoList;
	private String[] itemCodeList;
	private String[] itemNameList;
	private String[] inputCodeList;
	private String[] commentsList;
	private String[] commCltIdList;
	private String[] commCltNameList;
	
	private String[] commProvinceIdList;
	private String[] commCityIdList;
	private String[] commCountyIdList;
	private String[] commProvinceNameList;
	private String[] commCityNameList;
	private String[] commCountyNameList;

	public CommConfigLocationVillageForm(){
		id = "";
		seqNo = "";
		itemCode = "";
		itemName = "";
		inputCode = "";
		comments = "";
		commCltId = "";
		commCltName = "";
		
		commProvinceId = "";
		commCityId = "";
		commCountyId = "";
		commProvinceName = "";
		commCityName = "";
		commCountyName = "";

		verbId = "" ;
		message = "" ;
		orderNo = "" ;
		asc = "";
		idHidden = "" ;	
		
		//添加字段的form 属性 总人数 总家庭数 联系人的名字 电话 构造
		villagerNum = "";
		familyNum = "";
		contactPersonName = "";
		phohe = "";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getInputCode() {
		return inputCode;
	}
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getIdHidden() {
		return idHidden;
	}
	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
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
	public String[] getIdList() {
		return idList;
	}
	public void setIdList(String[] idList) {
		this.idList = idList;
	}
	public String[] getSeqNoList() {
		return seqNoList;
	}
	public void setSeqNoList(String[] seqNoList) {
		this.seqNoList = seqNoList;
	}
	public String[] getItemCodeList() {
		return itemCodeList;
	}
	public void setItemCodeList(String[] itemCodeList) {
		this.itemCodeList = itemCodeList;
	}
	public String[] getItemNameList() {
		return itemNameList;
	}
	public void setItemNameList(String[] itemNameList) {
		this.itemNameList = itemNameList;
	}
	public String[] getInputCodeList() {
		return inputCodeList;
	}
	public void setInputCodeList(String[] inputCodeList) {
		this.inputCodeList = inputCodeList;
	}
	public String[] getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(String[] commentsList) {
		this.commentsList = commentsList;
	}
	public String[] getCommCltIdList() {
		return commCltIdList;
	}
	public void setCommCltIdList(String[] commCltIdList) {
		this.commCltIdList = commCltIdList;
	}
	public String getCommProvinceId() {
		return commProvinceId;
	}
	public void setCommProvinceId(String commProvinceId) {
		this.commProvinceId = commProvinceId;
	}
	public String getCommCityId() {
		return commCityId;
	}
	public void setCommCityId(String commCityId) {
		this.commCityId = commCityId;
	}
	public String getCommCountyId() {
		return commCountyId;
	}
	public void setCommCountyId(String commCountyId) {
		this.commCountyId = commCountyId;
	}
	public String getCommProvinceName() {
		return commProvinceName;
	}
	public void setCommProvinceName(String commProvinceName) {
		this.commProvinceName = commProvinceName;
	}
	public String getCommCityName() {
		return commCityName;
	}
	public void setCommCityName(String commCityName) {
		this.commCityName = commCityName;
	}
	public String getCommCountyName() {
		return commCountyName;
	}
	public void setCommCountyName(String commCountyName) {
		this.commCountyName = commCountyName;
	}
	public String[] getCommProvinceIds() {
		return commProvinceIds;
	}
	public void setCommProvinceIds(String[] commProvinceIds) {
		this.commProvinceIds = commProvinceIds;
	}
	public String[] getCommCityIds() {
		return commCityIds;
	}
	public void setCommCityIds(String[] commCityIds) {
		this.commCityIds = commCityIds;
	}
	public String[] getCommCountyIds() {
		return commCountyIds;
	}
	public void setCommCountyIds(String[] commCountyIds) {
		this.commCountyIds = commCountyIds;
	}
	public String[] getCommProvinceNames() {
		return commProvinceNames;
	}
	public void setCommProvinceNames(String[] commProvinceNames) {
		this.commProvinceNames = commProvinceNames;
	}
	public String[] getCommCityNames() {
		return commCityNames;
	}
	public void setCommCityNames(String[] commCityNames) {
		this.commCityNames = commCityNames;
	}
	public String[] getCommCountyNames() {
		return commCountyNames;
	}
	public void setCommCountyNames(String[] commCountyNames) {
		this.commCountyNames = commCountyNames;
	}
	public String[] getCommCltNameList() {
		return commCltNameList;
	}
	public void setCommCltNameList(String[] commCltNameList) {
		this.commCltNameList = commCltNameList;
	}
	public String[] getCommProvinceIdList() {
		return commProvinceIdList;
	}
	public void setCommProvinceIdList(String[] commProvinceIdList) {
		this.commProvinceIdList = commProvinceIdList;
	}
	public String[] getCommCityIdList() {
		return commCityIdList;
	}
	public void setCommCityIdList(String[] commCityIdList) {
		this.commCityIdList = commCityIdList;
	}
	public String[] getCommCountyIdList() {
		return commCountyIdList;
	}
	public void setCommCountyIdList(String[] commCountyIdList) {
		this.commCountyIdList = commCountyIdList;
	}
	public String[] getCommProvinceNameList() {
		return commProvinceNameList;
	}
	public void setCommProvinceNameList(String[] commProvinceNameList) {
		this.commProvinceNameList = commProvinceNameList;
	}
	public String[] getCommCityNameList() {
		return commCityNameList;
	}
	public void setCommCityNameList(String[] commCityNameList) {
		this.commCityNameList = commCityNameList;
	}
	public String[] getCommCountyNameList() {
		return commCountyNameList;
	}
	public void setCommCountyNameList(String[] commCountyNameList) {
		this.commCountyNameList = commCountyNameList;
	}
	 
	 
	public String getVillagerNum() {
		return villagerNum;
	}
	public void setVillagerNum(String villagerNum) {
		this.villagerNum = villagerNum;
	}
	public String getFamilyNum() {
		return familyNum;
	}
	public void setFamilyNum(String familyNum) {
		this.familyNum = familyNum;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getPhohe() {
		return phohe;
	}
	public void setPhohe(String phohe) {
		this.phohe = phohe;
	}
	public String[] getVillagerNums() {
		return villagerNums;
	}
	public void setVillagerNums(String[] villagerNums) {
		this.villagerNums = villagerNums;
	}
	public String[] getFamilyNums() {
		return familyNums;
	}
	public void setFamilyNums(String[] familyNums) {
		this.familyNums = familyNums;
	}
	public String[] getContactPersonNames() {
		return contactPersonNames;
	}
	public void setContactPersonNames(String[] contactPersonNames) {
		this.contactPersonNames = contactPersonNames;
	}
	public String[] getPhones() {
		return phones;
	}
	public void setPhones(String[] phones) {
		this.phones = phones;
	}
}