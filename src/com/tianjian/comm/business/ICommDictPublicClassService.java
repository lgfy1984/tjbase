package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommDictPublicClass;
import com.tianjian.comm.struts.form.CommDictPublicClassForm;
 
public interface ICommDictPublicClassService {
  /**新增之前的初始化*/
	public void addInit(CommDictPublicClassForm form);
	/** checkData*/
	public CommDictPublicClass checkData(String id);
	/**保存*/
	public void save(CommDictPublicClassForm form);
	/**更新之前初始化*/
	public void updateInit(CommDictPublicClassForm form);
	/**更新*/
	public void update(CommDictPublicClassForm form);
	/**显示之前初始化*/
	public void showInit(CommDictPublicClassForm form);
	/**删除*/
	public void delete(CommDictPublicClassForm form);	
	/**获取总记录数*/
	public int getCount(String classCode, String className, String inputCode, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommDictPublicClassForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommDictPublicClassForm form);
	/**getDetail*/
	public void getDetail(CommDictPublicClassForm form);
	
}
