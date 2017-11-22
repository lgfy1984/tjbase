package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigRelationship;
import com.tianjian.comm.struts.form.CommConfigRelationshipForm;
 
public interface ICommConfigRelationshipService {
  /**新增之前的初始化*/
	public void addInit(CommConfigRelationshipForm form);
	/** checkData*/
	public CommConfigRelationship checkData(String id);
	/**保存*/
	public void save(CommConfigRelationshipForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigRelationshipForm form);
	/**更新*/
	public void update(CommConfigRelationshipForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigRelationshipForm form);
	/**删除*/
	public void delete(CommConfigRelationshipForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigRelationshipForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigRelationshipForm form);
	/**getDetail*/
	public void getDetail(CommConfigRelationshipForm form);
	
}
