package com.tianjian.comm.business;


import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigHospitalType;
import com.tianjian.comm.struts.form.CommConfigHospitalTypeForm;

public interface ICommConfigHospitalTypeService {
	public void setData(CommConfigHospitalTypeForm form,CommConfigHospitalType data,HttpServletRequest request);
	public void setForm(CommConfigHospitalTypeForm form,CommConfigHospitalType data,HttpServletRequest request);
	/**保存*/
	public void save(CommConfigHospitalTypeForm form,HttpServletRequest request);
	/**更新*/
	public void update(CommConfigHospitalTypeForm form,HttpServletRequest request);
	/**删除*/
	public void delete(CommConfigHospitalTypeForm form);
	/**添加初始化*/
	public void addInit(CommConfigHospitalTypeForm form,HttpServletRequest request);
	/**更新初始化*/
	public void updateInit(CommConfigHospitalTypeForm form,HttpServletRequest request);
	/**根据条件查找记录*/
	public void getSearch(CommConfigHospitalTypeForm form,int curCount,
			int pageSize);
	/**根据条件得到的记录条数*/
	public int getCount(CommConfigHospitalTypeForm form);
	/**弹出层所用*/
	public String getXml(String flag,String input,String hspTypeLevel,HttpServletRequest request);
	/**查找itemcode是否已经存在*/
	public  CommConfigHospitalType findByItemCode(String itemCode);
	/**set 中类*/
	public void setLevel2(CommConfigHospitalTypeForm form,HttpServletRequest request);
}
