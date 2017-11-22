package com.tianjian.comm.business;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigIcd10;
import com.tianjian.comm.struts.form.CommConfigIcd10Form;


public interface ICommConfigIcd10Service {
	 /**新增之前的初始化*/
	public void addInit(CommConfigIcd10Form form);
	/** checkData*/
	public CommConfigIcd10 checkData(String id);
	/**保存*/
	public void save(CommConfigIcd10Form form);
	/**更新之前初始化*/
	public void updateInit(CommConfigIcd10Form form);
	/**更新*/
	public void update(CommConfigIcd10Form form);
	/**显示之前初始化*/
	public void showInit(CommConfigIcd10Form form);
	/**删除*/
	public void delete(CommConfigIcd10Form form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigIcd10Form form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigIcd10Form form);
	/**getDetail*/
	public void getDetail(CommConfigIcd10Form form);
	public String getXml(String flag,String inputCode,HttpServletRequest request);
}
