package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigPositiontitle;
import com.tianjian.comm.struts.form.CommConfigPositiontitleForm;

public interface ICommConfigPositiontitleService {
	 /**新增之前的初始化*/
	public void addInit(CommConfigPositiontitleForm form);
	/** checkData*/
	public CommConfigPositiontitle checkData(String id);
	/**保存*/
	public void save(CommConfigPositiontitleForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigPositiontitleForm form);
	/**更新*/
	public void update(CommConfigPositiontitleForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigPositiontitleForm form);
	/**删除*/
	public void delete(CommConfigPositiontitleForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigPositiontitleForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigPositiontitleForm form);
	/**getDetail*/
	public void getDetail(CommConfigPositiontitleForm form);
}