package com.tianjian.hsp.struts.form;

import java.util.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class HealthRegisterTreeForm extends ActionForm {

	private static final long serialVersionUID = -3459853113647676008L;

	private String message;
	private String pid;
	private String parentItemCode;
	private String hspId;
	private String deptCode;
	private String type;
	private String staffId;
	private String equipId;

	private HspBean hspBean;
	private DeptBean deptBean;
	private StaffBean staffBean;
	private EquipBean equipBean;
	
	private String staffOrEquip;
	
	private String parentTId;

	public String getParentTId() {
		return parentTId;
	}

	public void setParentTId(String parentTId) {
		this.parentTId = parentTId;
	}

	public HspBean getHspBean() {
		if (this.hspBean == null) {
			this.hspBean = new HspBean();
		}
		return hspBean;
	}

	public void setHspBean(HspBean hspBean) {
		this.hspBean = hspBean;
	}

	public DeptBean getDeptBean() {
		if(this.deptBean == null){
			this.deptBean = new DeptBean();
		}
		return deptBean;
	}

	public void setDeptBean(DeptBean deptBean) {
		this.deptBean = deptBean;
	}

	public StaffBean getStaffBean() {
		if (this.staffBean == null) {
			this.staffBean = new StaffBean();
		}
		return staffBean;
	}

	public void setStaffBean(StaffBean staffBean) {
		this.staffBean = staffBean;
	}

	public EquipBean getEquipBean() {
		if (this.equipBean == null) {
			this.equipBean = new EquipBean();
		}
		return equipBean;
	}

	public void setEquipBean(EquipBean equipBean) {
		this.equipBean = equipBean;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffOrEquip() {
		return staffOrEquip;
	}

	public void setStaffOrEquip(String staffOrEquip) {
		this.staffOrEquip = staffOrEquip;
	}
	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getParentItemCode() {
		return parentItemCode;
	}

	public void setParentItemCode(String parentItemCode) {
		this.parentItemCode = parentItemCode;
	}

	public String getHspId() {
		return hspId;
	}

	public void setHspId(String hspId) {
		this.hspId = hspId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public class HspBean {
		private String id = "";
		private String itemCode = "";
		private String itemName = "";
		private String parentItemCode = "";
		private String commConfigUnittypeId = "";
		private String commConfigUnitgradeId = "";
		private String commConfigEconkindId = "";
		private String commConfigLocationId1 = "";
		private String commConfigLocationId2 = "";
		private String commConfigLocationId3 = "";
		private String commConfigLocationTownId = "";
		private String commClvId = "";
		private String address = "";
		private String zipcode = "";
		private String phone = "";
		private String dateClinicNum = "";
		private String yearOuthospitalNum = "";
		private String authorizedBedNum = "";
		private String outspreadBedNum = "";
		private String doctorNum = "";
		private String nurseNum = "";
		private String technicPersonNum = "";
		private String sickroomNum = "";
		private String operationroomNum = "";
		private String comments = "";
		private String seqNo = "";
		private String inputCode = "";
		private String levelDesc = "";
		private String commConfigHospitalTypeId = "";
		private String commConfigFtManageId = "";
		private String EMail = "";
		private String domainName = "";
		private String createDate = "";
		private String createUserId = "";
		private String createUserName = "";
		private String parentItemName = "";
		private String commConfigUnittypeName = "";
		private String commConfigUnitgradeName = "";
		private String cchtName = "";
		private String commConfigFtManageName = "";
		private String commConfigEconkindName = "";
		private String commConfigLocationName1 = "";
		private String commConfigLocationName2 = "";
		private String commConfigLocationName3 = "";
		private String ccltName = "";
		private String commClvName = "";
		private String contactPersonName = "";
		private String commConfigSetTypes = "";
		private String commConfigCovaffrss = "";
		private String staffId = "";
		private String staff;
		private String staffHspId = "";
		private FormFile photo;
		private String isPhoto = "";
		private String staffHsp;
		private String staffName;
		private String tseqno;
		// 时间
		private String createDateYear = "";
		private String createDateMonth = "";
		private String createDateDay = "";
		// 乡镇
		private String town = "";
		private String village = "";
		private String hspConfigBaseInfo = "";
		// 年份
		private int year;
		private String govaffrs = "";
		// 是否颁发证书
		private String isGetCer = "";
		private String hcaId = "";
		private String caCode = "";
		private String caName = "";
		private String passWord = "";
		private String hspConfigBaseInfoId1 = "";
		private String hspConfigBaseInfoId2 = "";
		private String hspConfigBaseInfoId3 = "";
		private String hspConfigBaseInfoId4 = "";
		private String hspConfigBaseInfoId5 = "";
		private String hspConfigBaseInfoId1Name = "";
		private String hspConfigBaseInfoId2Name = "";
		private String hspConfigBaseInfoId3Name = "";
		private String hspConfigBaseInfoId4Name = "";
		private String hspConfigBaseInfoId5Name = "";
		private String parentItemCode_name = "";

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getParentItemCode() {
			return parentItemCode;
		}

		public void setParentItemCode(String parentItemCode) {
			this.parentItemCode = parentItemCode;
		}

		public String getCommConfigUnittypeId() {
			return commConfigUnittypeId;
		}

		public void setCommConfigUnittypeId(String commConfigUnittypeId) {
			this.commConfigUnittypeId = commConfigUnittypeId;
		}

		public String getCommConfigUnitgradeId() {
			return commConfigUnitgradeId;
		}

		public void setCommConfigUnitgradeId(String commConfigUnitgradeId) {
			this.commConfigUnitgradeId = commConfigUnitgradeId;
		}

		public String getCommConfigEconkindId() {
			return commConfigEconkindId;
		}

		public void setCommConfigEconkindId(String commConfigEconkindId) {
			this.commConfigEconkindId = commConfigEconkindId;
		}

		public String getCommConfigLocationId1() {
			return commConfigLocationId1;
		}

		public void setCommConfigLocationId1(String commConfigLocationId1) {
			this.commConfigLocationId1 = commConfigLocationId1;
		}

		public String getCommConfigLocationId2() {
			return commConfigLocationId2;
		}

		public void setCommConfigLocationId2(String commConfigLocationId2) {
			this.commConfigLocationId2 = commConfigLocationId2;
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getDateClinicNum() {
			return dateClinicNum;
		}

		public void setDateClinicNum(String dateClinicNum) {
			this.dateClinicNum = dateClinicNum;
		}

		public String getYearOuthospitalNum() {
			return yearOuthospitalNum;
		}

		public void setYearOuthospitalNum(String yearOuthospitalNum) {
			this.yearOuthospitalNum = yearOuthospitalNum;
		}

		public String getAuthorizedBedNum() {
			return authorizedBedNum;
		}

		public void setAuthorizedBedNum(String authorizedBedNum) {
			this.authorizedBedNum = authorizedBedNum;
		}

		public String getOutspreadBedNum() {
			return outspreadBedNum;
		}

		public void setOutspreadBedNum(String outspreadBedNum) {
			this.outspreadBedNum = outspreadBedNum;
		}

		public String getDoctorNum() {
			return doctorNum;
		}

		public void setDoctorNum(String doctorNum) {
			this.doctorNum = doctorNum;
		}

		public String getNurseNum() {
			return nurseNum;
		}

		public void setNurseNum(String nurseNum) {
			this.nurseNum = nurseNum;
		}

		public String getTechnicPersonNum() {
			return technicPersonNum;
		}

		public void setTechnicPersonNum(String technicPersonNum) {
			this.technicPersonNum = technicPersonNum;
		}

		public String getSickroomNum() {
			return sickroomNum;
		}

		public void setSickroomNum(String sickroomNum) {
			this.sickroomNum = sickroomNum;
		}

		public String getOperationroomNum() {
			return operationroomNum;
		}

		public void setOperationroomNum(String operationroomNum) {
			this.operationroomNum = operationroomNum;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		public String getSeqNo() {
			return seqNo;
		}

		public void setSeqNo(String seqNo) {
			this.seqNo = seqNo;
		}

		public String getInputCode() {
			return inputCode;
		}

		public void setInputCode(String inputCode) {
			this.inputCode = inputCode;
		}

		public String getLevelDesc() {
			return levelDesc;
		}

		public void setLevelDesc(String levelDesc) {
			this.levelDesc = levelDesc;
		}

		public String getCommConfigHospitalTypeId() {
			return commConfigHospitalTypeId;
		}

		public void setCommConfigHospitalTypeId(String commConfigHospitalTypeId) {
			this.commConfigHospitalTypeId = commConfigHospitalTypeId;
		}

		public String getCommConfigFtManageId() {
			return commConfigFtManageId;
		}

		public void setCommConfigFtManageId(String commConfigFtManageId) {
			this.commConfigFtManageId = commConfigFtManageId;
		}

		public String getEMail() {
			return EMail;
		}

		public void setEMail(String mail) {
			EMail = mail;
		}

		public String getDomainName() {
			return domainName;
		}

		public void setDomainName(String domainName) {
			this.domainName = domainName;
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

		public String getParentItemName() {
			return parentItemName;
		}

		public void setParentItemName(String parentItemName) {
			this.parentItemName = parentItemName;
		}

		public String getCommConfigUnittypeName() {
			return commConfigUnittypeName;
		}

		public void setCommConfigUnittypeName(String commConfigUnittypeName) {
			this.commConfigUnittypeName = commConfigUnittypeName;
		}

		public String getCommConfigUnitgradeName() {
			return commConfigUnitgradeName;
		}

		public void setCommConfigUnitgradeName(String commConfigUnitgradeName) {
			this.commConfigUnitgradeName = commConfigUnitgradeName;
		}

		public String getCchtName() {
			return cchtName;
		}

		public void setCchtName(String cchtName) {
			this.cchtName = cchtName;
		}

		public String getCommConfigFtManageName() {
			return commConfigFtManageName;
		}

		public void setCommConfigFtManageName(String commConfigFtManageName) {
			this.commConfigFtManageName = commConfigFtManageName;
		}

		public String getCommConfigEconkindName() {
			return commConfigEconkindName;
		}

		public void setCommConfigEconkindName(String commConfigEconkindName) {
			this.commConfigEconkindName = commConfigEconkindName;
		}

		public String getCommConfigLocationName1() {
			return commConfigLocationName1;
		}

		public void setCommConfigLocationName1(String commConfigLocationName1) {
			this.commConfigLocationName1 = commConfigLocationName1;
		}

		public String getCommConfigLocationName2() {
			return commConfigLocationName2;
		}

		public void setCommConfigLocationName2(String commConfigLocationName2) {
			this.commConfigLocationName2 = commConfigLocationName2;
		}

		public String getCommConfigLocationName3() {
			return commConfigLocationName3;
		}

		public void setCommConfigLocationName3(String commConfigLocationName3) {
			this.commConfigLocationName3 = commConfigLocationName3;
		}

		public String getCcltName() {
			return ccltName;
		}

		public void setCcltName(String ccltName) {
			this.ccltName = ccltName;
		}

		public String getCommClvName() {
			return commClvName;
		}

		public void setCommClvName(String commClvName) {
			this.commClvName = commClvName;
		}

		public String getContactPersonName() {
			return contactPersonName;
		}

		public void setContactPersonName(String contactPersonName) {
			this.contactPersonName = contactPersonName;
		}

		public String getCommConfigSetTypes() {
			return commConfigSetTypes;
		}

		public void setCommConfigSetTypes(String commConfigSetTypes) {
			this.commConfigSetTypes = commConfigSetTypes;
		}

		public String getCommConfigCovaffrss() {
			return commConfigCovaffrss;
		}

		public void setCommConfigCovaffrss(String commConfigCovaffrss) {
			this.commConfigCovaffrss = commConfigCovaffrss;
		}

		public String getStaffId() {
			return staffId;
		}

		public void setStaffId(String staffId) {
			this.staffId = staffId;
		}

		public String getStaff() {
			return staff;
		}

		public void setStaff(String staff) {
			this.staff = staff;
		}

		public String getStaffHspId() {
			return staffHspId;
		}

		public void setStaffHspId(String staffHspId) {
			this.staffHspId = staffHspId;
		}

		public FormFile getPhoto() {
			return photo;
		}

		public void setPhoto(FormFile photo) {
			this.photo = photo;
		}

		public String getIsPhoto() {
			return isPhoto;
		}

		public void setIsPhoto(String isPhoto) {
			this.isPhoto = isPhoto;
		}

		public String getStaffHsp() {
			return staffHsp;
		}

		public void setStaffHsp(String staffHsp) {
			this.staffHsp = staffHsp;
		}

		public String getStaffName() {
			return staffName;
		}

		public void setStaffName(String staffName) {
			this.staffName = staffName;
		}

		public String getTseqno() {
			return tseqno;
		}

		public void setTseqno(String tseqno) {
			this.tseqno = tseqno;
		}

		public String getCreateDateYear() {
			return createDateYear;
		}

		public void setCreateDateYear(String createDateYear) {
			this.createDateYear = createDateYear;
		}

		public String getCreateDateMonth() {
			return createDateMonth;
		}

		public void setCreateDateMonth(String createDateMonth) {
			this.createDateMonth = createDateMonth;
		}

		public String getCreateDateDay() {
			return createDateDay;
		}

		public void setCreateDateDay(String createDateDay) {
			this.createDateDay = createDateDay;
		}

		public String getTown() {
			return town;
		}

		public void setTown(String town) {
			this.town = town;
		}

		public String getVillage() {
			return village;
		}

		public void setVillage(String village) {
			this.village = village;
		}

		public String getHspConfigBaseInfo() {
			return hspConfigBaseInfo;
		}

		public void setHspConfigBaseInfo(String hspConfigBaseInfo) {
			this.hspConfigBaseInfo = hspConfigBaseInfo;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public String getGovaffrs() {
			return govaffrs;
		}

		public void setGovaffrs(String govaffrs) {
			this.govaffrs = govaffrs;
		}

		public String getIsGetCer() {
			return isGetCer;
		}

		public void setIsGetCer(String isGetCer) {
			this.isGetCer = isGetCer;
		}

		public String getHcaId() {
			return hcaId;
		}

		public void setHcaId(String hcaId) {
			this.hcaId = hcaId;
		}

		public String getCaCode() {
			return caCode;
		}

		public void setCaCode(String caCode) {
			this.caCode = caCode;
		}

		public String getCaName() {
			return caName;
		}

		public void setCaName(String caName) {
			this.caName = caName;
		}

		public String getPassWord() {
			return passWord;
		}

		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}

		public String getHspConfigBaseInfoId1() {
			return hspConfigBaseInfoId1;
		}

		public void setHspConfigBaseInfoId1(String hspConfigBaseInfoId1) {
			this.hspConfigBaseInfoId1 = hspConfigBaseInfoId1;
		}

		public String getHspConfigBaseInfoId2() {
			return hspConfigBaseInfoId2;
		}

		public void setHspConfigBaseInfoId2(String hspConfigBaseInfoId2) {
			this.hspConfigBaseInfoId2 = hspConfigBaseInfoId2;
		}

		public String getHspConfigBaseInfoId3() {
			return hspConfigBaseInfoId3;
		}

		public void setHspConfigBaseInfoId3(String hspConfigBaseInfoId3) {
			this.hspConfigBaseInfoId3 = hspConfigBaseInfoId3;
		}

		public String getHspConfigBaseInfoId4() {
			return hspConfigBaseInfoId4;
		}

		public void setHspConfigBaseInfoId4(String hspConfigBaseInfoId4) {
			this.hspConfigBaseInfoId4 = hspConfigBaseInfoId4;
		}

		public String getHspConfigBaseInfoId5() {
			return hspConfigBaseInfoId5;
		}

		public void setHspConfigBaseInfoId5(String hspConfigBaseInfoId5) {
			this.hspConfigBaseInfoId5 = hspConfigBaseInfoId5;
		}

		public String getHspConfigBaseInfoId1Name() {
			return hspConfigBaseInfoId1Name;
		}

		public void setHspConfigBaseInfoId1Name(String hspConfigBaseInfoId1Name) {
			this.hspConfigBaseInfoId1Name = hspConfigBaseInfoId1Name;
		}

		public String getHspConfigBaseInfoId2Name() {
			return hspConfigBaseInfoId2Name;
		}

		public void setHspConfigBaseInfoId2Name(String hspConfigBaseInfoId2Name) {
			this.hspConfigBaseInfoId2Name = hspConfigBaseInfoId2Name;
		}

		public String getHspConfigBaseInfoId3Name() {
			return hspConfigBaseInfoId3Name;
		}

		public void setHspConfigBaseInfoId3Name(String hspConfigBaseInfoId3Name) {
			this.hspConfigBaseInfoId3Name = hspConfigBaseInfoId3Name;
		}

		public String getHspConfigBaseInfoId4Name() {
			return hspConfigBaseInfoId4Name;
		}

		public void setHspConfigBaseInfoId4Name(String hspConfigBaseInfoId4Name) {
			this.hspConfigBaseInfoId4Name = hspConfigBaseInfoId4Name;
		}

		public String getHspConfigBaseInfoId5Name() {
			return hspConfigBaseInfoId5Name;
		}

		public void setHspConfigBaseInfoId5Name(String hspConfigBaseInfoId5Name) {
			this.hspConfigBaseInfoId5Name = hspConfigBaseInfoId5Name;
		}

		public String getParentItemCode_name() {
			return parentItemCode_name;
		}

		public void setParentItemCode_name(String parentItemCode_name) {
			this.parentItemCode_name = parentItemCode_name;
		}

	}

	public class DeptBean {
		private String hspId;
		private String deptCode;
		private String deptName;
		private Long seqNo;
		private String deptAttrCode;
		private String deptAttrName;
		private String inputCode;
		private String comments;
		private String hspConfigBaseinfoName;
		private String healthBureauCode;
		private String socialSecurityBureauCode;
		
		private LinkedHashMap<String, String> deptAttrMap;

		public LinkedHashMap<String, String> getDeptAttrMap() {
			return deptAttrMap;
		}

		public void setDeptAttrMap(LinkedHashMap<String, String> deptAttrMap) {
			this.deptAttrMap = deptAttrMap;
		}

		public String getHspId() {
			return hspId;
		}

		public void setHspId(String hspId) {
			this.hspId = hspId;
		}

		public Long getSeqNo() {
			return seqNo;
		}

		public void setSeqNo(Long seqNo) {
			this.seqNo = seqNo;
		}

		public String getDeptAttrCode() {
			return deptAttrCode;
		}

		public void setDeptAttrCode(String deptAttrCode) {
			this.deptAttrCode = deptAttrCode;
		}

		public String getDeptAttrName() {
			return deptAttrName;
		}

		public void setDeptAttrName(String deptAttrName) {
			this.deptAttrName = deptAttrName;
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

		public String getHspConfigBaseinfoName() {
			return hspConfigBaseinfoName;
		}

		public void setHspConfigBaseinfoName(String hspConfigBaseinfoName) {
			this.hspConfigBaseinfoName = hspConfigBaseinfoName;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}

		public String getHealthBureauCode() {
			return healthBureauCode;
		}

		public void setHealthBureauCode(String healthBureauCode) {
			this.healthBureauCode = healthBureauCode;
		}

		public String getSocialSecurityBureauCode() {
			return socialSecurityBureauCode;
		}

		public void setSocialSecurityBureauCode(String socialSecurityBureauCode) {
			this.socialSecurityBureauCode = socialSecurityBureauCode;
		}

		public String getDeptCode() {
			return deptCode;
		}

		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}

	}

	public class StaffBean {
		/** ID */
		private String id;
		/** 组织机构ID */
		private String hspConfigBaseinfoId;
		
		private String hspConfigBaseinfoName;
		/** 人员编码(卫生局统一) */
		private String empNo;
		/** 姓名 */
		private String name;
		/** 证件号码码 */
		private String idNo;
		/** 出生日期 */
		private Date birthday;
		/** 性别 */
		private String commConfigSexId;
		private String commConfigSexName;
		/** 民族 */
		private String commConfigNationalityId;
		private String commConfigNationalityName;
		/** 参加工作日期 */
		private Date startWorkDate;
		/** 办公室电话 */
		private String officeTel;
		/** 手机号码 */
		private String mobileTel;
		/** 从事专业类别 */
		private String commDictPublicCharId1;
		private String commDictPublicCharName1;
		/** （ 医师/ 卫生监督员）执业证书编码 */
		private String workCertificateNo;
		/** 医师执业类别 */
		private String commDictPublicCharId2;
		private String commDictPublicCharName2;
		/** 行政职务 */
		private String commConfigPositiontitleId;
		private String commConfigPositiontitleName;
		/** 专业技术资格（评） */
		private String commConfigEmptitleId;
		private String commConfigEmptitleName;
		/** 专业技术职务（聘） */
		private String commDictPublicCharId3;
		private String commDictPublicCharName3;
		/** 学历 */
		private String commConfigDegreeId;
		private String commConfigDegreeName;
		/** 学位 */
		private String commConfigDegreeLevelId;
		private String commConfigDegreeLevelName;
		/** 所学专业 */
		private String commConfigProfessionId;
		private String commConfigProfessionName;
		/** 在位标志 */
		private Long islocation;
		/** 记录日期 */
		private String createDate;
		/** 记录人员ID */
		private String createUserId;
		/** 记录人员名称 */
		private String createUserName;
		/** 所属科室 */
		private String relatedDepartment = "";
		
		private String deptName;

		private String personnelCode;

		private String securityUserBaseinfoId;
		
		private String createYear;
		private String createMonth;
		private String createDay;
		
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

		public String getHspConfigBaseinfoName() {
			return hspConfigBaseinfoName;
		}

		public void setHspConfigBaseinfoName(String hspConfigBaseinfoName) {
			this.hspConfigBaseinfoName = hspConfigBaseinfoName;
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

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public String getCommConfigSexId() {
			return commConfigSexId;
		}

		public void setCommConfigSexId(String commConfigSexId) {
			this.commConfigSexId = commConfigSexId;
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

		public String getCommDictPublicCharId1() {
			return commDictPublicCharId1;
		}

		public void setCommDictPublicCharId1(String commDictPublicCharId1) {
			this.commDictPublicCharId1 = commDictPublicCharId1;
		}

		public String getWorkCertificateNo() {
			return workCertificateNo;
		}

		public void setWorkCertificateNo(String workCertificateNo) {
			this.workCertificateNo = workCertificateNo;
		}

		public String getCommDictPublicCharId2() {
			return commDictPublicCharId2;
		}

		public void setCommDictPublicCharId2(String commDictPublicCharId2) {
			this.commDictPublicCharId2 = commDictPublicCharId2;
		}

		public String getCommConfigPositiontitleId() {
			return commConfigPositiontitleId;
		}

		public void setCommConfigPositiontitleId(
				String commConfigPositiontitleId) {
			this.commConfigPositiontitleId = commConfigPositiontitleId;
		}

		public String getCommConfigEmptitleId() {
			return commConfigEmptitleId;
		}

		public void setCommConfigEmptitleId(String commConfigEmptitleId) {
			this.commConfigEmptitleId = commConfigEmptitleId;
		}

		public String getCommDictPublicCharId3() {
			return commDictPublicCharId3;
		}

		public void setCommDictPublicCharId3(String commDictPublicCharId3) {
			this.commDictPublicCharId3 = commDictPublicCharId3;
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

		public Long getIslocation() {
			return islocation;
		}

		public void setIslocation(Long islocation) {
			this.islocation = islocation;
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

		public String getRelatedDepartment() {
			return relatedDepartment;
		}

		public void setRelatedDepartment(String relatedDepartment) {
			this.relatedDepartment = relatedDepartment;
		}

		public String getPersonnelCode() {
			return personnelCode;
		}

		public void setPersonnelCode(String personnelCode) {
			this.personnelCode = personnelCode;
		}

		public String getSecurityUserBaseinfoId() {
			return securityUserBaseinfoId;
		}

		public void setSecurityUserBaseinfoId(String securityUserBaseinfoId) {
			this.securityUserBaseinfoId = securityUserBaseinfoId;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}

		public String getCreateYear() {
			return createYear;
		}

		public void setCreateYear(String createYear) {
			this.createYear = createYear;
		}

		public String getCreateMonth() {
			return createMonth;
		}

		public void setCreateMonth(String createMonth) {
			this.createMonth = createMonth;
		}

		public String getCreateDay() {
			return createDay;
		}

		public void setCreateDay(String createDay) {
			this.createDay = createDay;
		}

		public String getCommConfigSexName() {
			return commConfigSexName;
		}

		public void setCommConfigSexName(String commConfigSexName) {
			this.commConfigSexName = commConfigSexName;
		}

		public String getCommConfigNationalityName() {
			return commConfigNationalityName;
		}

		public void setCommConfigNationalityName(String commConfigNationalityName) {
			this.commConfigNationalityName = commConfigNationalityName;
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

		public String getCommConfigEmptitleName() {
			return commConfigEmptitleName;
		}

		public void setCommConfigEmptitleName(String commConfigEmptitleName) {
			this.commConfigEmptitleName = commConfigEmptitleName;
		}

	}

	public class EquipBean {

	}

}
