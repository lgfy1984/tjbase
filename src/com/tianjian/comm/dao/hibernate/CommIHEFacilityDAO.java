package com.tianjian.comm.dao.hibernate;


import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.tianjian.comm.bean.CommIHEFacility;
import com.tianjian.comm.dao.ICommIHEFacilityDAO;
 


public class CommIHEFacilityDAO extends HibernateDaoSupport implements  ICommIHEFacilityDAO{

	private static final Logger log = LogManager.getLogger(CommIHEFacilityDAO.class);
	
	public CommIHEFacility findById(String id){
		try{
			CommIHEFacility fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommIHEFacility a where a.id = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommIHEFacility) ls.get(0);
			}
			log.debug("findById success!");
			return fac;
		}catch(Exception re){
			log.error("findById fail!",re);
			re.printStackTrace();
			return null; 
		}
	}
//	public String getItemName(String id){
//		String itemName = "";
//		try {
//			List<?> l = getHibernateTemplate().find(" from CommConfigAbo aa where aa.itemCode = ? ", id);
//			if(l != null && l.size() > 0){
//				CommConfigAbo pub = (CommConfigAbo)l.get(0);
//				itemName = pub.getItemName();
//			}
//			log.debug("getClassName success!");
//			return itemName;
//        } catch (Exception re) {
//        	log.error("getClassName fail!",re);
//        	re.printStackTrace();
//			return null; 
//        }
//	} 
    
    public void save(CommIHEFacility commIHEFacility){
    	try {
        	getHibernateTemplate().save(commIHEFacility);
        	log.debug("save success!");
        } catch (Exception re) {
        	log.error("save fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void update(CommIHEFacility commIHEFacility){
    	try {
        	getHibernateTemplate().update(commIHEFacility);
        	log.debug("update success!");
        } catch (Exception re) {
        	log.error("update fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void delete(CommIHEFacility commIHEFacility){
    	try {
        	getHibernateTemplate().delete(commIHEFacility);
        	log.debug("delete success!");
        } catch (Exception re) {
        	log.error("delete fail!",re);
        	re.printStackTrace(); 
        }
    } 
    
    public List<?> findAll(){
    	try {
    		String sql = " from CommIHEFacility a  ";
	       
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
    
    public List<?> getData(String facilityUniversalId, String facilityUniversalIdType, String facilityNamespaceId, String seqNo, String orderNo, int curCount,int pageSize){
    	try {
    		String sql = " select a from CommIHEFacility a  where 1=1 ";
	       
	    
	    	if(facilityUniversalId.trim().length() > 0){
	    		sql += " and a.facilityUniversalId = '" + facilityUniversalId.trim() + "' ";
	    	}
	    	if(facilityUniversalIdType.trim().length() > 0){
	    		sql += " and lower(a.facilityUniversalIdType) like '%" + facilityUniversalIdType.trim().toLowerCase() + "%' ";
	    	}
	    	if(facilityNamespaceId.trim().length() > 0){
	    		sql += " and a.facilityNamespaceId like '%" + facilityNamespaceId.trim().toUpperCase() + "%' ";
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
    
    public int getCount(String facilityUniversalId, String facilityUniversalIdType, String facilityNamespaceId, String seqNo){
    	try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommIHEFacility a  where 1=1  ";
	    	
	    	if(facilityUniversalId.trim().length() > 0){
	    		sql += " and a.facilityUniversalId = '" + facilityUniversalId.trim() + "' ";
	    	}
	    	if(facilityUniversalIdType.trim().length() > 0){
	    		sql += " and a.facilityUniversalIdType like '%" + facilityUniversalIdType.trim() + "%' ";
	    	}
	    	if(facilityNamespaceId.trim().length() > 0){
	    		sql += " and a.facilityNamespaceId like '" + facilityNamespaceId.trim().toUpperCase() + "%' ";
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