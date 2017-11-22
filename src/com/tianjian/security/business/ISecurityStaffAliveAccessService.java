package com.tianjian.security.business;

import com.tianjian.security.bean.SecurityStaffAliveAccess;

public interface ISecurityStaffAliveAccessService {
	/**
	 * 保存
	 * */
	public void save(SecurityStaffAliveAccess sSAA);
	/**
	 * 更新
	 * */
	public void update(SecurityStaffAliveAccess sSAA);
	/**
	 * 保存或更新
	 * */
	public void saveOrUpdate(SecurityStaffAliveAccess sSAA);
	
	
	/**
	 * 查询是否存在
	 * */
	public SecurityStaffAliveAccess getByStaffId(String staffId);
}
