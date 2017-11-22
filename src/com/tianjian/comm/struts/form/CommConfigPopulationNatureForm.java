package com.tianjian.comm.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class CommConfigPopulationNatureForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6962568298449908712L;
	private String itemCode="";
	private String itemName="";
	private String inputCode="";
	private String comments="";
	private String seqNo="";
	
	private String itemCodeHidden="";
	private String verbId="";
	private String message="";
	private String orderNo="";
	private String asc="";
	private String mypage="";
	private String itemNameHidden="";
	private String inputCodeHidden="";
	
	private List<?> itemCodeList=null;
	private List<?> itemNameList=null;
	private List<?> inputCodeList=null;
	private List<?> seqNoList=null;
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
	public List<?> getItemCodeList() {
		return itemCodeList;
	}
	public void setItemCodeList(List<?> itemCodeList) {
		this.itemCodeList = itemCodeList;
	}
	public List<?> getItemNameList() {
		return itemNameList;
	}
	public void setItemNameList(List<?> itemNameList) {
		this.itemNameList = itemNameList;
	}
	public List<?> getInputCodeList() {
		return inputCodeList;
	}
	public void setInputCodeList(List<?> inputCodeList) {
		this.inputCodeList = inputCodeList;
	}
	public List<?> getSeqNoList() {
		return seqNoList;
	}
	public void setSeqNoList(List<?> seqNoList) {
		this.seqNoList = seqNoList;
	}
	public String getAsc() {
		return asc;
	}
	public void setAsc(String asc) {
		this.asc = asc;
	}
	public String getMypage() {
		return mypage;
	}
	public void setMypage(String mypage) {
		this.mypage = mypage;
	}
	public String getItemNameHidden() {
		return itemNameHidden;
	}
	public void setItemNameHidden(String itemNameHidden) {
		this.itemNameHidden = itemNameHidden;
	}
	public String getInputCodeHidden() {
		return inputCodeHidden;
	}
	public void setInputCodeHidden(String inputCodeHidden) {
		this.inputCodeHidden = inputCodeHidden;
	}
}
