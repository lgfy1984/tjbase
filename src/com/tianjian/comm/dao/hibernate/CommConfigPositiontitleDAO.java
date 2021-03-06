package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigPositiontitle;
import com.tianjian.comm.dao.ICommConfigPositiontitleDAO;

public class CommConfigPositiontitleDAO extends HibernateDaoSupport implements ICommConfigPositiontitleDAO {
	private static final Logger log = LogManager.getLogger(CommConfigPositiontitleDAO.class);

	public void delete(CommConfigPositiontitle commConfigPositiontitle) {
		try{
			this.getHibernateTemplate().delete(commConfigPositiontitle);
			log.debug("delete success!");
		}catch(Exception re){
			log.error("delete error!",re);
			re.printStackTrace();
		}
	}

	public List<?> findAll() {
		try{
			Query q = getSession().createQuery("from CommConfigPositiontitle a");
			List<?> l=q.list();
			log.debug("findAll success!");
			return l;
		}catch(Exception re){
			log.error("findAll error!",re);
			re.printStackTrace();
			return null;
		}
	}

	public CommConfigPositiontitle findById(String id) {
		CommConfigPositiontitle data=null;
		try{
			List<?> l=this.getHibernateTemplate().find("from CommConfigPositiontitle a where a.itemCode = ? ", id);
			if(l!=null&&l.size()>0){
				data=(CommConfigPositiontitle) l.get(0);
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
	    	sql += " from CommConfigPositiontitle a  where 1=1  ";
	    	
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
    		String sql = " select a.itemCode, a.itemName,a.inputCode,a.comments,a.seqNo  ";
	    	sql += " from CommConfigPositiontitle a  where 1=1 ";
	       
	    
	    	if(itemCode != null && itemCode.trim().length() > 0){
	    		sql += " and a.itemCode like '" + itemCode.trim() + "%' ";
	    	}
	    	if(itemName != null && itemName.trim().length() > 0){
	    		sql += " and lower(a.itemName) like '%" + itemName.trim().toLowerCase() + "%' ";
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
			List<?> l= this.getHibernateTemplate().find("from CommConfigPositiontitle a where a.itemName=?",id);
			if(l!=null&&l.size()>0){
				CommConfigPositiontitle commConfigPositiontitle= (CommConfigPositiontitle) l.get(0);
				name=commConfigPositiontitle.getItemName();
			}
			log.debug("getNameById success!");
			return name;
		}catch(Exception re){
			log.error("getNameById error!",re);
			re.printStackTrace();
			return null;
		}
	}

	public void save(CommConfigPositiontitle commConfigPositiontitle) {
		try{
			this.getHibernateTemplate().save(commConfigPositiontitle);
			log.debug("save success!");
		}catch(Exception re){
			log.error("save error!",re);
			re.printStackTrace();
		}
	}

	public void update(CommConfigPositiontitle commConfigPositiontitle) {
		try{
			this.getHibernateTemplate().update(commConfigPositiontitle);
			log.debug("update success!");
		}catch(Exception re){
			log.error("update error!",re);
			re.printStackTrace();
		}
	}

	
	/**
     * 自动获取序号
     * @param commConfigYes
     */
	public Long seqNoMaker(String nameOfTable) {
		try {
			Long temp = (Long)this.getSession().createQuery(" select max(a.seqNo) from " + nameOfTable + " a ").uniqueResult();
			if(temp==null)
				return 1L;
			else
				return temp+1;
		}
		catch (Exception re) {
			log.error("seqNoMaker error!", re);
			return 1L;
		}

	}
}
