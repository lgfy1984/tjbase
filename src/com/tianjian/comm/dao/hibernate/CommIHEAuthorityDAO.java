package com.tianjian.comm.dao.hibernate;


import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommIHEAuthority;
import com.tianjian.comm.dao.ICommIHEAuthorityDAO;
 


public class CommIHEAuthorityDAO extends HibernateDaoSupport implements  ICommIHEAuthorityDAO{

	private static final Logger log = LogManager.getLogger(CommIHEAuthorityDAO.class);
	
	public CommIHEAuthority findById(String id){
		try{
			CommIHEAuthority fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommIHEAuthority a where a.id = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommIHEAuthority) ls.get(0);
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
    
    public void save(CommIHEAuthority commIHEAuthority){
    	try {
        	getHibernateTemplate().save(commIHEAuthority);
        	log.debug("save success!");
        } catch (Exception re) {
        	log.error("save fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void update(CommIHEAuthority commIHEAuthority){
    	try {
        	getHibernateTemplate().update(commIHEAuthority);
        	log.debug("update success!");
        } catch (Exception re) {
        	log.error("update fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void delete(CommIHEAuthority commIHEAuthority){
    	try {
        	getHibernateTemplate().delete(commIHEAuthority);
        	log.debug("delete success!");
        } catch (Exception re) {
        	log.error("delete fail!",re);
        	re.printStackTrace(); 
        }
    } 
    
    public List<?> findAll(){
    	try {
    		String sql = " from CommIHEAuthority a  ";
	       
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
    
    public List<?> getData(String universalId, String universalIdType, String namespaceId, String seqNo, String orderNo, int curCount,int pageSize){
    	try {
    		String sql = " select a  from CommIHEAuthority a  where 1=1 ";
	       
	    
	    	if(namespaceId.trim().length() > 0){
	    		sql += " and a.namespaceId  like '%" + namespaceId.trim() + "%' ";
	    	}
	    	if(universalId.trim().length() > 0){
	    		sql += " and lower(a.universalId) like '%" + universalId.trim().toLowerCase() + "%' ";
	    	}
	    	if(universalIdType.trim().length() > 0){
	    		sql += " and a.universalIdType like '" + universalIdType.trim().toUpperCase() + "%' ";
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
    
    public int getCount(String universalId, String universalIdType, String namespaceId,String seqNo){
    	try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommIHEAuthority a  where 1=1  ";
	    	
	    	if(namespaceId.trim().length() > 0){
	    		sql += " and a.namespaceId  like '%" + namespaceId.trim() + "%'";
	    	}
	    	if(universalId.trim().length() > 0){
	    		sql += " and a.universalId like '%" + universalId.trim() + "%'";
	    	}
	    	if(universalIdType.trim().length() > 0){
	    		sql += " and a.universalIdType like '%" + universalIdType.trim().toLowerCase() + "%' ";
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