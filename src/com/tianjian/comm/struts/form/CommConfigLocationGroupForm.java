package com.tianjian.comm.struts.form;

import org.apache.struts.action.ActionForm;

/**
 * COMM_CONFIG_LOCATION_GROUP居民组字典用���ActionForm
 * @author Dzenall
 * @since 2008-9-18
 */
public class CommConfigLocationGroupForm extends ActionForm{

	private static final long serialVersionUID = 1L;

	private String id;
	private String seqNo;//数据库中原来是Long类型
	private String itemCode;
	private String itemName;
	private String inputCode;
	private String comments;
	private String commClvId;//所属村Id
	private String commClvName;//所属村名称

	private String commProvinceId;//����ʡ所属省Id
	private String commProvinceName;//所属省名称
	private String commCityId;//������所属市Id
	private String commCityName;//所属市名称
	private String commCountyId;//������所属县Id
	private String commCountyName;//所属县名称
	private String commCltId;//��������所属乡镇Id
	private String commCltName;//所属乡镇名称

	private String verbId;
	private String message;
	private String orderNo;
	private String asc;
	private String idHidden;

	private String[] commClvIds;
	private String[] commClvNames;	
	private String[] commCltIds;
	private String[] commCltNames;
	private String[] commCountyIds;
	private String[] commCountyNames;
	private String[] commCityIds;
	private String[] commCityNames;
	private String[] commProvinceIds;	
	private String[] commProvinceNames;

	private String[] idList;
	private String[] seqNoList;
	private String[] itemCodeList;
	private String[] itemNameList;
	private String[] inputCodeList;
	private String[] commentsList;
	private String[] commClvIdList;
	private String[] commClvNameList;

	private String[] commProvinceIdList;
	private String[] commProvinceNameList;
	private String[] commCityIdList;
	private String[] commCityNameList;
	private String[] commCountyIdList;
	private String[] commCountyNameList;
	private String[] commCltIdList;
	private String[] commCltNameList;

	public CommConfigLocationGroupForm(){
		id = "";
		seqNo = "";
		itemCode = "";
		itemName = "";
		inputCode = "";
		comments = "";
		commClvId = "";
		commClvName = "";

		commProvinceId = "";
		commProvinceName = "";
		commCityId = "";
		commCityName = "";
		commCountyId = "";
		commCountyName = "";
		commCltId = "";
		commCltName = "";

		verbId = "";
		message = "";
		orderNo = "";
		asc = "";
		idHidden = "";
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


}
