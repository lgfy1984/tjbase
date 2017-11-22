package com.tianjian.comm.struts.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class CommConfigHospitalTypeForm extends ActionForm implements Serializable{

	/**
	 * 卫生机构类别
	 */
	private static final long serialVersionUID = 1L;
	/**代码*/
	private String itemCode;
	/**描述*/
	private String itemName;
	/**输入码*/
	private String inputCode;
	/**级别*/
	private String levelFlag;
	/**所属代码*/
	private String parentItemCode;
	/**备注*/
	private String comments;
	/**序号*/
	private String seqNo;
	
	//--------------------------
	private String itemCodeHidden;
    
    private String verbId;
    
    private String message;
	/**排序*/
	private String orderNo;
     /**升序*/
	private String asc;
	
	//查询
	private String itemCodeQuery;
	private String itemNameQuery;
	private String inputCodeQuery;
	private String seqNoQuery;
	
	private String[] itemCodeList;
	private String[] itemNameList;
	private String[] inputCodeList;
	private String[] levelFlagList;
	private String[] parentItemCodeList;
	private String[] commentsList;
	private String[] seqNoList;
	/**大类*/
	private String[] levelCode_1List;
	private String[] levelName_1List;
	/**中类*/
	private String[] levelCode_2List;
	private String[] levelName_2List;
	/**小类*/
	private String[] levelCode_3List;
	private String[] levelName_3List;
	
	private String levelCode_1;
	private String levelName_1;
	private String levelCode_2;
	private String levelName_2;
	private String levelCode_3;
	private String levelName_3;
	
	//详细
	private String parentItemName;
	
	//初始化等级列表
	private String[] levelFlagAllList;
	
	public String[] getLevelFlagAllList() {
		return levelFlagAllList;
	}

	public void setLevelFlagAllList(String[] levelFlagAllList) {
		this.levelFlagAllList = levelFlagAllList;
	}

	public CommConfigHospitalTypeForm() {
		itemCode = "";
		itemName = "";
		inputCode = "";
		levelFlag = "";
		parentItemCode = "";
		comments = "";
		seqNo = "";
		
		itemCodeHidden="";
		message="";
		orderNo="";
		verbId="";
		asc="";
		
		itemCodeQuery = "";
		itemNameQuery = "";
		inputCodeQuery = "";
		seqNoQuery = "";
		
		parentItemName = "";
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

	public String getLevelFlag() {
		return levelFlag;
	}

	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}

	public String getParentItemCode() {
		return parentItemCode;
	}

	public void setParentItemCode(String parentItemCode) {
		this.parentItemCode = parentItemCode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getItemCodeHidden() {
		return itemCodeHidden;
	}

	public void setItemCodeHidden(String itemCodeHidden) {
		this.itemCodeHidden = itemCodeHidden;
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

	public String[] getLevelFlagList() {
		return levelFlagList;
	}

	public void setLevelFlagList(String[] levelFlagList) {
		this.levelFlagList = levelFlagList;
	}

	public String[] getParentItemCodeList() {
		return parentItemCodeList;
	}

	public void setParentItemCodeList(String[] parentItemCodeList) {
		this.parentItemCodeList = parentItemCodeList;
	}

	public String[] getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(String[] commentsList) {
		this.commentsList = commentsList;
	}

	public String[] getSeqNoList() {
		return seqNoList;
	}

	public void setSeqNoList(String[] seqNoList) {
		this.seqNoList = seqNoList;
	}

	public String getItemCodeQuery() {
		return itemCodeQuery;
	}

	public void setItemCodeQuery(String itemCodeQuery) {
		this.itemCodeQuery = itemCodeQuery;
	}

	public String getItemNameQuery() {
		return itemNameQuery;
	}

	public void setItemNameQuery(String itemNameQuery) {
		this.itemNameQuery = itemNameQuery;
	}

	public String getInputCodeQuery() {
		return inputCodeQuery;
	}

	public void setInputCodeQuery(String inputCodeQuery) {
		this.inputCodeQuery = inputCodeQuery;
	}

	public String getSeqNoQuery() {
		return seqNoQuery;
	}

	public void setSeqNoQuery(String seqNoQuery) {
		this.seqNoQuery = seqNoQuery;
	}

	public String[] getLevelCode_1List() {
		return levelCode_1List;
	}

	public void setLevelCode_1List(String[] levelCode_1List) {
		this.levelCode_1List = levelCode_1List;
	}

	public String[] getLevelName_1List() {
		return levelName_1List;
	}

	public void setLevelName_1List(String[] levelName_1List) {
		this.levelName_1List = levelName_1List;
	}

	public String[] getLevelCode_2List() {
		return levelCode_2List;
	}

	public void setLevelCode_2List(String[] levelCode_2List) {
		this.levelCode_2List = levelCode_2List;
	}

	public String[] getLevelName_2List() {
		return levelName_2List;
	}

	public void setLevelName_2List(String[] levelName_2List) {
		this.levelName_2List = levelName_2List;
	}

	public String[] getLevelCode_3List() {
		return levelCode_3List;
	}

	public void setLevelCode_3List(String[] levelCode_3List) {
		this.levelCode_3List = levelCode_3List;
	}

	public String[] getLevelName_3List() {
		return levelName_3List;
	}

	public void setLevelName_3List(String[] levelName_3List) {
		this.levelName_3List = levelName_3List;
	}

	public String getLevelCode_1() {
		return levelCode_1;
	}

	public void setLevelCode_1(String levelCode_1) {
		this.levelCode_1 = levelCode_1;
	}

	public String getLevelCode_2() {
		return levelCode_2;
	}

	public void setLevelCode_2(String levelCode_2) {
		this.levelCode_2 = levelCode_2;
	}

	public String getLevelCode_3() {
		return levelCode_3;
	}

	public void setLevelCode_3(String levelCode_3) {
		this.levelCode_3 = levelCode_3;
	}

	public String getLevelName_1() {
		return levelName_1;
	}

	public void setLevelName_1(String levelName_1) {
		this.levelName_1 = levelName_1;
	}

	public String getLevelName_2() {
		return levelName_2;
	}

	public void setLevelName_2(String levelName_2) {
		this.levelName_2 = levelName_2;
	}

	public String getLevelName_3() {
		return levelName_3;
	}

	public void setLevelName_3(String levelName_3) {
		this.levelName_3 = levelName_3;
	}

	public String getParentItemName() {
		return parentItemName;
	}

	public void setParentItemName(String parentItemName) {
		this.parentItemName = parentItemName;
	}
}
