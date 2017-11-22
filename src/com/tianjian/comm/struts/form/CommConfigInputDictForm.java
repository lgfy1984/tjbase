package com.tianjian.comm.struts.form;

import java.io.Serializable;

import com.tianjian.core.struts.form.ActionForm;

public class CommConfigInputDictForm extends ActionForm implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 代码 */
	private String itemCode;
	private String itemName;
	private String inputCode;
	private String inputCodeWb;
	private String inputCode1;
	private String inputCode2;
	private String comments;

	// --------------------------
	private String itemCodeHidden;

	private String verbId;

	private String message;
	/** 排序 */
	private String orderNo;
	/** 升序 */
	private String asc;

	private String[] itemCodeList;
	private String[] itemNameList;
	private String[] inputCodeList;
	private String[] inputCodeWbList;
	private String[] commentsList;

	public CommConfigInputDictForm() {
		itemCode = "";
		itemName = "";
		inputCode = "";
		inputCodeWb = "";
		inputCode1 = "";
		inputCode2 = "";
		comments = "";
		message = "";
	}

	public CommConfigInputDictForm(String itemCode, String itemName,
			String inputCode, String inputCodeWb, String inputCode1,
			String inputCode2, String comments) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.inputCode = inputCode;
		this.inputCodeWb = inputCodeWb;
		this.inputCode1 = inputCode1;
		this.inputCode2 = inputCode2;
		this.comments = comments;
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

	public String getInputCodeWb() {
		return inputCodeWb;
	}

	public void setInputCodeWb(String inputCodeWb) {
		this.inputCodeWb = inputCodeWb;
	}

	public String getInputCode1() {
		return inputCode1;
	}

	public void setInputCode1(String inputCode1) {
		this.inputCode1 = inputCode1;
	}

	public String getInputCode2() {
		return inputCode2;
	}

	public void setInputCode2(String inputCode2) {
		this.inputCode2 = inputCode2;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String[] getInputCodeWbList() {
		return inputCodeWbList;
	}

	public void setInputCodeWbList(String[] inputCodeWbList) {
		this.inputCodeWbList = inputCodeWbList;
	}

	public String[] getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(String[] commentsList) {
		this.commentsList = commentsList;
	}
}
