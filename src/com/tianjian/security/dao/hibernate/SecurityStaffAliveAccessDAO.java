package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityStaffAliveAccess;
import com.tianjian.security.dao.ISecurityStaffAliveAccessDAO;

public class SecurityStaffAliveAccessDAO extends HibernateDaoSupport implements ISecurityStaffAliveAccessDAO{
	public void save(SecurityStaffAliveAccess sSAA){
		getHibernateTemplate().save(sSAA);
	}

	
	@Override
	public void update(SecurityStaffAliveAccess sSAA) {
		getHibernateTemplate().update(sSAA);
	}
	
	@Override
	public SecurityStaffAliveAccess getByStaffId(String staffId) {
		SecurityStaffAliveAccess temp = null;
		List<?> ls = getHibernateTemplate().find(" from SecurityStaffAliveAccess a where a.securityStaffBaseinfoId = ? ", staffId);
		if (ls != null && ls.size() > 0) {
			temp = (SecurityStaffAliveAccess) ls.get(0);
			return temp;
		}
		
		return null;
	}


	@Override
	public void saveOrUpdate(SecurityStaffAliveAccess sSAA) {
		getHibernateTemplate().saveOrUpdate(sSAA);
	}

	
}

