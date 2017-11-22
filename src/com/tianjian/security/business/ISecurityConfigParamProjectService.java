package com.tianjian.security.business;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.bean.SecurityConfigParamProject;
import com.tianjian.security.struts.form.SecurityConfigParamProjectForm;



public interface ISecurityConfigParamProjectService {
	/**
	 * 统计表单数据量
	 * @param classCode
	 * @param className
	 * @param inputCode
	 * @return
	 */
	public int count(SecurityConfigParamProjectForm form);
	/**
	 * 获取数据
	 * @param form
	 * @param cur
	 * @param pageSize
	 * @return
	 */
	public void getData(SecurityConfigParamProjectForm form,int cur,int pageSize);
	/**
	 * 通过classCode查询详细信息
	 * @param form
	 */
	public void getByClassCode(SecurityConfigParamProjectForm form);
	/**
	 * 删除数据
	 * @param form
	 */
	public void deleteData(String projectCode);
	/**
	 * 修改数据
	 * @param form
	 */
	public void updateData(SecurityConfigParamProjectForm form);
	/**
	 * 保存数据
	 * @param form
	 */
	public void saveData(SecurityConfigParamProjectForm form);
	/**
	 * 选择classCode数量
	 * @param classCode
	 * @return
	 */
	public int check(String projectCode);
	
	public void seqNoMaker(SecurityConfigParamProjectForm form);
	
	public SecurityConfigParamProject checkData(String projectCode);
	
	public String getXml(String flag,String inputCode,HttpServletRequest request);
}
