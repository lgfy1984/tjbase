package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigHospitalClass;
import com.tianjian.comm.struts.form.CommConfigHospitalClassForm;

public interface ICommConfigHospitalClassDAO {
	
	/**获得总的个数*/
	public int getCount(CommConfigHospitalClassForm form);
	
	/**
	 * 获得需求的数据
	 * @param form CommConfigHospitalClassForm
	 * @param count 读取数据的起始位置
	 * @param pageSize 一次读取数据的个数
	 * @return List
	 */
	public List<?> getSearch(CommConfigHospitalClassForm form,int count,int pageSize);
	/**保存*/
	public void save(CommConfigHospitalClass bean);
	/**修改*/
	public void update(CommConfigHospitalClass bean);
	/**删除*/
	public void delete(CommConfigHospitalClass bean);
	/**通过classCode获得CommConfigHospitalClass*/
	public CommConfigHospitalClass getByItemCode(String classCode);
	/**用于弹出层,得到需求的数据*/
	public List<?> findHspClassList(String flag,String input,int start,int pageSize);
}
