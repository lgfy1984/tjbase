package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigCountry;
import com.tianjian.comm.struts.form.CommConfigCountryForm;
 
public interface ICommConfigCountryService {
  /**新增之前的初始化*/
	public void addInit(CommConfigCountryForm form);
	/** checkData*/
	public CommConfigCountry checkData(String id);
	/**保存*/
	public void save(CommConfigCountryForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigCountryForm form);
	/**更新*/
	public void update(CommConfigCountryForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigCountryForm form);
	/**删除*/
	public void delete(CommConfigCountryForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigCountryForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigCountryForm form);
	/**getDetail*/
	public void getDetail(CommConfigCountryForm form);
	
}
