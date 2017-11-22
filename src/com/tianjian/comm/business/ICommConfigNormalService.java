package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigNormal;
import com.tianjian.comm.struts.form.CommConfigNormalForm;


public interface ICommConfigNormalService {
	 /**新增之前的初始化*/
	public void addInit(CommConfigNormalForm form);
	/** checkData*/
	public CommConfigNormal checkData(String id);
	/**保存*/
	public void save(CommConfigNormalForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigNormalForm form);
	/**更新*/
	public void update(CommConfigNormalForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigNormalForm form);
	/**删除*/
	public void delete(CommConfigNormalForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigNormalForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigNormalForm form);
	/**getDetail*/
	public void getDetail(CommConfigNormalForm form);
}
