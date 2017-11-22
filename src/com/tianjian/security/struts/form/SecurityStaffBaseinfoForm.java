package com.tianjian.security.struts.form;


import java.io.Serializable;
import org.apache.struts.action.ActionForm;
/**
 * SECURITY_STAFF_BASEINFO医务人员基本信息表用ActionForm<br>
 * MODUFY BY DZENALL 08-3-25 15:07
 * @author 
 */
public class SecurityStaffBaseinfoForm extends ActionForm implements Serializable {
	
	private static final long serialVersionUID = 2717849952365436900L;
	/*Bean Fields*/
	private String id                     ;
	private String staffCode              ;
	private String staffType			  ;//操作人员类别字段	
	private String passwd                 ;//原來的Bean中沒有這個字段
	private String hspConfigBaseinfoId	  ;
	private String hspStaffBaseinfoId	  ;
	private String email                  ;
	private String name                   ;
	private String nameEn                 ;
	private String inputCode              ;
	private String pinyinInputCode        ;
	private String commConfigSexId        ;
//	private String dateOfBirth            ;
	private String year                   ;
	private String month                  ;
	private String day                    ;
	private String commConfigStafftypeId  ;
	private String idNo                   ;
	private String phone                  ;
	private String islocation             ;
	private String islocationName         ;
	private String comments               ;
	private String seqNo             ;
	private String createDate;
	private String createUserId;
	private String createUserName;
	//时间
	private String createDateYear="";
	private String createDateMonth="";
	private String createDateDay="";  
	
	/*Selected Names In Each Selection*/
	private String hspConfigBaseinfoName    ;
	private String commConfigSexName        ;
	private String commConfigStafftypeName  ;
	/*Common Fields*/
	private String verbId;
    private String message;
    private String orderNo;
    private String asc;
    private String staffId;
    
    //注册信息
    private String registCode;
    private String regTime;
    private String startTime;
    private String stopDate;
    
    
    
    
    private String homePageType;
    /*Selecting Collection In Each Selection*/
	private String[] hspConfigBaseinfoIds     ;
	private String[] commConfigSexIds         ;
	private String[] commConfigStafftypeIds   ;
	private String[] islocationIds            ;
	
	private String[] hspConfigBaseinfoNames   ; 
	private String[] commConfigSexNames       ; 
	private String[] commConfigStafftypeNames ; 
	private String[] islocationNames          ;
	/*View Page Lists*/
	private String[] idList;
	private String[] staffCodeList;
	private String[] hspConfigBaseinfoIdList;
	private String[] hspConfigBaseinfoNameList;
	private String[] nameList;
	private String[] commConfigSexIdList;
	private String[] commConfigSexNameList;
	private String[] dateOfBirthList;
	private String[] regTimeList;
	private String[] stopTimeList;
	private String[] registCodeList;
	
	/*Constructor*/
	public SecurityStaffBaseinfoForm(){
		this.id                     = "";   
		this.staffCode              = "";  
		this.passwd 				= "";//原來的Bean中沒有這個字段
		this.hspConfigBaseinfoId    = "";  
		this.hspStaffBaseinfoId     = "";
		this.email                  = "";
		this.name                   = "";   
		this.nameEn                 = "";  
		this.inputCode              = "";
		this.pinyinInputCode        = "";
		this.commConfigSexId        = ""; 
//		this.dateOfBirth            = ""; 
		this.year                   = "";
		this.month                  = "";
		this.day                    = "";
		this.commConfigStafftypeId  = ""; 
		this.idNo                   = ""; 
		this.phone                  = "";  
		this.islocation             = "";  
		this.comments               = ""; 
		this.seqNo                  = ""; 
		this.islocationName 		= "";		
		this.hspConfigBaseinfoName  = "";   
		this.commConfigSexName      = ""; 
		this.commConfigStafftypeName= ""; 
		
		this.homePageType 			= "";
		this.staffType   			= "";
		this.createUserId			= "";
		this.createUserName			= "";
		
		this.verbId 				= "";
		this.message 				= "";
		this.orderNo 				= "";
		this.asc 					= "";
		this.staffId    			= "";    
		
		this.registCode             = "";
		this.regTime 				= "";
		this.startTime				= "";
		this.stopDate 				= "";
	}

