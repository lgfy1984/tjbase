package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigDrugform;

public interface ICommConfigDrugformDAO {
	//保存药品剂型信息
	public void save(CommConfigDrugform pojo);
	//
	public void update(CommConfigDrugform pojo);
	public void delete(CommConfigDrugform pojo);
	//取得符合查询条件的记录条数
	public int getCount(String itemCode, String itemName, String inputCode);
	//通过itemCode得到CommConfigDrugform
	public CommConfigDrugform getByItemCode(String itemCode);
	public CommConfigDrugform getByItemName(String itemName);
	//取得符合查询条件的记录的list
	public List<?> getData(String itemCode, String itemName, String inputCode, String orderNo, int curCount,int quChuCount);
}
