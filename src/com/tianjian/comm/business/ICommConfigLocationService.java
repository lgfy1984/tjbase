package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.struts.form.CommConfigLocationForm;
 
public interface ICommConfigLocationService {
  /**新增之前的初始化*/
	public void addInit(CommConfigLocationForm form);
	/** checkData*/
	public CommConfigLocation checkData(String id);
	/**保存*/
	public void save(CommConfigLocationForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigLocationForm form);
	/**更新*/
	public void update(CommConfigLocationForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigLocationForm form);
	/**删除*/
	public void delete(CommConfigLocationForm form);	
	/**获取总记录数*/
	public int getCount(String id,String itemCode, String itemName, String levelFlag,String parentId, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigLocationForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigLocationForm form);
	/**getDetail*/
	public void getDetail(CommConfigLocationForm form);
	
}
