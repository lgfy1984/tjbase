package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigDept;
import com.tianjian.comm.struts.form.CommConfigDeptForm;
 
public interface ICommConfigDeptService {
  /**新增之前的初始化*/
	public void addInit(CommConfigDeptForm form);
	/** checkData*/
	public CommConfigDept checkData(String id);
	/**保存*/
	public void save(CommConfigDeptForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigDeptForm form);
	/**更新*/
	public void update(CommConfigDeptForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigDeptForm form);
	/**删除*/
	public void delete(CommConfigDeptForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigDeptForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigDeptForm form);
	/**getDetail*/
	public void getDetail(CommConfigDeptForm form);
	
}
