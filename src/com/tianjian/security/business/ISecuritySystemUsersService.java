package com.tianjian.security.business;

import com.tianjian.security.bean.SecurityLicense;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.struts.form.SecuritySystemUsersForm;

/**
 * SECURITY_SYSTEM_USERS用户登陆表用Service接口
 * @author DZENALL
 * @since 2008-3-24 15:43
 */
public interface ISecuritySystemUsersService {
	
	/**
	 * 根據SecuritySystemUsersForm狀態，在數據庫中查看對應記錄是否存在<br>
	 * 主要是根據SecuritySystemUsersForm中staffCode屬性值來判斷 
	 * @param form
	 * @return 存在則返囬該相對應的對象，否則返囬null
	 */
	public SecurityStaffBaseinfo checkData(SecuritySystemUsersForm form);
	public SecurityLicense checkRegistCode(String registCode);
	/**
	 * 
	 * @param form
	 */
	public void save(SecuritySystemUsersForm form);
	
	/**
	 * 
	 * @param hosForm
	 */
	public void registInit(SecuritySystemUsersForm hosForm);
	
	/**
	 * 查看該licenseCode在數據庫中是否存在
	 * @param licenseCode
	 * @return 存在則返囬該licenseCode，否則返囬空
	 */
	public String hasLicense(String licenseCode);
	
	public boolean isRegisted(String securityStaffBaseinfoId);
}
