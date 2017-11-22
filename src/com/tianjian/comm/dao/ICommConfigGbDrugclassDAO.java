package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigGbDrugclass;

public interface ICommConfigGbDrugclassDAO {
   
	//保存药品厂家信息
	public void save(CommConfigGbDrugclass pojo);
	public void update(CommConfigGbDrugclass pojo);
	public void delete(CommConfigGbDrugclass pojo);
	
	public String findValueByProp(String nameOfTable, String nameOfField,
			String nameOfProp, String valueOfProp);
	//取得符合查询条件的记录条数
	public int getCount(String itemCode, String itemName, String inputCode);
	//通过itemCode得到CommConfigDrugfactory
	public CommConfigGbDrugclass getByItemCode(String itemCode);
	
	public List<?> getAllByParentId(String parentId);
	//取得所有记录条数并排序
	public List<?> findAllOrder();
	//取得符合查询条件的记录的list
	public List<?> getData(String itemCode, String itemName, String inputCode, String orderNo, int curCount,int quChuCount);
}
