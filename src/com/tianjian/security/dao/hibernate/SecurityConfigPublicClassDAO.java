package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.dao.ISecurityConfigPublicClassDAO;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;

/**
 * 模块类别表DAO
 * @author ch_f001
 * @since 2008-3-24 16:34
 * @version 1.0
 */
public class SecurityConfigPublicClassDAO extends HibernateDaoSupport implements ISecurityConfigPublicClassDAO {

	private static final Logger log = LogManager.getLogger(SecurityConfigPublicClassDAO.class);

	public void delete(SecurityConfigPublicClass ecurityConfigPublicClass) {
		try {
			getHibernateTemplate().delete(ecurityConfigPublicClass);
			log.debug("delete success!");
		}
		catch (RuntimeException re) {
			log.error("delete fail!", re);
			throw re;
		}
	}

	public String getClassName(String id) {
		String className = "";
		try {
			List<?> l = getHibernateTemplate().find(" from SecurityConfigPublicClass aa where aa.id = ? ", id);
			if (l != null && l.size() > 0) {
				SecurityConfigPublicClass pub = (SecurityConfigPublicClass) l.get(0);
				className = pub.getClassName();
			}
			log.debug("getClassName success!");
			return className;
		}
		catch (Exception re) {
			log.error("getClassName fail!", re);
			re.printStackTrace();
			return null;
		}
	}

	public List<?> findAll() {
		try {
			String sql = " from SecurityConfigPublicClass a where a.levelFlag = '1' and a.classFlag = '" + TJInit.getProperty("classFlag") +"'";
			Query q = getSession().createQuery(sql);
			List<?> l = q.list();
			log.debug("findAll success!");
			return l;
		}
		catch (RuntimeException re) {
			log.error("findAll fail", re);
			throw re;
		}
	}
	
	public List<?> findAllPublic() {
		try {
			String sql = " from SecurityConfigPublicClass a  where (a.levelFlag = '2' or a.id not in (select b.parentId from SecurityConfigPublicClass b where b.parentId is not null)) and a.classFlag = '" + TJInit.getProperty("classFlag") +"' order by a.levelFlag, a.serialNo";
			Query q = getSession().createQuery(sql);
			List<?> l = q.list();
			log.debug("findAll success!");
			return l;
		}
		catch (Exception re) {
			log.error("findAll fail", re);
			re.printStackTrace();
			return null;
		}
	}
	
	public List<?> findParentPublicClass() {
		try {
			String sql = " from SecurityConfigPublicClass a  where a.levelFlag = '1' and a.id not in (select b.scpcId from SecurityConfigPublic b where b.scpcId is not null) and a.classFlag = '" + TJInit.getProperty("classFlag") +"'";
			Query q = getSession().createQuery(sql);
			List<?> l = q.list();
			log.debug("findAll success!");
			return l;
		}
		catch (RuntimeException re) {
			log.error("findAll fail", re);
			throw re;
		}
	}
	
