package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommMenuHelp;
import com.tianjian.comm.struts.form.CommMenuHelpForm;
 
public interface ICommMenuHelpService {
	public void help(CommMenuHelpForm form);
  /**新增之前的初始化*/
	public void addInit(CommMenuHelpForm form);
	/** checkData*/
	public CommMenuHelp checkData(String id);
	/**保存*/
	public void save(CommMenuHelpForm form);
	/**更新之前初始化*/
	public void updateInit(CommMenuHelpForm form);
	/**更新*/
	public void update(CommMenuHelpForm form);
	/**显示之前初始化*/
	public void showInit(CommMenuHelpForm form);
	/**删除*/
	public void delete(CommMenuHelpForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommMenuHelpForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommMenuHelpForm form);
	/**getDetail*/
	public void getDetail(CommMenuHelpForm form);
	
}
