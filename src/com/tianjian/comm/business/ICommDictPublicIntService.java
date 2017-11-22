package com.tianjian.comm.business;



import com.tianjian.comm.bean.CommDictPublicInt;
import com.tianjian.comm.struts.form.CommDictPublicIntForm;
 
public interface ICommDictPublicIntService {
  /**新增之前的初始化*/
	public void addInit(CommDictPublicIntForm form);
	/** checkData*/
	public CommDictPublicInt checkData(String id);
	/**保存*/
	public void save(CommDictPublicIntForm form);
	/**更新之前初始化*/
	public void updateInit(CommDictPublicIntForm form);
	/**更新*/
	public void update(CommDictPublicIntForm form);
	/**显示之前初始化*/
	public void showInit(CommDictPublicIntForm form);
	/**删除*/
	public void delete(CommDictPublicIntForm form);	
	/**获取总记录数*/
	public int getCount(String id, String classCode, String dictCode, String dictValue, String inputCode,String seqNo);
	/**获取当页记录*/
	public void getSearch(CommDictPublicIntForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommDictPublicIntForm form);
	/**getDetail*/
	public void getDetail(CommDictPublicIntForm form);
	
}
