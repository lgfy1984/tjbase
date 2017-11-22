package com.tianjian.security.business;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.struts.form.FindPasswordForm;

public interface IFindPasswordService { 
	
    public List<?> getInformation(FindPasswordForm findPasswordForm);
    
    public void updatePassword(String securityStaffBaseinfoId,String password);
    
    public String checkData(FindPasswordForm findPasswordForm,HttpServletRequest request);
}
