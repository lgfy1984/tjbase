package com.tianjian.comm.struts.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class CommGbCoptpForm extends ActionForm implements Serializable{
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
	private String parentItemCodeName;
	private String[] parentItemCodes;
	private String[] parentItemNames;
	/**备注*/
	private String comments;
	/**序号*/
	private String seqNo;
	
	//-------------------辅助业务处理--------开始----------
	/**隐式ID*/
    private String itemCodeHidden;
    /**动作ID*/
    private String verbId;   
    /**传递的消息*/
    private String message;
    /**排序列*/
	private String orderNo;
	/**升降序标志**/
	private String asc;
	//----------------辅助业务处理--------结束--------
	
	//----------------查询结果数组--------开始---------
	private String[] itemCodeList;
	private String[] itemNameList;
	private String[] inputCodeList;
	private String[] levelFlagList;
	private String[] parentItemCodeList;
	private String[] parentItemCodeNameList;
	private String[] commentsList;
	private String[] seqNoList;
	//----------------查询结果数组--------结束----------
	public CommGbCoptpForm(){
		itemCode = "";
		itemName = "";
		inputCode = "";
		levelFlag = "";
		parentItemCode = "";
		comments = "";
		seqNo = "";
		itemCodeHidden = "";
		verbId = "";
		message = "";
		orderNo = "";
		asc = "";
	}
	
	
	public CommGbCoptpForm(String itemCode, String itemName,
			String inputCode, String levelFlag, String parentItemCode,
			String comments, String seqNo) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.inputCode = inputCode;
		this.levelFlag = levelFlag;
		this.parentItemCode = parentItemCode;
		this.comments = comments;
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
	public String getLevelFlag() {
		return levelFlag;
	}
	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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


	public String getParentItemCodeName() {
		return parentItemCodeName;
	}


	public void setParentItemCodeName(String parentItemCodeName) {
		this.parentItemCodeName = parentItemCodeName;
	}


	public String[] getParentItemCodes() {
		return parentItemCodes;
	}


	public void setParentItemCodes(String[] parentItemCodes) {
		this.parentItemCodes = parentItemCodes;
	}


	public String[] getParentItemNames() {
		return parentItemNames;
	}


	public void setParentItemNames(String[] parentItemNames) {
		this.parentItemNames = parentItemNames;
	}


	public String[] getParentItemCodeNameList() {
		return parentItemCodeNameList;
	}


	public void setParentItemCodeNameList(String[] parentItemCodeNameList) {
		this.parentItemCodeNameList = parentItemCodeNameList;
	}
}
