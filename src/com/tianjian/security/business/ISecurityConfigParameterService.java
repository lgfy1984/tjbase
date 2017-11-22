package com.tianjian.security.business;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.struts.form.SecurityConfigParameterForm;

public interface ISecurityConfigParameterService {
	
	public List<?> getData();
	public List<?> findByClassCode(String classCode);
	
	/**
	 * 统计表单数据量
	 * @param classCode
	 * @param className
	 * @param inputCode
	 * @return
	 */
	public int getCount(SecurityConfigParameterForm form);
	/**
	 * 获取数据
	 * @param form
	 * @param cur
	 * @param pageSize
	 * @return
	 */
	public void getData(SecurityConfigParameterForm form,int cur,int pageSize);
	/**
	 * 通过classCode查询详细信息
	 * @param form
	 */
	public void getByClassCode(SecurityConfigParameterForm form);
	/**
	 * 删除数据
	 * @param form
	 */
	public void deleteData(String classCode);
	/**
	 * 修改数据
	 * @param form
	 */
	public void updateData(SecurityConfigParameterForm form);
	/**
	 * 保存数据
	 * @param form
	 */
	public boolean saveData(SecurityConfigParameterForm form);
	/**
	 * 选择classCode数量
	 * @param classCode
	 * @return
	 */
	public int check(String classCode);
	
	
	public String getXml(String flag,String inputCode,HttpServletRequest request);
	
	public String getHsp(String flag,String inputCode,HttpServletRequest request);
	
	public boolean saveList(SecurityConfigParameterForm form);
}
