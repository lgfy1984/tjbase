package com.tianjian.security.dao.hibernate;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.dao.ISecurityUserVsRolesOpDAO;

public class SecurityUserVsRolesOpDAO extends HibernateDaoSupport implements ISecurityUserVsRolesOpDAO{
	
	private static final Logger log = LogManager.getLogger(SecurityUserVsRolesOpDAO.class);
	
	
	public SecurityStaffBaseinfo findById(String id) {
    	try{
	    	SecurityStaffBaseinfo info = null;
	    	List<?> ls = getHibernateTemplate().find(" from SecurityStaffBaseinfo a where a.id = ? ", id);
	        if(ls != null && ls.size() > 0){
	        	info = (SecurityStaffBaseinfo)ls.get(0);
	        }
	        log.debug("findById success!");
	        return info;
    	} catch(Exception re){
    		log.error("findById fail!", re);
            re.printStackTrace();
            return null;
    	}        
    }

	public List<?> getData(String securityConfigRolesId, String hspConfigBaseinfoId, String order, int curCount, int quChuCount){
		try{
    		List<?> l;
	    	String sql = "select a.id,"               //0
		    	+ " a.staffCode, "                    //1
		    	+ " a.hspConfigBaseinfoId, "          //2
		    	+ " a.name, "                         //3
		    	+ " a.nameEn, "                       //4
		    	+ " a.inputCode, "                    //5
		    	+ " a.commConfigSexId, "              //6
		    	+ " a.dateOfBirth, "                  //7
		    	+ " a.commConfigStafftypeId, "        //8
		    	+ " a.idNo, "                         //9
		    	+ " a.phone, "                        //10
		    	+ " a.islocation, "                   //11
		    	+ " a.comments, "                     //12
		    	+ " b.securityConfigRolesId, "        //13
		    	+ " c.itemName "                      //14
	            + " from SecurityStaffBaseinfo a,SecurityUserVsRoles b,HspConfigBaseinfoLocalBase c " 
	        	+ " where a.id = b.securityStaffBaseinfoId " 
	        	+ " and a.hspConfigBaseinfoId = c.id ";
	        
	        if(securityConfigRolesId.trim().length() > 0){
	        	sql += " and b.securityConfigRolesId = '" + securityConfigRolesId.trim() + "'";
	        }
	        if(hspConfigBaseinfoId.trim().length() > 0){
	        	sql += " and a.hspConfigBaseinfoId = '" + hspConfigBaseinfoId.trim() + "'";
	        }
	        
	        if(order.trim().length() > 0){
	        	sql += " order by " + order.trim() + "'";
	        }
	        
	        Query q = getSession().createQuery(sql);
	        q.setFirstResult(curCount);
	        q.setMaxResults(quChuCount);
	        l = q.list();
	        log.debug("getData success!");
	        return l;
    	} catch(Exception re){
	        log.error("getData fail!", re);
	        re.printStackTrace();
	        return null;
    	}
	}

    public int getCount(String securityConfigRolesId, String hspConfigBaseinfoId){
    	try{
    		int count;
	    	count = 0;
	        String sql = "select count(*) ";
	        sql += " from SecurityStaffBaseinfo a,SecurityUserVsRoles b,HspConfigBaseinfoLocalBase c " 
	        	+ " where a.id = b.securityStaffBaseinfoId " 
	        	+ " and a.hspConfigBaseinfoId = c.id ";
	        
	        if(securityConfigRolesId.trim().length() > 0){
	        	sql += " and b.securityConfigRolesId = '" + securityConfigRolesId.trim() + "'";
	        }
	        if(hspConfigBaseinfoId.trim().length() > 0){
	        	sql += " and a.hspConfigBaseinfoId = '" + hspConfigBaseinfoId.trim() + "'";
	        }
	        
	        
	        List<?> list = getHibernateTemplate().find(sql);
	        if(list != null && list.size() > 0)
	            count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
	        log.debug("getCount success!");
	        return count;
    	} catch(Exception re){
    		log.error("getCount fail!", re);
            re.printStackTrace();
            return (new Integer("0")).intValue();
    	}
    }
}
