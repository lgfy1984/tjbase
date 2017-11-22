package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigDeptAttr;
import com.tianjian.comm.struts.form.CommConfigDepttypeForm;
 
public interface ICommConfigDepttypeService {
  /**新增之前的初始化*/
	public void addInit(CommConfigDepttypeForm form);
	/** checkData*/
	public CommConfigDeptAttr checkData(String id);
	/**保存*/
	public void save(CommConfigDepttypeForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigDepttypeForm form);
	/**更新*/
	public void update(CommConfigDepttypeForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigDepttypeForm form);
	/**删除*/
	public void delete(CommConfigDepttypeForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigDepttypeForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigDepttypeForm form);
	/**getDetail*/
	public void getDetail(CommConfigDepttypeForm form);
	
}
