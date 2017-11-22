package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigYes;
import com.tianjian.comm.struts.form.CommConfigYesForm;


public interface ICommConfigYesService {
	 /**新增之前的初始化*/
	public void addInit(CommConfigYesForm form);
	/** checkData*/
	public CommConfigYes checkData(String id);
	/**保存*/
	public void save(CommConfigYesForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigYesForm form);
	/**更新*/
	public void update(CommConfigYesForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigYesForm form);
	/**删除*/
	public void delete(CommConfigYesForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigYesForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigYesForm form);
	/**getDetail*/
	public void getDetail(CommConfigYesForm form);
}
