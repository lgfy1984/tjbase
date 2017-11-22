package com.tianjian.hsp.struts.form;

import com.tianjian.core.struts.form.ActionForm;

public class HspStaffBaseinfoLocalBaseForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6114812473620506428L;
	
	/**组织机构ID*/
	private String hspConfigBaseinfoIdQuery;
	/**姓名*/
	private String nameQuery;
	/**证件号码码*/
	private String idNoQuery;
	/**操作人员id*/
	private String staffId;
	/**操作人员所在卫生机构的id*/
	private String hspConfigBaseinfoId;
	
	private String[] hspIdArray;
	private String[] hspNameArray;
	private String[] nameArray;
	private String[] commSexIdArray;
	private String[] commSexNameArray;
	private String[] dateOfBirthArray;
	private String[] idNoArray;
	private String[] idsHiddenArray;
	private String[] empNoArray;
	private String[] mobileTelArray;
	
	public String[] getMobileTelArray() {
		return mobileTelArray;
	}

	public void setMobileTelArray(String[] mobileTelArray) {
		this.mobileTelArray = mobileTelArray;
	}

	public HspStaffBaseinfoLocalBaseForm(){
		hspConfigBaseinfoIdQuery = "";
		nameQuery = "";
		idNoQuery = "";
		staffId = "";
		hspConfigBaseinfoId = "";
	}

	public String getHspConfigBaseinfoIdQuery() {
		return hspConfigBaseinfoIdQuery;
	}

	public void setHspConfigBaseinfoIdQuery(String hspConfigBaseinfoIdQuery) {
		this.hspConfigBaseinfoIdQuery = hspConfigBaseinfoIdQuery;
	}

	public String getNameQuery() {
		return nameQuery;
	}

	public void setNameQuery(String nameQuery) {
		this.nameQuery = nameQuery;
	}

	public String getIdNoQuery() {
		return idNoQuery;
	}

	public void setIdNoQuery(String idNoQuery) {
		this.idNoQuery = idNoQuery;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getHspConfigBaseinfoId() {
		return hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	public String[] getNameArray() {
		return nameArray;
	}

	public void setNameArray(String[] nameArray) {
		this.nameArray = nameArray;
	}

	public String[] getHspIdArray() {
		return hspIdArray;
	}

	public void setHspIdArray(String[] hspIdArray) {
		this.hspIdArray = hspIdArray;
	}

	public String[] getHspNameArray() {
		return hspNameArray;
	}

	public void setHspNameArray(String[] hspNameArray) {
		this.hspNameArray = hspNameArray;
	}

	public String[] getCommSexIdArray() {
		return commSexIdArray;
	}

	public void setCommSexIdArray(String[] commSexIdArray) {
		this.commSexIdArray = commSexIdArray;
	}

	public String[] getDateOfBirthArray() {
		return dateOfBirthArray;
	}

	public void setDateOfBirthArray(String[] dateOfBirthArray) {
		this.dateOfBirthArray = dateOfBirthArray;
	}

	public String[] getIdsHiddenArray() {
		return idsHiddenArray;
	}

	public void setIdsHiddenArray(String[] idsHiddenArray) {
		this.idsHiddenArray = idsHiddenArray;
	}

	public String[] getIdNoArray() {
		return idNoArray;
	}

	public void setIdNoArray(String[] idNoArray) {
		this.idNoArray = idNoArray;
	}

	public String[] getCommSexNameArray() {
		return commSexNameArray;
	}

	public void setCommSexNameArray(String[] commSexNameArray) {
		this.commSexNameArray = commSexNameArray;
	}

	public String[] getEmpNoArray() {
		return empNoArray;
	}

	public void setEmpNoArray(String[] empNoArray) {
		this.empNoArray = empNoArray;
	}

	
}
