package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigClinicattr;
import com.tianjian.comm.struts.form.CommConfigClinicattrForm;
 
public interface ICommConfigClinicattrService {
  /**新增之前的初始化*/
	public void addInit(CommConfigClinicattrForm form);
	/** checkData*/
	public CommConfigClinicattr checkData(String id);
	/**保存*/
	public void save(CommConfigClinicattrForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigClinicattrForm form);
	/**更新*/
	public void update(CommConfigClinicattrForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigClinicattrForm form);
	/**删除*/
	public void delete(CommConfigClinicattrForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigClinicattrForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigClinicattrForm form);
	/**getDetail*/
	public void getDetail(CommConfigClinicattrForm form);
	
}