	public SecurityConfigPublicClass findById(String Id) {
		try {
			SecurityConfigPublicClass temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigPublicClass a where a.id = ? ", Id);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigPublicClass) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findById error!", re);
			re.printStackTrace();
			return null;
		}
	}
	

	public SecurityConfigPublicClass findByCode(String Id) {
		try {
			SecurityConfigPublicClass temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigPublicClass a where a.classCode = ? ", Id);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigPublicClass) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findByCode error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public void save(SecurityConfigPublicClass securityConfigPublicClass) {
		try {
			String tenantId = TenantIdHolder.get();
			securityConfigPublicClass.setTenantId(tenantId);
			getHibernateTemplate().save(securityConfigPublicClass);
			log.debug("save success!");
		}
		catch (RuntimeException re) {
			log.error("save fail!", re);
			throw re;
		}
	}

	public void update(SecurityConfigPublicClass securityConfigPublicClass) {
		try {
			String tenantId = TenantIdHolder.get();
			securityConfigPublicClass.setTenantId(tenantId);
			getHibernateTemplate().update(securityConfigPublicClass);
			log.debug("update success!");
		}
		catch (RuntimeException re) {
			log.error("update fail!", re);
			throw re;
		}
	}

	public List<?> getDataList(String id, String classCode, String className, String inputCode, String serialNo,String levelFlag,String panterId ,String orderNo, int curCount, int pageSize) {
		try {
			String sql = "  ";
			sql += " from SecurityConfigPublicClass a  where 1=1 and a.classFlag = '" +  TJInit.getProperty("classFlag").trim() + "'";
			if (id.trim().length() > 0) {
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if (classCode.trim().length() > 0) {
				sql += " and a.classCode = '" + classCode.trim() + "' ";
			}
			if (className.trim().length() > 0) {
				sql += " and lower(a.className) like '%" + className.trim().toLowerCase() + "%' ";
			}
			if (inputCode.trim().length() > 0) {
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if (levelFlag.trim().length() > 0) {
				sql += " and a.levelFlag = '" + levelFlag.trim() + "' ";
			}
			if (panterId.trim().length() > 0) {
				sql += " and a.parentId = '" + panterId.trim() + "' ";
			}
			if (serialNo.trim().length() > 0) {
				sql += " and a.serialNo = '" + serialNo.trim() + "' ";
			}
			if (orderNo.trim().length() > 0) {
				sql += " order by " + orderNo;
			}
			Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount);
			q.setMaxResults(pageSize);
			List<?> l = q.list();
			log.debug("getDataList success!");
			return l;
		}
		catch (Exception re) {
			log.error("getDataList fail!", re);
			re.printStackTrace();
			return null;
		}
	}

	public int getCount(String id, String classCode, String className, String inputCode, String serialNo,String levelFlag,String panterId) {
		try {
			int count = 0;
			String sql = "select count(*) ";
			sql += " from SecurityConfigPublicClass a  where 1=1  and a.classFlag = '" + TJInit.getProperty("classFlag").trim() +"'";
			if (id.trim().length() > 0) {
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if (classCode.trim().length() > 0) {
				sql += " and a.classCode = '" + classCode.trim() + "' ";
			}
			if (className.trim().length() > 0) {
				sql += " and lower(a.className) like '%" + className.trim().toLowerCase() + "%' ";
			}
			if (inputCode.trim().length() > 0) {
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if (levelFlag.trim().length() > 0) {
				sql += " and a.levelFlag = '" + levelFlag.trim() + "' ";
			}
			if (panterId.trim().length() > 0) {
				sql += " and a.parentId = '" + panterId.trim() + "' ";
			}
			if (serialNo.trim().length() > 0) {
				sql += " and a.serialNo = '" + serialNo.trim() + "' ";
			}
			List<?> list = getHibernateTemplate().find(sql);
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			log.debug("getCount success!");
			return count;
		}
		catch (Exception re) {
			log.error("getCount fail!", re);
			re.printStackTrace();
			return new Integer("0");
		}
	}
	   public long getMaxSeqNo(){
		   long temp = 1l;
			try {
				List<?> l = getHibernateTemplate().find(" select max(aa.serialNo) from SecurityConfigPublicClass aa ");
				if(l != null && l.size() > 0){
					Object o = l.get(0);
					if(o==null){
						return temp;
					}else{
						temp = Long.valueOf(String.valueOf(l.get(0)))+1L;
					}
					
				}
				log.debug("getMaxSeqNo success!");
				
	        } catch (Exception re) {
	        	log.error("getMaxSeqNo fail!",re);
	        	re.printStackTrace();
	        }
			return temp;
		}

	@Override
	public int getPublicCountByPublicClassId(String publicClassId) {
		List<?> list = getHibernateTemplate().find("select count(a) from SecurityConfigPublic a  where a.scpcId = ?", publicClassId);
		if(list.size() > 0){
			return Integer.valueOf(String.valueOf(list.get(0)));
		}
		return 0;
	}

	@Override
	public int getPublicClass2CountByPublicClassId(String publicClassId) {
		List<?> list = getHibernateTemplate().find("select count(a) from SecurityConfigPublicClass a  where a.parentId = ?", publicClassId);
		if(list.size() > 0){
			return Integer.valueOf(String.valueOf(list.get(0)));
		}
		return 0;
	} 
}
