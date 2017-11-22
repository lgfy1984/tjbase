//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.security.struts.form;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 04-26-2007 XDoclet definition:
 * 
 * @struts.form name="SessionForm"
 */
public class SessionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6631247531295694628L;
	private String staffId;// 操作员ID
	private String staffCode;// 操作员用户名
	private String staffName; // 操作员姓名
	private String[] staffRoles; // 角色
	private String systemLimitedFlag; // 系统访问权限控制标志，0为不限制，1为限制
	private String selectedPublicClassId;// 当前使用的模块类别ID
	private String selectedPublicId;// 当前激活的模块ID
	private String staffLoginTime;// 操作员登录时间
	private String homePageType;// 操作员主界面的显示类型
	private String staffType;// 操作员类型 0普通 1超级管理员
	private Map maps;//分院map

	// -------------------------------------------------------
	private String staffLicenseStopDate;// 操作员的帐号截止日期

	// -------------------------------------------------------
	private String versionUserName;// 软件授权显示客户名称
	private String versionStopDate;// 软件授权结束时间
	// ------------------------------------------------------
	private String staffHospitalId; // 医疗机构ID
	private String commConfigLocationId1;// 医疗机构所在省
	private String commConfigLocationId2;// 医疗机构所在市
	private String commConfigLocationId3;// 医疗机构所在县
	private String commCltId;// 医疗机构所在乡镇
	private String commClvId;// 医疗机构所在村

	private String[] publicClassIds;
	private String[] publicClassNames;
	private String[] publicClassSysFlags;
	private String[] publicClassRedirectUrls;
	// -------------------------------------------------------
	private String tenantId;// 租户id

	// -------------------------------------------------------
	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public SessionForm() {
		super();
		staffId = "";
		staffName = "";
		staffHospitalId = "";
		commConfigLocationId1 = "";
		commConfigLocationId2 = "";
		commConfigLocationId3 = "";
		commCltId = "";
		commClvId = "";
		versionUserName = "";
		homePageType = "";
		staffType = "";
		staffLicenseStopDate = "";
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffHospitalId() {
		return staffHospitalId;
	}

	public void setStaffHospitalId(String staffHospitalId) {
		this.staffHospitalId = staffHospitalId;
	}

	public String[] getStaffRoles() {
		return staffRoles;
	}

	public void setStaffRoles(String[] staffRoles) {
		this.staffRoles = staffRoles;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getSystemLimitedFlag() {
		return systemLimitedFlag;
	}

	public void setSystemLimitedFlag(String systemLimitedFlag) {
		this.systemLimitedFlag = systemLimitedFlag;
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

	public String getSelectedPublicClassId() {
		return selectedPublicClassId;
	}

	public void setSelectedPublicClassId(String selectedPublicClassId) {
		this.selectedPublicClassId = selectedPublicClassId;
	}

	public String getSelectedPublicId() {
		return selectedPublicId;
	}

	public void setSelectedPublicId(String selectedPublicId) {
		this.selectedPublicId = selectedPublicId;
	}

	public String getVersionUserName() {
		return versionUserName;
	}

	public void setVersionUserName(String versionUserName) {
		this.versionUserName = versionUserName;
	}

	public String getVersionStopDate() {
		return versionStopDate;
	}

	public void setVersionStopDate(String versionStopDate) {
		this.versionStopDate = versionStopDate;
	}

	public String getStaffLoginTime() {
		return staffLoginTime;
	}

	public void setStaffLoginTime(String staffLoginTime) {
		this.staffLoginTime = staffLoginTime;
	}

	public String getHomePageType() {
		return homePageType;
	}

	public void setHomePageType(String homePageType) {
		this.homePageType = homePageType;
	}

	public String[] getPublicClassIds() {
		return publicClassIds;
	}

	public void setPublicClassIds(String[] publicClassIds) {
		this.publicClassIds = publicClassIds;
	}

	public String[] getPublicClassNames() {
		return publicClassNames;
	}

	public void setPublicClassNames(String[] publicClassNames) {
		this.publicClassNames = publicClassNames;
	}

	public String[] getPublicClassSysFlags() {
		return publicClassSysFlags;
	}

	public void setPublicClassSysFlags(String[] publicClassSysFlags) {
		this.publicClassSysFlags = publicClassSysFlags;
	}

	public String[] getPublicClassRedirectUrls() {
		return publicClassRedirectUrls;
	}

	public void setPublicClassRedirectUrls(String[] publicClassRedirectUrls) {
		this.publicClassRedirectUrls = publicClassRedirectUrls;
	}

	public String getStaffLicenseStopDate() {
		return staffLicenseStopDate;
	}

	public void setStaffLicenseStopDate(String staffLicenseStopDate) {
		this.staffLicenseStopDate = staffLicenseStopDate;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Map getMaps() {
		return maps;
	}

	public void setMaps(Map maps) {
		this.maps = maps;
	}
    
}
