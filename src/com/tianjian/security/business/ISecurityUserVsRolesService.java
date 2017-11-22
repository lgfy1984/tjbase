package com.tianjian.security.business;

import com.tianjian.security.struts.form.SecurityUserVsRolesForm;

public interface ISecurityUserVsRolesService {
	public int count(SecurityUserVsRolesForm form,String staffType,String hspConfigBaseinfoId);
	
	public void getInitData(SecurityUserVsRolesForm form,String staffType,String hspConfigId,int count, int pageSize);
	
	public String[] getUserRoles(String userId);
	
	public void saveUserVsRoles(String userId, String[] roleId);
		
}
