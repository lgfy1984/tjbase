package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityLicense;
import com.tianjian.security.dao.ISecurityLicenseDAO;
/**
 * SECURITY_LICENSE用户许可证表用DAO
 * @author DZENALL
 * @since 2008-3-24 10:09
 * @version 1.0
 */
public class SecurityLicenseDAO extends HibernateDaoSupport implements ISecurityLicenseDAO {
	private static final Logger log = LogManager.getLogger(ISecurityLicenseDAO.class);
	
	public SecurityLicense findById(String securityLicenseId) {
    	try{
    		SecurityLicense info = null;
	    	List<?> ls = getHibernateTemplate().find(" from SecurityLicense a where a.id = ? ", securityLicenseId);
	        if(ls != null && ls.size() > 0){
	        	info = (SecurityLicense)ls.get(0);
	        }
	        log.debug("checkData success!");
	        return info;
    	} catch(Exception re){
    		log.error("checkData fail!", re);
            re.printStackTrace();
            return null;
    	}   
    }
    //存在注册信息
	public SecurityLicense findBySecurityStaffBaseinfoId(String securityStaffBaseinfoId) {
    	try{
    		SecurityLicense info = null;
	    	List<?> ls = getHibernateTemplate().find(" from SecurityLicense a where a.securityStaffBaseinfoId = ? ", securityStaffBaseinfoId);
	        if(ls != null && ls.size() > 0){
	        	info = (SecurityLicense)ls.get(0);
	        }
	        log.debug("checkData success!");
	        return info;
    	} catch(Exception re){
    		log.error("checkData fail!", re);
            re.printStackTrace();
            return null;
    	}   
    }
	
	
	 //存在注册信息
	public SecurityLicense findByRegistCode(String registCode) {
    		SecurityLicense info = null;
	    	List<?> ls = getHibernateTemplate().find(" from SecurityLicense a where a.registCode = ? ", registCode);
	        if(ls != null && ls.size() > 0){
	        	info = (SecurityLicense)ls.get(0);
	        }
	        
	        return info;
    }
	
	
	public void delete(SecurityLicense securityLicense) {
        try{
            getHibernateTemplate().delete(securityLicense);
            log.debug("delete success!");
        } catch(Exception re) {
            log.error("delete fail!", re);
            re.printStackTrace();
        }
    }

	public void save(SecurityLicense securityLicense) {
        try{
            getHibernateTemplate().save(securityLicense);
            log.debug("save success!");
        } catch(Exception re) {
            log.error("save fail!", re);
            re.printStackTrace();
        }
    }

	public void update(SecurityLicense securityLicense) {
        try{
            getHibernateTemplate().update(securityLicense);
            log.debug("update success!");
        } catch(Exception re) {
            log.error("update fail!", re);
            re.printStackTrace();
        }
    }

}
