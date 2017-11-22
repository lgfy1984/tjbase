package com.tianjian.security.dao.hibernate;

import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.dao.ISecurityStaffPasswordInitDAO;
import com.tianjian.util.comm.TJInit;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class SecurityStaffPasswordInitDAO extends HibernateDaoSupport implements ISecurityStaffPasswordInitDAO {

	private static final Logger log = LogManager.getLogger(SecurityStaffPasswordInitDAO.class);

    public SecurityStaffPasswordInitDAO() {
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
    
    public void save(SecuritySystemUsers securitySystemUsers) {
        try{
            getHibernateTemplate().save(securitySystemUsers);
            log.debug("save success!");
        } catch(Exception re) {
            log.error("save fail!", re);
            re.printStackTrace();
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
    /***
     * 根据条件获取操作员列表分页显示
     */
    	public List<?> getStaffs(String staffId, String staffName, String inputCode, String itemCode,String itemCodeHidden, String hspConfigId,String orderNo, int from, int length) {
    		try {
    			String sql = "select a.id, a.staffCode,b.itemName,a.name,a.commConfigSexId,c.startTime,c.stopDate " +
				" from SecurityStaffBaseinfo a, HspConfigBaseinfoLocalBase b ,SecurityLicense c " +
				" where c.securityStaffBaseinfoId = a.id and " +
				" a.hspConfigBaseinfoId = b.id and b.hspType = '"+ TJInit.getProperty("classFlag").trim() +"'";	
    			
    			
    			if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("1")){
    			    sql +=" and b.hspConfigBaseinfoId3 = (select s.hspConfigBaseinfoId3 from HspConfigBaseinfoLocalBase s where s.id = '"+ hspConfigId +"') " ;
    			 }else if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("2")){
    			    sql +=" and b.hspConfigBaseinfoId2 = (select s.hspConfigBaseinfoId2 from HspConfigBaseinfoLocalBase s where s.id = '"+ hspConfigId +"') " ;
    			 }else{
    				sql +=" ";
    			 }
    			if (staffId.trim().length() > 0) {
    				sql += " and lower(a.staffCode) like '%" + staffId.trim().toLowerCase() + "%' ";
    			}
    			if (staffName.trim().length() > 0) {
    				sql += " and lower(a.name) like '%" + staffName.trim().toLowerCase() + "%' ";
    			}
    			if (inputCode.trim().length() > 0) {
    				sql += " and a.inputCode like '%" + inputCode.trim().toUpperCase() + "%' ";
    			}
    			if (itemCode.trim().length() > 0) {
    				sql += " and lower(a.hspConfigBaseinfoId) =  '" + itemCode.trim().toLowerCase() + "' ";
    			}
    			if (orderNo.trim().length() > 0) {
    				sql += " order by " + orderNo;
    			}
    			Query q = getSession().createQuery(sql);
    			q.setFirstResult(from);
    			q.setMaxResults(length);
    			List<?> l = q.list();
    			return l;
    		}
    		catch (RuntimeException re) {
    			log.error(re.toString());
    			throw re;
    		}
    	}

    /***
     * 获取某个机构内操作员列表
     */
    	public int getStaffsCount(String staffId, String staffName, String inputCode, String itemCode,String staffHspId,String hspConfigId) {
    		try {
    			int count = 0;
    			String sql = "select count(*) from SecurityStaffBaseinfo a ,HspConfigBaseinfoLocalBase b ,SecurityLicense c where " +
    					"c.securityStaffBaseinfoId = a.id and a.hspConfigBaseinfoId = b.id and b.hspType = '"+TJInit.getProperty("classFlag").trim() +"'";
    			
    			if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("1")){
    			    sql +=" and b.hspConfigBaseinfoId3 = (select s.hspConfigBaseinfoId3 from HspConfigBaseinfoLocalBase s where s.id = '"+ hspConfigId +"') " ;
    			 }else if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("2")){
    			    sql +=" and b.hspConfigBaseinfoId2 = (select s.hspConfigBaseinfoId2 from HspConfigBaseinfoLocalBase s where s.id = '"+ hspConfigId +"') " ;
    			 }else{
    				sql +=" ";
    			 }	
    			if (staffId.trim().length() > 0) {
    				sql += " and lower(a.staffCode) like '%" + staffId.toLowerCase() + "%' ";
    			}
    			if (staffName.trim().length() > 0) {
    				sql += " and lower(a.name) like '%" + staffName.toLowerCase() + "%' ";
    			}
    			if (inputCode.trim().length() > 0) {
    				sql += " and a.inputCode like '%" + inputCode.trim().toUpperCase() + "%' ";
    			}
    			if (itemCode.trim().length() > 0) {
    				sql += " and lower(a.hspConfigBaseinfoId) =  '" + itemCode.trim().toLowerCase() + "' ";
    			}
    			List<?> list = getHibernateTemplate().find(sql);
    			if (list != null && list.size() > 0) {
    				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
    			}
    			return count;
    		}
    		catch (RuntimeException re) {
    			log.error(re.toString());
    			throw re;
    		}
    	}

    	/**
    	 * 从指定的表中获取ID和名称列
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
    		 * 从指定的表中根据条件获取列值
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
    	  * 获取机构列表
    	  */
    	 public List<?> getHospitals(){
    	    	try{
    	    		String sql = "select a.id, a.itemName ";
    		        sql += " from HspConfigBaseinfoLocalBase a ";
    		        sql += " order by a.seqNo ";
    		        
    		        List<?> ls = getHibernateTemplate().find(sql);
    		        log.debug("getHospitals success!");
    		        return ls;
    	    	} catch(Exception re){
    		        log.error("getHospitals fail!", re);
    		        re.printStackTrace();
    		        return null;
    	    	}
    	    }
    	 
	    //医疗机构弹出层
		public List<?> findHspList(String flag,String inputCode,String hspType, int curCount, int pageSize){
			try {
				String sql = "";
				sql += "select t.id,t.item_code,t.item_name,t.input_code from hsp_config_baseinfo t where 1=1";
				//---1拼音 2代码 3汉字--
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
}