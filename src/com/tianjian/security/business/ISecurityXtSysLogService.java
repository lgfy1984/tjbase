package com.tianjian.security.business;

import com.tianjian.security.struts.form.SecurityXtSysLogForm;

/**
 * SECURITY_CONFIG_PUBLIC_CLASS Service接口
 * @author ch_f001
 * @since 2008-3-24 15:43
 */
public interface ISecurityXtSysLogService {
	/**显示之前初始化*/
	public void showInit(SecurityXtSysLogForm form);
	/**获取总记录数*/
	public int getCount(SecurityXtSysLogForm form);
	/**获取当页记录*/
	public void getSearch(SecurityXtSysLogForm form, int curCount, int pageSize);
	/**serchInit*/
	 
}
