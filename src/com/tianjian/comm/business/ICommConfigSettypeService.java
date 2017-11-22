package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigSettype;
import com.tianjian.comm.struts.form.CommConfigSettypeForm;
 
public interface ICommConfigSettypeService {
	/**新增之前的初始化*/
	public void addInit(CommConfigSettypeForm form);
	/** checkData*/
	public CommConfigSettype checkData(String id);
	/**保存*/
	public void save(CommConfigSettypeForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigSettypeForm form);
	/**更新*/
	public void update(CommConfigSettypeForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigSettypeForm form);
	/**删除*/
	public void delete(CommConfigSettypeForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigSettypeForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigSettypeForm form);
	/**getDetail*/
	public void getDetail(CommConfigSettypeForm form);
	
}
