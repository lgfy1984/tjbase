package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommConfigEconkind;
import com.tianjian.comm.struts.form.CommConfigEconkindForm;
 
public interface ICommConfigEconkindService {
  /**新增之前的初始化*/
	public void addInit(CommConfigEconkindForm form);
	/** checkData*/
	public CommConfigEconkind checkData(String id);
	/**保存*/
	public void save(CommConfigEconkindForm form);
	/**更新之前初始化*/
	public void updateInit(CommConfigEconkindForm form);
	/**更新*/
	public void update(CommConfigEconkindForm form);
	/**显示之前初始化*/
	public void showInit(CommConfigEconkindForm form);
	/**删除*/
	public void delete(CommConfigEconkindForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommConfigEconkindForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommConfigEconkindForm form);
	/**getDetail*/
	public void getDetail(CommConfigEconkindForm form);
	
}
