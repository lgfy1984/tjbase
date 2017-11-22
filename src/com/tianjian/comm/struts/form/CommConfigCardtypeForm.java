package com.tianjian.comm.struts.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class CommConfigCardtypeForm extends ActionForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 180918013294914538L;
	private String itemCode;
	private String seqNo;
	private String itemName;
	private String inputCode;
	private String stopFlag;
	private String displayFlag;
	private String comments;
	
	private String itemCodeHidden;
	private String orderNo;
	private String asc;
	private String verbId;
	private String message;
	
	private String[] itemCodeList;
	private String[] itemNameList;
	private String[] seqNoList;
	private String[] inputCodeList;
	private String[] commentsList;
	private String[] stopFlagList;
	
	public CommConfigCardtypeForm(){
		itemCode="";
		seqNo="";
		itemName="";
		inputCode="";
		comments="";
		stopFlag="";
		displayFlag="";
		itemCodeHidden="";
		orderNo="";
		asc="";
		verbId="";
		message="";
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

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(String stopFlag) {
		this.stopFlag = stopFlag;
	}

	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
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

	public String[] getSeqNoList() {
		return seqNoList;
	}

	public void setSeqNoList(String[] seqNoList) {
		this.seqNoList = seqNoList;
	}

	public String[] getInputCodeList() {
		return inputCodeList;
	}

	public void setInputCodeList(String[] inputCodeList) {
		this.inputCodeList = inputCodeList;
	}

	public String getItemCodeHidden() {
		return itemCodeHidden;
	}

	public void setItemCodeHidden(String itemCodeHidden) {
		this.itemCodeHidden = itemCodeHidden;
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

	public String[] getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(String[] commentsList) {
		this.commentsList = commentsList;
	}

	public String[] getStopFlagList() {
		return stopFlagList;
	}

	public void setStopFlagList(String[] stopFlagList) {
		this.stopFlagList = stopFlagList;
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
}
