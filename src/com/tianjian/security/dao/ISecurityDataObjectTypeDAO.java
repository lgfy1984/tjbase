package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityDataObjectType;

public interface ISecurityDataObjectTypeDAO {
	public void save(Object obj);
	
	public int checkId(String id);
	
	public int getCount(String id,String dataObjectTypeName,String comulnName);
	
	public List<?> getDate(String id,String dataObjectTypeName,String comulnName,int first,int pageSize,String orderNo);

	public SecurityDataObjectType detail(String id);
	
	public void delete(Object obj);
	
	public void update(Object obj);
}
