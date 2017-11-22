package com.tianjian.security.business;

import com.tianjian.security.bean.SecurityConfigRoles;
import com.tianjian.security.struts.form.SecurityConfigRolesForm;

/**
 * SECURITY_CONFIG_PUBLIC_CLASS Service接口
 * @author ch_f001
 * @since 2008-3-24 15:43
 */
public interface ISecurityConfigRolesService {
	
	 /**新增之前的初始化*/
	public void addInit(SecurityConfigRolesForm form);
	/** checkData*/
	public SecurityConfigRoles checkData(String id);
	/**保存*/
	public void save(SecurityConfigRolesForm form);
	/**更新之前初始化*/
	public void updateInit(SecurityConfigRolesForm form);
	/**更新*/
	public void update(SecurityConfigRolesForm form);
	/**显示之前初始化*/
	public void showInit(SecurityConfigRolesForm form);
	/**删除*/
	public void delete(SecurityConfigRolesForm form);	
	/**获取总记录数*/
	public int getCount(String id,String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(SecurityConfigRolesForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(SecurityConfigRolesForm form);
	/**getDetail*/
	public void getDetail(SecurityConfigRolesForm form);

	 
}
