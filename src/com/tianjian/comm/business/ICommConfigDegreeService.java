package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigDegree;
import com.tianjian.comm.struts.form.CommConfigDegreeForm;
 
public interface ICommConfigDegreeService {
  /**新增之前的初始化*/
	public void addInit(CommConfigDegreeForm form);
	/** checkData*/
	public CommConfigDegree checkData(String id);
	/**保存*/
	public void save(CommConfigDegreeForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigDegreeForm form);
	/**更新*/
	public void update(CommConfigDegreeForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigDegreeForm form);
	/**删除*/
	public void delete(CommConfigDegreeForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigDegreeForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigDegreeForm form);
	/**getDetail*/
	public void getDetail(CommConfigDegreeForm form);
	
}
