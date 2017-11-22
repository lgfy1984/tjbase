package com.tianjian.security.business.service;

import com.tianjian.security.bean.SecurityStaffAliveAccess;
import com.tianjian.security.business.ISecurityStaffAliveAccessService;
import com.tianjian.security.dao.ISecurityStaffAliveAccessDAO;

public class SecurityStaffAliveAccessServiceImpl implements ISecurityStaffAliveAccessService{
	private ISecurityStaffAliveAccessDAO securityStaffAliveAccessDAO;

	public ISecurityStaffAliveAccessDAO getSecurityStaffAliveAccessDAO() {
		return securityStaffAliveAccessDAO;
	}

	public void setSecurityStaffAliveAccessDAO(
			ISecurityStaffAliveAccessDAO securityStaffAliveAccessDAO) {
		this.securityStaffAliveAccessDAO = securityStaffAliveAccessDAO;
	}
	
	public void save(SecurityStaffAliveAccess sSAA){
		this.securityStaffAliveAccessDAO.save(sSAA);
	}
	
	public void update(SecurityStaffAliveAccess sSAA){
		this.securityStaffAliveAccessDAO.update(sSAA);
	} 
	
	public SecurityStaffAliveAccess getByStaffId(String staffId){
		return this.securityStaffAliveAccessDAO.getByStaffId(staffId);
	}

	@Override
	public void saveOrUpdate(SecurityStaffAliveAccess sSAA) {
		this.securityStaffAliveAccessDAO.saveOrUpdate(sSAA);
	} 
}
