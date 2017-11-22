package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigVocation;
import com.tianjian.comm.struts.form.CommConfigVocationForm;

public interface ICommConfigVocationService {

	 /**新增之前的初始化*/
	public void addInit(CommConfigVocationForm form);
	/** checkData*/
	public CommConfigVocation checkData(String id);
	/**保存*/
	public void save(CommConfigVocationForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigVocationForm form);
	/**更新*/
	public void update(CommConfigVocationForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigVocationForm form);
	/**删除*/
	public void delete(CommConfigVocationForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigVocationForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigVocationForm form);
	/**getDetail*/
	public void getDetail(CommConfigVocationForm form);
}
