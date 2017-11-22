package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommGbCoptp;
import com.tianjian.comm.struts.form.CommGbCoptpForm;

public interface ICommGbCoptpService {
	 /**新增之前的初始化*/
	public void addInit(CommGbCoptpForm form);
	/** checkData*/
	public CommGbCoptp checkData(String id);
	/**保存*/
	public void save(CommGbCoptpForm form);
	/**更新之前初始化*/
	public void updateInit(CommGbCoptpForm form);
	/**更新*/
	public void update(CommGbCoptpForm form);
	/**显示之前初始化*/
	public void showInit(CommGbCoptpForm form);
	/**删除*/
	public void delete(CommGbCoptpForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommGbCoptpForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommGbCoptpForm form);
	/**getDetail*/
	public void getDetail(CommGbCoptpForm form);
}