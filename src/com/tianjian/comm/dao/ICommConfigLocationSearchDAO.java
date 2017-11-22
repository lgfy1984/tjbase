package com.tianjian.comm.dao;

import java.util.List;

public interface ICommConfigLocationSearchDAO {
	public String findValueByProp(String nameOfTab, String nameOfField, String nameOfProp, String valueOfProp);
	/**
	 * 在CommConfigLocation表中查找parentId和levelFlag为相应参数值的所有记录
	 * @param parentId
	 * @param levelFlag
	 * @return List{CommConfigLocation}/null
	 */
	 public List<?> getByParent(String parentId, String levelFlag);
	
	 public List<?> getByParent(String parentId, String levelFlag, String staffId);
	 
	/**
	 * 在CommConfigLocationTown表中查找commConfigLocationId字段值为parentId的所有记录
	 * @param parentId
	 * @return List{CommConfigLocationTown}/null
	 */
	List<?> getTownsByParent(String parentId);
	List<?> getTownsByParent(String parentId, String staffId);
	
	
	/**
	 * 在CommConfigLocationVillage表中查找commCltId字段值为parentId的所有记录
	 * @param parentId
	 * @return List{CommConfigLocationVillage}/null
	 */
	List<?> getVillagesByParent(String parentId);
	List<?> getVillagesByParent(String parentId, String staffId);
	/**
	 * 在CommConfigLocationGroup表中查找commClvId字段值为parentId的所有记录
	 * @param parentId
	 * @return List{CommConfigLocationVillage}/null
	 */
	List<?> getGroupByParent(String parentId);
	
}
