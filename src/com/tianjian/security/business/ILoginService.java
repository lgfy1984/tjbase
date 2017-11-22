package com.tianjian.security.business;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.struts.form.LoginForm;
import com.tianjian.security.struts.form.LoginSecondForm;

public interface ILoginService {

	public void checkUser(LoginForm form,HttpServletRequest request);	
	
    public void getPublicClass(String UserId, LoginSecondForm loginSecondForm ,HttpServletRequest request);
    
    public void checkVersionRegistCode(LoginForm form,HttpServletRequest request);
    
    public String getEndPublicClass(String publicClassId,String staffId ,HttpServletRequest request) throws Exception;
    
    public void setRequest(HttpServletRequest request);
   }
