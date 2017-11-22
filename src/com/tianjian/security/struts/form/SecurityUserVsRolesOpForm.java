package com.tianjian.security.struts.form;


import org.apache.struts.action.ActionForm;

/**
 * SecurityUserVsRoles 角色信息
 * @author lengj
 */
public class SecurityUserVsRolesOpForm extends ActionForm{
   
	//Default SerialVersionUID
	private static final long serialVersionUID = 1L;
	//Basic Fields
	private String id;
	private String securityStaffBaseinfoId;
	
	
	private String staffCode;
	private String hspDeptBaseinfoId;
	private String hspDeptBaseinfoName;
	private String name;
	private String nameEn;
	private String inputCode;
	private String commConfigSexId;
	private String commConfigSexName;
	private String dateOfBirth;
	private String commConfigStafftypeId;
	private String commConfigStafftypeName;
	private String idNo;
	private String commConfigProfessionId;
	private String commConfigProfessionName;
	private String commConfigEmptitleId;
	private String commConfigEmptitleName;
	private String empPosition;
	private String commConfigDegreeId;
	private String commConfigDegreeName;
	private String phone;
	private String islocation;
	private String islocationName;
	private String workstationDesc;
	private String comments;
	
	private String securityConfigRolesId;   //角色ID
	private String securityConfigRolesName; //角色姓名

	private String hspConfigBaseinfoId;     //医院ID
	private String hspConfigBaseinfoName;   //医院姓名
	//--------所有字典都定义成数组---------------------------
	/**角色字典*/
	private String[] securityConfigRolesIds;
	private String[] securityConfigRolesNames;
	
	/**医院字典*/
	private String[] hspConfigBaseinfoIds;
	private String[] hspConfigBaseinfoNames;
	//-----定义query查询列表-------------------------------
	private String[] idList;
	private String[] staffCodeList;
	private String[] hspConfigBaseinfoIdList;
	private String[] hspConfigBaseinfoNameList;
	private String[] hspDeptBaseinfoIdList;
	private String[] hspDeptBaseinfoNameList;
	private String[] nameList;
	private String[] commConfigSexIdList;
	private String[] commConfigSexNameList;
	private String[] dateOfBirthList;
	private String[] securityConfigRolesIdList;
	private String[] securityConfigRolesNameList;

	//-----------------处理公用部分------------------------------------------
	private String verbId;
	private String message;
	private String idHidden;
	private String orderNo;
	private String asc;
	
