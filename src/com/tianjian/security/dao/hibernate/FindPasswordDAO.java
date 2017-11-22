package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.dao.IFindPasswordDAO;
public class FindPasswordDAO extends HibernateDaoSupport implements IFindPasswordDAO{
	public List<?> getList(String userName){
		try{
			List<?> l;
			String sql = "select a from SecurityStaffBaseinfo a  where 1=1 and a.staffCode = '" + userName.trim() + "'";
			System.out.println("this is findPasswordDAO"+sql);
			Query q = getSession().createQuery(sql);
			l = q.list();
			return l;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public SecurityStaffBaseinfo findByStaffCode(String userName){
		try{
	    	SecurityStaffBaseinfo info = null;
	    	List<?> ls = getHibernateTemplate().find(" from SecurityStaffBaseinfo a where a.staffCode = ? ", userName.trim());
	        if(ls != null && ls.size() > 0){
	        	info = (SecurityStaffBaseinfo)ls.get(0);
	        }
	        return info;
    	} catch(Exception re){
            re.printStackTrace();
            return null;
    	}  
	}
	
	public void updatePassword(SecuritySystemUsers securitySystemUsers){
		try{
			this.getHibernateTemplate().update(securitySystemUsers);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public SecuritySystemUsers findBySecurityStaffBaseinfoId(String securityStaffBaseinfoId){
		SecuritySystemUsers bean = null;
		String sql = "from SecuritySystemUsers a where a.securityStaffBaseinfoId = '"+ securityStaffBaseinfoId +"'";
		List<?> list = this.getHibernateTemplate().find(sql);
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				bean = (SecuritySystemUsers)list.get(0);
			}
		}
		return bean;
	}
}