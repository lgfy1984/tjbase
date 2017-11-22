package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigRh;
import com.tianjian.comm.struts.form.CommConfigRhForm;
 
public interface ICommConfigRhService {
  /**新增之前的初始化*/
	public void addInit(CommConfigRhForm form);
	/** checkData*/
	public CommConfigRh checkData(String id);
	/**保存*/
	public void save(CommConfigRhForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigRhForm form);
	/**更新*/
	public void update(CommConfigRhForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigRhForm form);
	/**删除*/
	public void delete(CommConfigRhForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigRhForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigRhForm form);
	/**getDetail*/
	public void getDetail(CommConfigRhForm form);
	
}
