package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigDrugunits;

public interface ICommConfigDrugunitsDAO {
	//保存药品单位信息
	public void save(CommConfigDrugunits pojo);
	public void update(CommConfigDrugunits pojo);
	public void delete(CommConfigDrugunits pojo);
	//取得符合查询条件的记录条数
	public int getCount(String itemCode, String itemName, String inputCode);
	//通过itemCode得到CommConfigDrugunits
	public CommConfigDrugunits getByItemCode(String itemCode);
	public CommConfigDrugunits getByItemName(String itemName);
	//取得符合查询条件的记录的list
	public List<?> getData(String itemCode, String itemName, String inputCode, String orderNo, int curCount,int quChuCount);
}
