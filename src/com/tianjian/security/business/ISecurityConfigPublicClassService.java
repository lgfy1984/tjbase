package com.tianjian.security.business;

import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.struts.form.SecurityConfigPublicClassForm;

/**
 * SECURITY_CONFIG_PUBLIC_CLASS Service接口
 * @author ch_f001
 * @since 2008-3-24 15:43
 */
public interface ISecurityConfigPublicClassService {
	
	 /**新增之前的初始化*/
	public void addInit(SecurityConfigPublicClassForm form);
	/** checkData*/
	public SecurityConfigPublicClass checkData(String id);
	/**保存*/
	public void save(SecurityConfigPublicClassForm form);
	/**更新之前初始化*/
	public void updateInit(SecurityConfigPublicClassForm form);
	/**更新*/
	public void update(SecurityConfigPublicClassForm form);
	/**显示之前初始化*/
	public void showInit(SecurityConfigPublicClassForm form);
	/**删除*/
	public void delete(SecurityConfigPublicClassForm form);	
	/**获取总记录数*/
	public int getCount(String id,String classCode, String className, String inputCode, String serialNo,String levelFlag,String panterId);
	/**获取当页记录*/
	public void getSearch(SecurityConfigPublicClassForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(SecurityConfigPublicClassForm form);
	
	public int getPublicCountByPublicClassId(String publicClassId);
	
	public int getPublicClass2CountByPublicClassId(String publicClassId);
	
	 
}
