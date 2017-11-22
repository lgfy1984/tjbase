package com.tianjian.security.business;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.bean.SecurityConfigParamclass;
import com.tianjian.security.struts.form.SecurityConfigParamClassForm;

public interface ISecurityConfigParamClassService {
	
	public List<?> addTable(String[] tableName,String fileName);
	
	public String queryTable(String tableName);
	
	public String getExcel(String[] tableName,String path);
	
	public void setRequest(HttpServletRequest request);
	//*****************************************
	/**
	 * 统计表单数据量
	 * @param classCode
	 * @param className
	 * @param inputCode
	 * @return
	 */
	public int count(SecurityConfigParamClassForm form);
	/**
	 * 获取数据
	 * @param form
	 * @param cur
	 * @param pageSize
	 * @return
	 */
	public void getData(SecurityConfigParamClassForm form,int cur,int pageSize);
	
	
	/**
	 * 通过classCode查询详细信息
	 * @param form
	 */
	public void getByClassCode(SecurityConfigParamClassForm form);
	/**
	 * 删除数据
	 * @param form
	 */
	public void deleteData(String classCode);
	/**
	 * 修改数据
	 * @param form
	 */
	public void updateData(SecurityConfigParamClassForm form);
	/**
	 * 保存数据
	 * @param form
	 */
	public void saveData(SecurityConfigParamClassForm form);
	/**
	 * 选择classCode数量
	 * @param classCode
	 * @return
	 */
	public int check(String classCode);
	
	public void seqNoMaker(SecurityConfigParamClassForm form);
	
	public SecurityConfigParamclass checkData(String projectCode);
}
