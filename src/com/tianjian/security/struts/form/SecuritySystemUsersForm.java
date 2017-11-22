package com.tianjian.security.struts.form;

import java.util.Date;
import org.apache.struts.action.ActionForm;

/**
 * SECURITY_SYSTEM_USERS用户登陆表用ActionForm
 * @author DZENALL
 * @since 2008-3-24 15:39
 * 該糢塊中隻有註冊並驗證註冊碼業務，所以沒有verbId等公共字段
 */
public class SecuritySystemUsersForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6853316557847352633L;
	private String staffCode;
	private String regCode_1;
	private String regCode_2;
	private String regCode_3;
	private String regCode_4;
	private String regCode_5;
	private String securityStaffBaseinfoId;
	private String passwd;
	private String passwd_;
	private Long tagc;
	private Long tag;
	private Long licenseTag;
	private Date forbidTime;
	private Long forbidFlag;
	private Long limitedFlag;
	private String comments;
	/*公用数据集*/
	private String verbId="";
	private String message="";
	private String idHidden; //供修改.删除使用
	private String orderNo="";
	private String asc="";
	
	public SecuritySystemUsersForm(){
		staffCode = "";
		regCode_1 = "";
		regCode_2 = "";
		regCode_3 = "";
		regCode_4 = "";
		regCode_5 = "";
		securityStaffBaseinfoId = "";
		passwd = "";
		passwd_ = "";
		tagc = 0L;
		tag = 0L;
		licenseTag = 0L;
		forbidTime = new Date();
		forbidFlag = 0L;
		limitedFlag = 0L;
		comments = "";
		/*公用数据集*/
		verbId="";
		message="";
		idHidden = ""; //供修改.删除使用
		orderNo="";
		asc="";
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

	public String getSecurityStaffBaseinfoId() {
		return securityStaffBaseinfoId;
	}

	public void setSecurityStaffBaseinfoId(String securityStaffBaseinfoId) {
		this.securityStaffBaseinfoId = securityStaffBaseinfoId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Long getTagc() {
		return tagc;
	}

	public void setTagc(Long tagc) {
		this.tagc = tagc;
	}

	public Long getTag() {
		return tag;
	}

	public void setTag(Long tag) {
		this.tag = tag;
	}

	public Long getLicenseTag() {
		return licenseTag;
	}

	public void setLicenseTag(Long licenseTag) {
		this.licenseTag = licenseTag;
	}

	public Date getForbidTime() {
		return forbidTime;
	}

	public void setForbidTime(Date forbidTime) {
		this.forbidTime = forbidTime;
	}

	public Long getForbidFlag() {
		return forbidFlag;
	}

	public void setForbidFlag(Long forbidFlag) {
		this.forbidFlag = forbidFlag;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public String getRegCode_1() {
		return regCode_1;
	}

	public void setRegCode_1(String regCode_1) {
		this.regCode_1 = regCode_1;
	}

	public String getRegCode_2() {
		return regCode_2;
	}

	public void setRegCode_2(String regCode_2) {
		this.regCode_2 = regCode_2;
	}

	public String getRegCode_3() {
		return regCode_3;
	}

	public void setRegCode_3(String regCode_3) {
		this.regCode_3 = regCode_3;
	}

	public String getRegCode_4() {
		return regCode_4;
	}

	public void setRegCode_4(String regCode_4) {
		this.regCode_4 = regCode_4;
	}

	public String getRegCode_5() {
		return regCode_5;
	}

	public void setRegCode_5(String regCode_5) {
		this.regCode_5 = regCode_5;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getPasswd_() {
		return passwd_;
	}

	public void setPasswd_(String passwd_) {
		this.passwd_ = passwd_;
	}

	
	public Long getLimitedFlag() {
		return limitedFlag;
	}

	
	public void setLimitedFlag(Long limitedFlag) {
		this.limitedFlag = limitedFlag;
	}
}
