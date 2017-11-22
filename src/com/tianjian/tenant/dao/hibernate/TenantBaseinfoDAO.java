package com.tianjian.tenant.dao.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.tenant.bean.TenantBaseinfo;
import com.tianjian.tenant.dao.ITenantBaseinfoDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * TenantBaseinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.tianjian.tenant.bean.TenantBaseinfo
 * @author MyEclipse Persistence Tools
 */
public class TenantBaseinfoDAO extends HibernateDaoSupport implements ITenantBaseinfoDAO {
	private static final Log log = LogFactory.getLog(TenantBaseinfoDAO.class);
	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#save(com.tianjian.tenant.bean.TenantBaseinfo)
	 */
	@Override
	public void save(TenantBaseinfo transientInstance) {
		log.debug("saving TenantBaseinfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#delete(com.tianjian.tenant.bean.TenantBaseinfo)
	 */
	@Override
	public void delete(TenantBaseinfo persistentInstance) {
		log.debug("deleting TenantBaseinfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findById(java.lang.String)
	 */
	@Override
	public TenantBaseinfo findById(java.lang.String id) {
		log.debug("getting TenantBaseinfo instance with id: " + id);
		try {
			TenantBaseinfo instance = (TenantBaseinfo) getHibernateTemplate()
					.get("com.tianjian.tenant.bean.TenantBaseinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByExample(com.tianjian.tenant.bean.TenantBaseinfo)
	 */
	@Override
	public List findByExample(TenantBaseinfo instance) {
		log.debug("finding TenantBaseinfo instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TenantBaseinfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TenantBaseinfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByItemCode(java.lang.Object)
	 */
	@Override
	public List findByItemCode(Object itemCode) {
		return findByProperty(ITEM_CODE, itemCode);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByItemName(java.lang.Object)
	 */
	@Override
	public List findByItemName(Object itemName) {
		return findByProperty(ITEM_NAME, itemName);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByAddress(java.lang.Object)
	 */
	@Override
	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByZipcode(java.lang.Object)
	 */
	@Override
	public List findByZipcode(Object zipcode) {
		return findByProperty(ZIPCODE, zipcode);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByContactPersonName(java.lang.Object)
	 */
	@Override
	public List findByContactPersonName(Object contactPersonName) {
		return findByProperty(CONTACT_PERSON_NAME, contactPersonName);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByPhone(java.lang.Object)
	 */
	@Override
	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByComments(java.lang.Object)
	 */
	@Override
	public List findByComments(Object comments) {
		return findByProperty(COMMENTS, comments);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findBySeqNo(java.lang.Object)
	 */
	@Override
	public List findBySeqNo(Object seqNo) {
		return findByProperty(SEQ_NO, seqNo);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByEMail(java.lang.Object)
	 */
	@Override
	public List findByEMail(Object EMail) {
		return findByProperty(_EMAIL, EMail);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByPasswd(java.lang.Object)
	 */
	@Override
	public List findByPasswd(Object passwd) {
		return findByProperty(PASSWD, passwd);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByCreateUserId(java.lang.Object)
	 */
	@Override
	public List findByCreateUserId(Object createUserId) {
		return findByProperty(CREATE_USER_ID, createUserId);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findByCreateUserName(java.lang.Object)
	 */
	@Override
	public List findByCreateUserName(Object createUserName) {
		return findByProperty(CREATE_USER_NAME, createUserName);
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all TenantBaseinfo instances");
		try {
			String queryString = "from TenantBaseinfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#findIdAndNameList()
	 */
	@Override
	public List findIdAndNameList() {
		log.debug("finding TenantBaseinfo only id and name instances");
		try {
			String queryString = "select new TenantBaseinfo(model.id, model.itemName) from TenantBaseinfo model";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find id and name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#merge(com.tianjian.tenant.bean.TenantBaseinfo)
	 */
	@Override
	public TenantBaseinfo merge(TenantBaseinfo detachedInstance) {
		log.debug("merging TenantBaseinfo instance");
		try {
			TenantBaseinfo result = (TenantBaseinfo) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#attachDirty(com.tianjian.tenant.bean.TenantBaseinfo)
	 */
	@Override
	public void attachDirty(TenantBaseinfo instance) {
		log.debug("attaching dirty TenantBaseinfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.tianjian.tenant.dao.hibernate.ITenantBaseinfoDAO#attachClean(com.tianjian.tenant.bean.TenantBaseinfo)
	 */
	@Override
	public void attachClean(TenantBaseinfo instance) {
		log.debug("attaching clean TenantBaseinfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ITenantBaseinfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ITenantBaseinfoDAO) ctx.getBean("TenantBaseinfoDAO");
	}
}