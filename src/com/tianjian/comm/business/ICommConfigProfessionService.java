package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigProfession;
import com.tianjian.comm.struts.form.CommConfigProfessionForm;
 
public interface ICommConfigProfessionService {
  /**新增之前的初始化*/
	public void addInit(CommConfigProfessionForm form);
	/** checkData*/
	public CommConfigProfession checkData(String id);
	/**保存*/
	public void save(CommConfigProfessionForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigProfessionForm form);
	/**更新*/
	public void update(CommConfigProfessionForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigProfessionForm form);
	/**删除*/
	public void delete(CommConfigProfessionForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigProfessionForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigProfessionForm form);
	/**getDetail*/
	public void getDetail(CommConfigProfessionForm form);
	
}
