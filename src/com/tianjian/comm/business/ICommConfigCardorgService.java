package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigCardorg;
import com.tianjian.comm.struts.form.CommConfigCardorgForm;
 
public interface ICommConfigCardorgService {
  /**新增之前的初始化*/
	public void addInit(CommConfigCardorgForm form);
	/** checkData*/
	public CommConfigCardorg checkData(String id);
	/**保存*/
	public void save(CommConfigCardorgForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigCardorgForm form);
	/**更新*/
	public void update(CommConfigCardorgForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigCardorgForm form);
	/**删除*/
	public void delete(CommConfigCardorgForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigCardorgForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigCardorgForm form);
	/**getDetail*/
	public void getDetail(CommConfigCardorgForm form);
	
}
