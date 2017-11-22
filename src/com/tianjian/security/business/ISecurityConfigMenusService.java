package com.tianjian.security.business;

import java.util.List;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.struts.form.SecurityConfigMenusForm;
import com.tianjian.security.struts.form.SecurityRoleVsMenusForm;

/**
 * SECURITY_CONFIG_PUBLIC_CLASS Service接口
 * @author ch_f001
 * @since 2008-3-24 15:43
 */
public interface ISecurityConfigMenusService {
	
	public void addClassId(SecurityConfigMenusForm form);
	
	public SecurityConfigMenusForm getMenu(String modId,String staffType);
	 /**新增之前的初始化*/
	public void addInit(SecurityConfigMenusForm form);
	/** checkData*/
	public SecurityConfigMenus checkData(String id);
	
	public List<?> checkDataParent(String id);
	/**保存*/
	public void save(SecurityConfigMenusForm form);
	/**更新之前初始化*/
	public void updateInit(SecurityConfigMenusForm form);
	/**更新*/
	public void update(SecurityConfigMenusForm form);
	/**显示之前初始化*/
	public void showInit(SecurityConfigMenusForm form);
	/**删除*/
	public void delete(SecurityConfigMenusForm form);	
	/**获取总记录数*/
	public int getCount(String id,String menuCode, String menuDetail, String inputCode, String serialNo);
	/**获取当页记录*/
	public void getSearch(SecurityConfigMenusForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(SecurityConfigMenusForm form);
	/**getDetail*/
	public void getDetail(SecurityConfigMenusForm form);
	
	public List<?> getAjaxDict(String iputFlag, String inputValue);
	
	public void getInit(SecurityConfigMenusForm form);

	public int getChildrenCount(String idHidden);
	 
}
