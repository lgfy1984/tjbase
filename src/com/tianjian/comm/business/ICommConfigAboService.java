package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigAbo;
import com.tianjian.comm.struts.form.CommConfigAboForm;
 
public interface ICommConfigAboService {
  /**新增之前的初始化*/
	public void addInit(CommConfigAboForm form);
	/** checkData*/
	public CommConfigAbo checkData(String id);
	/**保存*/
	public void save(CommConfigAboForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigAboForm form);
	/**更新*/
	public void update(CommConfigAboForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigAboForm form);
	/**删除*/
	public void delete(CommConfigAboForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigAboForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigAboForm form);
	/**getDetail*/
	public void getDetail(CommConfigAboForm form);
	
}
