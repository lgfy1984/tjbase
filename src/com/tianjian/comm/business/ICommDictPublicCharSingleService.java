package com.tianjian.comm.business;


 
import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.struts.form.CommDictPublicCharForm;
 
public interface ICommDictPublicCharSingleService {
  /**新增之前的初始化*/
	public void addInit(CommDictPublicCharForm form);
	/** checkData*/
	public CommDictPublicChar checkData(String id);
	/**保存*/
	public void save(CommDictPublicCharForm form);
	/**更新之前初始化*/
	public void updateInit(CommDictPublicCharForm form);
	/**更新*/
	public void update(CommDictPublicCharForm form);
	/**显示之前初始化*/
	public void showInit(CommDictPublicCharForm form);
	/**删除*/
	public void delete(CommDictPublicCharForm form);	
	/**获取总记录数*/
	public int getCount(String id, String classCode, String dictCode, String dictValue, String inputCode,String seqNo);
	/**获取当页记录*/
	public void getSearch(CommDictPublicCharForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommDictPublicCharForm form);
	/**getDetail*/
	public void getDetail(CommDictPublicCharForm form);
	
}
