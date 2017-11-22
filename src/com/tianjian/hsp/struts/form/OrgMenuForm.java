package com.tianjian.hsp.struts.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tianjian.comm.bean.CommConfigDeptAttr;
import com.tianjian.hsp.bean.HspDeptBaseinfo;
import com.tianjian.hsp.bean.ZTreeNode;

public class OrgMenuForm extends ActionForm{

	private static final long serialVersionUID = 18773423476867L;
	
	private List<ZTreeNode> firstNodeList;
	private List<ZTreeNode> secondNodeList;
	private List<ZTreeNode> thirdNodeList;
	private List<ZTreeNode> fourthNodeList;
	private List<ZTreeNode> fifthNodeList;
	private List<CommConfigDeptAttr> deptAttrList;
	
	private List<ZTreeNode> childNodeList;
	
	private String message;
	private String newNode;
	private String menuId;
	private String pId;
	
	private String status;
	
	//科室
	private String seqNo;
	private String hspConfigBaseinfoId;
	private String hspConfigBaseinfoName;
	private String healthBureauCode;
	private String socialSecurityBureauCode;
	private String deptAttrCode="";
	private String deptAttrName="";
	private String deptCode="";
	private String deptName="";
	private String inputCode;
	private String comments;
	
	//人员信息
	/**ID*/
	private String id;
	/**组织机构ID*/
	private String hspConfigBaseinfoId1;
	/**人员编码(卫生局统一)*/
	private String empNo;
	/**姓名*/
	private String name;
	/**证件号码码*/
	private String idNo;
	/**出生日期*/
	private Date birthday;
	/**性别*/
	private String commConfigSexId;
	/**民族*/
	private String commConfigNationalityId;
	/**参加工作日期*/
	private Date startWorkDate;
	/**办公室电话*/
	private String officeTel;
	/**手机号码*/
	private String mobileTel;
	/**从事专业类别*/
	private String commDictPublicCharId1;
	/**（ 医师/ 卫生监督员）执业证书编码*/
	private String workCertificateNo;
	/**医师执业类别*/
	private String commDictPublicCharId2;
	/**行政职务*/
	private String commConfigPositiontitleId;
	/**专业技术资格（评）*/
	private String commConfigEmptitleId;
	/**专业技术职务（聘）*/
	private String commDictPublicCharId3;
	/**学历*/
	private String commConfigDegreeId;
	/**学位*/
	private String commConfigDegreeLevelId;
	/**所学专业*/
	private String commConfigProfessionId;
	/**在位标志*/
	private Long islocation;
	/**记录日期*/
	private String createDate;
	/**记录人员ID*/
	private String createUserId;
	/**记录人员名称*/
	private String createUserName;
	/**所属科室*/
	private String relatedDepartment="";
	
	private String personnelCode;
	
	private String theNationalName;//民族名称
	private String hspConfigBaseinfoIdHidden;
	private String nationalityIdHidden;
	private String hspStaffBaseinfoId;
	private String securityUserBaseinfoId;

	/**操作类型变量(公用的)*/
	private String verbId;
	private String orderNo;
	private String asc;
	private String itemCodeHidden;
	private String totalPage;
	private String idHidden;
	
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
	
	private List<HspDeptBaseinfo> deptList;
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
    
    private String queryType;
    private String queryKey;
    private String queryValue;
    
