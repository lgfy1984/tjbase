package com.tianjian.security.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityConfigRoles;
import com.tianjian.security.bean.SecurityUserVsRoles;
import com.tianjian.security.dao.ISecurityUserVsRolesDAO;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;

public class SecurityUserVsRolesDAO extends HibernateDaoSupport implements ISecurityUserVsRolesDAO{
	
	private static final Log log = LogFactory.getLog(SecurityUserVsRolesDAO.class);
	
	public List<?> getUsers(String userId,String userName, String hspConfigBaseinfoId, String userInput, String staffType, String staffHspId, int count, int pageSize){
		try {
        	StringBuilder sql = new StringBuilder(" select a.id, a.staffCode, a.name, c.itemName")
			        	.append(" from SecurityStaffBaseinfo a ,HspConfigBaseinfoLocalBase c")
						.append(" where c.id = a.hspConfigBaseinfoId");
			sql.append(" and (c.id = :staffHspId");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("staffHspId", staffHspId);
			List<String> subHspIdList = this.getSubHspIds(staffHspId);
			if(subHspIdList != null && subHspIdList.size() > 0){
				for(String subHspId : subHspIdList){
					sql.append(" or c.id = '").append(subHspId).append("'");
				}
			}
			sql.append(")");
			if(userId.trim().length() > 0){
			sql.append(" and lower(a.staffCode) like :staffCode");
			map.put("staffCode", "%" + userId.trim().toLowerCase() + "%");
			}
			if(userName.trim().length() > 0){
			sql.append(" and lower(a.name) like :name");
			map.put("name", "%" + userName.trim().toLowerCase() + "%");
			}
			if(userInput.trim().length() > 0){
			sql.append(" and lower(a.inputCode) like :inputCode");
			map.put("inputCode", "%" + userInput.trim().toLowerCase() + "%");
			}
			
			if(hspConfigBaseinfoId.trim().length() > 0){
			sql.append(" and a.hspConfigBaseinfoId = :hspConfigBaseinfoId");
			map.put("hspConfigBaseinfoId", hspConfigBaseinfoId.trim());
			}
			//如果是普通人员 则只显示普通操作人员，屏蔽掉超级管理员
			if(staffType!=null&&staffType.trim().equals("0")){
			sql.append(" and a.staffType=0");
			}
			sql.append(" order by a.name");
			Query q = this.getSession().createQuery(sql.toString());
			for(Map.Entry<String, Object> entry : map.entrySet()){
			q.setParameter(entry.getKey(), entry.getValue());
			}
			q.setFirstResult(count);
			q.setMaxResults(pageSize);
			return q.list();
        } catch (RuntimeException re) {
        	log.error(re.toString());
        	throw re;
        }
	}
    
