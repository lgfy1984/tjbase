package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityStaffBaseinfo;

public interface ISecurityUserVsRolesOpDAO {
	
	public SecurityStaffBaseinfo findById(String id);
	
	public List<?> getData(String securityConfigRolesId, String hspConfigBaseinfoId, String order, int curCount, int quChuCount);

    public int getCount(String securityConfigRolesId, String hspConfigBaseinfoId);

}
