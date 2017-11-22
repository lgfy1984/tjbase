package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CurrentPriceList;

public interface ICurrentPriceListDAO {

	/**
	 * 根据id查询对象
	 * @param no
	 */
	public CurrentPriceList findById(String id);
	
	/**
	 * 保存对象
	 * @param DrugManufacturersCatalog
	 */
	public void save(CurrentPriceList data);
	
	/**
	 * 修改对象
	 * @param DrugManufacturersCatalog
	 */
	public void update(CurrentPriceList data);
	
	/**
	 * 删除对象
	 * @param DrugManufacturersCatalog
	 */
	public void delete(CurrentPriceList data);
	
	/**
	 * 获得记录总条数
	 */
	public int getCountAll(String EanCode,String drugDictId);

	/**
	 * 获得分页记录数
	 */
	public List<?> getDataAll(String EanCode, String DrugDictId,  int curCount, int pageSize,  String orderNo);
	
	/**
	 *  获取ItemCode
	 */
	public String getItemCode(String itemCodeString);

	/**
	 * 获得生成厂家所有对象
	 */
	public List<?> getfirmIdAll();
	
	public String getitemnamelist(String firmid);
	
	public List<?> getdrugname(String flag,String inputCode,String hspType, int curCount, int pageSize);
    
    
}
