package com.tianjian.security.dao;

import com.tianjian.security.bean.SecurityStaffAliveAccess;

/**
 * @author 
 *
 */
public interface ISecurityStaffAliveAccessDAO {
	
	/**
	 * 保存
	 * */
	public void save(SecurityStaffAliveAccess sSAA);
	/**
	 * 修改
	 * */
	public void saveOrUpdate(SecurityStaffAliveAccess sSAA);
	/**
	 * 保存或修改
	 * */
	public void update(SecurityStaffAliveAccess sSAA);
	/**
	 * 根据staffid查询
	 * */
	public SecurityStaffAliveAccess getByStaffId(String staffId);
}
