package com.tianjian.comm.dao.hibernate;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.tianjian.comm.bean.CommTableDateId;
import com.tianjian.comm.dao.ICommTableDateIdDAO;

public class CommTableDateIdDAO extends HibernateDaoSupport implements ICommTableDateIdDAO{

	private static final Log log = LogFactory.getLog(CommTableDateIdDAO.class);
	
	public CommTableDateId getByColumn(String tableName, String columnName, Date date){
		try{
			CommTableDateId col = null;
			String sql = " from CommTableDateId col ";
			sql += "where col.tableName = '" + tableName + "' ";
			sql += "and col.columnName = '" + columnName + "' ";
			sql += "and col.seqDate = ?";
			List<?> ls = getHibernateTemplate().find(sql, date);
			if(ls != null && ls.size() >0){
				col = (CommTableDateId) ls.get(0);
			}
			return col;
		}catch(RuntimeException re){
			log.error(re.toString());
        	throw re;
		}
	}
    
	public List<?> getTableColumn(String tableName, String columnName){
		try{
			String sql = " from CommTableDateId col ";
			sql += "where col.tableName = '" + tableName + "' ";
			sql += "and col.columnName = '" + columnName + "' ";
			List<?> ls = getHibernateTemplate().find(sql);
			return ls;
		}catch(RuntimeException re){
			log.error(re.toString());
        	throw re;
		}
	}
    
    public void save(CommTableDateId commTableDateId){
    	try {
        	getHibernateTemplate().save(commTableDateId);
        } catch (RuntimeException re) {
        	log.error(re.toString());
        	throw re;
        }
    }
    
    public void update(CommTableDateId commTableDateId){
    	try {
        	getHibernateTemplate().update(commTableDateId);
        } catch (RuntimeException re) {
        	log.error(re.toString());
        	throw re;
        }
    }
    
    public void delete(CommTableDateId commTableDateId){
    	try {
        	getHibernateTemplate().delete(commTableDateId);
        } catch (RuntimeException re) {
        	log.error(re.toString());
        	throw re;
        }
    }
    
    public long getMaxId(){
    	try {
    		long count = 0;
	    	String sql = "select max(col.id) ";
	    	sql += " from CommTableDateId col ";
	    	
	    	List<?> list = getHibernateTemplate().find(sql);
	    	if(list != null && list.size() > 0){
	    		if(list.get(0) != null && list.get(0) != ""){
	    			count = Long.valueOf(String.valueOf(list.get(0))).longValue();
	    		}
	    	}	    	
	    	return count;
	    } catch (RuntimeException re) {
	    	log.error(re.toString());
	    	throw re;
	    }
    }
    
	   
}