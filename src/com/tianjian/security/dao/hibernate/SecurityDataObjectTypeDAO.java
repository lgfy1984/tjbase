package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.dao.ISecurityDataObjectTypeDAO;

public class SecurityDataObjectTypeDAO extends HibernateDaoSupport implements ISecurityDataObjectTypeDAO  {

	
	public void save(Object obj) {
		getHibernateTemplate().save(obj);
	}

	
	public int checkId(String id) {
		return getHibernateTemplate().find("from SecurityDataObjectType t where t.id='"+id+"'").size();
	}

	
	public int getCount(String id, String dataObjectTypeName, String comulnName) {
		String hql="from SecurityDataObjectType s where 1=1";
		if(id.trim().length()>0){
			hql+=" and s.id = '"+id.trim()+"'";
		}
		if(dataObjectTypeName.trim().length()>0){
			hql+=" and s.dataObjectTypeName like '"+dataObjectTypeName.trim()+"%'";
		}
		if(comulnName.trim().length()>0){
			hql+=" and s.columnName like '"+comulnName.trim()+"%'";
		}
		return this.getHibernateTemplate().find(hql).size();
	}

	@SuppressWarnings("unchecked")
	
	public List<?> getDate(String id,
			String dataObjectTypeName, String comulnName,int first,int pageSize,String orderNo) {
		String hql="from SecurityDataObjectType s where 1=1";
		if(id.trim().length()>0){
			hql+=" and s.id = '"+id.trim()+"'";
		}
		if(dataObjectTypeName.trim().length()>0){
			hql+=" and s.dataObjectTypeName like '"+dataObjectTypeName.trim()+"%'";
		}
		if(comulnName.trim().length()>0){
			hql+=" and s.columnName like '"+comulnName.trim()+"%'";
		}
		if(orderNo != null && orderNo.trim().length() > 0){
			hql += " order by " + orderNo;
		}
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	
	public SecurityDataObjectType detail(String id) {
		SecurityDataObjectType type=null;
		String hql="from SecurityDataObjectType t where t.id=?";
		List<?> list=getHibernateTemplate().find(hql,new String[]{id.trim()});
		if(list!=null&&list.size()>0){
			type=(SecurityDataObjectType)list.get(0);
		}
		return type;
		
	}

	
	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	
	public void update(Object obj) {
		getHibernateTemplate().update(obj);
	}

}
