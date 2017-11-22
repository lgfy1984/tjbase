package com.tianjian.comm.struts.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;


public class CommConfigAboForm  extends ActionForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3114346780385030192L;
	// Fields
	
	
	/**代码*/
	private String itemCode;
	/**名称*/
	private String itemName;
	/**输入码*/
	private String inputCode;
	/**备注*/
	private String comments;
	/**序号*/
	private String seqNo;
	
    private String itemCodeHidden;
    
    private String verbId;
    
    private String message;
	/**排序*/
	private String orderNo;
     /**升序*/
	private String asc;
	
	private String[] itemCodeList;
	private String[] itemNameList;
	private String[] inputCodeList;
	private String[] commentsList;
	private String[] seqNoList;

	public CommConfigAboForm(String itemCode, String itemName, String inputCode, String comments, String seqNo) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.inputCode = inputCode;
		this.comments = comments;
		this.seqNo = seqNo;
	}

	public CommConfigAboForm() {
		super();
		this.itemCode ="";
		this.itemName = "";
		this.inputCode = "";
		this.comments = "";
		this.seqNo = "";
		this.orderNo = "";
		this.itemCodeHidden="";
		this.asc="";
		this.message="";
		// TODO Auto-generated constructor stub
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

	
}
