package com.tianjian.comm.struts.form;

import org.apache.struts.action.ActionForm;
/**
 * COMM_CONFIG_LOCATION_TOWN乡镇字典用ActionForm
 * @author Dzenall
 * @since 2008-9-17
 */
public class CommConfigLocationTownForm extends ActionForm{

	private static final long serialVersionUID = 1L;

	/*
	 * 搜索项：输入码、代码、名称
	 * 列表项：代码itemCode、名称itemName、输入码inputCode、所属地commConfigLocationName
	 */
	private String id;
	private String itemCode;//代码，是检测唯一性的唯一标识
	private String seqNo;//数据库中是Long类型，序号
	private String itemName;//描述
	private String inputCode;//输入码
	private String commConfigLocationId1;//所属省代码
	private String commConfigLocationName1;//所属省名称
	private String commConfigLocationId2;//所属市代码
	private String commConfigLocationName2;//所属市名称
	private String commConfigLocationId3;//所属县代码，数据库中直接记录这个字段
	private String commConfigLocationName3;//所属县名称
	private String comments;//备注

	private String verbId;
	private String message;
	private String orderNo;
	private String asc;
	private String idHidden;
	private String ctrlNo;//为1时将显示全部Location，为0时只显示县一级

	private String[] commConfigLocationIds1;
	private String[] commConfigLocationNames1;
	private String[] commConfigLocationIds2;
	private String[] commConfigLocationNames2;
	private String[] commConfigLocationIds3;
	private String[] commConfigLocationNames3;

	private String[] idList;
	private String[] itemCodeList;
	private String[] seqNoList;
	private String[] itemNameList;
	private String[] inputCodeList;
	/*列表显示时，可以直接显示最近的县一级，也可以显示全部（从省到县），其控制字段由ctrlNo控制*/
	private String[] commConfigLocationIdList1;
	private String[] commConfigLocationNameList1;
	private String[] commConfigLocationIdList2;
	private String[] commConfigLocationNameList2;
	private String[] commConfigLocationIdList3;
	private String[] commConfigLocationNameList3;
	private String[] commentsList;

	public CommConfigLocationTownForm(){
		id = "";
		itemCode = "";
		seqNo = "";
		itemName = "";
		inputCode = "";
		commConfigLocationId1 = "";
		commConfigLocationName1 = "";
		commConfigLocationId2 = "";
		commConfigLocationName2 = "";
		commConfigLocationId3 = "";
		commConfigLocationName3 = "";
		comments = "";

		verbId = "";
		message = "";
		orderNo = "";
		asc = "";
		idHidden = "";
		ctrlNo = "";
	}

	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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
	public String getCommConfigLocationId1() {
		return commConfigLocationId1;
	}
	public void setCommConfigLocationId1(String commConfigLocationId1) {
		this.commConfigLocationId1 = commConfigLocationId1;
	}
	public String getCommConfigLocationName1() {
		return commConfigLocationName1;
	}
	public void setCommConfigLocationName1(String commConfigLocationName1) {
		this.commConfigLocationName1 = commConfigLocationName1;
	}
	public String getCommConfigLocationId2() {
		return commConfigLocationId2;
	}
	public void setCommConfigLocationId2(String commConfigLocationId2) {
		this.commConfigLocationId2 = commConfigLocationId2;
	}
	public String getCommConfigLocationName2() {
		return commConfigLocationName2;
	}
	public void setCommConfigLocationName2(String commConfigLocationName2) {
		this.commConfigLocationName2 = commConfigLocationName2;
	}
	public String getCommConfigLocationId3() {
		return commConfigLocationId3;
	}
	public void setCommConfigLocationId3(String commConfigLocationId3) {
		this.commConfigLocationId3 = commConfigLocationId3;
	}
	public String getCommConfigLocationName3() {
		return commConfigLocationName3;
	}
	public void setCommConfigLocationName3(String commConfigLocationName3) {
		this.commConfigLocationName3 = commConfigLocationName3;
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
	public String getIdHidden() {
		return idHidden;
	}
	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
	}
	public String getCtrlNo() {
		return ctrlNo;
	}
	public void setCtrlNo(String ctrlNo) {
		this.ctrlNo = ctrlNo;
	}
	public String[] getCommConfigLocationIds1() {
		return commConfigLocationIds1;
	}
	public void setCommConfigLocationIds1(String[] commConfigLocationIds1) {
		this.commConfigLocationIds1 = commConfigLocationIds1;
	}
	public String[] getCommConfigLocationNames1() {
		return commConfigLocationNames1;
	}
	public void setCommConfigLocationNames1(String[] commConfigLocationNames1) {
		this.commConfigLocationNames1 = commConfigLocationNames1;
	}
	public String[] getCommConfigLocationIds2() {
		return commConfigLocationIds2;
	}
	public void setCommConfigLocationIds2(String[] commConfigLocationIds2) {
		this.commConfigLocationIds2 = commConfigLocationIds2;
	}
	public String[] getCommConfigLocationNames2() {
		return commConfigLocationNames2;
	}
	public void setCommConfigLocationNames2(String[] commConfigLocationNames2) {
		this.commConfigLocationNames2 = commConfigLocationNames2;
	}
	public String[] getCommConfigLocationIds3() {
		return commConfigLocationIds3;
	}
	public void setCommConfigLocationIds3(String[] commConfigLocationIds3) {
		this.commConfigLocationIds3 = commConfigLocationIds3;
	}
	public String[] getCommConfigLocationNames3() {
		return commConfigLocationNames3;
	}
	public void setCommConfigLocationNames3(String[] commConfigLocationNames3) {
		this.commConfigLocationNames3 = commConfigLocationNames3;
	}
	public String[] getItemCodeList() {
		return itemCodeList;
	}
	public void setItemCodeList(String[] itemCodeList) {
		this.itemCodeList = itemCodeList;
	}
	public String[] getSeqNoList() {
		return seqNoList;
	}
	public void setSeqNoList(String[] seqNoList) {
		this.seqNoList = seqNoList;
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
	public String[] getCommConfigLocationIdList1() {
		return commConfigLocationIdList1;
	}
	public void setCommConfigLocationIdList1(String[] commConfigLocationIdList1) {
		this.commConfigLocationIdList1 = commConfigLocationIdList1;
	}
	public String[] getCommConfigLocationNameList1() {
		return commConfigLocationNameList1;
	}
	public void setCommConfigLocationNameList1(String[] commConfigLocationNameList1) {
		this.commConfigLocationNameList1 = commConfigLocationNameList1;
	}
	public String[] getCommConfigLocationIdList2() {
		return commConfigLocationIdList2;
	}
	public void setCommConfigLocationIdList2(String[] commConfigLocationIdList2) {
		this.commConfigLocationIdList2 = commConfigLocationIdList2;
	}
	public String[] getCommConfigLocationNameList2() {
		return commConfigLocationNameList2;
	}
	public void setCommConfigLocationNameList2(String[] commConfigLocationNameList2) {
		this.commConfigLocationNameList2 = commConfigLocationNameList2;
	}
	public String[] getCommConfigLocationIdList3() {
		return commConfigLocationIdList3;
	}
	public void setCommConfigLocationIdList3(String[] commConfigLocationIdList3) {
		this.commConfigLocationIdList3 = commConfigLocationIdList3;
	}
	public String[] getCommConfigLocationNameList3() {
		return commConfigLocationNameList3;
	}
	public void setCommConfigLocationNameList3(String[] commConfigLocationNameList3) {
		this.commConfigLocationNameList3 = commConfigLocationNameList3;
	}
	public String[] getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(String[] commentsList) {
		this.commentsList = commentsList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}
}
