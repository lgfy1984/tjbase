package com.tianjian.hsp.struts.form;

import org.apache.struts.action.ActionForm;

public class HspStaffBaseinfoMergeForm extends ActionForm {
	
	/**ID*/
	private String id;
	/**组织机构ID*/
	private String hspConfigBaseinfoId;
	/**人员编码(卫生局统一)*/
	private String empNo;
	/**姓名*/
	private String name;
	/**证件号码码*/
	private String idNo;
	/**出生日期*/
	private String birthday;
	/**性别*/
	private String commConfigSexId;
	
	private String[] idArray;
	private String[] hspConfigBaseinfoIdArray;
	private String[] empNoArray;
	private String[] nameArray;
	private String[] idNoArray;
	private String[] birthdayArray;
	private String[] commConfigSexIdArray;
	private String[] commConfigSexNameArray;
	
	private String[] commConfigSexIds;
	private String[] commConfigSexNames;
	
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
	
	public HspStaffBaseinfoMergeForm(){
		this.id = "" ;
		this.hspConfigBaseinfoId = "" ;
		this.empNo = "" ;
		this.name = "" ;
		this.idNo = "" ;
		this.commConfigSexId = "" ;
		this.createUserId = "";
		this.createUserName = "";
		this.birthday = "";
	}
	
	public HspStaffBaseinfoMergeForm(String id, String hspConfigBaseinfoId,
			String empNo, String name, String idNo, String birthday,
			String commConfigSexId, String[] idArray,
			String[] hspConfigBaseinfoIdArray, String[] empNoArray,
			String[] nameArray, String[] idNoArray, String[] birthdayArray,
			String[] commConfigSexIdArray, String verbId, String orderNo,
			String asc, String itemCodeHidden, String totalPage,
			String checkValue, String message,String createUserId,String createUserName) {
		super();
		this.id = id;
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
		this.empNo = empNo;
		this.name = name;
		this.idNo = idNo;
		this.birthday = birthday;
		this.commConfigSexId = commConfigSexId;
		this.idArray = idArray;
		this.hspConfigBaseinfoIdArray = hspConfigBaseinfoIdArray;
		this.empNoArray = empNoArray;
		this.nameArray = nameArray;
		this.idNoArray = idNoArray;
		this.birthdayArray = birthdayArray;
		this.commConfigSexIdArray = commConfigSexIdArray;
		this.verbId = verbId;
		this.orderNo = orderNo;
		this.asc = asc;
		this.itemCodeHidden = itemCodeHidden;
		this.totalPage = totalPage;
		this.checkValue = checkValue;
		this.message = message;
		this.createUserId = checkValue;
		this.createUserName = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHspConfigBaseinfoId() {
		return hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCommConfigSexId() {
		return commConfigSexId;
	}

	public void setCommConfigSexId(String commConfigSexId) {
		this.commConfigSexId = commConfigSexId;
	}

	public String[] getIdArray() {
		return idArray;
	}

	public void setIdArray(String[] idArray) {
		this.idArray = idArray;
	}

	public String[] getHspConfigBaseinfoIdArray() {
		return hspConfigBaseinfoIdArray;
	}

	public void setHspConfigBaseinfoIdArray(String[] hspConfigBaseinfoIdArray) {
		this.hspConfigBaseinfoIdArray = hspConfigBaseinfoIdArray;
	}

	public String[] getEmpNoArray() {
		return empNoArray;
	}

	public void setEmpNoArray(String[] empNoArray) {
		this.empNoArray = empNoArray;
	}

	public String[] getNameArray() {
		return nameArray;
	}

	public void setNameArray(String[] nameArray) {
		this.nameArray = nameArray;
	}

	public String[] getIdNoArray() {
		return idNoArray;
	}

	public void setIdNoArray(String[] idNoArray) {
		this.idNoArray = idNoArray;
	}

	public String[] getBirthdayArray() {
		return birthdayArray;
	}

	public void setBirthdayArray(String[] birthdayArray) {
		this.birthdayArray = birthdayArray;
	}

	public String[] getCommConfigSexIdArray() {
		return commConfigSexIdArray;
	}

	public void setCommConfigSexIdArray(String[] commConfigSexIdArray) {
		this.commConfigSexIdArray = commConfigSexIdArray;
	}

	public String[] getCommConfigSexNameArray() {
		return commConfigSexNameArray;
	}

	public void setCommConfigSexNameArray(String[] commConfigSexNameArray) {
		this.commConfigSexNameArray = commConfigSexNameArray;
	}

	public String[] getCommConfigSexIds() {
		return commConfigSexIds;
	}

	public void setCommConfigSexIds(String[] commConfigSexIds) {
		this.commConfigSexIds = commConfigSexIds;
	}

	public String[] getCommConfigSexNames() {
		return commConfigSexNames;
	}

	public void setCommConfigSexNames(String[] commConfigSexNames) {
		this.commConfigSexNames = commConfigSexNames;
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