	public int count(String userId, String userName, String hspConfigBaseinfoId,String userInput,String staffType,String staffHspId)  {
		try{
			StringBuilder sql = new StringBuilder("select count(*)")
								.append(" from SecurityStaffBaseinfo a ,HspConfigBaseinfoLocalBase c")
								.append(" where c.id = a.hspConfigBaseinfoId");
			sql.append(" and (c.id = :staffHspId");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("staffHspId", staffHspId);
			List<String> subHspIdList = this.getSubHspIds(staffHspId);
			if(subHspIdList != null && subHspIdList.size() > 0){
				for(String subHspId : subHspIdList){
					sql.append(" or c.id = '").append(subHspId).append("'");
				}
			}
			sql.append(")");
	    	if(userId.trim().length() > 0){
	    		sql.append(" and lower(a.staffCode) like :staffCode");
	    		map.put("staffCode", "%" + userId.trim().toLowerCase() + "%");
	        }
	    	if(userName.trim().length() > 0){
	    		sql.append(" and lower(a.name) like :name");
	    		map.put("name", "%" + userName.trim().toLowerCase() + "%");
	    	}
	    	if(userInput.trim().length() > 0){
	    		sql.append(" and lower(a.inputCode) like :inputCode");
	    		map.put("inputCode", "%" + userInput.trim().toLowerCase() + "%");
	    	}
	    	
	    	if(hspConfigBaseinfoId.trim().length() > 0){
	    		sql.append(" and a.hspConfigBaseinfoId = :hspConfigBaseinfoId");
	    		map.put("hspConfigBaseinfoId", hspConfigBaseinfoId.trim());
	    	}
	    	//如果是普通人员 则只显示普通操作人员，屏蔽掉超级管理员
	    	if(staffType!=null&&staffType.trim().equals("0")){
				sql.append(" and a.staffType=0");
			}
	    	sql.append(" order by a.name");
	    	Query q = this.getSession().createQuery(sql.toString());
	    	for(Map.Entry<String, Object> entry : map.entrySet()){
	        	q.setParameter(entry.getKey(), entry.getValue());
	        }
			List<?> list = q.list();
			int count = 0;
			if(list != null && list.size() > 0){
				count = ((Long) list.get(0)).intValue();
			}
			return count;
		}catch(RuntimeException re){
			log.error(re.toString());
        	throw re;
		}
    	
	}

    
    public List<?> getRoles(String staffType){
		try {
			String sql = "from SecurityConfigRoles securityConfigRoles where 1=1";
        	
        	//如果是普通人员 则只显示普通角色，屏蔽掉超级管理员的角色
        	if(staffType!=null&&staffType.trim().equals("0")){
				sql +=" and securityConfigRoles.roleType=0" ;
			}
        	//市级
        	if(TJInit.getProperty("classFlag").trim().length()>0){
        		sql += " and securityConfigRoles.roleFlag="+TJInit.getProperty("classFlag").trim();
        	}
        	String tenantId = TenantIdHolder.get();
        	sql += " and securityConfigRoles.tenantId = ?";
        	
        	return getHibernateTemplate().find(sql, tenantId);
        } catch (RuntimeException re) {
        	log.error(re.toString());
        	throw re;
        }
	}
    
    public List<?> getUserRoles(String userId){
    	try {
        	return getHibernateTemplate().find("from SecurityUserVsRoles securityUserVsRoles" +
        			" where securityUserVsRoles.securityStaffBaseinfoId = ? ",
        			userId);
        } catch (RuntimeException re) {
        	log.error(re.toString());
        	throw re;
        }
    }    
    
	public void save(SecurityUserVsRoles securityUserVsRoles) {
    	try {
        	getHibernateTemplate().save(securityUserVsRoles);
        } catch (RuntimeException re) {
        	log.error(re.toString());
        	throw re;
        }
    }
	
	public void delete(SecurityUserVsRoles securityUserVsRoles) {
    	try {
        	getHibernateTemplate().delete(securityUserVsRoles);
        } catch (RuntimeException re) {
        	log.error(re.toString());
        	throw re;
        }
    }
	
	public SecurityConfigRoles findByRoleId(String roleId) {
        try {
        	SecurityConfigRoles securityConfigRoles = null;
        	List<?> list = getHibernateTemplate().find("from SecurityConfigRoles securityConfigRoles" +
        			" where securityConfigRoles.id = ? ",
        			roleId);
        	if(list != null && list.size() > 0){
        		securityConfigRoles = (SecurityConfigRoles)list.get(0);
        	}
        	return securityConfigRoles;        	
        } catch (RuntimeException re) {
        	log.error(re.toString());
        	throw re;
        }
          
    }
	
	
	/**
	 * 按机构id获取下属机构id集合
	 * @param hspId
	 * @return
	 */
	private List<String> getSubHspIds(String id){
		//查询该id的下属机构id
		String hql = "select sub.id from HspConfigBaseinfoLocalBase super, HspConfigBaseinfoLocalBase sub where super.itemCode = sub.parentItemCode and super.id = ?";
		List<?> list = this.getHibernateTemplate().find(hql, id);
		if(list != null && list.size() > 0){
			List<String> hspIdList = new ArrayList<String>();
			for(Iterator<?> it = list.iterator(); it.hasNext(); ){
				String subId = (String)it.next();
				hspIdList.add(subId);
				//根据id迭代获取下属机构id列表
				List<String> subIdList = getSubHspIds(subId);
				if(subIdList != null && subIdList.size() > 0){
					hspIdList.addAll(subIdList);
				}
			}
			return hspIdList;
		}
		return null;
	}
    
}