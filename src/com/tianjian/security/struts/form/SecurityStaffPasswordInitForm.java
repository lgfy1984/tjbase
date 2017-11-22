package com.tianjian.security.struts.form;

import java.io.Serializable;
import org.apache.struts.action.ActionForm;

public class SecurityStaffPasswordInitForm extends ActionForm implements Serializable {
	
	private static final long serialVersionUID = 2717849952365436900L;
	

	
	
	private String staffId;//用户名
	private String name;//用户姓名
	private String passwd;//用户密码
	private String inputCode;//输入码
	private String itemCode;//机构代码
	private String itemName;//机构名称
	private String staffHspId;
	
	private String hspConfigId;
	
    
	private String verbId="";	
	private String message="";	
    private String orderNo="";
    private String asc="";
    	

	//------查询列表-------------
    private String[] idList;//用户ID
    private String[] staffIdList;//用户名
    private String[] nameList;//用户姓名
    private String[] sexList;//性别
    private String[] birthdayList;//出生日期
    private String[] itemCodeList;//机构代码
	private String[] itemNameList;//机构名称
    
    //------字典处理------------
    private String[] itemCodes;//机构代码
	private String[] itemNames;//机构名称
	
	private String[] regTimes;
	private String[] stopTimes;
    	
	public SecurityStaffPasswordInitForm(){
		
		this.staffId="";
		this.name="";
		this.inputCode="";
		this.itemCode="";
		this.itemName="";
		this.passwd="";
		this.staffHspId = "";
		
	}

	
	public String getStaffId() {
		return staffId;
	}

	
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getInputCode() {
		return inputCode;
	}

	
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
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

	
	public String[] getRegTimes() {
		return regTimes;
	}


	public void setRegTimes(String[] regTimes) {
		this.regTimes = regTimes;
	}


	public String[] getStopTimes() {
		return stopTimes;
	}


	public void setStopTimes(String[] stopTimes) {
		this.stopTimes = stopTimes;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
	public String getPasswd() {
		return passwd;
	}

	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	
	
	public String[] getIdList() {
		return idList;
	}

	
	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	
	public String[] getStaffIdList() {
		return staffIdList;
	}

	
	public void setStaffIdList(String[] staffIdList) {
		this.staffIdList = staffIdList;
	}

	
	public String[] getNameList() {
		return nameList;
	}

	
	public void setNameList(String[] nameList) {
		this.nameList = nameList;
	}

	
	public String[] getSexList() {
		return sexList;
	}

	
	public void setSexList(String[] sexList) {
		this.sexList = sexList;
	}

	
	public String[] getBirthdayList() {
		return birthdayList;
	}

	
	public void setBirthdayList(String[] birthdayList) {
		this.birthdayList = birthdayList;
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


	
	public String[] getItemCodes() {
		return itemCodes;
	}


	
	public void setItemCodes(String[] itemCodes) {
		this.itemCodes = itemCodes;
	}


	
	public String[] getItemNames() {
		return itemNames;
	}


	
	public void setItemNames(String[] itemNames) {
		this.itemNames = itemNames;
	}


	public String getStaffHspId() {
		return staffHspId;
	}


	public void setStaffHspId(String staffHspId) {
		this.staffHspId = staffHspId;
	}


	public String getHspConfigId() {
		return hspConfigId;
	}


	public void setHspConfigId(String hspConfigId) {
		this.hspConfigId = hspConfigId;
	}

	
 
	

}