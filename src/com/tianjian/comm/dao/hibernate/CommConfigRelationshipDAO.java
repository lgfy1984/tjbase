package com.tianjian.comm.dao.hibernate;


import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.tianjian.comm.bean.CommConfigRelationship;
import com.tianjian.comm.dao.ICommConfigRelationshipDAO;
 


public class CommConfigRelationshipDAO extends HibernateDaoSupport implements  ICommConfigRelationshipDAO{

	private static final Logger log = LogManager.getLogger(CommConfigRelationshipDAO.class);
	
	public CommConfigRelationship findById(String id){
		try{
			CommConfigRelationship fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommConfigRelationship a where a.itemCode = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommConfigRelationship) ls.get(0);
			}
			log.debug("findById success!");
			return fac;
		}catch(Exception re){
			log.error("findById fail!",re);
			re.printStackTrace();
			return null; 
		}
	}
	public String getItemName(String id){
		String itemName = "";
		try {
			List<?> l = getHibernateTemplate().find(" from CommConfigRelationship aa where aa.itemCode = ? ", id);
			if(l != null && l.size() > 0){
				CommConfigRelationship pub = (CommConfigRelationship)l.get(0);
				itemName = pub.getItemName();
			}
			log.debug("getClassName success!");
			return itemName;
        } catch (Exception re) {
        	log.error("getClassName fail!",re);
        	re.printStackTrace();
			return null; 
        }
	} 
    
    public void save(CommConfigRelationship commConfigCountry){
    	try {
        	getHibernateTemplate().save(commConfigCountry);
        	log.debug("save success!");
        } catch (Exception re) {
        	log.error("save fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void update(CommConfigRelationship commConfigCountry){
    	try {
        	getHibernateTemplate().update(commConfigCountry);
        	log.debug("update success!");
        } catch (Exception re) {
        	log.error("update fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void delete(CommConfigRelationship commConfigCountry){
    	try {
        	getHibernateTemplate().delete(commConfigCountry);
        	log.debug("delete success!");
        } catch (Exception re) {
        	log.error("delete fail!",re);
        	re.printStackTrace(); 
        }
    } 
    
    public List<?> findAll(){
    	try {
    		String sql = " from CommConfigRelationship a  ";
	       
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
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize){
    	try {
    		String sql = " select a.itemCode, a.itemName,a.inputCode,a.comments,a.seqNo  ";
	    	sql += " from CommConfigRelationship a  where 1=1 ";
	       
	    
	    	if(itemCode.trim().length() > 0){
	    		sql += " and a.itemCode = '" + itemCode.trim() + "' ";
	    	}
	    	if(itemName.trim().length() > 0){
	    		sql += " and lower(a.itemName) like '%" + itemName.trim().toLowerCase() + "%' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if(seqNo.trim().length() > 0){
	    		sql += " and a.seqNo = '" + seqNo.trim() + "' ";
	    	}
	    	if(orderNo.trim().length() > 0){
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
    
    public int getCount( String itemCode, String itemName, String inputCode,String seqNo){
    	try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommConfigRelationship a  where 1=1  ";
	    	
	    	if(itemCode.trim().length() > 0){
	    		sql += " and a.itemCode = '" + itemCode.trim() + "' ";
	    	}
	    	if(itemName.trim().length() > 0){
	    		sql += " and a.itemName like '%" + itemName.trim() + "%' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if(seqNo.trim().length() > 0){
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
    public int getMaxSeqNo(){
		int temp = 1;
		try {
			List<?> l = getHibernateTemplate().find(" select max(aa.seqNo) from CommConfigRelationship aa ");
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