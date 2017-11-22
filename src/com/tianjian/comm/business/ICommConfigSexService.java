package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigSex;
import com.tianjian.comm.struts.form.CommConfigSexForm;
 
public interface ICommConfigSexService {
  /**新增之前的初始化*/
	public void addInit(CommConfigSexForm form);
	/** checkData*/
	public CommConfigSex checkData(String id);
	/**保存*/
	public void save(CommConfigSexForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigSexForm form);
	/**更新*/
	public void update(CommConfigSexForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigSexForm form);
	/**删除*/
	public void delete(CommConfigSexForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigSexForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigSexForm form);
	/**getDetail*/
	public void getDetail(CommConfigSexForm form);
	
}
