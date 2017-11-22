package com.tianjian.comm.dao.hibernate;


import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.tianjian.comm.bean.CommConfigAbo;
import com.tianjian.comm.bean.CommMenuHelp;
import com.tianjian.comm.dao.ICommMenuHelpDAO;
 


public class CommMenuHelpDAO extends HibernateDaoSupport implements  ICommMenuHelpDAO{

	private static final Logger log = LogManager.getLogger(CommMenuHelpDAO.class);
	
	public CommMenuHelp findById(String id){
		try{
			CommMenuHelp fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommMenuHelp a where a.id = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommMenuHelp) ls.get(0);
			}
			log.debug("findById success!");
			return fac;
		}catch(Exception re){
			log.error("findById fail!",re);
			re.printStackTrace();
			return null; 
		}
	}
	public CommMenuHelp findByMenuFlag(String menuFlag){
		try{
			CommMenuHelp fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommMenuHelp a where a.menuFlag = ? ", menuFlag);
			if(ls != null && ls.size() >0){
				fac = (CommMenuHelp) ls.get(0);
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
			List<?> l = getHibernateTemplate().find(" from CommConfigAbo aa where aa.itemCode = ? ", id);
			if(l != null && l.size() > 0){
				CommConfigAbo pub = (CommConfigAbo)l.get(0);
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
    
    public void save(Object obj){
    	try {
        	getHibernateTemplate().save(obj);
        	log.debug("save success!");
        } catch (Exception re) {
        	log.error("save fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void update(Object obj){
    	try {
        	getHibernateTemplate().update(obj);
        	log.debug("update success!");
        } catch (Exception re) {
        	log.error("update fail!",re);
        	re.printStackTrace(); 
        }
    }
    
    public void delete(Object obj){
    	try {
        	getHibernateTemplate().delete(obj);
        	log.debug("delete success!");
        } catch (Exception re) {
        	log.error("delete fail!",re);
        	re.printStackTrace(); 
        }
    } 
    
    public List<?> findAll(){
    	try {
    		String sql = " from CommConfigAbo a  ";
	       
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
    
    public List<?> getData(String menuName, String menuHelpTitle, String createDate,String seqNo, String orderNo, int curCount,int pageSize){
    	try {
    		String sql =  " from CommMenuHelp a  where 1=1 ";
	       
	    
    		if(menuName.trim().length() > 0){
	    		sql += " and a.menuName like '%" + menuName.trim() + "%' ";
	    	}
	    	if(menuHelpTitle.trim().length() > 0){
	    		sql += " and a.menuHelpTitle like '%" + menuHelpTitle.trim() + "%' ";
	    	}
	    	if(createDate.trim().length() > 0){
	    		sql += " and a.createDate = to_date('"+createDate+"','yyyy-mm-dd') '";
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
    
    public int getCount( String menuName, String menuHelpTitle, String createDate,String seqNo){
    	try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommMenuHelp a  where 1=1  ";
	    	
	    	if(menuName.trim().length() > 0){
	    		sql += " and a.menuName like '%" + menuName.trim() + "%' ";
	    	}
	    	if(menuHelpTitle.trim().length() > 0){
	    		sql += " and a.menuHelpTitle like '%" + menuHelpTitle.trim() + "%' ";
	    	}
	    	if(createDate.trim().length() > 0){
	    		sql += " and a.createDate = to_date('"+createDate+"','yyyy-mm-dd') '";
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