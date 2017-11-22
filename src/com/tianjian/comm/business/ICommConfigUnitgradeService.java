package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigUnitgrade;
import com.tianjian.comm.struts.form.CommConfigUnitgradeForm;
 
public interface ICommConfigUnitgradeService {
  /**新增之前的初始化*/
	public void addInit(CommConfigUnitgradeForm form);
	/** checkData*/
	public CommConfigUnitgrade checkData(String id);
	/**保存*/
	public void save(CommConfigUnitgradeForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigUnitgradeForm form);
	/**更新*/
	public void update(CommConfigUnitgradeForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigUnitgradeForm form);
	/**删除*/
	public void delete(CommConfigUnitgradeForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigUnitgradeForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigUnitgradeForm form);
	/**getDetail*/
	public void getDetail(CommConfigUnitgradeForm form);
	
}
