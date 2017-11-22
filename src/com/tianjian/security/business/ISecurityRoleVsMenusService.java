package com.tianjian.security.business;

import java.util.List;

public interface ISecurityRoleVsMenusService {
		
	public String getRoleTreeNodes();
	
	public String getMenuTreeNodes();

	public List<String> getMenuIdsByRoleId(String roleId);

	public void saveSecurityRoleVsMenus(String roleId, String menuIds);
}
