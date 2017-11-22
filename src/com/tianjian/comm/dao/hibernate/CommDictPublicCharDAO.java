package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.dao.ICommDictPublicCharDAO;
 


public class CommDictPublicCharDAO extends HibernateDaoSupport implements  ICommDictPublicCharDAO{

	private static final Logger log = LogManager.getLogger(CommDictPublicCharDAO.class);
	
	/**
	 * 查找所有记录根据ClassCode
	 * @return
	 */
	public List<?> findAllByClassCode(String classCode) {
		try{
			String sql="from CommDictPublicChar a where 1=1";
			if(classCode!=null && classCode.length()>0){
				sql +=" and a.classCode ='"+classCode+"'";
			}
			Query q=getSession().createQuery(sql);
			List<?> l=q.list();
			log.debug("findAllByClassCode success");
			return l;
		}catch(Exception re){
			log.error("findAllByClassCode error",re);
			re.printStackTrace();
			return null;
		}
	}
	/**
	 * 通过classcode和dictcode联合查询获取纪录
	 * @param id
	 * @return
	 */
	public CommDictPublicChar findByDictcode(String classCode, String dictCode) {
		try{
			CommDictPublicChar fac = null;
			String sql="from CommDictPublicChar a where 1=1";
			if(classCode!=null && classCode.length()>0){
				sql +=" and a.classCode ='"+classCode+"'";
			}
			if(dictCode!=null && dictCode.length()>0){
				sql +=" and a.dictCode ='"+dictCode+"'";
			}
			Query q=getSession().createQuery(sql);
			List<?> l=q.list();
			fac = (CommDictPublicChar)l.get(0);
			log.debug("findByDictcode success!");
			return fac;
		}catch(Exception re){
			log.error("findByDictcode fail!",re);
			re.printStackTrace();
			return null; 
		}
	}
	
	
	public CommDictPublicChar findById(String id){
		try{
			CommDictPublicChar fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommDictPublicChar a where a.id = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommDictPublicChar) ls.get(0);
			}
			log.debug("findById success!");
			return fac;
		}catch(Exception re){
			log.error("findById error!",re);
			re.printStackTrace();
			return null; 
		}
	}
    
    public void save(CommDictPublicChar commDictPublicChar){
    	try {
        	getHibernateTemplate().save(commDictPublicChar);
        	log.debug("save success!");
        } catch (Exception re) {
        	log.error("save error!",re);
        	re.printStackTrace();
        }
    }
    
    public void update(CommDictPublicChar commDictPublicChar){
    	try {
        	getHibernateTemplate().update(commDictPublicChar);
        	log.debug("update success!");
        } catch (Exception re) {
        	log.error("update error!",re);
        	re.printStackTrace();
        }
    }
    
    public void delete(CommDictPublicChar commDictPublicChar){
    	try {
        	getHibernateTemplate().delete(commDictPublicChar);
        	log.debug("delete success!");
        } catch (Exception re) {
        	log.error("delete error!",re); 
        	re.printStackTrace();
        }
    } 
    
    public List<?> getData(String id,String classCode, String dictCode, String dictValue, String inputCode, String seqNo, String orderNo, int curCount,int pageSize){
    	try {
    		String sql = " select a.id,a.classCode, a.dictCode, a.dictValue,a.inputCode,a.comments,a.seqNo  ";
	    	sql += " from CommDictPublicChar a  where 1=1 ";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(classCode.trim().length() > 0){
	    		sql += " and a.classCode = '" + classCode.trim() + "' ";
	    	}
	    	if(dictCode.trim().length() > 0){
	    		sql += " and a.dictCode = '" + dictCode.trim() + "' ";
	    	}
	    	if(dictValue.trim().length() > 0){
	    		sql += " and lower(a.dictValue) like '%" + dictValue.trim().toLowerCase() + "%' ";
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
        	log.error("getData error!",re);
        	re.printStackTrace();
        	return null;
        }
    }
    
    public int getCount(String id, String classCode,String dictCode, String dictValue, String inputCode,String seqNo){
    	try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommDictPublicChar a  where 1=1  ";
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(classCode.trim().length() > 0){
	    		sql += " and a.classCode = '" + classCode.trim() + "' ";
	        }
	    	if(dictCode.trim().length() > 0){
	    		sql += " and a.dictCode = '" + dictCode.trim() + "' ";
	    	}
	    	if(dictValue.trim().length() > 0){
	    		sql += " and a.dictValue like '%" + dictValue.trim() + "%' ";
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
	    	log.error("getCount error!",re);
	    	re.printStackTrace();
	    	return new Integer("0");
	    }
    }
    public int getMaxSeqNo(){
		int temp = 1;
		try {
			List<?> l = getHibernateTemplate().find(" select max(aa.seqNo) from CommDictPublicChar aa ");
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