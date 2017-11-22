package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigPopulationNature;
import com.tianjian.comm.struts.form.CommConfigPopulationNatureForm;

public interface ICommConfigPopulationNatureDAO {
	
	/**获得总的个数*/
	public int getCount(CommConfigPopulationNatureForm form);
	/**
	 * 获得需求的数据
	 * @param form CommConfigPopulationNatureForm
	 * @param count 读取数据的起始位置
	 * @param pageSize 一次读取数据的个数
	 * @return List
	 */
	public List<?> getSearch(CommConfigPopulationNatureForm form,int count,int pageSize);
	/**保存*/
	public void save(CommConfigPopulationNature bean);
	/**修改*/
	public void update(CommConfigPopulationNature bean);
	/**删除*/
	public void delete(CommConfigPopulationNature bean);
	/**通过itemCode获得CommConfigPopulationNature*/
	public CommConfigPopulationNature getByItemCode(String itemCode);
}