    private String itemName;
    private String itemCode;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getQueryKey() {
		return queryKey;
	}
	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
	public OrgMenuForm(){
		/**ID*/
		id="";
		/**组织机构ID*/
		hspConfigBaseinfoId="";
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
//		
//		staffId="";
	    
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
		hspConfigBaseinfoName="";//医院名称
		theNationalName="";//民族名称
		
		hspConfigBaseinfoIdHidden="";
		nationalityIdHidden="";
	}
	public OrgMenuForm(List<ZTreeNode> firstNodeList,
			List<ZTreeNode> secondNodeList, List<ZTreeNode> thirdNodeList,
			List<ZTreeNode> fourthNodeList, List<ZTreeNode> fifthNodeList,
			List<CommConfigDeptAttr> deptAttrList,
			List<ZTreeNode> childNodeList, String message, String newNode,
			String menuId, String id, String status, String seqNo,
			String hspConfigBaseinfoId, String hspConfigBaseinfoName,
			String healthBureauCode, String socialSecurityBureauCode,
			String deptAttrCode, String deptAttrName, String deptCode,
			String deptName, String inputCode, String comments, String id2,
			String hspConfigBaseinfoId1, String empNo, String name,
			String idNo, Date birthday, String commConfigSexId,
			String commConfigNationalityId, Date startWorkDate,
			String officeTel, String mobileTel, String commDictPublicCharId1,
			String workCertificateNo, String commDictPublicCharId2,
			String commConfigPositiontitleId, String commConfigEmptitleId,
			String commDictPublicCharId3, String commConfigDegreeId,
			String commConfigDegreeLevelId, String commConfigProfessionId,
			Long islocation, String createDate, String createUserId,
			String createUserName, String relatedDepartment,
			String personnelCode, String theNationalName,
			String hspConfigBaseinfoIdHidden, String nationalityIdHidden,
			String hspStaffBaseinfoId, String securityUserBaseinfoId,
			String verbId, String orderNo, String asc, String itemCodeHidden,
			String totalPage, String idHidden, String birthdayYear,
			String birthdayMonth, String birthdayDay, String startWorkDateYear,
			String startWorkDateMonth, String year, String month, String day,
			List<HspDeptBaseinfo> deptList, List<?> hspConfigBaseinfoIdList,
			List<?> commConfigSexIdList, List<?> commConfigNationalityIdL,
			List<?> commDictPublicCharId1List,
			List<?> commDictPublicCharId2List,
			List<?> commConfigPositiontitleIdList,
			List<?> commConfigEmptitleIdList,
			List<?> commDictPublicCharId3List, List<?> commConfigDegreeIdList,
			List<?> commConfigDegreeLevelIdList,
			List<?> commConfigProfessionIdList) {
		super();
		this.firstNodeList = firstNodeList;
		this.secondNodeList = secondNodeList;
		this.thirdNodeList = thirdNodeList;
		this.fourthNodeList = fourthNodeList;
		this.fifthNodeList = fifthNodeList;
		this.deptAttrList = deptAttrList;
		this.childNodeList = childNodeList;
		this.message = message;
		this.newNode = newNode;
		this.menuId = menuId;
		pId = id;
		this.status = status;
		this.seqNo = seqNo;
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
		this.hspConfigBaseinfoName = hspConfigBaseinfoName;
		this.healthBureauCode = healthBureauCode;
		this.socialSecurityBureauCode = socialSecurityBureauCode;
		this.deptAttrCode = deptAttrCode;
		this.deptAttrName = deptAttrName;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.inputCode = inputCode;
		this.comments = comments;
		id = id2;
		this.hspConfigBaseinfoId1 = hspConfigBaseinfoId1;
		this.empNo = empNo;
		this.name = name;
		this.idNo = idNo;
		this.birthday = birthday;
		this.commConfigSexId = commConfigSexId;
		this.commConfigNationalityId = commConfigNationalityId;
		this.startWorkDate = startWorkDate;
		this.officeTel = officeTel;
		this.mobileTel = mobileTel;
		this.commDictPublicCharId1 = commDictPublicCharId1;
		this.workCertificateNo = workCertificateNo;
		this.commDictPublicCharId2 = commDictPublicCharId2;
		this.commConfigPositiontitleId = commConfigPositiontitleId;
		this.commConfigEmptitleId = commConfigEmptitleId;
		this.commDictPublicCharId3 = commDictPublicCharId3;
		this.commConfigDegreeId = commConfigDegreeId;
		this.commConfigDegreeLevelId = commConfigDegreeLevelId;
		this.commConfigProfessionId = commConfigProfessionId;
		this.islocation = islocation;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.relatedDepartment = relatedDepartment;
		this.personnelCode = personnelCode;
		this.theNationalName = theNationalName;
		this.hspConfigBaseinfoIdHidden = hspConfigBaseinfoIdHidden;
		this.nationalityIdHidden = nationalityIdHidden;
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
		this.securityUserBaseinfoId = securityUserBaseinfoId;
		this.verbId = verbId;
		this.orderNo = orderNo;
		this.asc = asc;
		this.itemCodeHidden = itemCodeHidden;
		this.totalPage = totalPage;
		this.idHidden = idHidden;
		this.birthdayYear = birthdayYear;
		this.birthdayMonth = birthdayMonth;
		this.birthdayDay = birthdayDay;
		this.startWorkDateYear = startWorkDateYear;
		this.startWorkDateMonth = startWorkDateMonth;
		this.year = year;
		this.month = month;
		this.day = day;
		this.deptList = deptList;
		this.hspConfigBaseinfoIdList = hspConfigBaseinfoIdList;
		this.commConfigSexIdList = commConfigSexIdList;
		this.commConfigNationalityIdL = commConfigNationalityIdL;
		this.commDictPublicCharId1List = commDictPublicCharId1List;
		this.commDictPublicCharId2List = commDictPublicCharId2List;
		this.commConfigPositiontitleIdList = commConfigPositiontitleIdList;
		this.commConfigEmptitleIdList = commConfigEmptitleIdList;
		this.commDictPublicCharId3List = commDictPublicCharId3List;
		this.commConfigDegreeIdList = commConfigDegreeIdList;
		this.commConfigDegreeLevelIdList = commConfigDegreeLevelIdList;
		this.commConfigProfessionIdList = commConfigProfessionIdList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHspConfigBaseinfoId1() {
		return hspConfigBaseinfoId1;
	}
	public void setHspConfigBaseinfoId1(String hspConfigBaseinfoId1) {
		this.hspConfigBaseinfoId1 = hspConfigBaseinfoId1;
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
	public void setCommConfigPositiontitleId(String commConfigPositiontitleId) {
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
	public List<HspDeptBaseinfo> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<HspDeptBaseinfo> deptList) {
		this.deptList = deptList;
	}
	public List<ZTreeNode> getFirstNodeList() {
		return firstNodeList;
	}
	public void setFirstNodeList(List<ZTreeNode> firstNodeList) {
		this.firstNodeList = firstNodeList;
	}
	public List<ZTreeNode> getSecondNodeList() {
		return secondNodeList;
	}
	public void setSecondNodeList(List<ZTreeNode> secondNodeList) {
		this.secondNodeList = secondNodeList;
	}
	public List<ZTreeNode> getThirdNodeList() {
		return thirdNodeList;
	}
	public void setThirdNodeList(List<ZTreeNode> thirdNodeList) {
		this.thirdNodeList = thirdNodeList;
	}
	public List<ZTreeNode> getFourthNodeList() {
		return fourthNodeList;
	}
	public void setFourthNodeList(List<ZTreeNode> fourthNodeList) {
		this.fourthNodeList = fourthNodeList;
	}
	public List<ZTreeNode> getFifthNodeList() {
		return fifthNodeList;
	}
	public void setFifthNodeList(List<ZTreeNode> fifthNodeList) {
		this.fifthNodeList = fifthNodeList;
	}
	public List<CommConfigDeptAttr> getDeptAttrList() {
		return deptAttrList;
	}
	public void setDeptAttrList(List<CommConfigDeptAttr> deptAttrList) {
		this.deptAttrList = deptAttrList;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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
	public List<ZTreeNode> getChildNodeList() {
		return childNodeList;
	}
	public void setChildNodeList(List<ZTreeNode> childNodeList) {
		this.childNodeList = childNodeList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNewNode() {
		return newNode;
	}
	public void setNewNode(String newNode) {
		this.newNode = newNode;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getPId() {
		return pId;
	}
	public void setPId(String id) {
		pId = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public List<?> getCommConfigNationalityIdL() {
		return commConfigNationalityIdL;
	}
	public void setCommConfigNationalityIdL(List<?> commConfigNationalityIdL) {
		this.commConfigNationalityIdL = commConfigNationalityIdL;
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
	public String getPersonnelCode() {
		return personnelCode;
	}
	public void setPersonnelCode(String personnelCode) {
		this.personnelCode = personnelCode;
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
	public String getTheNationalName() {
		return theNationalName;
	}
	public void setTheNationalName(String theNationalName) {
		this.theNationalName = theNationalName;
	}
	public String getHspConfigBaseinfoIdHidden() {
		return hspConfigBaseinfoIdHidden;
	}
	public void setHspConfigBaseinfoIdHidden(String hspConfigBaseinfoIdHidden) {
		this.hspConfigBaseinfoIdHidden = hspConfigBaseinfoIdHidden;
	}
	public String getNationalityIdHidden() {
		return nationalityIdHidden;
	}
	public void setNationalityIdHidden(String nationalityIdHidden) {
		this.nationalityIdHidden = nationalityIdHidden;
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
	public String getHspStaffBaseinfoId() {
		return hspStaffBaseinfoId;
	}
	public void setHspStaffBaseinfoId(String hspStaffBaseinfoId) {
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
	}
	public String getSecurityUserBaseinfoId() {
		return securityUserBaseinfoId;
	}
	public void setSecurityUserBaseinfoId(String securityUserBaseinfoId) {
		this.securityUserBaseinfoId = securityUserBaseinfoId;
	}

}
