package com.tianjian.security.dao;

import com.tianjian.security.bean.SecurityLicense;

/**
 * SECURITY_LICENSE用户许可证表用DAO的接口
 * @author DZENALL
 * @since 2008-3-24 10:01
 * @version 1.0
 */
public interface ISecurityLicenseDAO {
	
	public SecurityLicense findById(String securityLicenseId);
	
	/**
	 * 根据医务人员代码securityStaffBaseinfoId在用户许可证表中查询相关记录是否存在
	 * @param securityStaffBaseinfoId
	 * @return 存在则返回该条记录，否则返回null
	 */
	public SecurityLicense findBySecurityStaffBaseinfoId(String securityStaffBaseinfoId);
	/**通过注册码找到注册信息*/
	public SecurityLicense findByRegistCode(String registCode);
	/**
	 * 保存
	 * @param securityLicense
	 */
	public void save(SecurityLicense securityLicense);
	
	/**
	 * 删除
	 * @param securityLicense
	 */
	public void delete(SecurityLicense securityLicense);

	public void update(SecurityLicense securityLicense);
}
