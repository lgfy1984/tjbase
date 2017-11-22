package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigInputDict;
import com.tianjian.comm.dao.ICommConfigInputDict2DAO;

public class CommConfigInputDict2DAO extends HibernateDaoSupport implements ICommConfigInputDict2DAO {
	private static final Logger log = LogManager.getLogger(CommConfigInputDict2DAO.class);

	public void delete(CommConfigInputDict commConfigInputDict) {
		try{
			this.getHibernateTemplate().delete(commConfigInputDict);
			log.debug("delete success!");
		}catch(Exception re){
			log.error("delete error!",re);
			re.printStackTrace();
		}
	}

	public List<?> findAll() {
		try{
			Query q = getSession().createQuery("from CommConfigInputDict a");
			List<?> l=q.list();
			log.debug("findAll success!");
			return l;
		}catch(Exception re){
			log.error("findAll error!",re);
			re.printStackTrace();
			return null;
		}
	}

	public CommConfigInputDict findById(String id) {
		CommConfigInputDict data=null;
		try{
			List<?> l=this.getHibernateTemplate().find("from CommConfigInputDict a where a.itemCode = ? ", id);
			if(l!=null&&l.size()>0){
				data=(CommConfigInputDict) l.get(0);
			}
			log.debug("findById success!");
			return data;
		}catch(Exception re){
			log.error("findById error!",re);
			re.printStackTrace();
			return null;
			
		}
	}


	public CommConfigInputDict findByIdAndName(String id, String name) {
		CommConfigInputDict data=null;
		try{
			String sql = "from CommConfigInputDict a where a.itemCode ='"+id+"' or a.itemName = '"+name+"'";
			List<?> l=this.getHibernateTemplate().find(sql);
			if(l!=null&&l.size()>0){
				data=(CommConfigInputDict) l.get(0);
			}
			log.debug("findById success!");
			return data;
		}catch(Exception re){
			log.error("findById error!",re);
			re.printStackTrace();
			return null;
			
		}
	}
	
	public int getCount(String itemCode, String itemName, String inputCode, String inputCodeWb, String inputCode1, String inputCode2) {
		try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommConfigInputDict a  where 1=1  ";
	    	
	    	if(itemCode != null && itemCode.trim().length() > 0){
	    		sql += " and a.itemCode like '" + itemCode.trim() + "%' ";
	    	}
	    	if(itemName != null && itemName.trim().length() > 0){
	    		sql += " and a.itemName like '%" + itemName.trim() + "%' ";
	    	}
	    	if(inputCode != null && inputCode.trim().length() > 0){
	    		sql += " and upper(a.inputCode) like '%" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if(inputCodeWb != null && inputCodeWb.trim().length() > 0){
	    		sql += " and upper(a.inputCodeWb) like '%" + inputCodeWb.trim().toUpperCase() + "%' ";
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

	public List<?> getData(String itemCode, String itemName, String inputCode, String inputCodeWb, String inputCode1, String inputCode2, String orderNo, int curCount, int pageSize) {
		try {
    		String sql = " select a.itemCode, a.itemName,a.inputCode,a.inputCodeWb ";
	    	sql += " from CommConfigInputDict a  where 1=1 ";
	       
	    
	    	if(itemCode != null && itemCode.trim().length() > 0){
	    		sql += " and a.itemCode like '" + itemCode.trim() + "%' ";
	    	}
	    	if(itemName != null && itemName.trim().length() > 0){
	    		sql += " and a.itemName like '%" + itemName.trim() + "%' ";
	    	}
	    	if(inputCode != null && inputCode.trim().length() > 0){
	    		sql += " and upper(a.inputCode) like '%" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if(inputCodeWb != null && inputCodeWb.trim().length() > 0){
	    		sql += " and upper(a.inputCodeWb) like '%" + inputCodeWb.trim().toUpperCase() + "%' ";
	    	}
	    	if(orderNo != null && orderNo.trim().length() > 0){
	    		sql += " order by " + orderNo;
	    	}
	    	
	    	Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(pageSize); 
			List<?> l=q.list();
			log.debug("getData success!");
			return l;
    	} catch (Exception re) {
        	log.error("getData fail!",re);
        	re.printStackTrace(); 
        	 return null;
        }
	}

	
	public String getNameById(String id) {
		String name="";
		try{
			List<?> l= this.getHibernateTemplate().find("from CommConfigInputDict a where a.itemName=?",id);
			if(l!=null&&l.size()>0){
				CommConfigInputDict commConfigInputDict= (CommConfigInputDict) l.get(0);
				name=commConfigInputDict.getItemName();
			}
			log.debug("getNameById success!");
			return name;
		}catch(Exception re){
			log.error("getNameById error!",re);
			re.printStackTrace();
			return null;
		}
	}

	public void save(CommConfigInputDict commConfigInputDict) {
		try{
			this.getHibernateTemplate().save(commConfigInputDict);
			log.debug("save success!");
		}catch(Exception re){
			log.error("save error!",re);
			re.printStackTrace();
		}
	}

	public void update(CommConfigInputDict commConfigInputDict) {
		try{
			this.getHibernateTemplate().update(commConfigInputDict);
			log.debug("update success!");
		}catch(Exception re){
			log.error("update error!",re);
			re.printStackTrace();
		}
	}



}
