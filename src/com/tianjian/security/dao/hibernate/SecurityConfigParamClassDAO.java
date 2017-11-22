package com.tianjian.security.dao.hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigParamProject;
import com.tianjian.security.bean.SecurityConfigParamclass;
import com.tianjian.security.bean.SecurityConfigParameter;
import com.tianjian.security.dao.ISecurityConfigParamClassDAO;

public class SecurityConfigParamClassDAO extends HibernateDaoSupport implements
		ISecurityConfigParamClassDAO {

	@Override
	public int count(String calssCode,String itemName,String itemValue) {
		String hql="from SecurityConfigParamclass s, SecurityConfigParameter s1 where s.classCode=s1.classCode";
		if(calssCode.trim().length()>0){
			hql+=" and s.className='"+calssCode.trim().toUpperCase()+"'";
		}
		if(itemName.trim().length()>0){
			hql+=" and s1.itemName like '%"+itemName.trim()+"%'";
		}
		if(itemValue.trim().length()>0){
			hql+=" and s1.itemValue like '%"+itemValue.trim()+"%'";
		}
		List<?> ls=getHibernateTemplate().find(hql);
		return ls.size();
	}

	@Override
	public List<?> getData(String calssCode, String itemName, String itemValue,
			int cur, int pageSize) {
		String hql="from SecurityConfigParamclass s, SecurityConfigParameter s1 where s.classCode=s1.classCode";
		if(calssCode.trim().length()>0){
			hql+=" and s.className='"+calssCode.trim().toUpperCase()+"'";
		}
		if(itemName.trim().length()>0){
			hql+=" and s1.itemName like '%"+itemName.trim()+"%'";
		}
		if(itemValue.trim().length()>0){
			hql+=" and s1.itemValue like '%"+itemValue.trim()+"%'";
		}
		hql+=" order by s.classCode";
		Query q=getSession().createQuery(hql);
		q.setFirstResult(cur);
		q.setMaxResults(pageSize);
		return q.list();
	}

	@Override
	public boolean initTables(Connection conn, List sql) {
		try {
			conn.setAutoCommit(false);
			Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			for(int i=0;i<sql.size();i++){
				st.addBatch(sql.get(i).toString());
			}
			st.executeBatch();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		}
		return false;
	}

	@Override
	public boolean deleteTable(Connection conn, String sql) {
		Statement st;
		try {
			System.out.println(sql);
			st = conn.createStatement();
			
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public SecurityConfigParameter getTableName(String table) {
		SecurityConfigParameter parameter=null;
		int t=table.lastIndexOf(".");
		List<?> ls=getHibernateTemplate().find("from SecurityConfigParameter s where s.itemValue='"+table.substring(t+1)+"'");
		if(ls!=null&&ls.size()>0){
			for(int i=0;i<ls.size();i++){
				parameter=(SecurityConfigParameter)ls.get(0);
			}
		}
		return parameter;
	}

	@Override
	public SecurityConfigMenus getUrl(String menusValue) {
		SecurityConfigMenus menus=null;
		List<?> ls=getHibernateTemplate().find("from SecurityConfigMenus s where s.menuDetail='"+menusValue+"'");
		if(ls!=null&&ls.size()>0){
			for(int i=0;i<ls.size();i++){
				menus=(SecurityConfigMenus)ls.get(0);
			}
		}
		return menus;
	}

	@Override
	public List<?> getTables(Connection conn, String tableName) {
		List list = new ArrayList();
		list = getSession().createSQLQuery("select * from " + tableName).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getColmun(Connection conn,String tableName) {
		try {
			List list=new ArrayList();
			ResultSet rs = conn.createStatement().executeQuery("select * from "+tableName);
			ResultSetMetaData rss = rs.getMetaData();   
			int columnCount = rss.getColumnCount();   
			 for(int i = 1;i<=columnCount; i++){   
				 list.add(rss.getColumnName(i));   
			}
			 return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int count(String classCode, String className, String inputCode,
			String projectName) {
		String hql = "from SecurityConfigParamclass s,SecurityConfigParamProject a where 1=1 and s.projectCode = a.projectCode";
		if (classCode.trim().length() > 0) {
			hql += " and s.classCode='" + classCode.trim() + "'";
		}
		if (className.trim().length() > 0) {
			hql += " and lower(s.className) like '%" + className.trim().toLowerCase() + "%'";
		}
		if (inputCode.trim().length() > 0) {
			hql += " and s.inputCode like '%" + inputCode.trim().toUpperCase() + "%'";
		}
		if (projectName.trim().length() > 0) {
			hql += " and a.projectName like '%" + projectName.trim() + "%'";
		}
		List<?> list = getHibernateTemplate().find(hql);
		return list.size();
	}

	
	public List<?> queryList(String projectName, String className,
			String inputCode, String classCode, String orderNo,
			int cur, int pageSize) {
		String hql = "select s.classCode,s.className,s.inputCode,s.functionDescription,s.comments, " +
				" a.projectCode,a.projectName from SecurityConfigParamclass s ,SecurityConfigParamProject a where 1=1 and s.projectCode = a.projectCode";
		if (projectName.trim().length() > 0) {
			hql += " and a.projectName like '%" + projectName.trim() + "%'";
		}
		if (className.trim().length() > 0) {
			hql += " and s.className like '%" + className.trim() + "%'";
		}
		if (inputCode.trim().length() > 0) {
			hql += " and lower(s.inputCode) like '%" + inputCode.trim().toLowerCase()+ "%'";
		}
		if (classCode.trim().length() > 0) {
			hql += " and s.classCode = '" + classCode.trim() + "'";
		}
		hql += " order by" + orderNo;
		Query q = getSession().createQuery(hql);
		q.setFirstResult(cur);
		q.setMaxResults(pageSize);
		return q.list();
	}

	
	public SecurityConfigParamclass queryByClassCode(String classCode) {
		SecurityConfigParamclass scp = null;
		String hql = "from SecurityConfigParamclass s where 1=1";
		if (classCode.trim().length() > 0) {
			hql += " and s.classCode='" + classCode.trim() + "'";
		}
		List<?> list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = (Object) list.get(i);
				scp = (SecurityConfigParamclass) obj;
			}
		}
		return scp;
	}

	public SecurityConfigParamProject queryByProjectCode(String projectCode) {
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

	
	public int checkClassCode(String classCode) {
		return getHibernateTemplate().find(
				"from SecurityConfigParamclass s where s.classCode='"
						+ classCode + "'").size();
	}

	
	public List<?> findAll() {
		String hql = "from SecurityConfigParamclass s";
		List<?> list = getHibernateTemplate().find(hql);
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
	
	public Long maxFind(){
		try{
			String sql = "select max(t.seqNo) from SecurityConfigParamclass t";
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
	
	public SecurityConfigParamclass getByProjectCode(String classCode) {
		try {
			SecurityConfigParamclass temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigParamclass a where a.classCode = ? ", classCode);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigParamclass)ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	
}
