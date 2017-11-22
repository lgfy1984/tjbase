package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityConfigParamclass;
import com.tianjian.security.bean.SecurityConfigParameter;

public interface ISecurityConfigParameterDAO {
	/**
	 * 统计数据量
	 * 
	 * @param id
	 * @param classCode
	 * @param hspConfigBaseinfoId
	 * @param itemName
	 * @param itemValue
	 * @param className
	 * @return
	 */
	public int count(String id, String classCode, String hspConfigBaseinfoId,
			String itemName, String itemValue,String className);

	/**
	 * 查出系统参数配置表中所有数据并放到List集合中
	 * 
	 * @param id
	 * @param classCode
	 * @param hspConfigBaseinfoId
	 * @param itemName
	 * @param itemValue
	 * @param cur
	 * @param pageSize
	 * @return
	 */
	public List<?> queryList(String id, String classCode,
			String hspConfigBaseinfoId, String itemName, String itemValue,
			String className, String orderNo, int cur, int pageSize);

	/**
	 * 通过ID查询详细消息
	 * 
	 * @param id
	 * @return
	 */
	public SecurityConfigParameter queryById(String id);

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
	 * 判断ID是否已经存在
	 * 
	 * @param id
	 * @return
	 */
	public int checkId(String id);

	/**
	 * 通过classCode查询并放到List集合中
	 * 
	 * @param classCode
	 * @return
	 */
	public List<?> queryByClassCode(String classCode);

	
	//************************************************************
	/**
	 * 统计数据量
	 * 
	 * @param classCode
	 * @param className
	 * @param inputCode
	 * @return
	 */
	public int count(String className, String inputCode,
			String itemName, String itemValue);

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
	public List<?> queryList(String className, String inputCode,
			String itemName, String itemValue,String orderNo, int cur, int pageSize);

	/**
	 * 通过classCode查询详细信息
	 * 
	 * @param classCode
	 * @return
	 */
	public List<?> queryClassByParamId(String id);
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
	
	public SecurityConfigParameter findById(String id);
	
	public SecurityConfigParamclass findByClassCode(String id);
	
	
	public List<?> getHsp(String falg, String input, int count, int pageSize);
	
	//医疗机构弹出层
	public List<?> findHspList(String flag,String inputCode,String hspType, int curCount, int pageSize);
	
	
	public List<?> queryProjectName(String classCode);
	
	
}
