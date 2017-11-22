package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigInputDict;
import com.tianjian.comm.struts.form.CommConfigInputDictForm;

public interface ICommConfigInputDict2Service {
	 /**新增之前的初始化*/
	public void addInit(CommConfigInputDictForm form);
	/** checkData*/
	public CommConfigInputDict checkData(String id, String name);
	/**保存*/
	public void save(CommConfigInputDictForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigInputDictForm form);
	/**更新*/
	public void update(CommConfigInputDictForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigInputDictForm form);
	/**删除*/
	public void delete(CommConfigInputDictForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String inputCodeWb, String inputCode1, String inputCode2);
	/**获取当页记录*/
	public void getSearch(CommConfigInputDictForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigInputDictForm form);
	/**getDetail*/
	public void getDetail(CommConfigInputDictForm form);
}