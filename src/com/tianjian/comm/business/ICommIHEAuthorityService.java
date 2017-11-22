package com.tianjian.comm.business;


 

import com.tianjian.comm.bean.CommIHEAuthority;
import com.tianjian.comm.struts.form.CommIHEAuthorityForm;
 
public interface ICommIHEAuthorityService {
  /**新增之前的初始化*/
	public void addInit(CommIHEAuthorityForm form);
	/** checkData*/
	public CommIHEAuthority checkData(String id);
	/**保存*/
	public void save(CommIHEAuthorityForm form);
	/**更新之前初始化*/
	public void updateInit(CommIHEAuthorityForm form);
	/**更新*/
	public void update(CommIHEAuthorityForm form);
	/**显示之前初始化*/
	public void showInit(CommIHEAuthorityForm form);
	/**删除*/
	public void delete(CommIHEAuthorityForm form);	
	/**获取总记录数*/
	public int getCount(String universalId, String universalIdType, String namespaceId, String seqNo);
	/**获取当页记录*/
	public void getSearch(CommIHEAuthorityForm form, int curCount, int pageSize);
	/**serchInit*/
	public void serchInit(CommIHEAuthorityForm form);
	/**getDetail*/
	public void getDetail(CommIHEAuthorityForm form);
	
}
