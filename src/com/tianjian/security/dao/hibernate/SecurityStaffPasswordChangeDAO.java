package com.tianjian.security.dao.hibernate;

import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.dao.ISecurityStaffPasswordChangeDAO;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class SecurityStaffPasswordChangeDAO extends HibernateDaoSupport implements ISecurityStaffPasswordChangeDAO {

	private static final Logger log = LogManager.getLogger(SecurityStaffPasswordChangeDAO.class);

    public SecurityStaffPasswordChangeDAO() {
    }

    public SecuritySystemUsers findById(String id) {
    	try{
    		SecuritySystemUsers info = null;
	    	List<?> ls = getHibernateTemplate().find(" from SecuritySystemUsers a where a.securityStaffBaseinfoId = ? ", id);
	        if(ls != null && ls.size() > 0){
	        	info = (SecuritySystemUsers)ls.get(0);
	        }
	        log.debug("findById success!");
	        return info;
    	} catch(Exception re){
    		log.error("findById fail!", re);
            re.printStackTrace();
            return null;
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

}