package com.tianjian.hsp.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.dao.IHealthRegisterTreeDAO;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.Converter;

public class HealthRegisterTreeDAO extends HibernateDaoSupport implements IHealthRegisterTreeDAO{

	@Override
	public String getMaxSeqNo(String tableName, String seqNoFieldName) {
		try{
			String tenantId = TenantIdHolder.get();
			String sql = "select max(a."+seqNoFieldName+") from "+tableName+" a where a.tenant_id = '"+tenantId+"'";
			List<Object> list = this.getSession().createSQLQuery(sql).list();
			if(list != null && list.size() > 0){
				Object obj = list.get(0);
				return Converter.toBlank(obj);
			}else{
				return "0";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean delete(Object obj) {
		try{
			this.getHibernateTemplate().delete(obj);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean save(Object obj) {
		try{
			this.getHibernateTemplate().save(obj);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean merge(Object obj) {
		try{
			this.getHibernateTemplate().merge(obj);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<?> getDeptByHspId(String flag, String input, String hspId,
			int currentCount, int pageSize) {
		String tenantId = TenantIdHolder.get();
		StringBuilder hql = new StringBuilder("from HspDeptBaseinfo a where a.id.hspConfigBaseinfoId = ? and a.tenantId = ? ");
		if(input != null && input.trim().length() > 0){
			if("1".equals(flag)){
				hql.append(" and a.inputCode like '%").append(input.trim().toUpperCase()).append("%'");
			}
			if("2".equals(flag)){
				hql.append(" and a.id.deptCode='").append(input.trim()).append("'");
			}
			if("3".equals(flag)){
				hql.append(" and a.deptName like '%").append(input.trim()).append("%'");
			}
		}
		Query q=this.getSession().createQuery(hql.toString());
		q.setString(0, hspId);
		q.setString(1, tenantId);
		q.setFirstResult(currentCount);
		q.setMaxResults(pageSize);
		return q.list();		
	}

	@Override
	public List<?> getDeptByHspCode(String flag, String input,
			String hspCode, Integer currentCount, Integer pageSize) {
		String tenantId = TenantIdHolder.get();
		StringBuilder hql = new StringBuilder("select a from HspDeptBaseinfo a, HspConfigBaseinfo b where a.id.hspConfigBaseinfoId = b.id and b.itemCode=? and a.tenantId = ? ");
		if(input != null && input.trim().length() > 0){
			if("1".equals(flag)){
				hql.append(" and a.inputCode like '%").append(input.trim().toUpperCase()).append("%'");
			}
			if("2".equals(flag)){
				hql.append(" and a.id.deptCode='").append(input.trim()).append("'");
			}
			if("3".equals(flag)){
				hql.append(" and a.deptName like '%").append(input.trim()).append("%'");
			}
		}
		Query q=this.getSession().createQuery(hql.toString());
		q.setString(0, hspCode);
		q.setString(1, tenantId);
		q.setFirstResult(currentCount);
		q.setMaxResults(pageSize);
		return q.list();	
	}

}
