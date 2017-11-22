package com.tianjian.security.dao;

import com.tianjian.security.bean.SecurityRoleVsMenus;

import java.util.List;

public interface ISecurityRoleVsMenusDAO {

	public List<?> getRolesIdAndName();

	public List<?> getPublicClassNodes();

	public List<?> getPublicNodes();

	public List<?> getMenuNodes();

	public List<String> getMenuIdsByRoleId(String roleId);

	public void deleteOldRoleVsMenus(String roleId);
	
	/** 保存SecurityRoleVsMenus 
	 * @throws Exception */
	public void save(SecurityRoleVsMenus securityRoleVsMenus);
}