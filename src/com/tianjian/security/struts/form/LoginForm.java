//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.tianjian.security.struts.form;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 04-26-2007
 * 
 * XDoclet definition:
 * 
 * @struts.form name="ClinicItemNameDictForm"
 */
public class LoginForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4901812357079093400L;
	private String verbId;
	private String message;
	private String staffCode;// --userLoginCode 操作员用户登陆名
	private String password;// ---操作员用户密码
	private String staffId; // ---使用
	private String name; // ---使用
	private String systemUserLimitedFlag;
	// -------------------------------------------------------
	private String staffLicenseStopDate;// 操作员的帐号截止日期
	private String loginVerCode;// 验证码

	private String hospitalId; // ---医疗机构ID
	private String commConfigLocationId1;// 医疗机构所在省
	private String commConfigLocationId2;// 医疗机构所在市
	private String commConfigLocationId3;// 医疗机构所在县
	private String commCltId;// 医疗机构所在乡镇
	private String commClvId;// 医疗机构所在村

	private String homePageType;// 主界面类型
	private String staffType;// 操作员类型 0普通 1超级管理员
	// ------------------------------------------------
	private String versionUserName;// 软件授权显示客户名称
	private String versionStopDate;// 软件授权结束时间
	// ------------------------------------------------
	private String tenantId;// 租户id
	private Map maps;//分院map

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getVersionStopDate() {
		return versionStopDate;
	}

	public void setVersionStopDate(String versionStopDate) {
		this.versionStopDate = versionStopDate;
	}

	public String getSystemUserLimitedFlag() {
		return systemUserLimitedFlag;
	}

	public void setSystemUserLimitedFlag(String systemUserLimitedFlag) {
		this.systemUserLimitedFlag = systemUserLimitedFlag;
	}

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		return null;
	}

	public LoginForm() {
		super();
		verbId = "";
		message = "";
		staffId = "";
		password = "";
		staffCode = "";
		name = "";
		hospitalId = "";
		staffLicenseStopDate = "";
		loginVerCode = "";

		homePageType = "";
		staffType = "";
		commConfigLocationId1 = "";
		commConfigLocationId2 = "";
		commConfigLocationId3 = "";
		commCltId = "";
		commClvId = "";
		systemUserLimitedFlag = "";

	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
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

	public String getCommCltId() {
		return commCltId;
	}

	public void setCommCltId(String commCltId) {
		this.commCltId = commCltId;
	}

	public String getCommClvId() {
		return commClvId;
	}

	public void setCommClvId(String commClvId) {
		this.commClvId = commClvId;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getVersionUserName() {
		return versionUserName;
	}

	public void setVersionUserName(String versionUserName) {
		this.versionUserName = versionUserName;
	}

	public String getHomePageType() {
		return homePageType;
	}

	public void setHomePageType(String homePageType) {
		this.homePageType = homePageType;
	}

	public String getStaffLicenseStopDate() {
		return staffLicenseStopDate;
	}

	public void setStaffLicenseStopDate(String staffLicenseStopDate) {
		this.staffLicenseStopDate = staffLicenseStopDate;
	}

	public String getLoginVerCode() {
		return loginVerCode;
	}

	public void setLoginVerCode(String loginVerCode) {
		this.loginVerCode = loginVerCode;
	}

	public Map getMaps() {
		return maps;
	}

	public void setMaps(Map maps) {
		this.maps = maps;
	}

}
