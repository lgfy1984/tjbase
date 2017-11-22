package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigEmptitle;
import com.tianjian.comm.struts.form.CommConfigEmptitleForm;
 
public interface ICommConfigEmptitleService {
  /**新增之前的初始化*/
	public void addInit(CommConfigEmptitleForm form);
	/** checkData*/
	public CommConfigEmptitle checkData(String id);
	/**保存*/
	public void save(CommConfigEmptitleForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigEmptitleForm form);
	/**更新*/
	public void update(CommConfigEmptitleForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigEmptitleForm form);
	/**删除*/
	public void delete(CommConfigEmptitleForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigEmptitleForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigEmptitleForm form);
	/**getDetail*/
	public void getDetail(CommConfigEmptitleForm form);
	
}