	public String getPinyinInputCode() {
		return pinyinInputCode;
	}

	public void setPinyinInputCode(String pinyinInputCode) {
		this.pinyinInputCode = pinyinInputCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
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

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getCommConfigSexId() {
		return commConfigSexId;
	}

	public void setCommConfigSexId(String commConfigSexId) {
		this.commConfigSexId = commConfigSexId;
	}

//	public String getDateOfBirth() {
//		return dateOfBirth;
//	}
//
//	public void setDateOfBirth(String dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}

	public String getCommConfigStafftypeId() {
		return commConfigStafftypeId;
	}

	public void setCommConfigStafftypeId(String commConfigStafftypeId) {
		this.commConfigStafftypeId = commConfigStafftypeId;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
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

	public String getCommConfigSexName() {
		return commConfigSexName;
	}

	public void setCommConfigSexName(String commConfigSexName) {
		this.commConfigSexName = commConfigSexName;
	}

	public String getCommConfigStafftypeName() {
		return commConfigStafftypeName;
	}

	public void setCommConfigStafftypeName(String commConfigStafftypeName) {
		this.commConfigStafftypeName = commConfigStafftypeName;
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

	public String[] getHspConfigBaseinfoIds() {
		return hspConfigBaseinfoIds;
	}

	public void setHspConfigBaseinfoIds(String[] hspConfigBaseinfoIds) {
		this.hspConfigBaseinfoIds = hspConfigBaseinfoIds;
	}

	public String[] getCommConfigSexIds() {
		return commConfigSexIds;
	}

	public void setCommConfigSexIds(String[] commConfigSexIds) {
		this.commConfigSexIds = commConfigSexIds;
	}

	public String[] getCommConfigStafftypeIds() {
		return commConfigStafftypeIds;
	}

	public void setCommConfigStafftypeIds(String[] commConfigStafftypeIds) {
		this.commConfigStafftypeIds = commConfigStafftypeIds;
	}

	

	public String[] getHspConfigBaseinfoNames() {
		return hspConfigBaseinfoNames;
	}

	public void setHspConfigBaseinfoNames(String[] hspConfigBaseinfoNames) {
		this.hspConfigBaseinfoNames = hspConfigBaseinfoNames;
	}

	public String[] getCommConfigSexNames() {
		return commConfigSexNames;
	}

	public void setCommConfigSexNames(String[] commConfigSexNames) {
		this.commConfigSexNames = commConfigSexNames;
	}

	public String[] getCommConfigStafftypeNames() {
		return commConfigStafftypeNames;
	}

	public void setCommConfigStafftypeNames(String[] commConfigStafftypeNames) {
		this.commConfigStafftypeNames = commConfigStafftypeNames;
	}

	
	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String[] getIslocationIds() {
		return islocationIds;
	}

	public void setIslocationIds(String[] islocationIds) {
		this.islocationIds = islocationIds;
	}

	public String[] getIslocationNames() {
		return islocationNames;
	}

	public void setIslocationNames(String[] islocationNames) {
		this.islocationNames = islocationNames;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getIslocationName() {
		return islocationName;
	}

	public void setIslocationName(String islocationName) {
		this.islocationName = islocationName;
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

	
	public String getSeqNo() {
		return seqNo;
	}

	
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getHomePageType() {
		return homePageType;
	}

	public void setHomePageType(String homePageType) {
		this.homePageType = homePageType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	
	
	public String[] getRegTimeList() {
		return regTimeList;
	}

	public void setRegTimeList(String[] regTimeList) {
		this.regTimeList = regTimeList;
	}

	public String[] getStopTimeList() {
		return stopTimeList;
	}

	public void setStopTimeList(String[] stopTimeList) {
		this.stopTimeList = stopTimeList;
	}

	public String getRegistCode() {
		return registCode;
	}

	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopDate() {
		return stopDate;
	}

	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
	}

	public String[] getRegistCodeList() {
		return registCodeList;
	}

	public void setRegistCodeList(String[] registCodeList) {
		this.registCodeList = registCodeList;
	}

	public String getHspStaffBaseinfoId() {
		return hspStaffBaseinfoId;
	}

	public void setHspStaffBaseinfoId(String hspStaffBaseinfoId) {
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
	}
	
}