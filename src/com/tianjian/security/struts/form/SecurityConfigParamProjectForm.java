package com.tianjian.security.struts.form;

import org.apache.struts.action.ActionForm;



public class SecurityConfigParamProjectForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String projectCode = "";
	private String projectName = "";
	private String inputCode = "";
	private String seqNo = "";
	private String comments = "";
	private String verbId = "";
	private String message = "";
	private String orderNo = "";
	private String asc = "";
	private String pageNow = "";
	private String projectCodeHidden = "";

	private String[] projectCodeList;
	private String[] projectNameList;
	private String[] inputCodeList;
	private String[] seqNoList;
	private String[] commentsList;
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getPageNow() {
		return pageNow;
	}
	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
	}
	public String getProjectCodeHidden() {
		return projectCodeHidden;
	}
	public void setProjectCodeHidden(String projectCodeHidden) {
		this.projectCodeHidden = projectCodeHidden;
	}
	public String[] getProjectCodeList() {
		return projectCodeList;
	}
	public void setProjectCodeList(String[] projectCodeList) {
		this.projectCodeList = projectCodeList;
	}
	public String[] getProjectNameList() {
		return projectNameList;
	}
	public void setProjectNameList(String[] projectNameList) {
		this.projectNameList = projectNameList;
	}
	public String[] getInputCodeList() {
		return inputCodeList;
	}
	public void setInputCodeList(String[] inputCodeList) {
		this.inputCodeList = inputCodeList;
	}
	public String[] getSeqNoList() {
		return seqNoList;
	}
	public void setSeqNoList(String[] seqNoList) {
		this.seqNoList = seqNoList;
	}
	public String[] getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(String[] commentsList) {
		this.commentsList = commentsList;
	}




	
	
	
	
	
}
