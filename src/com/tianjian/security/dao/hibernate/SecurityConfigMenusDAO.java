package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublic;
import com.tianjian.security.dao.ISecurityConfigMenusDAO;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;

/**
 * 模块类别表DAO
 * @author ch_f001
 * @since 2008-3-24 16:34
 * @version 1.0
 */
public class SecurityConfigMenusDAO extends HibernateDaoSupport implements ISecurityConfigMenusDAO {

	private static final Logger log = LogManager.getLogger(SecurityConfigMenusDAO.class);

	public void delete(SecurityConfigMenus ecurityConfigMenus) {
		try {
			getHibernateTemplate().delete(ecurityConfigMenus);
			log.debug("delete success!");
		}
		catch (RuntimeException re) {
			log.error("delete fail!", re);
			throw re;
		}
	}
	public SecurityConfigPublic getModPublic(String modId){
		try {
			SecurityConfigPublic temp = null;
			String sql = "from SecurityConfigPublic a where a.id =  '" + modId +"'";
			//取本系统的模块类别
			List<?> list = this.getHibernateTemplate().find(sql);
			if(list != null && list.size()>0){
				temp = (SecurityConfigPublic)list.get(0);
			}
        	return  temp;
		} catch (Exception re) {
			log.error("getModClass error!", re);
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
	public String getMenuDetail(String id) {
		String menuDetail = "";
		try {
			List<?> l = getHibernateTemplate().find(" from SecurityConfigMenus aa where aa.id = ? ", id);
			if (l != null && l.size() > 0) {
				SecurityConfigMenus pub = (SecurityConfigMenus) l.get(0);
				menuDetail = pub.getMenuDetail();
			}
			log.debug("getItemName success!");
			return menuDetail;
		}
		catch (Exception re) {
			log.error("getItemName fail!", re);
			re.printStackTrace();
			return null;
		}
	}

	public List<?> findAll() {
		try {
			String sql = " from SecurityConfigMenus a  ";
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
	
	public List<?> findByParentId(String id) {
		try {
			String sql = " from SecurityConfigMenus a  where a.parentId = '"+ id +"'";
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
	
	public SecurityConfigMenus findById(String Id) {
		try {
			SecurityConfigMenus temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigMenus a where a.id = ? ", Id);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigMenus) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findById error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	public SecurityConfigMenus findByCode(String Id) {
		try {
			SecurityConfigMenus temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigMenus a where a.menuCode = ? ", Id);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigMenus) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findByCode error!", re);
			re.printStackTrace();
			return null;
		}
	}
	/**获取模块对应的菜单列表 */
	public List<?> getModMenu(String modId,String staffType){
		try {
			String sql = "";
			sql += "from SecurityConfigMenus securityConfigMenus" + 
			" where securityConfigMenus.securityConfigPublicId = ? " ;
			
			//判断是不是超级管理员
			
			//如果不是将系统超级用户的菜单给屏蔽
			if(staffType!=null&&staffType.trim().equals("0")){
				sql +=" and securityConfigMenus.menuType=0" ;
			}
			
			
			sql +=" order by securityConfigMenus.menuLevel, securityConfigMenus.menuSeq, " +
			" securityConfigMenus.parentId, securityConfigMenus.menuCode";
        	return getHibernateTemplate().find(sql, modId);
		   } catch (Exception re) {
				log.error("getModMenu error!", re);
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
	public void save(SecurityConfigMenus securityConfigMenus) {
		try {
			String tenantId = TenantIdHolder.get();
			securityConfigMenus.setTenantId(tenantId);
			getHibernateTemplate().save(securityConfigMenus);
			log.debug("save success!");
		}
		catch (RuntimeException re) {
			log.error("save fail!", re);
			throw re;
		}
	}

	public void update(SecurityConfigMenus securityConfigMenus) {
		try {
			String tenantId = TenantIdHolder.get();
			securityConfigMenus.setTenantId(tenantId);
			getHibernateTemplate().update(securityConfigMenus);
			log.debug("update success!");
		}
		catch (RuntimeException re) {
			log.error("update fail!", re);
			throw re;
		}
	}

	public List<?> getDataList(String id, String menuCode, String menuDetail, String inputCode, String serialNo, String orderNo, int curCount, int pageSize) {
		try {
			String sql = "  ";
			sql += " from SecurityConfigMenus a  where 1=1 ";
			if (id.trim().length() > 0) {
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if (menuCode.trim().length() > 0) {
				sql += " and a.menuCode = '" + menuCode.trim() + "' ";
			}
			if (menuDetail.trim().length() > 0) {
				sql += " and lower(a.menuDetail) like '%" + menuDetail.trim().toLowerCase() + "%' ";
			}
			if (inputCode.trim().length() > 0) {
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
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

	public int getCount(String id, String menuCode, String menuDetail, String inputCode, String serialNo) {
		try {
			int count = 0;
			String sql = "select count(*) ";
			sql += " from SecurityConfigMenus a  where 1=1  ";
			if (id.trim().length() > 0) {
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if (menuCode.trim().length() > 0) {
				sql += " and a.menuCode = '" + menuCode.trim() + "' ";
			}
			if (menuDetail.trim().length() > 0) {
				sql += " and lower(a.menuDetail) like '%" + menuDetail.trim().toLowerCase() + "%' ";
			}
			if (inputCode.trim().length() > 0) {
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
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

	// 根据输入的姓名查找符合条件的数据
	public List<?> getAjaxDict(String inputFlag, String inputValue) {
		String newInputCode = "";
		String newCode = "";		
		String newName = "";
		//限制条件是只能查询是县级或市级下面的模块
		String sql = "select a FROM SecurityConfigMenus  a , SecurityConfigPublic b , SecurityConfigPublicClass c WHERE a.securityConfigPublicId = b.id and b.scpcId = c.id and c.classFlag =  '" + TJInit.getPageSize("classFlag") +"'";
		if ((inputFlag != null && inputFlag.equals("1")) && (inputValue != null && !inputValue.equals(""))) {
			newInputCode = new String(inputValue.trim());
			sql += "  and upper(a.inputCode) like '" + newInputCode.toUpperCase() + "%'";
		}
		if ((inputFlag != null && inputFlag.equals("2")) && (inputValue != null && !inputValue.equals(""))) {
			newCode = new String(inputValue.trim());
			sql += "  and a.menuCode like '" + newCode + "%'";
		}
		if ((inputFlag != null && inputFlag.equals("3")) && (inputValue != null && !inputValue.equals(""))) {
			newName = new String(inputValue.trim());
			sql += "  and lower(a.menuDetail) like '" + newName.trim().toLowerCase() + "%'";
		}
		Query q = getSession().createQuery(sql);
		q.setMaxResults(Integer.valueOf("10"));
		List<?> list = q.list();
		return list;
	}
	   public long getMaxSeqNo(){
			long temp = 1l;
			try {
				List<?> l = getHibernateTemplate().find(" select max(aa.serialNo) from SecurityConfigMenus aa ");
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
	public int getChildrenCount(String id) {
		List<?> list = getHibernateTemplate().find("select count(a) from SecurityConfigMenus a  where a.parentId = ?", id);
		if(list.size() > 0){
			return Integer.valueOf(String.valueOf(list.get(0)));
		}
		return 0;
	} 
}
