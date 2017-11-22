package com.tianjian.security.business;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.struts.form.LoginMenuForm;
import com.tianjian.security.struts.form.LoginSecondForm;
import com.tianjian.security.struts.form.SessionForm;

public interface ILoginSecondService {

	 
    public void getPublicAndMenu(LoginMenuForm loginMenuForm);
    
    public void getMenuDetail(String pubId, String[] rolesId, LoginMenuForm loginMenuForm) throws Exception ;
    
    public void getLoginSecondSessionForm(SessionForm sessionForm);
    
    /**
	 * 根据父级菜单ID查找下一级的子菜单
	 * */
	public List<?> findByParentId(String id, String[] rolesId);
	
	public SecurityConfigMenus findById(String Id);
	
	/**
	 * 根据第二类菜单ID寻找它的父类菜单（就是第一类菜单ID）
	 * */
	
	public String findByPublicClassId(String publicClassId);
	
	public void getEndPublicClass(SessionForm sessionForm,String publicClassId,String staffId,HttpServletRequest request);
	
	public List<?> getPublic(LoginMenuForm loginMenuForm);
	
	public List<?> getPublicAll(SessionForm sessionForm);
	
	public List<?> getMenuAll(SessionForm sessionForm);
	
	public List<?> getMenusByPubId(SessionForm sessionForm,String pubId);
	
	public void findByPublicClass(SessionForm sessionForm ,String publicClassId);
	//处理菜单
	public String replaceUrl(String line, String oldString, String staffId);
}
