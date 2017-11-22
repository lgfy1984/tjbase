package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityConfigParamclass;
import com.tianjian.security.bean.SecurityConfigParameter;
import com.tianjian.security.dao.ISecurityConfigParameterDAO;
import com.tianjian.util.comm.TJInit;

public class SecurityConfigParameterDAO extends HibernateDaoSupport implements
ISecurityConfigParameterDAO {

	@Override
	public int count(String id, String classCode, String hspConfigBaseinfoId,
		String itemName, String itemValue, String className) {
	String hql = " from  SecurityConfigParameter s , SecurityConfigParamclass s1 where s.classCode = s1.classCode ";
	// String hql = "from SecurityConfigParameter s where 1=1";
	if (id.trim() != null && id.trim().length() > 0) {
		hql += " and s.id='" + id.trim() + "'";
	}
	if (classCode.trim().length() > 0) {
		hql += " and s.classCode='" + classCode.trim() + "'";
	}
	if (hspConfigBaseinfoId.trim().length() > 0) {
		hql += " and s.hspConfigBaseinfoId='" + hspConfigBaseinfoId.trim()
				+ "'";
	}
	if (itemName.trim().length() > 0) {
		hql += " and s.itemName like '%" + itemName.trim() + "%'";
	}
	if (itemValue.trim().length() > 0) {
		hql += " and s.itemValue like '%" + itemValue.trim() + "%'";
	}
	if (className.trim().length() > 0) {
		hql += " and s1.className like '%" + className.trim() + "%'";
	}
	List<?> list = getHibernateTemplate().find(hql);
	return list.size();
	}

	@Override
	public List<?> queryList(String id, String classCode,
		String hspConfigBaseinfoId, String itemName, String itemValue,
		String className, String orderNo, int cur, int pageSize) {
	String hql = " from  SecurityConfigParameter s , SecurityConfigParamclass s1 where s.classCode = s1.classCode ";
	// String hql = " from SecurityConfigParameter s where 1=1";
	if (id.trim() != null && id.trim().length() > 0) {
		hql += " and s.id='" + id.trim() + "'";
	}
	if (classCode.trim().length() > 0) {
		hql += " and s.classCode='" + classCode.trim() + "'";
	}
	if (hspConfigBaseinfoId.trim().length() > 0) {
		hql += " and s.hspConfigBaseinfoId='" + hspConfigBaseinfoId.trim()
				+ "'";
	}
	if (itemName.trim().length() > 0) {
		hql += " and s.itemName like '%" + itemName.trim() + "%'";
	}
	if (itemValue.trim().length() > 0) {
		hql += " and s.itemValue like '%" + itemValue.trim() + "%'";
	}
	if (className.trim().length() > 0) {
		hql += " and s1.className like '%" + className.trim() + "%'";
	}
	if (orderNo.trim() != null && orderNo.trim().length() > 0) {
		hql += " order by" + orderNo;
	}
	
	Query q = getSession().createQuery(hql);
	q.setFirstResult(cur);
	q.setMaxResults(pageSize);
	return q.list();
	}
	
	

	@Override
	public SecurityConfigParameter queryById(String id) {
	SecurityConfigParameter scp = null;
	String hql = "from SecurityConfigParameter s where 1=1";
	if (id.trim() != null && id.trim().length() > 0) {
		hql += " and s.id='" + id.trim() + "'";
	}
	List<?> list = getHibernateTemplate().find(hql);
	if (list != null && list.size() > 0) {
		for (int i = 0; i < list.size(); i++) {
			Object obj = (Object) list.get(i);
			scp = (SecurityConfigParameter) obj;
		}
	}
	return scp;
	}

	

	@Override
	public int checkId(String id) {
	try{
		return getHibernateTemplate().find(
				"from SecurityConfigParameter s where s.id='" + id + "'")
				.size();
	}catch(Exception e){
		return 0;
	}
	
	}

	@Override
	public List<?> queryByClassCode(String classCode) {
		try{
			String hql = "from SecurityConfigParameter s where 1=1";
			if (classCode.trim() != null && classCode.trim().length() > 0) {
				hql += " and s.classCode='" + classCode.trim() + "'";
			}
			List<?> list = getHibernateTemplate().find(hql);
			return list;
		}catch(Exception e){
			return null;
		}
		
	}


	public List<?> queryClassByParamId(String id) {
		String hql = "select s.classCode, s.className, s.inputCode, s.functionDescription, s.comments, b.id, " +
				"b.hspConfigBaseinfoId, b.itemName, b.itemValue, b.usageDescription, b.initValue " +
				"from SecurityConfigParamclass s ,SecurityConfigParameter b where b.classCode = s.classCode";
		if (id.trim().length() > 0) {
			hql += " and b.id='" + id.trim() + "'";
		}
		List<?> list = getHibernateTemplate().find(hql);
		return list;
	}
	
	
  
	
	public int count(String className, String projectName,
			String itemName, String itemValue) {
		String hql = " select count(b.id) from	SecurityConfigParamclass s, SecurityConfigParameter b ,SecurityConfigParamProject c where b.classCode = s.classCode and s.projectCode = c.projectCode";
		if (projectName != null && projectName.trim().length() > 0) {
			hql += " and lower(c.projectName) like '%" + projectName.trim().toLowerCase() + "%'";
		}
		if (className!=null&&className.trim().length() > 0) {
			hql += " and lower(s.className) like '%" + className.trim().toLowerCase() + "%'";
		}	
		if (itemName!=null&&itemName.trim().length() > 0) {
			hql += " and lower(b.itemName) like '%" + itemName.trim().toLowerCase() + "%'";
		}
		if (itemValue!=null&&itemValue.trim().length() > 0) {
			hql += " and lower(b.itemValue) like '%" + itemValue.trim().toLowerCase() + "%'";
		}
		List<?> list = getHibernateTemplate().find(hql);
		return Integer.valueOf(String.valueOf(list.get(0))).intValue();
	}

	
	public List<?> queryList(String className, String projectName,
			String itemName, String itemValue, String orderNo,
			int cur, int pageSize) {
		String hql = "select s.classCode, s.className, s.inputCode, s.functionDescription, s.comments, b.id, b.hspConfigBaseinfoId, b.itemName, b.itemValue, b.usageDescription, b.initValue from " +
						"SecurityConfigParamclass s, SecurityConfigParameter b ,SecurityConfigParamProject c where b.classCode = s.classCode and s.projectCode = c.projectCode";
		if (projectName != null && projectName.trim().length() > 0) {
			hql += " and lower(c.projectName) like '%" + projectName.trim().toLowerCase() + "%'";
		}
		if (className!=null&&className.trim().length() > 0) {
			hql += " and lower(s.className) like '%" + className.trim().toLowerCase() + "%'";
		}	
		if (itemName!=null&&itemName.trim().length() > 0) {
			hql += " and lower(b.itemName) like '%" + itemName.trim().toLowerCase() + "%'";
		}
		if (itemValue!=null&&itemValue.trim().length() > 0) {
			hql += " and lower(b.itemValue) like '%" + itemValue.trim().toLowerCase() + "%'";
		}
		hql += " order by" + orderNo;
		Query q = getSession().createQuery(hql);
		q.setFirstResult(cur);
		q.setMaxResults(pageSize);
		return q.list();
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
	
	public SecurityConfigParameter findById(String id){
		String sql = "from SecurityConfigParameter where id = '" + id + "'";
		SecurityConfigParameter scp = new SecurityConfigParameter();
		List<?> list = getHibernateTemplate().find(sql);
		if(list != null && list.size() > 0){		
			for(int i = 0; i < list.size(); i++){
				scp = (SecurityConfigParameter)list.get(0);
			}
		}
		return scp;
	}
	
	public SecurityConfigParamclass findByClassCode(String classCode){
		String sql = "from SecurityConfigParamclass  s where s.classCode = '" + classCode + "'";
		SecurityConfigParamclass scpc = new SecurityConfigParamclass();
		List<?> list = getHibernateTemplate().find(sql);
		if(list != null && list.size() > 0){		
			for(int i = 0; i < list.size(); i++){
				scpc = (SecurityConfigParamclass)list.get(0);
			}
		}
		return scpc;
		
	}
	public int checkClassCode(String classCode) {
		return getHibernateTemplate().find("from SecurityConfigParamclass s where s.classCode='"
						+ classCode + "'").size();
	}
	
	
	
	public List<?> findAll() {
		String hql = "select s.classCode, s.className  from SecurityConfigParamclass s , SecurityConfigParamProject a";
			hql += "  where s.projectCode= a.projectCode  and lower(a.projectCode) = '" + 
						TJInit.getProperty("systemCode").toLowerCase() + "'";
		Query q = getSession().createQuery(hql);
		List<?> list = q.list();
		return list;
		/*
		 * try { String sql = " from SecurityConfigParamclass a ";
		 * 
		 * Query q = getSession().createQuery(sql); List<?> l = q.list();
		 * return l; } catch (Exception re) { re.printStackTrace(); return null; }
		 */
	}

	
	public SecurityConfigParamclass queryByClassName(String className) {
		SecurityConfigParamclass scp = null;
		String hql = "from SecurityConfigParamclass s where 1=1";
		if (className.trim().length() > 0) {
			hql += " and s.className='" + className.trim() + "'";
		}
		List<?> list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			scp = (SecurityConfigParamclass) list.get(0);
		}
		return scp;
	}
	
	public List<?> getHsp(String falg, String input, int count, int pageSize) {
		String hql="select h.classCode,h.className,a.projectCode from SecurityConfigParamclass h ,SecurityConfigParamProject a where h.projectCode = a.projectCode";
		if(input != null && input.trim().length()>0 && falg.equals("1")){
			hql+=" and a.projectCode  like '%" + input.trim()+ "%'";
		}
		if(falg.equals("2")){
			hql+=" and h.classCode like'%" + input.trim() + "%'";
		}
		if(falg.equals("3")){
			hql+=" and h.className like '%" + input.trim() +"%'";
		}
		Query q=this.getSession().createQuery(hql);
		q.setFirstResult(count);
		q.setMaxResults(pageSize);
		return q.list();
	}
	
	//医疗机构弹出层
	public List<?> findHspList(String flag,String inputCode,String hspType, int curCount, int pageSize){
		try {
			String sql = "";
			sql += "select t.id,t.item_code,t.item_name,t.input_code from hsp_config_baseinfo t where 1=1";
			//---1拼音 2代码 3汉字--
			if(flag.equals("1")){
				sql = sql + " and upper(t.input_code) like '"+inputCode.toUpperCase()+"%'";
			}else if(flag.equals("2")){
				sql = sql + " and t.item_code like '"+inputCode+"%'";
			}else if(flag.equals("3")){
				sql = sql + " and t.item_name like '%"+inputCode+"%'";
			}else{
				sql = sql + "  ";
			}
			Query q = getSession().createSQLQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(pageSize); 
			List<?> l=q.list();
			return l;
		}
		catch (Exception re) {
			logger.error("findHspList error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	public List<?> queryProjectName(String classCode){
		String sql = "select b.projectName from SecurityConfigParamclass a, SecurityConfigParamProject b where " +
				" a.projectCode = b.projectCode and a.classCode = '" + classCode + "'";
		Query q = this.getSession().createQuery(sql);
		List<?> list = q.list();
		return list;
	}
	
}
