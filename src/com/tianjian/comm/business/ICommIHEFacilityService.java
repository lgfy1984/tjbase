package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommIHEFacility;
import com.tianjian.comm.struts.form.CommIHEFacilityForm;
 
public interface ICommIHEFacilityService {
  /**新增之前的初始化*/
	public void addInit(CommIHEFacilityForm form);
	/** checkData*/
	public CommIHEFacility checkData(String id);
	/**保存*/
	public void save(CommIHEFacilityForm form);
	/**更新之前初始化*/
	public void updateInit(CommIHEFacilityForm form);
	/**更新*/
	public void update(CommIHEFacilityForm form);
	/**显示之前初始化*/
	public void showInit(CommIHEFacilityForm form);
	/**删除*/
	public void delete(CommIHEFacilityForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommIHEFacilityForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommIHEFacilityForm form);
	/**getDetail*/
	public void getDetail(CommIHEFacilityForm form);
	
}
