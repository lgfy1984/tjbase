package com.tianjian.comm.dao.hibernate;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommIHENameType;
import com.tianjian.comm.dao.ICommIHENameTypeDAO;
 


public class CommIHENameTypeDAO extends HibernateDaoSupport implements  ICommIHENameTypeDAO{

	private static final Logger log = LogManager.getLogger(CommIHENameTypeDAO.class);
	
	public CommIHENameType findById(String id){
		try{
			CommIHENameType fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommIHENameType a where a.id = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommIHENameType) ls.get(0);
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
    
    public void save(CommIHENameType commIHENameType){
    	try {
        	getHibernateTemplate().save(commIHENameType);
        	log.debug("save success!");
        } catch (Exception re) {
        	log.error("save fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void update(CommIHENameType commIHENameType){
    	try {
        	getHibernateTemplate().update(commIHENameType);
        	log.debug("update success!");
        } catch (Exception re) {
        	log.error("update fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void delete(CommIHENameType commIHENameType){
    	try {
        	getHibernateTemplate().delete(commIHENameType);
        	log.debug("delete success!");
        } catch (Exception re) {
        	log.error("delete fail!",re);
        	re.printStackTrace(); 
        }
    } 
    
    public List<?> findAll(){
    	try {
    		String sql = " from CommIHENameType a  ";
	       
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
    
    public List<?> getData(String nameTypeCode, String nameTypeName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize){
    	try {
    		String sql = " select a.id,a.nameTypeCode, a.nameTypeName,a.inputCode,a.comments,a.seqNo  ";
	    	sql += " from CommIHENameType a  where 1=1 ";
	       
	    
	    	if(nameTypeCode.trim().length() > 0){
	    		sql += " and a.nameTypeCode = '" + nameTypeCode.trim() + "' ";
	    	}
	    	if(nameTypeName.trim().length() > 0){
	    		sql += " and lower(a.nameTypeName) like '%" + nameTypeName.trim().toLowerCase() + "%' ";
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
    
    public int getCount(String nameTypeCode, String nameTypeName, String inputCode, String seqNo){
    	try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommIHENameType a  where 1=1  ";
	    	
	    	if(nameTypeCode.trim().length() > 0){
	    		sql += " and a.nameTypeCode = '" + nameTypeCode.trim() + "' ";
	    	}
	    	if(nameTypeName.trim().length() > 0){
	    		sql += " and a.nameTypeName like '%" + nameTypeName.trim() + "%' ";
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