package com.tianjian.security.business;


import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.struts.form.SecurityUserVsRolesOpForm;

public interface ISecurityUserVsRolesOpService {
	
	public void getDetail(SecurityUserVsRolesOpForm form);
	
	public void searchInit(SecurityUserVsRolesOpForm form);
	
	public int getCount(String securityConfigRolesId, String hspConfigBaseinfoId);

    public void getSearch(SecurityUserVsRolesOpForm form, int i, int j);
    
    public void setRequest(HttpServletRequest request);
}
