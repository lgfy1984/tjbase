package com.tianjian.security.business;

import com.tianjian.security.bean.SecurityConfigPublic;
import com.tianjian.security.struts.form.SecurityConfigPublicForm;

/**
 * SECURITY_CONFIG_PUBLIC_CLASS Service接口
 * @author ch_f001
 * @since 2008-3-24 15:43
 */
public interface ISecurityConfigPublicService {
	
	public void getPublic(SecurityConfigPublicForm form,String modId,String staffType);
	
	public void getInit(SecurityConfigPublicForm form);
	 /**新增之前的初始化*/
	public void addInit(SecurityConfigPublicForm form);
	/** checkData*/
	public SecurityConfigPublic checkData(String id);
	/**保存*/
	public void save(SecurityConfigPublicForm form);
	/**更新之前初始化*/
	public void updateInit(SecurityConfigPublicForm form);
	/**更新*/
	public void update(SecurityConfigPublicForm form);
	/**显示之前初始化*/
	public void showInit(SecurityConfigPublicForm form);
	/**删除*/
	public void delete(SecurityConfigPublicForm form);	
	/**获取总记录数*/
	public int getCount(String id,String modCode, String reason, String inputCode, String serialNo, String parentId);
	/**获取当页记录*/
	public void getSearch(SecurityConfigPublicForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(SecurityConfigPublicForm form);

	public int getMenusCountByPublicId(String publicId);
	
	 
}
