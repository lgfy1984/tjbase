package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigLocationGroup;
/**
 * COMM_CONFIG_LOCATION_GROUP居民组字典用DAO接口
 * @author Dzenall
 * @since 2008-9-18
 */
public interface ICommConfigLocationGroupDAO {

	void save(CommConfigLocationGroup data);
	
	
	/**
	 * 
	 * @return
	 */
	Long seqNoMaker();	
	

	/**
	 * 在CommConfigLocation表中查找parentId和levelFlag为相应参数值的所有记录
	 * @param parentId
	 * @param levelFlag
	 * @return List{CommConfigLocation}/null
	 */
	List<?> getByParent(String parentId, String levelFlag);

	
	/**
	 * 在CommConfigLocationTown表中查找commConfigLocationId字段值为parentId的所有记录
	 * @param parentId
	 * @return List{CommConfigLocationTown}/null
	 */
	List<?> getTownsByParent(String parentId);
	
	
	/**
	 * 在CommConfigLocationVillage表中查找commCltId字段值为parentId的所有记录
	 * @param parentId
	 * @return List{CommConfigLocationVillage}/null
	 */
	List<?> getVillagesByParent(String parentId);

	/**
	 * 
	 * @param itemCode
	 * @return CommConfigLocationGroup/null
	 */
	CommConfigLocationGroup findByItemCode(String itemCode);
	
	/**
	 * 
	 * @param id
	 * @return CommConfigLocationGroup/null
	 */
	CommConfigLocationGroup findById(String id);


	void delete(CommConfigLocationGroup data);


	int getCount(String itemCode, String itemName, String inputCode);


	/**
	 * 不能防止注入攻击，使用时需要特别注意
	 * @param nameOfTab
	 * @param nameOfField
	 * @param nameOfProp
	 * @param valueOfProp
	 * @return String/null
	 */
	String findValueByProp(String nameOfTab, String nameOfField, String nameOfProp, String valueOfProp);


	List<?> getData(String itemCode, String itemName, String inputCode, String order, int curCount, int pageSize);


	void update(CommConfigLocationGroup data);
	
	//定义行政组编号
	public String getItemCode(String country);
	//得到行政村编号
	public String getVillageItemCode(String commCltId);
}
