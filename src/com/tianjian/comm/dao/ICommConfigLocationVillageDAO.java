package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigLocationVillage;
/**
 * COMM_CONFIG_LOCATION_VILLAGE村字典用DAO接口
 * @author Dzenall
 * @since 2008-9-18
 */
public interface ICommConfigLocationVillageDAO {

	void save(CommConfigLocationVillage data);

	/**
	 * 在数据库CommConfigLocationVillage表中查询seqNo字段中数值最大的那个数字，并在其基础上加一，如果没有找到该数字，则置为一后返回
	 * @param seqNo
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
	 * 在CommConfigLocationTown中查找commConfigLocationId为parentId的所有记录
	 * @param parentId
	 * @return List{CommConfigLocationTown}/null
	 */
	List<?> getByParent(String parentId);

	/**
	 * 根据itemCode查找CommConfigLocationVillage
	 * @param itemCode
	 * @return CommConfigLocationVillage/null
	 */
	CommConfigLocationVillage findByItemCode(String itemCode);

	/**
	 * 根据id查找CommConfigLocationVillage
	 * @param id
	 * @return CommConfigLocationVillage/null
	 */
	CommConfigLocationVillage findById(String id);
	
	void delete(CommConfigLocationVillage data);

	int getCount(String itemCode, String itemName, String inputCode);

	/**
	 * 慎用：不能防止注入攻击
	 * @param nameOfTab
	 * @param nameOfFiled
	 * @param nameOfProp
	 * @param valueOfProp
	 * @return String/null
	 */
	String findValueByProp(String nameOfTab, String nameOfFiled, String nameOfProp, String valueOfProp);

	List<?> getData(String itemCode, String itemName, String inputCode,
			String order, int curCount, int pageSize);

	void update(CommConfigLocationVillage data);
	
	//定义行政村编号
	public String getItemCode(String country);
	public String getItemName(String itemCode);
	//得到乡镇编号
	public String getTownItemCode(String commCltId);
}
