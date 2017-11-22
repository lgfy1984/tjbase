package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommIHENameType;
import com.tianjian.comm.struts.form.CommIHENameTypeForm;
 
public interface ICommIHENameTypeService {
  /**新增之前的初始化*/
	public void addInit(CommIHENameTypeForm form);
	/** checkData*/
	public CommIHENameType checkData(String id);
	/**保存*/
	public void save(CommIHENameTypeForm form);
	/**更新之前初始化*/
	public void updateInit(CommIHENameTypeForm form);
	/**更新*/
	public void update(CommIHENameTypeForm form);
	/**显示之前初始化*/
	public void showInit(CommIHENameTypeForm form);
	/**删除*/
	public void delete(CommIHENameTypeForm form);	
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommIHENameTypeForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommIHENameTypeForm form);
	/**getDetail*/
	public void getDetail(CommIHENameTypeForm form);
	
}
