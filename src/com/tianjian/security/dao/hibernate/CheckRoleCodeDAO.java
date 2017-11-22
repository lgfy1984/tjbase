package com.tianjian.security.dao.hibernate;

import java.util.Arrays;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.dao.ICheckRoleCodeDAO;

public class CheckRoleCodeDAO extends HibernateDaoSupport implements ICheckRoleCodeDAO{

	@Override
	public boolean checkRoleCodeByIds(String[] roleIds, String checkRoleCode) {
		if(roleIds == null || roleIds.length == 0 || checkRoleCode == null || checkRoleCode.length() == 0){
			return false;
		}
		StringBuilder sb = new StringBuilder("select 1 from security.security_config_roles where 1=1 ");
		sb.append(" and (id in(");
		for(int i = 0; i < roleIds.length; i++){
			if((i+1) % 1000 == 0){//in里面最多只能放1000个
				sb.delete(sb.length()-1, sb.length());
				sb.append(") or id in(");
			}
			sb.append("'").append(roleIds[i]).append("',");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")) and role_code='"+checkRoleCode+"'");
		List<?> li = this.getSession().createSQLQuery(sb.toString()).list();
		if(li != null && li.size() > 0){
			return true;
		}
		return false;
	}

}
