package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommGbCoptp;
import com.tianjian.comm.dao.ICommGbCoptpDAO;

public class CommGbCoptpDAO extends HibernateDaoSupport implements ICommGbCoptpDAO {
	private static final Logger log = LogManager.getLogger(CommGbCoptpDAO.class);

	public void delete(CommGbCoptp commGbCoptp) {
		try{
			this.getHibernateTemplate().delete(commGbCoptp);
			log.debug("delete success!");
		}catch(Exception re){
			log.error("delete error!",re);
			re.printStackTrace();
		}
	}

	public List<?> findAll() {
		try{
			Query q = getSession().createQuery("from CommGbCoptp a");
			List<?> l=q.list();
			log.debug("findAll success!");
			return l;
		}catch(Exception re){
			log.error("findAll error!",re);
			re.printStackTrace();
			return null;
		}
	}

	public CommGbCoptp findById(String id) {
		CommGbCoptp data=null;
		try{
			List<?> l=this.getHibernateTemplate().find("from CommGbCoptp a where a.itemCode = ? ", id);
			if(l!=null&&l.size()>0){
				data=(CommGbCoptp) l.get(0);
			}
			log.debug("findById success!");
			return data;
		}catch(Exception re){
			log.error("findById error!",re);
			re.printStackTrace();
			return null;
			
		}
	}

	public int getCount(String itemCode, String itemName, String inputCode,
			String seqNo) {
		try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommGbCoptp a  where 1=1  ";
	    	
	    	if(itemCode != null && itemCode.trim().length() > 0){
	    		sql += " and a.itemCode like '" + itemCode.trim() + "%' ";
	    	}
	    	if(itemName != null && itemName.trim().length() > 0){
	    		sql += " and a.itemName like '%" + itemName.trim() + "%' ";
	    	}
	    	if(inputCode != null && inputCode.trim().length() > 0){
	    		sql += " and upper(a.inputCode) like '%" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if(seqNo != null && seqNo.trim().length() > 0){
	    		sql += " and a.seqNo = '" + seqNo.trim() + "' ";
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

	public List<?> getData(String itemCode, String itemName, String inputCode,
			String seqNo, String orderNo, int curCount, int pageSize) {
		try {
			String sql = " select a.itemCode, a.itemName, a.inputCode, a.levelFlag, a.parentItemCode, a.comments, a.seqNo  ";
	    	sql += " from CommGbCoptp a  where 1=1 ";
	       
	    
	    	if(itemCode != null && itemCode.trim().length() > 0){
	    		sql += " and a.itemCode like '" + itemCode.trim() + "%' ";
	    	}
	    	if(itemName != null && itemName.trim().length() > 0){
	    		sql += " and a.itemName like '%" + itemName.trim() + "%' ";
	    	}
	    	if(inputCode != null && inputCode.trim().length() > 0){
	    		sql += " and upper(a.inputCode) like '%" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if(seqNo != null && seqNo.trim().length() > 0){
	    		sql += " and a.seqNo = '" + seqNo.trim() + "' ";
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
			List<?> l= this.getHibernateTemplate().find("from CommGbCoptp a where a.itemName=?",id);
			if(l!=null&&l.size()>0){
				CommGbCoptp commGbCoptp= (CommGbCoptp) l.get(0);
				name=commGbCoptp.getItemName();
			}
			log.debug("getNameById success!");
			return name;
		}catch(Exception re){
			log.error("getNameById error!",re);
			re.printStackTrace();
			return null;
		}
	}

	public void save(CommGbCoptp commGbCoptp) {
		try{
			this.getHibernateTemplate().save(commGbCoptp);
			log.debug("save success!");
		}catch(Exception re){
			log.error("save error!",re);
			re.printStackTrace();
		}
	}

	public void update(CommGbCoptp commGbCoptp) {
		try{
			this.getHibernateTemplate().update(commGbCoptp);
			log.debug("update success!");
		}catch(Exception re){
			log.error("update error!",re);
			re.printStackTrace();
		}
	}
	public List<?> getCodeAndName() {
		//只取得1级别的
		String sql = "from CommGbCoptp a where a.levelFlag is not null";
		List<?> list = this.getHibernateTemplate().find(sql);
		if(list != null && list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	public String findLevelFlagByParentId(String id) {
		String sql = "select a.levelFlag from CommGbCoptp a where a.itemCode='"+id.trim()+"'";
		String level = "";
		List<?> list = this.getHibernateTemplate().find(sql);
		if(list != null && list.size() > 0){
			level = list.get(0).toString();
		}
		return level;
	}

	public String findNameByParentId(String id) {
		String sql = "select a.itemName from CommGbCoptp a where a.itemCode='"+id.trim()+"'";
		String name = "";
		List<?> list = this.getHibernateTemplate().find(sql);
		if(list != null && list.size() > 0){
			name = list.get(0).toString();
		}
		return name;
	}
	 public int getMaxSeqNo(){
			int temp = 1;
			try {
				List<?> l = getHibernateTemplate().find(" select max(aa.seqNo) from CommGbCoptp aa ");
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
