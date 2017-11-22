package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigTrue;
import com.tianjian.comm.struts.form.CommConfigTrueForm;


public interface ICommConfigTrueService {
	 /**新增之前的初始化*/
	public void addInit(CommConfigTrueForm form);
	/** checkData*/
	public CommConfigTrue checkData(String id);
	/**保存*/
	public void save(CommConfigTrueForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigTrueForm form);
	/**更新*/
	public void update(CommConfigTrueForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigTrueForm form);
	/**删除*/
	public void delete(CommConfigTrueForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigTrueForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigTrueForm form);
	/**getDetail*/
	public void getDetail(CommConfigTrueForm form);
}
