package com.tianjian.security.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.business.IFindPasswordService;
import com.tianjian.security.dao.IFindPasswordDAO;
import com.tianjian.security.struts.form.FindPasswordForm;
import com.tianjian.util.ResourcesUtil;

public class FindPasswordServiceImpl implements IFindPasswordService {
	
	private IFindPasswordDAO findPasswordDAO;
	
	public IFindPasswordDAO getFindPasswordDAO() {
		return findPasswordDAO;
	}

	public void setFindPasswordDAO(IFindPasswordDAO findPasswordDAO) {
		this.findPasswordDAO = findPasswordDAO;
	}

	@Override
	public List<?> getInformation(FindPasswordForm findPasswordForm) {
		return this.getFindPasswordDAO().getList(findPasswordForm.getUserName());
		
	}
	
	public String checkData(FindPasswordForm findPasswordForm,HttpServletRequest request) {
		String temp = "";
		if (findPasswordForm.getUserName() != null && findPasswordForm.getUserName().trim().length() > 0) {
			SecurityStaffBaseinfo data = this.getFindPasswordDAO().findByStaffCode(findPasswordForm.getUserName());
			if (data != null && data.getId() != null) {
				temp = data.getId();
			}
		} else {
			temp = ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn6", request);//"数据不存在";
		}
		return temp;
	}
	
	@Override
	public void updatePassword(String securityStaffBaseinfoId,String password){
		SecuritySystemUsers  bean = this.getFindPasswordDAO().findBySecurityStaffBaseinfoId(securityStaffBaseinfoId);
		bean.setPasswd(password);
		this.getFindPasswordDAO().updatePassword(bean);
	}
}
