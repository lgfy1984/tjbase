package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;

public interface IFindPasswordDAO {
    
	public List<?> getList(String userName);
	
	public void updatePassword(SecuritySystemUsers securitySystemUsers);
	
	public SecuritySystemUsers findBySecurityStaffBaseinfoId(String securityStaffBaseinfoId);
 
	public SecurityStaffBaseinfo findByStaffCode(String userName);
}