package com.tianjian.comm.struts.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;


public class CommConfigLocationForm  extends ActionForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5832929058666795379L;
	// Fields
	private String id;
	/**代码*/
	private String itemCode;
	/**名称*/
	private String itemName;
	
	private String levelFlag;
	
	private String parentId;
	
	private String parentName;
	/**输入码*/
	private String inputCode;
	/**备注*/
	private String comments;
	/**序号*/
	private String seqNo;
	
    private String idHidden;
    
    private String verbId;
    
    private String message;
	/**排序*/
	private String orderNo;
     /**升序*/
	private String asc;
	private String commConfigLocationId1 = "";
	private String commConfigLocationId2 = "";
	
	private String[] idList;
	private String[] itemCodeList;
	private String[] itemNameList;
	private String[] inputCodeList;
	private String[] commentsList;
	private String[] seqNoList;
	private String[] levelFlagList;
	private String[] parentIdList;
	private String[] parentNameList;
	
	/**所属省*/
	private String[] commConfigLocationId1s;
	private String[] commConfigLocationId1_names;
	/**所属市*/
	private String[] commConfigLocationId2s;
	private String[] commConfigLocationId2_names;
	
	private String[] parentIds;
	private String[] parentNames;


	public CommConfigLocationForm() {
		super();
		this.id="";
		this.itemCode ="";
		this.itemName = "";
		this.levelFlag="";
		this.parentId="";
		this.inputCode = "";
		this.comments = "";
		this.seqNo = "";
		this.orderNo = "";
		this.idHidden="";
		this.asc="";
		this.message="";
		this.parentName = "";
		this.parentId = "";
		// TODO Auto-generated constructor stub
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


	
	public String getLevelFlag() {
		return levelFlag;
	}


	
	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}


	
	public String getParentId() {
		return parentId;
	}


	
	public void setParentId(String parentId) {
		this.parentId = parentId;
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


	
	public String getIdHidden() {
		return idHidden;
	}


	
	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
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


	
	public String[] getLevelFlagList() {
		return levelFlagList;
	}


	
	public void setLevelFlagList(String[] levelFlagList) {
		this.levelFlagList = levelFlagList;
	}


	
	public String[] getParentIdList() {
		return parentIdList;
	}


	
	public void setParentIdList(String[] parentIdList) {
		this.parentIdList = parentIdList;
	}



	
	public String[] getIdList() {
		return idList;
	}



	
	public void setIdList(String[] idList) {
		this.idList = idList;
	}



	
	public String getParentName() {
		return parentName;
	}



	
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}



	
	public String[] getParentNameList() {
		return parentNameList;
	}



	
	public void setParentNameList(String[] parentNameList) {
		this.parentNameList = parentNameList;
	}



	
	public String[] getParentIds() {
		return parentIds;
	}



	
	public void setParentIds(String[] parentIds) {
		this.parentIds = parentIds;
	}



	
	public String[] getParentNames() {
		return parentNames;
	}



	
	public void setParentNames(String[] parentNames) {
		this.parentNames = parentNames;
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



	public String[] getCommConfigLocationId1s() {
		return commConfigLocationId1s;
	}



	public void setCommConfigLocationId1s(String[] commConfigLocationId1s) {
		this.commConfigLocationId1s = commConfigLocationId1s;
	}



	public String[] getCommConfigLocationId1_names() {
		return commConfigLocationId1_names;
	}



	public void setCommConfigLocationId1_names(String[] commConfigLocationId1Names) {
		commConfigLocationId1_names = commConfigLocationId1Names;
	}



	public String[] getCommConfigLocationId2s() {
		return commConfigLocationId2s;
	}



	public void setCommConfigLocationId2s(String[] commConfigLocationId2s) {
		this.commConfigLocationId2s = commConfigLocationId2s;
	}



	public String[] getCommConfigLocationId2_names() {
		return commConfigLocationId2_names;
	}



	public void setCommConfigLocationId2_names(String[] commConfigLocationId2Names) {
		commConfigLocationId2_names = commConfigLocationId2Names;
	}

	

	
}
