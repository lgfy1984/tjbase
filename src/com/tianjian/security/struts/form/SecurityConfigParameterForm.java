package com.tianjian.security.struts.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.struts.servlet.MoniItemList;
import com.tianjian.security.struts.servlet.SecurityConfigParam;

@SuppressWarnings("unchecked")
public class SecurityConfigParameterForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String classCode = "";//程序包ID
	private String className = "";//程序包名称
	private String inputCode = "";
	private String functionDescription = "";
	private String comments = "";
	private String id = "";
	private String itemName = "";//参数名称
	private String itemValue = "";//参数值
	private String hspConfigBaseinfoId = "";//医疗机构代码
	private String usageDescription = "";//对已参数的描述
	private String initValue = "";//初始值
	private String verbId = "";
	private String pageNow = "";
	private String message = "";
	private String orderNo = "";
	private String asc = "";
	private String classCode1="";
	private String hiddenId = "";
	private String hspConfigBaseinfoName = "";
	private String projectName;//参数名称

	private List<SecurityConfigParam> list = new  MoniItemList(SecurityConfigParam.class);
	
	private String[] classCodeList;
	private String[] classNameList;
	private String[] inputCodeList;
	private String[] functionDescriptionList;
	private String[] commentsList;
	private String[] idList;
	private String[] itemNameList;
	private String[] itemValueList;
	private String[] hspConfigBaseinfoIdList;
	private String[] usageDescriptionList;
	private String[] initValueList;
	private String[] projectNameList;
	public String getVerbId() {
		return verbId;
	}
	public String getPageNow() {
		return pageNow;
	}

	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
	}
	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.validate(mapping, request);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getClassCodeList() {
		return classCodeList;
	}

	public void setClassCodeList(String[] classCodeList) {
		this.classCodeList = classCodeList;
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

	public String getHspConfigBaseinfoId() {
		return hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	public String getUsageDescription() {
		return usageDescription;
	}

	public void setUsageDescription(String usageDescription) {
		this.usageDescription = usageDescription;
	}

	public String getInitValue() {
		return initValue;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}

	public String[] getItemNameList() {
		return itemNameList;
	}

	public void setItemNameList(String[] itemNameList) {
		this.itemNameList = itemNameList;
	}

	public String[] getItemValueList() {
		return itemValueList;
	}

	public void setItemValueList(String[] itemValueList) {
		this.itemValueList = itemValueList;
	}

	public String[] getHspConfigBaseinfoIdList() {
		return hspConfigBaseinfoIdList;
	}

	public void setHspConfigBaseinfoIdList(String[] hspConfigBaseinfoIdList) {
		this.hspConfigBaseinfoIdList = hspConfigBaseinfoIdList;
	}

	public String[] getUsageDescriptionList() {
		return usageDescriptionList;
	}

	public void setUsageDescriptionList(String[] usageDescriptionList) {
		this.usageDescriptionList = usageDescriptionList;
	}

	public String[] getInitValueList() {
		return initValueList;
	}

	public void setInitValueList(String[] initValueList) {
		this.initValueList = initValueList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public String getHiddenId() {
		return hiddenId;
	}

	public void setHiddenId(String hiddenId) {
		this.hiddenId = hiddenId;
	}

	public String getHspConfigBaseinfoName() {
		return hspConfigBaseinfoName;
	}

	public void setHspConfigBaseinfoName(String hspConfigBaseinfoName) {
		this.hspConfigBaseinfoName = hspConfigBaseinfoName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String[] getProjectNameList() {
		return projectNameList;
	}
	public void setProjectNameList(String[] projectNameList) {
		this.projectNameList = projectNameList;
	}
	public List<SecurityConfigParam> getList() {
		return list;
	}
	public void setList(List<SecurityConfigParam> list) {
		this.list = list;
	}
	

	
	
}
