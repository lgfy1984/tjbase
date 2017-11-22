package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommDictPublicLevel;
import com.tianjian.comm.struts.form.CommDictPublicLevelForm;
 
public interface ICommDictPublicLevelSingleService {
  /**新增之前的初始化*/
	public void addInit(CommDictPublicLevelForm form);
	/** checkData*/
	public CommDictPublicLevel checkData(String id);
	/**保存*/
	public void save(CommDictPublicLevelForm form);
	/**更新之前初始化*/
	public void updateInit(CommDictPublicLevelForm form);
	/**更新*/
	public void update(CommDictPublicLevelForm form);
	/**显示之前初始化*/
	public void showInit(CommDictPublicLevelForm form);
	/**删除*/
	public void delete(CommDictPublicLevelForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode,String itemName, String inputCode,String parentItemCode, String classLevel,  String seqInLevel, String tableCode);
	/**获取当页记录*/
	public void getSearch(CommDictPublicLevelForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommDictPublicLevelForm form);
	/**getDetail*/
	public void getDetail(CommDictPublicLevelForm form);
	
}
