package com.tianjian.hsp.struts.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class HspStaffBaseinfoForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private String commConfigLocationId3="";
	private String commConfigLocationTownId="";
	private String commClvId="";
	
	
	/**ID*/
	private String id;
	/**组织机构ID*/
	private String hspConfigBaseinfoId;
	private String hspConfigBaseinfoName;
	private String theHspName;//医院名称
	/**是否机构卫生人员管理角色*/
	private boolean isStaffManagerRole;
	/**人员编码(卫生局统一)*/
	private String empNo;
	public String getPersonnelCode() {
		return personnelCode;
	}
	public void setPersonnelCode(String personnelCode) {
		this.personnelCode = personnelCode;
	}
	/**姓名*/
	private String name;
	/**证件号码码*/
	private String idNo;
	/**出生日期*/
	private Date birthday;
	/**性别*/
	private String commConfigSexId;
	private String commConfigSexName;
	/**民族*/
	private String commConfigNationalityId;
	private String commConfigNationalityName;//民族名称
	/**参加工作日期*/
	private Date startWorkDate;
	/**办公室电话*/
	private String officeTel;
	/**手机号码*/
	private String mobileTel;
	/**从事专业类别*/
	private String commDictPublicCharId1;
	private String commDictPublicCharName1;
	/**（ 医师/ 卫生监督员）执业证书编码*/
	private String workCertificateNo;
	/**医师执业类别*/
	private String commDictPublicCharId2;
	private String commDictPublicCharName2;
	/**行政职务*/
	private String commConfigPositiontitleId;
	private String commConfigPositiontitleName;
	/**专业技术资格（评）*/
	private String commConfigEmptitleId;
	private String commConfigEmptitleName;
	/**专业技术职务（聘）*/
	private String commDictPublicCharId3;
	private String commDictPublicCharName3;
	/**学历*/
	private String commConfigDegreeId;
	private String commConfigDegreeName;
	/**学位*/
	private String commConfigDegreeLevelId;
	private String commConfigDegreeLevelName;
	/**所学专业*/
	private String commConfigProfessionId;
	private String commConfigProfessionName;
	/**在位标志*/
	private Long islocation;
	/**记录日期*/
	private String createDate;
	/**记录人员ID*/
	private String createUserId;
	/**记录人员名称*/
	private String createUserName;

	private String securityUserBaseinfoId;
	private String hspConfigName="";
	
	private FormFile file = null;
	
	private String idType;
	private String idTypeName;
	private String deptCode;
	private String deptName;
	private String bdeptCode;
	private String bdeptName;
	
	private List<?> idTypeList;
	private List<?> deptList;
	private List<?> bdeptList;
	
	private String staffId;
	private String staffHspId;
	
	private List<?> hspConfigBaseinfoIdList;
	private List<?> commConfigSexIdList;
	private List<?> commConfigNationalityIdL;
    private List<?> commDictPublicCharId1List;
    private List<?> commDictPublicCharId2List;
    private List<?> commConfigPositiontitleIdList;
    private List<?> commConfigEmptitleIdList;
    private List<?> commDictPublicCharId3List;
    private List<?> commConfigDegreeIdList;
    private List<?> commConfigDegreeLevelIdList;
    private List<?> commConfigProfessionIdList;
    
	
	/**以下为页面中传过来的参数值&变量*/
	//出生日期
	private String birthdayYear;
	private String birthdayMonth;
	private String birthdayDay;	
	//参加工作日期
	private String startWorkDateYear;
	private String startWorkDateMonth;
	//填写日期
	private String year="";
	private String month="";
	private String day="";
	
	/**操作类型变量(公用的)*/
	private String verbId;
	private String orderNo;
	private String asc;
	private String itemCodeHidden;
	private String totalPage;
	private String idHidden;
	private String message="";
	
	private String personnelCode;
	
	/** cols show is the list . jps  */
	private String[] idsHiddenArray;
	private String[] hspNameArray;
	private String[] hspIdArray;
	private String[] deptNameArray;
	private String[] nameArray;
	private String[] sexArray;
	private String[] idNoArray;
	private String[] isIdNull;
	private String[] securityStaffBaseinfoIdArray;
	private String[] commSexIdArray;
	private String[] empNoArray;
	private String[] dateOfBirthArray;
	/** 注销表*/
	private String logoutTime;
	private String logoutReason;
	private String createUserId1;
	private String createUserName1;
	private String hspStaffBaseinfoId;
	
	public HspStaffBaseinfoForm(){
		/**ID*/
		id="";
		/**组织机构ID*/
		hspConfigBaseinfoId="";
		hspConfigBaseinfoName="";
		/**人员编码(卫生局统一)*/
		empNo="";
		/**姓名*/
		name="";
		/**证件号码码*/
		idNo="";
		/**性别*/
		commConfigSexId="";
		/**民族*/
		commConfigNationalityId="";
		/**办公室电话*/
		officeTel="";
		/**手机号码*/
		mobileTel="";
		/**从事专业类别*/
		commDictPublicCharId1="";
		/**（ 医师/ 卫生监督员）执业证书编码*/
		workCertificateNo="";
		/**医师执业类别*/
		commDictPublicCharId2="";
		/**行政职务*/
		commConfigPositiontitleId="";
		/**专业技术资格（评）*/
		commConfigEmptitleId="";
		/**专业技术职务（聘）*/
		commDictPublicCharId3="";
		/**学历*/
		commConfigDegreeId="";
		/**学位*/
		commConfigDegreeLevelId="";
		/**所学专业*/
		commConfigProfessionId="";
		
		/**记录人员ID*/
		createUserId="";
		/**记录人员名称*/
		createUserName="";
		
		staffId="";
	    
		securityUserBaseinfoId="";
		
		/**以下为页面中传过来的参数值&变量*/
		//出生日期
		birthdayYear="";
		birthdayMonth="";
		birthdayDay="";	
		//参加工作日期
		startWorkDateYear="";
		startWorkDateMonth="";
		//填写日期
		year="";
		month="";
		day="";
		
		/**操作类型变量(公用的)*/
		verbId="";
		orderNo="";
		asc="";
		itemCodeHidden="";
		totalPage="";
		idHidden="";
		
		//字典中ID取NAME
		theHspName="";//医院名称
		commConfigNationalityName="";//民族名称
		
	}
	
	
	
	
	
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdTypeName() {
		return idTypeName;
	}
	public void setIdTypeName(String idTypeName) {
		this.idTypeName = idTypeName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getBdeptCode() {
		return bdeptCode;
	}
	public void setBdeptCode(String bdeptCode) {
		this.bdeptCode = bdeptCode;
	}
	public String getBdeptName() {
		return bdeptName;
	}
	public void setBdeptName(String bdeptName) {
		this.bdeptName = bdeptName;
	}
	public List<?> getIdTypeList() {
		return idTypeList;
	}
	public void setIdTypeList(List<?> idTypeList) {
		this.idTypeList = idTypeList;
	}
	public List<?> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<?> deptList) {
		this.deptList = deptList;
	}
	public List<?> getBdeptList() {
		return bdeptList;
	}
	public void setBdeptList(List<?> bdeptList) {
		this.bdeptList = bdeptList;
	}
	public String getBirthdayYear() {
		return birthdayYear;
	}
	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}
	public String getBirthdayMonth() {
		return birthdayMonth;
	}
	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}
	public String getBirthdayDay() {
		return birthdayDay;
	}
	public void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}
	public String getStartWorkDateYear() {
		return startWorkDateYear;
	}
	public void setStartWorkDateYear(String startWorkDateYear) {
		this.startWorkDateYear = startWorkDateYear;
	}
	public String getStartWorkDateMonth() {
		return startWorkDateMonth;
	}
	public void setStartWorkDateMonth(String startWorkDateMonth) {
		this.startWorkDateMonth = startWorkDateMonth;
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
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getIdHidden() {
		return idHidden;
	}
	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getCommConfigNationalityId() {
		return commConfigNationalityId;
	}
	public void setCommConfigNationalityId(String commConfigNationalityId) {
		this.commConfigNationalityId = commConfigNationalityId;
	}
	public Date getStartWorkDate() {
		return startWorkDate;
	}
	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}
	public String getOfficeTel() {
		return officeTel;
	}
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
	
	public String getCommDictPublicCharId2() {
		return commDictPublicCharId2;
	}
	public void setCommDictPublicCharId2(String commDictPublicCharId2) {
		this.commDictPublicCharId2 = commDictPublicCharId2;
	}
	public String getWorkCertificateNo() {
		return workCertificateNo;
	}
	public void setWorkCertificateNo(String workCertificateNo) {
		this.workCertificateNo = workCertificateNo;
	}
	public String getCommDictPublicCharId3() {
		return commDictPublicCharId3;
	}
	public void setCommDictPublicCharId3(String commDictPublicCharId3) {
		this.commDictPublicCharId3 = commDictPublicCharId3;
	}
	
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getCommConfigSexId() {
		return commConfigSexId;
	}
	public void setCommConfigSexId(String commConfigSexId) {
		this.commConfigSexId = commConfigSexId;
	}
	public String getCommDictPublicCharId1() {
		return commDictPublicCharId1;
	}
	public void setCommDictPublicCharId1(String commDictPublicCharId1) {
		this.commDictPublicCharId1 = commDictPublicCharId1;
	}
	public String getCommConfigPositiontitleId() {
		return commConfigPositiontitleId;
	}
	public void setCommConfigPositiontitleId(String commConfigPositiontitleId) {
		this.commConfigPositiontitleId = commConfigPositiontitleId;
	}
	public String getCommConfigEmptitleId() {
		return commConfigEmptitleId;
	}
	public void setCommConfigEmptitleId(String commConfigEmptitleId) {
		this.commConfigEmptitleId = commConfigEmptitleId;
	}
	public String getCommConfigDegreeId() {
		return commConfigDegreeId;
	}
	public void setCommConfigDegreeId(String commConfigDegreeId) {
		this.commConfigDegreeId = commConfigDegreeId;
	}
	public String getCommConfigDegreeLevelId() {
		return commConfigDegreeLevelId;
	}
	public void setCommConfigDegreeLevelId(String commConfigDegreeLevelId) {
		this.commConfigDegreeLevelId = commConfigDegreeLevelId;
	}
	public String getCommConfigProfessionId() {
		return commConfigProfessionId;
	}
	public void setCommConfigProfessionId(String commConfigProfessionId) {
		this.commConfigProfessionId = commConfigProfessionId;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	public Long getIslocation() {
		return islocation;
	}
	public void setIslocation(Long islocation) {
		this.islocation = islocation;
	}
	public String[] getIdsHiddenArray() {
		return idsHiddenArray;
	}
	public void setIdsHiddenArray(String[] idsHiddenArray) {
		this.idsHiddenArray = idsHiddenArray;
	}
	public String[] getHspNameArray() {
		return hspNameArray;
	}
	public void setHspNameArray(String[] hspNameArray) {
		this.hspNameArray = hspNameArray;
	}
	public String[] getDeptNameArray() {
		return deptNameArray;
	}
	public void setDeptNameArray(String[] deptNameArray) {
		this.deptNameArray = deptNameArray;
	}
	public String[] getNameArray() {
		return nameArray;
	}
	public void setNameArray(String[] nameArray) {
		this.nameArray = nameArray;
	}
	public String[] getSexArray() {
		return sexArray;
	}
	public void setSexArray(String[] sexArray) {
		this.sexArray = sexArray;
	}
	public String[] getIdNoArray() {
		return idNoArray;
	}
	public void setIdNoArray(String[] idNoArray) {
		this.idNoArray = idNoArray;
	}
	public String getTheHspName() {
		return theHspName;
	}
	public void setTheHspName(String theHspName) {
		this.theHspName = theHspName;
	}
	public String getCommConfigNationalityName() {
		return commConfigNationalityName;
	}
	public void setCommConfigNationalityName(String commConfigNationalityName) {
		this.commConfigNationalityName = commConfigNationalityName;
	}
	public List<?> getCommDictPublicCharId1List() {
		return commDictPublicCharId1List;
	}
	public void setCommDictPublicCharId1List(List<?> commDictPublicCharId1List) {
		this.commDictPublicCharId1List = commDictPublicCharId1List;
	}
	public List<?> getCommDictPublicCharId2List() {
		return commDictPublicCharId2List;
	}
	public void setCommDictPublicCharId2List(List<?> commDictPublicCharId2List) {
		this.commDictPublicCharId2List = commDictPublicCharId2List;
	}
	public List<?> getCommConfigPositiontitleIdList() {
		return commConfigPositiontitleIdList;
	}
	public void setCommConfigPositiontitleIdList(
			List<?> commConfigPositiontitleIdList) {
		this.commConfigPositiontitleIdList = commConfigPositiontitleIdList;
	}
	public List<?> getCommConfigEmptitleIdList() {
		return commConfigEmptitleIdList;
	}
	public void setCommConfigEmptitleIdList(List<?> commConfigEmptitleIdList) {
		this.commConfigEmptitleIdList = commConfigEmptitleIdList;
	}
	public List<?> getCommDictPublicCharId3List() {
		return commDictPublicCharId3List;
	}
	public void setCommDictPublicCharId3List(List<?> commDictPublicCharId3List) {
		this.commDictPublicCharId3List = commDictPublicCharId3List;
	}
	public List<?> getCommConfigDegreeIdList() {
		return commConfigDegreeIdList;
	}
	public void setCommConfigDegreeIdList(List<?> commConfigDegreeIdList) {
		this.commConfigDegreeIdList = commConfigDegreeIdList;
	}
	public List<?> getCommConfigDegreeLevelIdList() {
		return commConfigDegreeLevelIdList;
	}
	public void setCommConfigDegreeLevelIdList(List<?> commConfigDegreeLevelIdList) {
		this.commConfigDegreeLevelIdList = commConfigDegreeLevelIdList;
	}
	public List<?> getCommConfigProfessionIdList() {
		return commConfigProfessionIdList;
	}
	public void setCommConfigProfessionIdList(List<?> commConfigProfessionIdList) {
		this.commConfigProfessionIdList = commConfigProfessionIdList;
	}
	public List<?> getHspConfigBaseinfoIdList() {
		return hspConfigBaseinfoIdList;
	}
	public void setHspConfigBaseinfoIdList(List<?> hspConfigBaseinfoIdList) {
		this.hspConfigBaseinfoIdList = hspConfigBaseinfoIdList;
	}
	public List<?> getCommConfigSexIdList() {
		return commConfigSexIdList;
	}
	public void setCommConfigSexIdList(List<?> commConfigSexIdList) {
		this.commConfigSexIdList = commConfigSexIdList;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public List<?> getCommConfigNationalityIdL() {
		return commConfigNationalityIdL;
	}
	public void setCommConfigNationalityIdL(List<?> commConfigNationalityIdL) {
		this.commConfigNationalityIdL = commConfigNationalityIdL;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[] getIsIdNull() {
		return isIdNull;
	}
	public void setIsIdNull(String[] isIdNull) {
		this.isIdNull = isIdNull;
	}
	public String[] getSecurityStaffBaseinfoIdArray() {
		return securityStaffBaseinfoIdArray;
	}
	public void setSecurityStaffBaseinfoIdArray(
			String[] securityStaffBaseinfoIdArray) {
		this.securityStaffBaseinfoIdArray = securityStaffBaseinfoIdArray;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String[] getEmpNoArray() {
		return empNoArray;
	}
	public void setEmpNoArray(String[] empNoArray) {
		this.empNoArray = empNoArray;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getSecurityUserBaseinfoId() {
		return securityUserBaseinfoId;
	}
	public void setSecurityUserBaseinfoId(String securityUserBaseinfoId) {
		this.securityUserBaseinfoId = securityUserBaseinfoId;
	}
	public String getHspConfigName() {
		return hspConfigName;
	}
	public void setHspConfigName(String hspConfigName) {
		this.hspConfigName = hspConfigName;
	}
	public String getCommConfigLocationId3() {
		return commConfigLocationId3;
	}
	public void setCommConfigLocationId3(String commConfigLocationId3) {
		this.commConfigLocationId3 = commConfigLocationId3;
	}
	public String getCommConfigLocationTownId() {
		return commConfigLocationTownId;
	}
	public void setCommConfigLocationTownId(String commConfigLocationTownId) {
		this.commConfigLocationTownId = commConfigLocationTownId;
	}
	public String getCommClvId() {
		return commClvId;
	}
	public void setCommClvId(String commClvId) {
		this.commClvId = commClvId;
	}
	public String[] getHspIdArray() {
		return hspIdArray;
	}
	public void setHspIdArray(String[] hspIdArray) {
		this.hspIdArray = hspIdArray;
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
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getLogoutReason() {
		return logoutReason;
	}
	public void setLogoutReason(String logoutReason) {
		this.logoutReason = logoutReason;
	}
	public String getCreateUserId1() {
		return createUserId1;
	}
	public void setCreateUserId1(String createUserId1) {
		this.createUserId1 = createUserId1;
	}
	public String getCreateUserName1() {
		return createUserName1;
	}
	public void setCreateUserName1(String createUserName1) {
		this.createUserName1 = createUserName1;
	}
	public String getHspStaffBaseinfoId() {
		return hspStaffBaseinfoId;
	}
	public void setHspStaffBaseinfoId(String hspStaffBaseinfoId) {
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
	}
	public String getHspConfigBaseinfoName() {
		return hspConfigBaseinfoName;
	}
	public void setHspConfigBaseinfoName(String hspConfigBaseinfoName) {
		this.hspConfigBaseinfoName = hspConfigBaseinfoName;
	}
	public boolean isStaffManagerRole() {
		return isStaffManagerRole;
	}
	public void setStaffManagerRole(boolean isStaffManagerRole) {
		this.isStaffManagerRole = isStaffManagerRole;
	}

	public String getCommConfigSexName() {
		return commConfigSexName;
	}
	public void setCommConfigSexName(String commConfigSexName) {
		this.commConfigSexName = commConfigSexName;
	}
	public String getCommDictPublicCharName1() {
		return commDictPublicCharName1;
	}
	public void setCommDictPublicCharName1(String commDictPublicCharName1) {
		this.commDictPublicCharName1 = commDictPublicCharName1;
	}
	public String getCommDictPublicCharName2() {
		return commDictPublicCharName2;
	}
	public void setCommDictPublicCharName2(String commDictPublicCharName2) {
		this.commDictPublicCharName2 = commDictPublicCharName2;
	}
	public String getCommConfigPositiontitleName() {
		return commConfigPositiontitleName;
	}
	public void setCommConfigPositiontitleName(String commConfigPositiontitleName) {
		this.commConfigPositiontitleName = commConfigPositiontitleName;
	}
	public String getCommConfigEmptitleName() {
		return commConfigEmptitleName;
	}
	public void setCommConfigEmptitleName(String commConfigEmptitleName) {
		this.commConfigEmptitleName = commConfigEmptitleName;
	}
	public String getCommDictPublicCharName3() {
		return commDictPublicCharName3;
	}
	public void setCommDictPublicCharName3(String commDictPublicCharName3) {
		this.commDictPublicCharName3 = commDictPublicCharName3;
	}
	public String getCommConfigDegreeName() {
		return commConfigDegreeName;
	}
	public void setCommConfigDegreeName(String commConfigDegreeName) {
		this.commConfigDegreeName = commConfigDegreeName;
	}
	public String getCommConfigDegreeLevelName() {
		return commConfigDegreeLevelName;
	}
	public void setCommConfigDegreeLevelName(String commConfigDegreeLevelName) {
		this.commConfigDegreeLevelName = commConfigDegreeLevelName;
	}
	public String getCommConfigProfessionName() {
		return commConfigProfessionName;
	}
	public void setCommConfigProfessionName(String commConfigProfessionName) {
		this.commConfigProfessionName = commConfigProfessionName;
	}
	public String getStaffHspId() {
		return staffHspId;
	}
	public void setStaffHspId(String staffHspId) {
		this.staffHspId = staffHspId;
	}
}
