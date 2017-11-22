package com.tianjian.security.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;

public interface ILoginSecondDAO {
    
	public SecurityStaffBaseinfo getByCode(String code) throws Exception ;
	
	public SecuritySystemUsers getById(String id) throws Exception;
	
	public List<?> getBysecurityStaffBaseinfoId(String rolesId)throws Exception;
	
	public List<?> getPublic(String publicClassId,String roles) throws Exception;
	 
	public List<?> getMenu(String roles,String pubId) throws Exception ;
	
	public List getPublicClass(String roles,HttpServletRequest request) throws Exception;
	
	/**
	 * 根据父级菜单ID查找所有的子菜单
	 * */
	public List<?> findByParentId(String id, String[] rolesId);
	public SecurityConfigMenus findById(String Id) ;
	
	public List<?> findByPublicClassId(String publicClassId);
	
	public List<SecurityConfigPublicClass> getPublicClassByParentId(String parentId);
	
	public SecurityConfigPublicClass getPublicClassById(String id) ;
	
	public List getPublicAll(String roles) throws Exception;
	public List getMenuAll(String roles) throws Exception;

}