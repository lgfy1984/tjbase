package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigSystemtype;
import com.tianjian.comm.struts.form.CommConfigSystemtypeForm;
 
public interface ICommConfigSystemtypeService {
  /**新增之前的初始化*/
	public void addInit(CommConfigSystemtypeForm form);
	/** checkData*/
	public CommConfigSystemtype checkData(String id);
	/**保存*/
	public void save(CommConfigSystemtypeForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigSystemtypeForm form);
	/**更新*/
	public void update(CommConfigSystemtypeForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigSystemtypeForm form);
	/**删除*/
	public void delete(CommConfigSystemtypeForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigSystemtypeForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigSystemtypeForm form);
	/**getDetail*/
	public void getDetail(CommConfigSystemtypeForm form);
	
}
