package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigIdType;
import com.tianjian.comm.struts.form.CommConfigIdTypeForm;
 
public interface ICommConfigIdTypeService {
  /**新增之前的初始化*/
	public void addInit(CommConfigIdTypeForm form);
	/** checkData*/
	public CommConfigIdType checkData(String id);
	/**保存*/
	public void save(CommConfigIdTypeForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigIdTypeForm form);
	/**更新*/
	public void update(CommConfigIdTypeForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigIdTypeForm form);
	/**删除*/
	public void delete(CommConfigIdTypeForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigIdTypeForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigIdTypeForm form);
	/**getDetail*/
	public void getDetail(CommConfigIdTypeForm form);
	
}
