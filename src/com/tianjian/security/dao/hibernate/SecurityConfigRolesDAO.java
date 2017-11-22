package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
 
import com.tianjian.security.bean.SecurityConfigRoles;
import com.tianjian.security.dao.ISecurityConfigRolesDAO;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;
/**
 * 模块类别表DAO
 * @author ch_f001
 * @since 2008-3-24 16:34
 * @version 1.0
 */
public class SecurityConfigRolesDAO extends HibernateDaoSupport implements ISecurityConfigRolesDAO {
	private static final Logger log = LogManager.getLogger(SecurityConfigRolesDAO.class);
	
	public void delete(SecurityConfigRoles ecurityConfigRoles) {
        try{
            getHibernateTemplate().delete(ecurityConfigRoles);
            log.debug("delete success!");
        } catch(Exception re) {
            log.error("delete fail!", re);
            re.printStackTrace();
        }
    }
	
	public String getItemName(String id){
		String itemName = "";
		try {
			String tenantId = TenantIdHolder.get();
			List<?> l = getHibernateTemplate().find(" from SecurityConfigRoles aa where aa.id = ? and aa.tenantId = ? ", new Object[]{id, tenantId});
			if(l != null && l.size() > 0){
				SecurityConfigRoles pub = (SecurityConfigRoles)l.get(0);
				itemName = pub.getRoleDetail();
			}
			log.debug("getItemName success!");
			return itemName;
        } catch (Exception re) {
        	log.error("getItemName fail!",re);
        	re.printStackTrace();
			return null; 
        }
	}
	public List<?> findAll() {
    	try {
    		String tenantId = TenantIdHolder.get();
    		String sql = " from SecurityConfigRoles a where a.tenantId='"+tenantId+"'";
	       
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

	public SecurityConfigRoles findById(String Id) {
		try {
			SecurityConfigRoles temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigRoles a where a.id = ? ", Id);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigRoles) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findById error!", re);
			re.printStackTrace();
			return null;
		}
	}
	public SecurityConfigRoles findByCode(String itemCode) {
		try {
			String tenantId = TenantIdHolder.get();
			SecurityConfigRoles temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigRoles a where a.roleCode = ? and a.tenantId=? ", new Object[]{itemCode, tenantId});
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigRoles) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findByCode error!", re);
			re.printStackTrace();
			return null;
		}
	}


	public void save(SecurityConfigRoles securityConfigRoles) {
        try{
        	String tenantId = TenantIdHolder.get();
        	securityConfigRoles.setTenantId(tenantId);
            getHibernateTemplate().save(securityConfigRoles);
            log.debug("save success!");
        } catch(Exception re) {
            log.error("save fail!", re);
            re.printStackTrace();
        }
    }

	public void update(SecurityConfigRoles securityConfigRoles) {
        try{
        	String tenantId = TenantIdHolder.get();
        	securityConfigRoles.setTenantId(tenantId);
            getHibernateTemplate().update(securityConfigRoles);
            log.debug("update success!");
        } catch(Exception re) {
            log.error("update fail!", re);
            re.printStackTrace();
        }
    }
	
	  public List<?> getDataList(String id,String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize){
	    	try {
	    		String tenantId = TenantIdHolder.get();
	    		StringBuilder sql = new StringBuilder();
		    	sql.append(" from SecurityConfigRoles a  where a.roleFlag = '").append(TJInit.getProperty("classFlag").trim()).append("'")
		    		.append(" and a.tenantId='").append(tenantId).append("'");
		        
		    	if(id.trim().length() > 0){
		    		sql.append(" and a.id = '").append(id.trim()).append("' ");
		    	}
		    	if(itemCode.trim().length() > 0){
		    		sql.append(" and a.roleCode = '").append(itemCode.trim()).append("' ");
		    	}
		    	if(itemName.trim().length() > 0){
		    		sql.append(" and lower(a.roleDetail) like '%").append(itemName.trim().toLowerCase()).append("%' ");
		    	}
		    	if(inputCode.trim().length() > 0){
		    		sql.append(" and a.inputCode like '").append(inputCode.trim().toUpperCase()).append("%' ");
		    	}
		    	if(seqNo.trim().length() > 0){
		    		sql.append(" and a.serialNo = '").append(seqNo.trim()).append("' ");
		    	}
		    	if(orderNo.trim().length() > 0){
		    		sql.append(" order by ").append(orderNo);
		    	}
		    	
		    	Query q = getSession().createQuery(sql.toString());
				q.setFirstResult(curCount); 
				q.setMaxResults(pageSize); 
				List<?> l=q.list();
				log.debug("getDataList success!");
				return l;
	    	} catch (Exception re) {
	        	log.error("getDataList fail!",re);
	        	re.printStackTrace(); 
	        	 return null;
	        }
	    }
	  
	    public int getCount(String id, String itemCode, String itemName, String inputCode,String seqNo){
	    	try {
	    		String tenantId = TenantIdHolder.get();
	    		int count = 0;
		    	StringBuilder sql = new StringBuilder("select count(*) ");
		    	sql.append(" from SecurityConfigRoles a  where a.roleFlag = '").append(TJInit.getProperty("classFlag").trim()).append("'")
		    		.append(" and a.tenantId='").append(tenantId).append("'");
		    		
		    	if(id.trim().length() > 0){
		    		sql.append(" and a.id = '").append(id.trim()).append("' ");
		    	}
		    	if(itemCode.trim().length() > 0){
		    		sql.append(" and a.roleCode = '").append(itemCode.trim()).append("' ");
		    	}
		    	if(itemName.trim().length() > 0){
		    		sql.append(" and lower(a.roleDetail) like '%").append(itemName.trim().toLowerCase()).append("%' ");
		    	}
		    	if(inputCode.trim().length() > 0){
		    		sql.append(" and a.inputCode like '").append(inputCode.trim().toUpperCase()).append("%' ");
		    	}
		    	if(seqNo.trim().length() > 0){
		    		sql.append(" and a.serialNo = '").append(seqNo.trim()).append("' ");
		    	}
		    	
		    	List<?> list = getHibernateTemplate().find(sql.toString());
		    	if(list != null && list.size() > 0){
		    		count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
		    	}
		    	log.debug("getCount success!");
		    	return count;
		    } catch (Exception re) {
		    	log.error("getCount fail!",re);
		    	re.printStackTrace();
		    	return new Integer("0");
		    }
	    }
		   public int getMaxSeqNo(){
				int temp = 1;
				try {
					String tenantId = TenantIdHolder.get();
					List<?> l = getHibernateTemplate().find(" select max(aa.serialNo) from SecurityConfigRoles aa where aa.tenantId = '"+tenantId+"'");
					if(l != null && l.size() > 0){
						Object o = l.get(0);
						if(o==null){
							return temp;
						}else{
							temp = Integer.valueOf(String.valueOf(l.get(0))).intValue()+1;
						}
						
					}
					log.debug("getMaxSeqNo success!");
					return temp;
		        } catch (Exception re) {
		        	log.error("getMaxSeqNo fail!",re);
		        	re.printStackTrace();
					return new Integer("1"); 
		        }
			} 
}
