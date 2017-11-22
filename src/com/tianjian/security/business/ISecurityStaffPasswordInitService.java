package com.tianjian.security.business;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.struts.form.SecurityStaffPasswordInitForm;

public interface ISecurityStaffPasswordInitService {

	public void update(SecurityStaffPasswordInitForm form);

	public int getStaffSelectCount(String staffId, String name, String inputCode, String itemCode,String staffHspId,String hspConfigId);

	public void getSearch(SecurityStaffPasswordInitForm form, int from, int length);

	public void searchInit(SecurityStaffPasswordInitForm form);
	
	public String getXml(String flag, String inputCode, String hspType,HttpServletRequest request);
}