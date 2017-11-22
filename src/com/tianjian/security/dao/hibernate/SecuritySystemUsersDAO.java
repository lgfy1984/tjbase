package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.dao.ISecuritySystemUsersDAO;
/**
 * SECURITY_SYSTEM_USERS用户登陆表用DAO
 * @author DZENALL
 * @since 2008-3-24 16:34
 * @version 1.0
 */
public class SecuritySystemUsersDAO extends HibernateDaoSupport implements ISecuritySystemUsersDAO {
	private static final Logger log = LogManager.getLogger(ISecuritySystemUsersDAO.class);
	
	public void delete(SecuritySystemUsers securitySystemUsers) {
        try{
            getHibernateTemplate().delete(securitySystemUsers);
            log.debug("delete success!");
        } catch(Exception re) {
            log.error("delete fail!", re);
            re.printStackTrace();
        }
    }

	public List<?> findAll() {
    	try {
    		String sql = " from SecuritySystemUsers a  ";
	       
	    	Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("findAll success!");
			return l;
    	} catch (Exception re) {
        	log.error("findAll fail",re);
        	re.printStackTrace(); 
        	return null;
        }
    }

	public SecuritySystemUsers findBySecurityStaffBaseinfoId(String securityStaffBaseinfoId) {
		try {
			SecuritySystemUsers temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecuritySystemUsers a where a.securityStaffBaseinfoId = ? ", securityStaffBaseinfoId);
			if (ls != null && ls.size() > 0) {
				temp = (SecuritySystemUsers) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findBySecurityStaffBaseinfoId error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public void save(SecuritySystemUsers securitySystemUsers) {
        try{
            getHibernateTemplate().save(securitySystemUsers);
            log.debug("save success!");
        } catch(Exception re) {
            log.error("save fail!", re);
            re.printStackTrace();
        }
    }

	public void update(SecuritySystemUsers securitySystemUsers) {
        try{
            getHibernateTemplate().update(securitySystemUsers);
            log.debug("update success!");
        } catch(Exception re) {
            log.error("update fail!", re);
            re.printStackTrace();
        }
    }

	public List<?> viewQuery(String querySql, String queryValue) {
		try {
			return getHibernateTemplate().find(querySql, queryValue);
		}
		catch (Exception re) {
			log.error("viewQuery error!", re);
			re.printStackTrace();
			return null;
		}
	}

}
