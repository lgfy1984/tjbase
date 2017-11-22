package com.tianjian.security.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.bean.SecurityDataObjectVsRoles;
import com.tianjian.security.struts.form.SecurityDataObjectVsRoleForm;

public interface ISecurityDataObjectVsRoleService {
	public int getCount(SecurityDataObjectVsRoleForm form,String id,String securityStaffBaseInfo, String sdotId);
	
	public void getSearch(SecurityDataObjectVsRoleForm form,int count,int pageSize);
	
	public void detail(SecurityDataObjectVsRoleForm form);
	
	public void delete(SecurityDataObjectVsRoleForm string);
	
	public void updateInit(SecurityDataObjectVsRoleForm form);
	
	public int checkId(String id);
	
	public String getXml(String flag,String inputCode,HttpServletRequest request);
	
	public String getLocationXml(String flag,String inputCode);
	
	public void  update(SecurityDataObjectVsRoleForm form);
	
	public void addInit(SecurityDataObjectVsRoleForm form);
	
	public boolean save(SecurityDataObjectVsRoleForm form);
	
	public String getPlaceXml(String flag,String inputCode,String id,HttpServletRequest request);
	
	public int checkStaffBaseinfoId(String id);
	
	public void addInits(SecurityDataObjectVsRoleForm form);
	
	public List<?> getNeedSecurityDataObjectVsRoles(String id,String sdotId);
	
	public List<?> getLocation(int level,String parentId);
	
	public List<?> getNameByObject(String tableName,String name,String value);
	
	public List<?> getLocationOther(String tableName,String columnName,String parentId);
	
	public void deleteObject(SecurityDataObjectVsRoles s);
	
	public void init(SecurityDataObjectVsRoleForm form);
}
