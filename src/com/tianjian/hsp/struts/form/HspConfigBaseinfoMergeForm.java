package com.tianjian.hsp.struts.form;

import org.apache.struts.action.ActionForm;

public class HspConfigBaseinfoMergeForm extends ActionForm {
	
	private String id;
	private String itemCode;
	private String itemName;
	private String itemAddress;
	private String commConfigLocationId1;
	private String commConfigLocationId2;
	private String commConfigLocationId3;
	private String inputCode;
	private String seqNo;
	
	private String[] idArray;
	private String[] itemCodeArray;
	private String[] itemNameArray;
	private String[] itemAddressArray;
	private String[] commConfigLocationId1s;
	private String[] commConfigLocationId2s;
	private String[] commConfigLocationId3s;
	private String[] commConfigLocationId1_names;
	private String[] commConfigLocationId2_names;
	private String[] commConfigLocationId3_names;
	private String[] inputCodeArray;
	private String[] seqNoArray;
	
	
	/**操作类型变量(公用的)*/
	private String verbId;
	private String orderNo;
	private String asc;
	private String itemCodeHidden;
	private String idHidden;
	private String totalPage;
	private String checkValue;
	private String message="";
	
	/**记录人员ID*/
	private String createUserId;
	/**记录人员名称*/
	private String createUserName;
	
	public HspConfigBaseinfoMergeForm(){
		this.id = "";
		this.itemCode = "";
		this.itemName = "";
		this.itemAddress = "";
		this.commConfigLocationId1 = "";
		this.commConfigLocationId2 = "";
		this.commConfigLocationId3 = "";
		this.inputCode = "";
		this.seqNo = "";
		this.verbId = "";
		this.orderNo = "";
		this.asc = "";
		this.itemCodeHidden = "";
		this.idHidden = "";
		this.totalPage = "";
		this.checkValue = "";
	}
	
	public HspConfigBaseinfoMergeForm(String id, String itemCode,
			String itemName, String itemAddress, String commConfigLocationId1,
			String commConfigLocationId2, String commConfigLocationId3,
			String inputCode, String seqNo, String[] idArray,
			String[] itemCodeArray, String[] itemNameArray,
			String[] itemAddressArray, String[] commConfigLocationId1s,
			String[] commConfigLocationId2s, String[] commConfigLocationId3s,
			String[] commConfigLocationId1_names,
			String[] commConfigLocationId2_names,
			String[] commConfigLocationId3_names, String[] inputCodeArray,
			String[] seqNoArray, String verbId, String orderNo, String asc,
			String itemCodeHidden, String idHidden, String totalPage,
			String checkValue, String message, String createUserId,
			String createUserName) {
		super();
		this.id = id;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemAddress = itemAddress;
		this.commConfigLocationId1 = commConfigLocationId1;
		this.commConfigLocationId2 = commConfigLocationId2;
		this.commConfigLocationId3 = commConfigLocationId3;
		this.inputCode = inputCode;
		this.seqNo = seqNo;
		this.idArray = idArray;
		this.itemCodeArray = itemCodeArray;
		this.itemNameArray = itemNameArray;
		this.itemAddressArray = itemAddressArray;
		this.commConfigLocationId1s = commConfigLocationId1s;
		this.commConfigLocationId2s = commConfigLocationId2s;
		this.commConfigLocationId3s = commConfigLocationId3s;
		this.commConfigLocationId1_names = commConfigLocationId1_names;
		this.commConfigLocationId2_names = commConfigLocationId2_names;
		this.commConfigLocationId3_names = commConfigLocationId3_names;
		this.inputCodeArray = inputCodeArray;
		this.seqNoArray = seqNoArray;
		this.verbId = verbId;
		this.orderNo = orderNo;
		this.asc = asc;
		this.itemCodeHidden = itemCodeHidden;
		this.idHidden = idHidden;
		this.totalPage = totalPage;
		this.checkValue = checkValue;
		this.message = message;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getItemAddress() {
		return itemAddress;
	}

	public void setItemAddress(String itemAddress) {
		this.itemAddress = itemAddress;
	}

	public String getCommConfigLocationId1() {
		return commConfigLocationId1;
	}

	public void setCommConfigLocationId1(String commConfigLocationId1) {
		this.commConfigLocationId1 = commConfigLocationId1;
	}

	public String getCommConfigLocationId2() {
		return commConfigLocationId2;
	}

	public void setCommConfigLocationId2(String commConfigLocationId2) {
		this.commConfigLocationId2 = commConfigLocationId2;
	}

	public String getCommConfigLocationId3() {
		return commConfigLocationId3;
	}

	public void setCommConfigLocationId3(String commConfigLocationId3) {
		this.commConfigLocationId3 = commConfigLocationId3;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String[] getIdArray() {
		return idArray;
	}

	public void setIdArray(String[] idArray) {
		this.idArray = idArray;
	}

	public String[] getItemCodeArray() {
		return itemCodeArray;
	}

	public void setItemCodeArray(String[] itemCodeArray) {
		this.itemCodeArray = itemCodeArray;
	}

	public String[] getItemNameArray() {
		return itemNameArray;
	}

	public void setItemNameArray(String[] itemNameArray) {
		this.itemNameArray = itemNameArray;
	}

	public String[] getItemAddressArray() {
		return itemAddressArray;
	}

	public void setItemAddressArray(String[] itemAddressArray) {
		this.itemAddressArray = itemAddressArray;
	}

	public String[] getCommConfigLocationId1s() {
		return commConfigLocationId1s;
	}

	public void setCommConfigLocationId1s(String[] commConfigLocationId1s) {
		this.commConfigLocationId1s = commConfigLocationId1s;
	}

	public String[] getCommConfigLocationId2s() {
		return commConfigLocationId2s;
	}

	public void setCommConfigLocationId2s(String[] commConfigLocationId2s) {
		this.commConfigLocationId2s = commConfigLocationId2s;
	}

	public String[] getCommConfigLocationId3s() {
		return commConfigLocationId3s;
	}

	public void setCommConfigLocationId3s(String[] commConfigLocationId3s) {
		this.commConfigLocationId3s = commConfigLocationId3s;
	}

	public String[] getCommConfigLocationId1_names() {
		return commConfigLocationId1_names;
	}

	public void setCommConfigLocationId1_names(String[] commConfigLocationId1_names) {
		this.commConfigLocationId1_names = commConfigLocationId1_names;
	}

	public String[] getCommConfigLocationId2_names() {
		return commConfigLocationId2_names;
	}

	public void setCommConfigLocationId2_names(String[] commConfigLocationId2_names) {
		this.commConfigLocationId2_names = commConfigLocationId2_names;
	}

	public String[] getCommConfigLocationId3_names() {
		return commConfigLocationId3_names;
	}

	public void setCommConfigLocationId3_names(String[] commConfigLocationId3_names) {
		this.commConfigLocationId3_names = commConfigLocationId3_names;
	}

	public String[] getInputCodeArray() {
		return inputCodeArray;
	}

	public void setInputCodeArray(String[] inputCodeArray) {
		this.inputCodeArray = inputCodeArray;
	}

	public String[] getSeqNoArray() {
		return seqNoArray;
	}

	public void setSeqNoArray(String[] seqNoArray) {
		this.seqNoArray = seqNoArray;
	}

	public String getVerbId() {
		return verbId;
	}

	public void setVerbId(String verbId) {
		this.verbId = verbId;
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

	public String getIdHidden() {
		return idHidden;
	}

	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

}
