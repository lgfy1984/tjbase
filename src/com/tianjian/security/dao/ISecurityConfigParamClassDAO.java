package com.tianjian.security.dao;

import java.sql.Connection;
import java.util.List;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigParamProject;
import com.tianjian.security.bean.SecurityConfigParamclass;
import com.tianjian.security.bean.SecurityConfigParameter;

public interface ISecurityConfigParamClassDAO {
	public int count(String calssCode,String itemName,String itemValue);
	public List<?> getData(String calssCode,String itemName,String itemValue,int cur,int pageSize);
	
	public boolean initTables(Connection conn,List sql);
	public boolean deleteTable(Connection conn,String sql);
	
	public SecurityConfigParameter getTableName(String table);
	
	public SecurityConfigMenus getUrl(String menusValue);
	
	public List<?> getTables(Connection conn,String tableName);
	
	public List<?> getColmun(Connection conn,String tableName);
	
	//*************************************
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
	public SecurityConfigParamclass queryByClassCode(String classCode);
	/**
	 * 通过className查询详细信息
	 * @param className
	 * @return
	 */
	public SecurityConfigParamclass queryByClassName(String className);

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
	
	public SecurityConfigParamclass getByProjectCode(String projectCode);
	
	public SecurityConfigParamProject queryByProjectCode(String projectCode);
	
	
	
	
}
