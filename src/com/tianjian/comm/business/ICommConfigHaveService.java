package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigHave;
import com.tianjian.comm.struts.form.CommConfigHaveForm;


public interface ICommConfigHaveService {
	 /**新增之前的初始化*/
	public void addInit(CommConfigHaveForm form);
	/** checkData*/
	public CommConfigHave checkData(String id);
	/**保存*/
	public void save(CommConfigHaveForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigHaveForm form);
	/**更新*/
	public void update(CommConfigHaveForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigHaveForm form);
	/**删除*/
	public void delete(CommConfigHaveForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigHaveForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigHaveForm form);
	/**getDetail*/
	public void getDetail(CommConfigHaveForm form);
}
