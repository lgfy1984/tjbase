package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityConfigParamProject;
import com.tianjian.security.dao.ISecurityConfigParamProjectDAO;

public class SecurityConfigParamProjectDAO extends HibernateDaoSupport implements
		ISecurityConfigParamProjectDAO {

	
	public int count(String projectCode, String projectName, String inputCode,
			String seqNo) {
		String hql = "from SecurityConfigParamProject s where 1=1";
		if (projectCode.trim().length() > 0) {
			hql += " and s.projectCode = '" + projectCode.trim() + "'";
		}
		if (projectName.trim().length() > 0) {
			hql += " and lower(s.projectName) like '%" + projectName.trim().toLowerCase() + "%'";
		}
		if (inputCode.trim().length() > 0) {
			hql += " and s.inputCode like '%" + inputCode.trim().toUpperCase()
					+ "%'";
		}
		if (seqNo.trim().length() > 0) {
			hql += " and s.seqNo  = '" + seqNo.trim() + "'";
		}
		List<?> list = getHibernateTemplate().find(hql);
		return list.size();
	}

	
	public List<?> queryList(String projectCode, String projectName, String inputCode,
			String seqNo, String orderNo,
			int cur, int pageSize) {
		String hql = "from SecurityConfigParamProject s where 1=1";
		if (projectCode.trim().length() > 0) {
			hql += " and s.projectCode='" + projectCode.trim() + "'";
		}
		if (projectName.trim().length() > 0) {
			hql += " and s.projectName like '%" + projectName.trim() + "%'";
		}
		if (inputCode.trim().length() > 0) {
			hql += " and s.inputCode like '%" + inputCode.trim().toUpperCase()
					+ "%'";
		}
		if (seqNo.trim().length() > 0) {
			hql += " and s.seqNo = '" + seqNo.trim() + "'";
		}
		hql += " order by" + orderNo;
		Query q = getSession().createQuery(hql);
		q.setFirstResult(cur);
		q.setMaxResults(pageSize);
		return q.list();
	}

	
	public SecurityConfigParamProject queryByClassCode(String projectCode) {
		SecurityConfigParamProject scp = null;
		String hql = "from SecurityConfigParamProject s where 1=1";
		if (projectCode.trim().length() > 0) {
			hql += " and s.projectCode='" + projectCode.trim() + "'";
		}
		List<?> list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = (Object) list.get(i);
				scp = (SecurityConfigParamProject) obj;
			}
		}
		return scp;
	}

	
	public void deleteData(Object obj) {
		getHibernateTemplate().delete(obj);

	}

	
	public void saveData(Object obj) {
		getHibernateTemplate().save(obj);

	}

	
	public void updateData(Object obj) {
		getHibernateTemplate().update(obj);

	}

	
	public int checkClassCode(String projectCode) {
		return getHibernateTemplate().find(
				"from SecurityConfigParamProject s where s.projectCode='"
						+ projectCode + "'").size();
	}

	
	public List<?> findAll() {
		String hql = "from SecurityConfigParamProject s";
		List<?> list = getHibernateTemplate().find(hql);
		return list;
		/*
		 * try { String sql = " from SecurityConfigParamclass a ";
		 * 
		 * Query q = getSession().createQuery(sql); List<?> l = q.list();
		 * return l; } catch (Exception re) { re.printStackTrace(); return null; }
		 */
	}

	
	public SecurityConfigParamProject queryByClassName(String projectName) {
		SecurityConfigParamProject scp = null;
		String hql = "from SecurityConfigParamProject s where 1=1";
		if (projectName.trim().length() > 0) {
			hql += " and s.projectName='" + projectName.trim() + "'";
		}
		List<?> list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			scp = (SecurityConfigParamProject) list.get(0);
		}
		return scp;
	}
	
	public Long maxFind(){
		try{
			String sql = "select max(t.seqNo) from SecurityConfigParamProject t";
			Long temp = (Long)this.getSession().createQuery(sql).uniqueResult();
			if(temp == null){
				return 1L;
			}else{
				return temp+1;
			}
		}catch (Exception e){
			e.printStackTrace();
			return 1L;
		}	
	}
	
	public SecurityConfigParamProject getByProjectCode(String projectCode) {
		try {
			SecurityConfigParamProject temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigParamProject a where a.projectCode = ? ", projectCode);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigParamProject)ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	public List<?> getProject(String falg, String input, int count, int pageSize) {
		String hql="from SecurityConfigParamProject h where 1=1";
		if(input!=null&&input.trim().length()>0&&falg.equals("1")){
			hql+=" and h.inputCode like '%"+input.trim().toUpperCase()+"%'";
		}
		if(falg.equals("2")){
			hql+=" and h.seqNo like'%"+input.trim()+"%'";
		}
		if(falg.equals("3")){
			hql+=" and h.projectName like '%"+ input.trim() +"%'";
		}
		Query q=this.getSession().createQuery(hql);
		q.setFirstResult(count);
		q.setMaxResults(pageSize);
		return q.list();
	}
}
