package com.tianjian.comm.struts.form;

import org.apache.struts.action.ActionForm;

public class CommConfigDrugunitsForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemCode;
	private String seqNo;
	private String itemName;
	private String inputCode;
	private String comments;
	
	//-----定义query查询列表-------------------------------
	private String[] itemCodeList;
	private String[] seqNoList;
	private String[] itemNameList;
	private String[] inputCodeList;
	private String[] commentsList;
	//-----定义公共列表-------------------------------
	private String verbId;
	private String message;
	private String orderNo;
	private String asc;
	private String itemCodeHidden;
	//-----------------------------
	public CommConfigDrugunitsForm(){
		itemCode="";
		seqNo = "";
		itemName="";
		inputCode="";
		comments ="";
		
		itemCodeList = null;
		seqNoList = null;
		itemNameList = null;
		inputCodeList = null;
		commentsList = null;
		
		verbId="";
		message="";
		orderNo="";
		asc="";
		itemCodeHidden="";
	}
	
	//----------------------------
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	//
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
	public String[] getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(String[] commentsList) {
		this.commentsList = commentsList;
	}

	public String getItemCodeHidden() {
		return itemCodeHidden;
	}

	public void setItemCodeHidden(String itemCodeHidden) {
		this.itemCodeHidden = itemCodeHidden;
	}



	
	
}
