package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecuritySystemUsers;

/**
 * SECURITY_SYSTEM_USERS用户登陆表用DAO接口
 * @author DZENALL
 * @since 2008-3-24 15:39
 */
public interface ISecuritySystemUsersDAO {

	public SecuritySystemUsers findBySecurityStaffBaseinfoId(String securityStaffBaseinfoId);
	public List<?> findAll();
	public void save(SecuritySystemUsers securitySystemUsers);
	public void delete(SecuritySystemUsers securitySystemUsers);
	public void update(SecuritySystemUsers securitySystemUsers);
	/**
	 * 查询医疗人员注册码
	 * @param querySql 所使用的SQL语句（其中有一个参数）
	 * @param queryValue SQL语句中参数的值
	 * @return 特定序列的数据元构成的List
	 */
	public List<?> viewQuery(String querySql, String queryValue);
}