	public SecurityUserVsRolesOpForm(){
		
		
		id = "" ;
		securityStaffBaseinfoId = "" ;
		
		
		staffCode = "" ;
		hspConfigBaseinfoId = "" ;
		hspDeptBaseinfoId = "" ;
		name = "" ;
		nameEn = "" ;
		inputCode = "" ;
		commConfigSexId = "" ;
		dateOfBirth = "" ;
		commConfigStafftypeId = "" ;
		idNo = "" ;
		commConfigProfessionId = "" ;
		commConfigEmptitleId = "" ;
		empPosition = "" ;
		commConfigDegreeId = "" ;
		phone = "" ;
		islocation = "" ;
		workstationDesc = "" ;
		comments = "" ;
		
		securityConfigRolesId = "" ;   //角色ID
		securityConfigRolesName = "" ; //角色姓名
		
		hspConfigBaseinfoId = "" ;//医院ID
		hspConfigBaseinfoName = "" ;//医院姓名
		
		verbId = "" ;
		message = "" ;
		idHidden = "" ;
		orderNo = "" ;
		asc = "" ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecurityStaffBaseinfoId() {
		return securityStaffBaseinfoId;
	}

	public void setSecurityStaffBaseinfoId(String securityStaffBaseinfoId) {
		this.securityStaffBaseinfoId = securityStaffBaseinfoId;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}


	public String getHspDeptBaseinfoId() {
		return hspDeptBaseinfoId;
	}

	public void setHspDeptBaseinfoId(String hspDeptBaseinfoId) {
		this.hspDeptBaseinfoId = hspDeptBaseinfoId;
	}
    
	public String getHspDeptBaseinfoName() {
		return hspDeptBaseinfoName;
	}

	public void setHspDeptBaseinfoName(String hspDeptBaseinfoName) {
		this.hspDeptBaseinfoName = hspDeptBaseinfoName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getCommConfigSexId() {
		return commConfigSexId;
	}

	public void setCommConfigSexId(String commConfigSexId) {
		this.commConfigSexId = commConfigSexId;
	}

	
	public String getCommConfigSexName() {
		return commConfigSexName;
	}

	public void setCommConfigSexName(String commConfigSexName) {
		this.commConfigSexName = commConfigSexName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCommConfigStafftypeId() {
		return commConfigStafftypeId;
	}

	public void setCommConfigStafftypeId(String commConfigStafftypeId) {
		this.commConfigStafftypeId = commConfigStafftypeId;
	}
    
	public String getCommConfigStafftypeName() {
		return commConfigStafftypeName;
	}

	public void setCommConfigStafftypeName(String commConfigStafftypeName) {
		this.commConfigStafftypeName = commConfigStafftypeName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getCommConfigProfessionId() {
		return commConfigProfessionId;
	}

	public void setCommConfigProfessionId(String commConfigProfessionId) {
		this.commConfigProfessionId = commConfigProfessionId;
	}
    
	public String getCommConfigProfessionName() {
		return commConfigProfessionName;
	}

	public void setCommConfigProfessionName(String commConfigProfessionName) {
		this.commConfigProfessionName = commConfigProfessionName;
	}

	public String getCommConfigEmptitleId() {
		return commConfigEmptitleId;
	}

	public void setCommConfigEmptitleId(String commConfigEmptitleId) {
		this.commConfigEmptitleId = commConfigEmptitleId;
	}
    
	public String getCommConfigEmptitleName() {
		return commConfigEmptitleName;
	}

	public void setCommConfigEmptitleName(String commConfigEmptitleName) {
		this.commConfigEmptitleName = commConfigEmptitleName;
	}

	public String getEmpPosition() {
		return empPosition;
	}

	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
	}

	public String getCommConfigDegreeId() {
		return commConfigDegreeId;
	}

	public void setCommConfigDegreeId(String commConfigDegreeId) {
		this.commConfigDegreeId = commConfigDegreeId;
	}
    
	public String getCommConfigDegreeName() {
		return commConfigDegreeName;
	}

	public void setCommConfigDegreeName(String commConfigDegreeName) {
		this.commConfigDegreeName = commConfigDegreeName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIslocation() {
		return islocation;
	}

	public void setIslocation(String islocation) {
		this.islocation = islocation;
	}
    
	public String getIslocationName() {
		return islocationName;
	}

	public void setIslocationName(String islocationName) {
		this.islocationName = islocationName;
	}

	public String getWorkstationDesc() {
		return workstationDesc;
	}

	public void setWorkstationDesc(String workstationDesc) {
		this.workstationDesc = workstationDesc;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSecurityConfigRolesId() {
		return securityConfigRolesId;
	}

	public void setSecurityConfigRolesId(String securityConfigRolesId) {
		this.securityConfigRolesId = securityConfigRolesId;
	}

	

	public String getSecurityConfigRolesName() {
		return securityConfigRolesName;
	}

	public void setSecurityConfigRolesName(String securityConfigRolesName) {
		this.securityConfigRolesName = securityConfigRolesName;
	}

	
	public String getHspConfigBaseinfoId() {
		return hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	public String getHspConfigBaseinfoName() {
		return hspConfigBaseinfoName;
	}

	public void setHspConfigBaseinfoName(String hspConfigBaseinfoName) {
		this.hspConfigBaseinfoName = hspConfigBaseinfoName;
	}

	public String[] getSecurityConfigRolesIds() {
		return securityConfigRolesIds;
	}

	public void setSecurityConfigRolesIds(String[] securityConfigRolesIds) {
		this.securityConfigRolesIds = securityConfigRolesIds;
	}

	public String[] getSecurityConfigRolesNames() {
		return securityConfigRolesNames;
	}

	public void setSecurityConfigRolesNames(String[] securityConfigRolesNames) {
		this.securityConfigRolesNames = securityConfigRolesNames;
	}
    
	public String[] getHspConfigBaseinfoIds() {
		return hspConfigBaseinfoIds;
	}

	public void setHspConfigBaseinfoIds(String[] hspConfigBaseinfoIds) {
		this.hspConfigBaseinfoIds = hspConfigBaseinfoIds;
	}

	public String[] getHspConfigBaseinfoNames() {
		return hspConfigBaseinfoNames;
	}

	public void setHspConfigBaseinfoNames(String[] hspConfigBaseinfoNames) {
		this.hspConfigBaseinfoNames = hspConfigBaseinfoNames;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public String[] getStaffCodeList() {
		return staffCodeList;
	}

	public void setStaffCodeList(String[] staffCodeList) {
		this.staffCodeList = staffCodeList;
	}

	public String[] getHspConfigBaseinfoIdList() {
		return hspConfigBaseinfoIdList;
	}

	public void setHspConfigBaseinfoIdList(String[] hspConfigBaseinfoIdList) {
		this.hspConfigBaseinfoIdList = hspConfigBaseinfoIdList;
	}

	public String[] getHspConfigBaseinfoNameList() {
		return hspConfigBaseinfoNameList;
	}

	public void setHspConfigBaseinfoNameList(String[] hspConfigBaseinfoNameList) {
		this.hspConfigBaseinfoNameList = hspConfigBaseinfoNameList;
	}

	public String[] getHspDeptBaseinfoIdList() {
		return hspDeptBaseinfoIdList;
	}

	public void setHspDeptBaseinfoIdList(String[] hspDeptBaseinfoIdList) {
		this.hspDeptBaseinfoIdList = hspDeptBaseinfoIdList;
	}

	public String[] getHspDeptBaseinfoNameList() {
		return hspDeptBaseinfoNameList;
	}

	public void setHspDeptBaseinfoNameList(String[] hspDeptBaseinfoNameList) {
		this.hspDeptBaseinfoNameList = hspDeptBaseinfoNameList;
	}

	public String[] getNameList() {
		return nameList;
	}

	public void setNameList(String[] nameList) {
		this.nameList = nameList;
	}

	public String[] getCommConfigSexIdList() {
		return commConfigSexIdList;
	}

	public void setCommConfigSexIdList(String[] commConfigSexIdList) {
		this.commConfigSexIdList = commConfigSexIdList;
	}

	public String[] getCommConfigSexNameList() {
		return commConfigSexNameList;
	}

	public void setCommConfigSexNameList(String[] commConfigSexNameList) {
		this.commConfigSexNameList = commConfigSexNameList;
	}

	public String[] getDateOfBirthList() {
		return dateOfBirthList;
	}

	public void setDateOfBirthList(String[] dateOfBirthList) {
		this.dateOfBirthList = dateOfBirthList;
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

	public String getIdHidden() {
		return idHidden;
	}

	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
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

	public String[] getSecurityConfigRolesIdList() {
		return securityConfigRolesIdList;
	}

	public void setSecurityConfigRolesIdList(String[] securityConfigRolesIdList) {
		this.securityConfigRolesIdList = securityConfigRolesIdList;
	}

	public String[] getSecurityConfigRolesNameList() {
		return securityConfigRolesNameList;
	}

	public void setSecurityConfigRolesNameList(String[] securityConfigRolesNameList) {
		this.securityConfigRolesNameList = securityConfigRolesNameList;
	}
	
	
}
