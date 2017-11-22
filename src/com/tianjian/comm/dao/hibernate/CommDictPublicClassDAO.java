package com.tianjian.comm.dao.hibernate;


import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.tianjian.comm.bean.CommDictPublicClass;
import com.tianjian.comm.dao.ICommDictPublicClassDAO;
 


public class CommDictPublicClassDAO extends HibernateDaoSupport implements  ICommDictPublicClassDAO{

	private static final Logger log = LogManager.getLogger(CommDictPublicClassDAO.class);
	
	public CommDictPublicClass findById(String id){
		try{
			CommDictPublicClass fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommDictPublicClass a where a.classCode = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommDictPublicClass) ls.get(0);
			}
			log.debug("findById success!");
			return fac;
		}catch(Exception re){
			log.error("findById fail!",re);
			re.printStackTrace();
			return null; 
		}
	}
	public String getClassName(String id){
		String className = "";
		try {
			List<?> l = getHibernateTemplate().find(" from CommDictPublicClass aa where aa.classCode = ? ", id);
			if(l != null && l.size() > 0){
				CommDictPublicClass pub = (CommDictPublicClass)l.get(0);
				className = pub.getClassName();
			}
			log.debug("getClassName success!");
			return className;
        } catch (Exception re) {
        	log.error("getClassName fail!",re);
        	re.printStackTrace();
			return null; 
        }
	} 
    
    public void save(CommDictPublicClass commDictPublicClass){
    	try {
        	getHibernateTemplate().save(commDictPublicClass);
        	log.debug("save success!");
        } catch (Exception re) {
        	log.error("save fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void update(CommDictPublicClass commDictPublicClass){
    	try {
        	getHibernateTemplate().update(commDictPublicClass);
        	log.debug("update success!");
        } catch (Exception re) {
        	log.error("update fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void delete(CommDictPublicClass commDictPublicClass){
    	try {
        	getHibernateTemplate().delete(commDictPublicClass);
        	log.debug("delete success!");
        } catch (Exception re) {
        	log.error("delete fail!",re);
        	re.printStackTrace(); 
        }
    } 
    
    public List<?> findAll(){
    	try {
    		String sql = " from CommDictPublicClass a  ";
	       
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
    
    public List<?> getData(String classCode, String className, String inputCode, String seqNo, String orderNo, int curCount,int pageSize){
    	try {
    		String sql = " select a.classCode, a.className,a.inputCode,a.comments,a.seqNo  ";
	    	sql += " from CommDictPublicClass a  where 1=1 ";
	       
	    
	    	if(classCode.trim().length() > 0){
	    		sql += " and a.classCode = '" + classCode.trim() + "' ";
	    	}
	    	if(className.trim().length() > 0){
	    		sql += " and lower(a.className) like '%" + className.trim().toLowerCase() + "%' ";
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
    
    public int getCount( String classCode, String className, String inputCode,String seqNo){
    	try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommDictPublicClass a  where 1=1  ";
	    	
	    	if(classCode.trim().length() > 0){
	    		sql += " and a.classCode = '" + classCode.trim() + "' ";
	    	}
	    	if(className.trim().length() > 0){
	    		sql += " and a.className like '%" + className.trim() + "%' ";
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
			List<?> l = getHibernateTemplate().find(" select max(aa.seqNo) from CommDictPublicClass aa ");
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
    
    public List<?> getClassComm(String falg, String input, int count, int pageSize) {
		String hql="from CommDictPublicClass h where 1=1";
		if(input!=null&&input.trim().length()>0&&falg.equals("1")){
			hql+=" and h.inputCode like '%"+input.trim().toUpperCase()+"%'";
		}
		if(falg.equals("2")){
			hql+=" and h.classCode like '%"+input.trim()+"%'";
		}
		if(falg.equals("3")){
			hql+=" and h.className like '%"+input.trim()+"%'";
		}
		Query q=this.getSession().createQuery(hql);
		q.setFirstResult(count);
		q.setMaxResults(pageSize);
		return q.list();
	}
}