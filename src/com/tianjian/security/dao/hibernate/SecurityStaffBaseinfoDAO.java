package com.tianjian.security.dao.hibernate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.dao.ISecurityStaffBaseinfoDAO;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;
/**
 * 
 * @author ch_f001
 *
 */
public class SecurityStaffBaseinfoDAO extends HibernateDaoSupport implements ISecurityStaffBaseinfoDAO {

	private static final Logger log = LogManager.getLogger(SecurityStaffBaseinfoDAO.class);

    public SecurityStaffBaseinfoDAO(){}
    
 
/**
 * 通过ID查找操作员信息
 */
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
/**
 * 通过操作员ID获取操作员登陆信息
 */
    public SecuritySystemUsers findByStaffId(String id){
    	try{
    		SecuritySystemUsers info = null;
	    	List<?> ls = getHibernateTemplate().find(" from SecuritySystemUsers a where a.securityStaffBaseinfoId = ? ", id);
	        if(ls != null && ls.size() > 0){
	        	info = (SecuritySystemUsers)ls.get(0);
	        }
	        log.debug("findByStaffId success!");
	        return info;
    	} catch(Exception re){
    		log.error("findByStaffId fail!", re);
            re.printStackTrace();
            return null;
    	}   
    }
    /**
     * 通过卫生人员ID查找操作员信息
     */
    public SecurityStaffBaseinfo findByStaffBsId(String id) {
    	try{
	    	SecurityStaffBaseinfo info = null;
	    	List<?> ls = getHibernateTemplate().find(" from SecurityStaffBaseinfo a where a.hspStaffBaseinfoId = ? ", id);
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
    
    
    /**
     * 通过操作员登陆用户名staffCode获取操作员信息
     */
    public SecurityStaffBaseinfo findByCode(String code) {
    	try{
	    	SecurityStaffBaseinfo info = null;
	    	List<?> ls = getHibernateTemplate().find(" from SecurityStaffBaseinfo a where lower(a.staffCode) = ? ", code.trim().toLowerCase());
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
/**
 * 保存操作员信息
 */
    public void save(SecurityStaffBaseinfo securityStaffBaseinfo) {
        try{
        	String tenantId = TenantIdHolder.get();
        	securityStaffBaseinfo.setTenantId(tenantId);
            getHibernateTemplate().save(securityStaffBaseinfo);
            log.debug("save success!");
        } catch(Exception re) {
            log.error("save fail!", re);
            re.printStackTrace();
        }
    }
    /**
     * 更新操作员信息
     */
    public void update(SecurityStaffBaseinfo securityStaffBaseinfo) {
        try{
            getHibernateTemplate().update(securityStaffBaseinfo);
            log.debug("update success!");
        } catch(Exception re) {
            log.error("update fail!", re);
            re.printStackTrace();
        }
    }
    /**
     * 删除操作员信息
     */
    public void delete(SecurityStaffBaseinfo securityStaffBaseinfo) {
        try{
            getHibernateTemplate().delete(securityStaffBaseinfo);
            log.debug("delete success!");
        } catch(Exception re){
            log.error("delete fail!", re);
            re.printStackTrace();
        }
    }
    /**
     * 保存操作员登陆信息
     */
    public void save(SecuritySystemUsers securitySystemUsers) {
        try{
            getHibernateTemplate().save(securitySystemUsers);
            log.debug("save success!");
        } catch(Exception re) {
            log.error("save fail!", re);
            re.printStackTrace();
        }
    }
    /**
     * 更新操作员登陆信息
     */
    public void update(SecuritySystemUsers securitySystemUsers) {
        try{
            getHibernateTemplate().update(securitySystemUsers);
            log.debug("update success!");
        } catch(Exception re) {
            log.error("update fail!", re);
            re.printStackTrace();
        }
    }
    /**
     * 删除操作员登陆信息
     */
    public void delete(SecuritySystemUsers securitySystemUsers) {
        try{
            getHibernateTemplate().delete(securitySystemUsers);
            log.debug("delete success!");
        } catch(Exception re){
            log.error("delete fail!", re);
            re.printStackTrace();
        }
    }
    /**
     * 根据条件获取指定页面长度的操作员信息列表
     */
    public List<?> getData(String staffCode, String hspConfigBaseinfoId, String name, String commConfigSexId, String itemName, 
    		String inputCode,String staffId, String order,int curCount, int quChuCount) {
    	try{
    		List<?> l;
	    	StringBuilder sql = new StringBuilder("select a.id, a.staffCode,b.itemName,a.name,a.commConfigSexId,c.startTime,c.stopDate")
	        			.append(" from SecurityStaffBaseinfo a , HspConfigBaseinfoLocalBase b ,SecurityLicense c ")
	        			.append(" where a.id = c.securityStaffBaseinfoId")
	        			.append(" and b.id = a.hspConfigBaseinfoId");
	        sql.append(" and (b.id = :hspConfigBaseinfoId");
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("hspConfigBaseinfoId", hspConfigBaseinfoId);
	    	List<String> subHspIdList = this.getSubHspIds(hspConfigBaseinfoId);
	    	if(subHspIdList != null && subHspIdList.size() > 0){
	    		for(String subHspId : subHspIdList){
	    			sql.append(" or b.id = '").append(subHspId).append("'");
	    		}
	    	}
	    	sql.append(")");
	    	
	        if(itemName.trim().length() > 0){
	            sql.append(" and b.itemName like :itemName");
	            map.put("itemName", "%" + itemName.trim() + "%");
	        }
	        if(staffCode.trim().length() > 0){
	            sql.append(" and lower(a.staffCode) like :staffCode");
	            map.put("staffCode", "%" + staffCode.trim().toLowerCase() + "%");
	        }
	        if(name.trim().length() > 0){
	        	sql.append(" and lower(a.name) like :name");
	        	map.put("name", "%" + name.trim().toLowerCase() + "%");
	        }
	        if(commConfigSexId.trim().length() > 0){
	        	sql.append(" and a.commConfigSexId = :commConfigSexId");
	        	map.put("commConfigSexId", commConfigSexId.trim());
	        }
//	        if(dateOfBirth.trim().length() > 0){
//	        	sql += " and a.dateOfBirth = '" + dateOfBirth.trim() + "'";
//	        }
	        if(inputCode.trim().length() > 0){
	        	sql.append(" and a.inputCode like :inputCode");
	        	map.put(inputCode, "%" + inputCode.toUpperCase().trim() + "%");
	        }
//	        if(staffId.trim().length() > 0){
//	        	sql += " and a.id = '" + staffId.trim() + "'";
//	        }
	        if(order.trim().length() > 0){
	        	sql.append(" order by " + order.trim() + "'");
	        }
	        
	        Query q = getSession().createQuery(sql.toString());
	        for(Map.Entry<String, Object> entry : map.entrySet()){
	        	q.setParameter(entry.getKey(), entry.getValue());
	        }
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

    public List<?> getDataRegisterCode(String staffCode, String hspConfigBaseinfoId, String name, String commConfigSexId, String itemName, String inputCode,String staffId, String order, int curCount, int quChuCount) {
    	try{
    		List<?> l;
	    	StringBuilder sql = new StringBuilder("select a.id, a.staffCode,b.itemName,a.name, c.registCode")
	        					.append(" from SecurityStaffBaseinfo a, HspConfigBaseinfoLocalBase b, SecurityLicense c")
	        					.append(" where a.id = c.securityStaffBaseinfoId")
	        					.append(" and b.id = a.hspConfigBaseinfoId");
	    	sql.append(" and (b.id = :hspConfigBaseinfoId");
	    	Map<String, Object> map = new HashMap<String, Object>();
	        map.put("hspConfigBaseinfoId", hspConfigBaseinfoId);
	    	List<String> subHspIdList = this.getSubHspIds(hspConfigBaseinfoId);
	    	if(subHspIdList != null && subHspIdList.size() > 0){
	    		for(String subHspId : subHspIdList){
	    			sql.append(" or b.id = '").append(subHspId).append("'");
	    		}
	    	}
	    	sql.append(")");
	        if(itemName.trim().length() > 0){
	        	sql.append(" and b.itemName like :itemName");
	            map.put("itemName", "%" + itemName.trim() + "%");
	        }
	        
	        if(staffCode.trim().length() > 0){
	            sql.append(" and lower(a.staffCode) like :staffCode");
	            map.put("staffCode", "%" + staffCode.trim().toLowerCase() + "%");
	        }
//	        if(hspConfigBaseinfoId.trim().length() > 0){
//	        	sql += " and a.hspConfigBaseinfoId = '" + hspConfigBaseinfoId.trim() + "'";
//	        }
	        if(name.trim().length() > 0){
	        	sql.append(" and lower(a.name) like :name");
	        	map.put("name", "%" + name.trim().toLowerCase() + "%");
	        }
	        if(commConfigSexId.trim().length() > 0){
	        	sql.append(" and a.commConfigSexId = :commConfigSexId");
	        	map.put("commConfigSexId", commConfigSexId.trim());
	        }
//	        if(dateOfBirth.trim().length() > 0){
//	        	sql += " and a.dateOfBirth = '" + dateOfBirth.trim() + "'";
//	        }
	        if(inputCode.trim().length() > 0){
	        	sql.append(" and a.inputCode like :inputCode");
	        	map.put("inputCode", "%" + inputCode.toUpperCase().trim() + "%");
	        }
//	        if(staffId.trim().length() > 0){
//	        	sql += " and a.id = '" + staffId.trim() + "'";
//	        }
	        if(order.trim().length() > 0){
	        	sql.append(" order by ").append(order.trim()).append("'");
	        }
	        
	        Query q = getSession().createQuery(sql.toString());
	        for(Map.Entry<String, Object> entry : map.entrySet()){
	        	q.setParameter(entry.getKey(), entry.getValue());
	        }
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
    
    public List<?> getRegisterExport(String staffCode, String hspConfigBaseinfoId, String name) {
    	try{
    		List<?> l;
	    	String sql = "select a.id, a.staffCode, b.itemName,a.name, c.registCode";
	        sql += " from SecurityStaffBaseinfo a , HspConfigBaseinfoLocalBase b ,SecurityLicense c where a.id = c.securityStaffBaseinfoId and b.id = a.hspConfigBaseinfoId"; 
	        if(staffCode.trim().length() > 0){
	            sql += " and lower(a.staffCode) like '%" + staffCode.trim().toLowerCase() + "%'";
	        }
	        if(hspConfigBaseinfoId.trim().length() > 0){
	        	sql += " and a.hspConfigBaseinfoId = '" + hspConfigBaseinfoId.trim() + "'";
	        }
	        if(name.trim().length() > 0){
	        	sql += " and lower(a.name) like '%" + name.trim().toLowerCase() + "%'";
	        }
	        
	        Query q = getSession().createQuery(sql);
	        l = q.list();
	        log.debug("getData success!");
	        return l;
    	} catch(Exception re){
	        log.error("getData fail!", re);
	        re.printStackTrace();
	        return null;
    	}
    }
/**
 * 根据条件获取的操作员的总数量
 */
    public int getCount(String staffCode, String hspConfigBaseinfoId, String name, String commConfigSexId, 
    		String itemName, String inputCode,String staffId) {
    	try{
    		int count;
	    	count = 0;
	        StringBuilder sql = new StringBuilder("select count(*)")
				        .append(" from SecurityStaffBaseinfo a , HspConfigBaseinfoLocalBase b ,SecurityLicense c ")
						.append(" where a.id = c.securityStaffBaseinfoId")
						.append(" and b.id = a.hspConfigBaseinfoId");
			sql.append(" and (b.id = :hspConfigBaseinfoId");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hspConfigBaseinfoId", hspConfigBaseinfoId);
			List<String> subHspIdList = this.getSubHspIds(hspConfigBaseinfoId);
			if(subHspIdList != null && subHspIdList.size() > 0){
				for(String subHspId : subHspIdList){
					sql.append(" or b.id = '").append(subHspId).append("'");
				}
			}
			sql.append(")");
			
			if(itemName.trim().length() > 0){
			    sql.append(" and b.itemName like :itemName");
			    map.put("itemName", "%" + itemName.trim() + "%");
			}
			if(staffCode.trim().length() > 0){
			    sql.append(" and lower(a.staffCode) like :staffCode");
			    map.put("staffCode", "%" + staffCode.trim().toLowerCase() + "%");
			}
			if(name.trim().length() > 0){
				sql.append(" and lower(a.name) like :name");
				map.put("name", "%" + name.trim().toLowerCase() + "%");
			}
			if(commConfigSexId.trim().length() > 0){
				sql.append(" and a.commConfigSexId = :commConfigSexId");
				map.put("commConfigSexId", commConfigSexId.trim());
			}
			//if(dateOfBirth.trim().length() > 0){
			//	sql += " and a.dateOfBirth = '" + dateOfBirth.trim() + "'";
			//}
			if(inputCode.trim().length() > 0){
				sql.append(" and a.inputCode like :inputCode");
				map.put(inputCode, "%" + inputCode.toUpperCase().trim() + "%");
			}
	        Query q = this.getSession().createQuery(sql.toString());
	        for(Map.Entry<String, Object> entry : map.entrySet()){
	        	q.setParameter(entry.getKey(), entry.getValue());
	        }
	        List<?> list = q.list();
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
/**
 *从指定的table表中获取id和name
 */
    public List<?> getIdNames(String table, String id, String name){
    	try{
    		String sql = "select a." + id + ", a." + name + " ";
	        sql += " from " + table + " a ";
	        sql += " order by a." + id + " ";
	        
	        List<?> ls = getHibernateTemplate().find(sql);
	        log.debug("getIdNames success!");
	        return ls;
    	} catch(Exception re){
	        log.error("getIdNames fail!", re);
	        re.printStackTrace();
	        return null;
    	}
    }
    /**
     *从指定的table表中查找满足id=idValue的记录的name
     */
    public String getNameById(String table, String id, String name, String idValue){
    	try{
    		String temp = "";
    		String sql = "select a." + name + " ";
	        sql += " from " + table + " a ";
	        sql += " where a." + id + " = '" + idValue + "' ";
	        
	        List<?> ls = getHibernateTemplate().find(sql);
	        if(ls != null && ls.size() > 0){
	        	temp = (String)ls.get(0);
	        }
	        if(temp == null){
	        	temp = "";
	        }
	        log.debug("getNameById success!");
	        return temp;
    	} catch(Exception re){
	        log.error("getNameById fail!", re);
	        re.printStackTrace();
	        return "";
    	}
    }

    /**
     * 获取所有操作员信息
     */
    public List<?> getSecData() {
    	try{
    		List<?> l;
	    	String sql = "select a ";
	        sql += " from SecurityStaffBaseinfo a  where 1=1  "; 
	        Query q = getSession().createQuery(sql);
	        
	        l = q.list();
	        log.debug("getSecData success!");
	        return l;
    	} catch(Exception re){
	        log.error("getSecData fail!", re);
	        re.printStackTrace();
	        return null;
    	}
    }
  //医疗机构弹出层
	public List<?> findHspList(String classFlag,String flag,String inputCode,String hspType, int curCount, int pageSize){
		
			try {
				String sql = "";
				sql += "select t.id,t.item_code,t.item_name,t.input_code from hsp_config_baseinfo t where 1=1";
				//---1拼音 2代码 3汉字--
				if(classFlag != null && classFlag.length() > 0){
					sql = sql + " and t.hsp_type = '" + classFlag + "'";
				}
				
				if(flag.equals("1")){
					sql = sql + " and upper(t.input_code) like '"+inputCode.toUpperCase()+"%'";
				}else if(flag.equals("2")){
					sql = sql + " and t.item_code like '"+inputCode+"%'";
				}else if(flag.equals("3")){
					sql = sql + " and t.item_name like '%"+inputCode+"%'";
				}else{
					sql = sql + "  ";
				}
				Query q = getSession().createSQLQuery(sql);
				q.setFirstResult(curCount); 
				q.setMaxResults(pageSize); 
				List<?> l=q.list();
				return l;
			}
			catch (Exception re) {
				log.error("findHspList error!", re);
				re.printStackTrace();
				return null;
			}
			
	}
	   public int getMaxSeqNo(){
			int temp = 1;
			try {
				List<?> l = getHibernateTemplate().find(" select max(aa.seqNo) from SecurityStaffBaseinfo aa ");
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