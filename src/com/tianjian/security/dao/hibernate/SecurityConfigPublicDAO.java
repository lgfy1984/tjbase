package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityConfigPublic;
import com.tianjian.security.dao.ISecurityConfigPublicDAO;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;
/**
 * 模块类别表DAO
 * @author ch_f001
 * @since 2008-3-24 16:34
 * @version 1.0
 */
public class SecurityConfigPublicDAO extends HibernateDaoSupport implements ISecurityConfigPublicDAO {
	private static final Logger log = LogManager.getLogger(SecurityConfigPublicDAO.class);
	
	public void delete(SecurityConfigPublic ecurityConfigPublic) {
        try{
            getHibernateTemplate().delete(ecurityConfigPublic);
            log.debug("delete success!");
        } catch(RuntimeException re) {
            log.error("delete fail!", re);
            throw re;
        }
    }
	
	public String getItemName(String id){
		String itemName = "";
		try {
			List<?> l = getHibernateTemplate().find(" from SecurityConfigPublic aa where aa.id = ? ", id);
			if(l != null && l.size() > 0){
				SecurityConfigPublic pub = (SecurityConfigPublic)l.get(0);
				itemName = pub.getReason();
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
    		String sql = "select a from SecurityConfigPublic a , SecurityConfigPublicClass b where a.scpcId = b.id and b.classFlag = '" + TJInit.getPageSize("classFlag") +"'";
	       
	    	Query q = getSession().createQuery(sql); 
			List<?> l = q.list();
			log.debug("findAll success!");
			return l;
    	} catch (Exception re) {
        	log.error("findAll fail",re);
        	re.printStackTrace(); 
        	return null;
        }
    }
	
	/**获取模块列表 */
	public List<?> getMods(){
		try {
			String sql = "";
			sql += " select distinct securityConfigPublic " +
					" from SecurityConfigPublic securityConfigPublic, " +
					" SecurityConfigPublicClass securityConfigPublicClass" +
					" where securityConfigPublic.scpcId = securityConfigPublicClass.id";
			//取本系统的模块类别
	    	if(TJInit.getProperty("classFlag").trim().length()>0){
	    		sql += " and securityConfigPublicClass.classFlag ='" +TJInit.getProperty("classFlag").trim()+"'" ;
	    	}
        	return getHibernateTemplate().find(sql+
        			"order by securityConfigPublic.modCode ");
        } catch (Exception re) {
			log.error("getMods error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	/**获取模块列表 */
	public List<?> getMod(String modClassId,String staffType){
		try {
			String sql = "";
			sql += "from SecurityConfigPublic a" + 
			" where a.scpcId = ? " ;
			
			//判断是不是超级管理员
			
			//如果不是将系统超级用户的菜单给屏蔽
//			if(staffType!=null&&staffType.trim().equals("0")){
//				sql +=" and securityConfigMenus.menuType=0" ;
//			}
//			
//			
//			sql +=" order by securityConfigMenus.menuLevel, securityConfigMenus.menuSeq, " +
//			" securityConfigMenus.parentId, securityConfigMenus.menuCode";
        	return getHibernateTemplate().find(sql, modClassId);
		   } catch (Exception re) {
				log.error("getModMenu error!", re);
				re.printStackTrace();
				return null;
			}
    }
	
	public SecurityConfigPublic findById(String Id) {
		try {
			SecurityConfigPublic temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigPublic a where a.id = ? ", Id);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigPublic) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findById error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	/**获取模块类别列表 */
	public List<?> getModClass(){
		try {
			String sql = "select securityConfigPublicClass from SecurityConfigPublicClass securityConfigPublicClass where 1=1 ";
			//取本系统的模块类别
	    	if(TJInit.getProperty("classFlag").trim().length()>0){
	    		sql += " and securityConfigPublicClass.classFlag ='" +TJInit.getProperty("classFlag").trim()+"'" ;
	    	}
        	return getHibernateTemplate().find(sql +
        			"order by securityConfigPublicClass.classCode ");
        	
		} catch (Exception re) {
			log.error("getModClass error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	public SecurityConfigPublic findByCode(String Id) {
		try {
			SecurityConfigPublic temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigPublic a where a.modCode = ? ", Id);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigPublic) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findByCode error!", re);
			re.printStackTrace();
			return null;
		}
	}
	public void save(SecurityConfigPublic securityConfigPublic) {
        try{
        	String tenantId = TenantIdHolder.get();
        	securityConfigPublic.setTenantId(tenantId);
            getHibernateTemplate().save(securityConfigPublic);
            log.debug("save success!");
        } catch(RuntimeException re) {
            log.error("save fail!", re);
            throw re;
        }
    }

	public void update(SecurityConfigPublic securityConfigPublic) {
        try{
        	String tenantId = TenantIdHolder.get();
        	securityConfigPublic.setTenantId(tenantId);
            getHibernateTemplate().update(securityConfigPublic);
            log.debug("update success!");
        } catch(RuntimeException re) {
            log.error("update fail!", re);
            throw re;
        }
    }

	  public List<?> getDataList(String id,String modCode, String reason, String inputCode, String serialNo, String parentId,String orderNo, int curCount,int pageSize){
	    	try {
	    		String sql = "select a.id, a.modCode,a.reason,a.serialNo,a.inputCode,b.className ";
		    	sql += " from SecurityConfigPublic a , SecurityConfigPublicClass b where 1=1  and a.scpcId = b.id";
		    	if(id.trim().length() > 0){
		    		sql += " and a.id = '" + id.trim() + "' ";
		    	}
		    	if(modCode.trim().length() > 0){
		    		sql += " and a.modCode = '" + modCode.trim() + "' ";
		    	}
		    	if(reason.trim().length() > 0){
		    		sql += " and lower(a.reason) like '%" + reason.trim().toLowerCase() + "%' ";
		    	}
		    	if(inputCode.trim().length() > 0){
		    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
		    	}
		    	if(serialNo.trim().length() > 0){
		    		sql += " and a.serialNo = '" + serialNo.trim() + "' ";
		    	}
		    	if(parentId.trim().length() > 0){
		    		sql += " and a.scpcId = '" + parentId.trim() + "' ";
		    	}
		    	if(orderNo.trim().length() > 0){
		    		sql += " order by " + orderNo;
		    	}
		    	
		    	Query q = getSession().createQuery(sql);
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

	    public int getCount(String id, String modCode, String reasonValue, String inputCode,String serialNo,String parentId){
	    	try {
	    		int count = 0;
		    	String sql = "select count(*) ";
		    	sql += " from SecurityConfigPublic a, SecurityConfigPublicClass b where a.scpcId = b.id ";
		    	if(id.trim().length() > 0){
		    		sql += " and a.id = '" + id.trim() + "' ";
		    	}
		    	if(modCode.trim().length() > 0){
		    		sql += " and a.modCode = '" + modCode.trim() + "' ";
		    	}
		    	if(reasonValue.trim().length() > 0){
		    		sql += " and lower(a.reasonValue) like '%" + reasonValue.trim().toLowerCase() + "%' ";
		    	}
		    	if(inputCode.trim().length() > 0){
		    		sql += " and a.inputCode like '%" + inputCode.trim().toUpperCase() + "%' ";
		    	}
		    	if(serialNo.trim().length() > 0){
		    		sql += " and a.serialNo = '" + serialNo.trim() + "' ";
		    	}
		    	if(parentId.trim().length() > 0){
		    		sql += " and a.scpcId = '" + parentId.trim() + "' ";
		    	}
		    	List<?> list = getHibernateTemplate().find(sql);
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
		   public long getMaxSeqNo(){
				long temp = 1;
				try {
					List<?> l = getHibernateTemplate().find(" select max(aa.serialNo) from SecurityConfigPublic aa ");
					if(l != null && l.size() > 0){
						Object o = l.get(0);
						if(o==null){
							return temp;
						}else{
							temp = Long.valueOf(String.valueOf(l.get(0)))+1l;
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
		public int getMenusCountByPublicId(String publicId) {
			List<?> list = getHibernateTemplate().find("select count(a) from SecurityConfigMenus a  where a.securityConfigPublicId = ?", publicId);
			if(list.size() > 0){
				return Integer.valueOf(String.valueOf(list.get(0)));
			}
			return 0;
		} 
}
