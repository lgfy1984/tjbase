package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigNationality;
import com.tianjian.comm.struts.form.CommConfigNationalityForm;
 
public interface ICommConfigNationalityService {
  /**新增之前的初始化*/
	public void addInit(CommConfigNationalityForm form);
	/** checkData*/
	public CommConfigNationality checkData(String id);
	/**保存*/
	public void save(CommConfigNationalityForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigNationalityForm form);
	/**更新*/
	public void update(CommConfigNationalityForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigNationalityForm form);
	/**删除*/
	public void delete(CommConfigNationalityForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigNationalityForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigNationalityForm form);
	/**getDetail*/
	public void getDetail(CommConfigNationalityForm form);
	
}
