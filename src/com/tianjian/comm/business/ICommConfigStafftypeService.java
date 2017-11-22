package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigStafftype;
import com.tianjian.comm.struts.form.CommConfigStafftypeForm;
 
public interface ICommConfigStafftypeService {
	public CommConfigStafftype checkDataByName(String itemName);
  /**新增之前的初始化*/
	public void addInit(CommConfigStafftypeForm form);
	/** checkData*/
	public CommConfigStafftype checkData(String id);
	/**保存*/
	public void save(CommConfigStafftypeForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigStafftypeForm form);
	/**更新*/
	public void update(CommConfigStafftypeForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigStafftypeForm form);
	/**删除*/
	public void delete(CommConfigStafftypeForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigStafftypeForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigStafftypeForm form);
	/**getDetail*/
	public void getDetail(CommConfigStafftypeForm form);
	
}
