package com.tianjian.security.struts.form;

import org.apache.struts.action.ActionForm;
/**
 * 查询医疗人员注册码用ActionForm
 * @author DZENALL
 */
public class ViewRegistCodeForm extends ActionForm {

	private static final long serialVersionUID = -8584799729643465051L;
	
	private String verbId;
	private String queryType;//查询用类别，即查询依据（1：姓名，2：编码，3：身份证号）
	private String queryValue;//查询用数据
	private String message;//
	private String orderNo;//
	private String asc;//

	private String[] idList;//内码ID数组
	private String[] nameList;//姓名数组
	private String[] staffCodeList;//人员编码数组
	private String[] idNoList;//身份证号数组
	private String[] registCodeList;//注册号码数组
	private String[] isRegistedList;//是否已经注册数组
	private String[] startTimeList;//注册时间数组

	public ViewRegistCodeForm(){
		verbId="";
		queryType="";
		queryValue="";
		message="";
		orderNo="";
		asc="";
	}

	public String getVerbId() {
		return verbId;
	}

	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}

	public String getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
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

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public String[] getNameList() {
		return nameList;
	}

	public void setNameList(String[] nameList) {
		this.nameList = nameList;
	}

	public String[] getStaffCodeList() {
		return staffCodeList;
	}

	public void setStaffCodeList(String[] staffCodeList) {
		this.staffCodeList = staffCodeList;
	}

	public String[] getIdNoList() {
		return idNoList;
	}

	public void setIdNoList(String[] idNoList) {
		this.idNoList = idNoList;
	}

	public String[] getRegistCodeList() {
		return registCodeList;
	}

	public void setRegistCodeList(String[] registCodeList) {
		this.registCodeList = registCodeList;
	}

	public String[] getIsRegistedList() {
		return isRegistedList;
	}

	public void setIsRegistedList(String[] isRegistedList) {
		this.isRegistedList = isRegistedList;
	}

	public String[] getStartTimeList() {
		return startTimeList;
	}

	public void setStartTimeList(String[] startTimeList) {
		this.startTimeList = startTimeList;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}	
}
