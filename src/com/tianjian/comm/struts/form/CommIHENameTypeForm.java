package com.tianjian.comm.struts.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;


public class CommIHENameTypeForm  extends ActionForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3114346780385030192L;
	// Fields
	
	
	private String id;
	private String seqNo;
	private String nameTypeCode;
	private String nameTypeName;
	private String inputCode;
	private String comments;
	
    private String idHidden;
    
    private String verbId;
    
    private String message;
	/**排序*/
	private String orderNo;
     /**升序*/
	private String asc;
	
	private String[] nameTypeCodeList;
	private String[] nameTypeNameList;
	private String[] inputCodeList;
	private String[] commentsList;
	private String[] seqNoList;
	private String[] idList;

	public CommIHENameTypeForm(String nameTypeCode, String nameTypeName, String inputCode, String comments, String seqNo) {
		super();
		this.nameTypeCode = nameTypeCode;
		this.nameTypeName = nameTypeName;
		this.inputCode = inputCode;
		this.comments = comments;
		this.seqNo = seqNo;
	}

	public CommIHENameTypeForm() {
		super();
		this.nameTypeCode ="";
		this.nameTypeName = "";
		this.inputCode = "";
		this.comments = "";
		this.seqNo = "";
		this.orderNo = "";
		this.idHidden="";
		this.asc="";
		this.message="";
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getNameTypeCode() {
		return nameTypeCode;
	}

	public void setNameTypeCode(String nameTypeCode) {
		this.nameTypeCode = nameTypeCode;
	}

	public String getNameTypeName() {
		return nameTypeName;
	}

	public void setNameTypeName(String nameTypeName) {
		this.nameTypeName = nameTypeName;
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

	public String[] getNameTypeCodeList() {
		return nameTypeCodeList;
	}

	public void setNameTypeCodeList(String[] nameTypeCodeList) {
		this.nameTypeCodeList = nameTypeCodeList;
	}

	public String[] getNameTypeNameList() {
		return nameTypeNameList;
	}

	public void setNameTypeNameList(String[] nameTypeNameList) {
		this.nameTypeNameList = nameTypeNameList;
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

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}



	
	
}
