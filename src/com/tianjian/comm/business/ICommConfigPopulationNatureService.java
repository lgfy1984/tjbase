package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigPopulationNature;
import com.tianjian.comm.struts.form.CommConfigPopulationNatureForm;

public interface ICommConfigPopulationNatureService {
	/**获得总的个数*/
	public int getCount(CommConfigPopulationNatureForm form);
	/**
	 * 获得需求的数据
	 * @param form CommConfigPopulationNatureForm
	 * @param count 读取数据的起始位置
	 * @param pageSize 一次读取数据的个数
	 * @return
	 */
	public void getSearch(CommConfigPopulationNatureForm form,int count,int pageSize);
	/**保存*/
	public void save(CommConfigPopulationNatureForm form);
	/**修改*/
	public void update(CommConfigPopulationNatureForm form);
	/**删除*/
	public void delete(String itemCode);
	/**通过itemCode获得CommConfigPopulationNature*/
	public CommConfigPopulationNature getByItemCode(String itemCode);
}
