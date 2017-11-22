package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigUnittype;
import com.tianjian.comm.struts.form.CommConfigUnittypeForm;
 
public interface ICommConfigUnittypeService {
  /**新增之前的初始化*/
	public void addInit(CommConfigUnittypeForm form);
	/** checkData*/
	public CommConfigUnittype checkData(String id);
	/**保存*/
	public void save(CommConfigUnittypeForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigUnittypeForm form);
	/**更新*/
	public void update(CommConfigUnittypeForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigUnittypeForm form);
	/**删除*/
	public void delete(CommConfigUnittypeForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigUnittypeForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigUnittypeForm form);
	/**getDetail*/
	public void getDetail(CommConfigUnittypeForm form);
	
}
