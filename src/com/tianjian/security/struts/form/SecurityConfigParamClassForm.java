package com.tianjian.security.struts.form;

import org.apache.struts.action.ActionForm;

public class SecurityConfigParamClassForm extends ActionForm {
	private String itemName="";
	private String itemValue="";

	
	
	private String[] itemNameList;
	private String[] itemValueList;
	private String[] usageDescription;
	private String[] id;
	
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String classCode = "";
	private String className = "";
	private String inputCode = "";
	private String functionDescription = "";
	private String projectName = "";
	private String comments = "";
	private String projectCode = "";
	private String verbId = "";
	private String message = "";
	private String orderNo = "";
	private String asc = "";
	private String classCode1="";
	private String pageNow = "";

	private String[] classCodeList;
	private String[] classNameList;
	private String[] inputCodeList;
	private String[] functionDescriptionList;
	private String[] commentsList;
	private String[] projectCodeList;
	private String[] projectNameList;
	
	
	
	
	
	
	
	
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getClassCodeList() {
		return classCodeList;
	}
	public void setClassCodeList(String[] classCodeList) {
		this.classCodeList = classCodeList;
	}
	public String[] getItemNameList() {
		return itemNameList;
	}
	public void setItemNameList(String[] itemNameList) {
		this.itemNameList = itemNameList;
	}
	public String[] getUsageDescription() {
		return usageDescription;
	}
	public void setUsageDescription(String[] usageDescription) {
		this.usageDescription = usageDescription;
	}
	public SecurityConfigParamClassForm() {
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemValue() {
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	public String getVerbId() {
		return verbId;
	}
	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}
	public String[] getItemValueList() {
		return itemValueList;
	}
	public void setItemValueList(String[] itemValueList) {
		this.itemValueList = itemValueList;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getInputCode() {
		return inputCode;
	}
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	public String getFunctionDescription() {
		return functionDescription;
	}
	public void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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
	public String getClassCode1() {
		return classCode1;
	}
	public void setClassCode1(String classCode1) {
		this.classCode1 = classCode1;
	}
	public String getPageNow() {
		return pageNow;
	}
	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
	}
	public String[] getClassNameList() {
		return classNameList;
	}
	public void setClassNameList(String[] classNameList) {
		this.classNameList = classNameList;
	}
	public String[] getInputCodeList() {
		return inputCodeList;
	}
	public void setInputCodeList(String[] inputCodeList) {
		this.inputCodeList = inputCodeList;
	}
	public String[] getFunctionDescriptionList() {
		return functionDescriptionList;
	}
	public void setFunctionDescriptionList(String[] functionDescriptionList) {
		this.functionDescriptionList = functionDescriptionList;
	}
	public String[] getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(String[] commentsList) {
		this.commentsList = commentsList;
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
}
