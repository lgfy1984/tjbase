package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigHospitalClass;
import com.tianjian.comm.struts.form.CommConfigHospitalClassForm;


public interface ICommConfigHospitalClassService {
	
	/**获得总的个数*/
	public int getCount(CommConfigHospitalClassForm form);
	/**
	 * 获得需求的数据
	 * @param form CommConfigHospitalClassForm
	 * @param count 读取数据的起始位置
	 * @param pageSize 一次读取数据的个数
	 * @return
	 */
	public void getSearch(CommConfigHospitalClassForm form,int count,int pageSize);
	/**保存*/
	public void save(CommConfigHospitalClassForm form);
	/**修改*/
	public void update(CommConfigHospitalClassForm form);
	/**删除*/
	public void delete(String classCode);
	/**通过classCode获得CommConfigHospitalClass*/
	public CommConfigHospitalClass getByItemCode(String classCode);
}
