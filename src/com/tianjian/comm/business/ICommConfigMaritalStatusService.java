package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigMaritalStatus;
import com.tianjian.comm.struts.form.CommConfigMaritalStatusForm;
 
public interface ICommConfigMaritalStatusService {
  /**新增之前的初始化*/
	public void addInit(CommConfigMaritalStatusForm form);
	/** checkData*/
	public CommConfigMaritalStatus checkData(String id);
	/**保存*/
	public void save(CommConfigMaritalStatusForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigMaritalStatusForm form);
	/**更新*/
	public void update(CommConfigMaritalStatusForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigMaritalStatusForm form);
	/**删除*/
	public void delete(CommConfigMaritalStatusForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigMaritalStatusForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigMaritalStatusForm form);
	/**getDetail*/
	public void getDetail(CommConfigMaritalStatusForm form);
	
}
