package com.tianjian.security.business.service;

import com.tianjian.security.business.ICheckRoleCodeService;
import com.tianjian.security.dao.ICheckRoleCodeDAO;

public class CheckRoleCodeServiceImpl implements ICheckRoleCodeService{
	private ICheckRoleCodeDAO checkRoleCodeDAO;
	
	public ICheckRoleCodeDAO getCheckRoleCodeDAO() {
		return checkRoleCodeDAO;
	}

	public void setCheckRoleCodeDAO(ICheckRoleCodeDAO checkRoleCodeDAO) {
		this.checkRoleCodeDAO = checkRoleCodeDAO;
	}


	public boolean checkRoleCodeByIds(String[] roleIds, String checkRoleCode){
		return this.checkRoleCodeDAO.checkRoleCodeByIds(roleIds, checkRoleCode);
	}
}
