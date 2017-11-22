package com.tianjian.comm.business;



import com.tianjian.comm.bean.CommDictPublicDouble;
import com.tianjian.comm.struts.form.CommDictPublicDoubleForm;
 
public interface ICommDictPublicDoubleService {
  /**新增之前的初始化*/
	public void addInit(CommDictPublicDoubleForm form);
	/** checkData*/
	public CommDictPublicDouble checkData(String id);
	/**保存*/
	public void save(CommDictPublicDoubleForm form);
	/**更新之前初始化*/
	public void updateInit(CommDictPublicDoubleForm form);
	/**更新*/
	public void update(CommDictPublicDoubleForm form);
	/**显示之前初始化*/
	public void showInit(CommDictPublicDoubleForm form);
	/**删除*/
	public void delete(CommDictPublicDoubleForm form);	
	/**获取总记录数*/
	public int getCount(String id, String classCode, String dictCode, String dictValue, String inputCode,String seqNo);
	/**获取当页记录*/
	public void getSearch(CommDictPublicDoubleForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommDictPublicDoubleForm form);
	/**getDetail*/
	public void getDetail(CommDictPublicDoubleForm form);
	
}
