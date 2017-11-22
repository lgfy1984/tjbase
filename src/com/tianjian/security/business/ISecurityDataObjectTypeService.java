package com.tianjian.security.business;

import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.struts.form.SecurityDataObjectTypeForm;


public interface ISecurityDataObjectTypeService {
	public boolean save(String id,String dataObjectTypeName,String comulnName);
	
	public int check(String id);
	
	public int getCount(String id,String dataObjectTypeName, String comulnName);
	public void getSearch(SecurityDataObjectTypeForm form,int count,int pageSize);
	
	public void detail(SecurityDataObjectTypeForm form);
	
	public SecurityDataObjectType detail(String form);
	
	public void delete(SecurityDataObjectTypeForm form);
	
	public void update(SecurityDataObjectTypeForm form);
}
