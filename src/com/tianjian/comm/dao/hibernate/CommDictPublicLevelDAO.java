package com.tianjian.comm.dao.hibernate;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommDictPublicLevel;
import com.tianjian.comm.dao.ICommDictPublicLevelDAO;
 


public class CommDictPublicLevelDAO extends HibernateDaoSupport implements  ICommDictPublicLevelDAO{

	private static final Logger log = LogManager.getLogger(CommDictPublicLevelDAO.class);
	
	public CommDictPublicLevel findById(String id){
		try{
			CommDictPublicLevel fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommDictPublicLevel a where a.itemCode = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommDictPublicLevel) ls.get(0);
			}
			log.debug("findById success!");
			return fac;
		}catch(Exception re){
			log.error("findById error!",re);
			re.printStackTrace();
			return null; 
		}
	}
	
	public String getItemName(String id){
		try{
			
			String s_temp="";
			CommDictPublicLevel fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommDictPublicLevel a where a.itemCode = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommDictPublicLevel) ls.get(0);
				s_temp = fac.getItemName();
				
			}
			
			log.debug("getItemName success!");
			return s_temp;
		}catch(Exception re){
			log.error("getItemName error!",re);
			re.printStackTrace();
			return null; 
		}
		
	}
	
    public void save(CommDictPublicLevel commDictPublicLevel){
    	try {
        	getHibernateTemplate().save(commDictPublicLevel);
        	log.debug("save success!");
        } catch (Exception re) {
        	log.error("save error!",re);
        	re.printStackTrace();
        }
    }
    
    public void update(CommDictPublicLevel commDictPublicLevel){
    	try {
        	getHibernateTemplate().update(commDictPublicLevel);
        	log.debug("update success!");
        } catch (Exception re) {
        	log.error("update error!",re);
        	re.printStackTrace();
        }
    }
    
    public void delete(CommDictPublicLevel commDictPublicLevel){
    	try {
        	getHibernateTemplate().delete(commDictPublicLevel);
        	log.debug("delete success!");
        } catch (Exception re) {
        	log.error("delete error!",re); 
        	re.printStackTrace();
        }
    } 
    
    public List<?> getData(String itemCode,String itemName, String inputCode,String parentItemCode, String classLevel,  String seqInLevel, String tableCode,String orderNo, int curCount,int pageSize){
    	try {
    		String sql = " select a.itemCode,a.itemName, a.inputCode, a.parentItemCode,a.classLevel,a.seqInLevel,a.tableCode,a.comments  ";
	    	sql += " from CommDictPublicLevel a  where 1=1 ";
	       
	    	if(itemCode.trim().length() > 0){
	    		sql += " and a.itemCode = '" + itemCode.trim() + "' ";
	        }
	    	if(itemName.trim().length() > 0){
	    		sql += " and lower(a.itemName) like '%" + itemName.trim().toLowerCase() + "%' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if(parentItemCode.trim().length() > 0){
	    		sql += " and a.parentItemCode = '" + parentItemCode.trim() + "' ";
	    	}
	    	if(classLevel.trim().length() > 0){
	    		sql += " and a.classLevel = '" + classLevel.trim() + "' ";
	    	}
	    	if(seqInLevel.trim().length() > 0){
	    		sql += " and a.seqInLevel = '" + seqInLevel.trim() + "' ";
	    	}
	    	if(tableCode.trim().length() > 0){
	    		sql += " and a.tableCode = '" + tableCode.trim() + "' ";
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
        	log.error("getData error!",re);
        	re.printStackTrace();
        	return null;
        }
    }
    
    public int getCount(String itemCode,String itemName, String inputCode,String parentItemCode, String classLevel,  String seqInLevel, String tableCode){
    	try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommDictPublicLevel a  where 1=1  ";
	    	if(itemCode.trim().length() > 0){
	    		sql += " and a.itemCode = '" + itemCode.trim() + "' ";
	        }
	    	if(itemName.trim().length() > 0){
	    		sql += " and a.itemName like '%" + itemName.trim() + "%' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if(parentItemCode.trim().length() > 0){
	    		sql += " and a.parentItemCode = '" + parentItemCode.trim() + "' ";
	    	}
	    	if(classLevel.trim().length() > 0){
	    		sql += " and a.classLevel = '" + classLevel.trim() + "' ";
	    	}
	    	if(seqInLevel.trim().length() > 0){
	    		sql += " and a.seqInLevel = '" + seqInLevel.trim() + "' ";
	    	}
	    	if(tableCode.trim().length() > 0){
	    		sql += " and a.tableCode = '" + tableCode.trim() + "' ";
	    	}
	    	List<?> list = getHibernateTemplate().find(sql);
	    	if(list != null && list.size() > 0){
	    		count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
	    	}
	    	log.debug("getCount success!");
	    	return count;
	    } catch (Exception re) {
	    	log.error("getCount error!",re);
	    	re.printStackTrace();
	    	return new Integer("0");
	    }
    }
    public List<?> findAll(){
    	try {
    		String sql = " from CommDictPublicLevel a  ";
	       
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
    
    public List<?> findAllByTableCode(String tableCode){
    	try {
    		String sql = " from CommDictPublicLevel  a where a.tableCode = '"+tableCode+"'";
	       
	    	Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("findAllByTableCode success!");
			return l;
    	} catch (Exception re) {
        	log.error("findAllByTableCode fail",re);
        	re.printStackTrace(); 
        	return null;
        }
    }
    public int getMaxSeqNo(){
		int temp = 1;
		try {
			List<?> l = getHibernateTemplate().find(" select max(aa.seqInLevel) from CommDictPublicLevel aa ");
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