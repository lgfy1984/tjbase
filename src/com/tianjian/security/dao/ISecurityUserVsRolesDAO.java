package com.tianjian.security.dao;

import com.tianjian.security.bean.SecurityConfigRoles;
import com.tianjian.security.bean.SecurityUserVsRoles;

import java.util.List;

public interface ISecurityUserVsRolesDAO {
    
	public List<?> getUsers(String userId, String userName, String hspConfigBaseinfoId, String userInput,String staffType,String hspConfigId,int count, int pageSize);
	
	public int count(String userId, String userName, String hspConfigBaseinfoId, String userInput,String staffType,String hspConfigId);
    
	public List<?> getRoles(String staffType);
    
    public List<?> getUserRoles(String userId);
    
    public void save(SecurityUserVsRoles securityUserVsRoles);
    
    public void delete(SecurityUserVsRoles securityUserVsRoles);
    
    public SecurityConfigRoles findByRoleId(String roleId);
}