package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigCardtype;
import com.tianjian.comm.struts.form.CommConfigCardtypeForm;
 
public interface ICommConfigCardtypeService {
  /**新增之前的初始化*/
	public void addInit(CommConfigCardtypeForm form);
	/** checkData*/
	public CommConfigCardtype checkData(String id);
	/**保存*/
	public void save(CommConfigCardtypeForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigCardtypeForm form);
	/**更新*/
	public void update(CommConfigCardtypeForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigCardtypeForm form);
	/**删除*/
	public void delete(CommConfigCardtypeForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigCardtypeForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigCardtypeForm form);
	/**getDetail*/
	public void getDetail(CommConfigCardtypeForm form);
	
}
