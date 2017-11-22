package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityConfigParamProject;

public interface ISecurityConfigParamProjectDAO {
	/**
	 * 统计数据量
	 * 
	 * @param classCode
	 * @param className
	 * @param inputCode
	 * @return
	 */
	public int count(String classCode, String className, String inputCode,String functionDescription);

	/**
	 * 查出系统参数类别表中所有数据并放到List集合中
	 * 
	 * @param classCode
	 * @param className
	 * @param inputCode
	 * @param cur
	 * @param pageSize
	 * @return
	 */
	public List<?> queryList(String classCode, String className,
			String inputCode,String functionDescription,String orderNo, int cur, int pageSize);

	/**
	 * 通过classCode查询详细信息
	 * 
	 * @param classCode
	 * @return
	 */
	public SecurityConfigParamProject queryByClassCode(String classCode);
	/**
	 * 通过className查询详细信息
	 * @param className
	 * @return
	 */
	public SecurityConfigParamProject queryByClassName(String className);

	/**
	 * 删除数据
	 * 
	 * @param obj
	 */
	public void deleteData(Object obj);

	/**
	 * 修改数据
	 * 
	 * @param obj
	 */
	public void updateData(Object obj);

	/**
	 * 添加保存数据
	 * 
	 * @param obj
	 */
	public void saveData(Object obj);

	/**
	 * 选择classCode数量
	 * 
	 * @param classCode
	 * @return
	 */
	public int checkClassCode(String classCode);

	/**
	 * 查询所有数据
	 * @return
	 */
	public List<?> findAll();
	
	
	public Long maxFind();
	
	public SecurityConfigParamProject getByProjectCode(String projectCode);
	
	
	public List<?> getProject(String falg, String input, int count, int pageSize);
}
