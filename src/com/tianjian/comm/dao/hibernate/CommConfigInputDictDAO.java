package com.tianjian.comm.dao.hibernate;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.tianjian.comm.dao.ICommConfigInputDictDAO;

public class CommConfigInputDictDAO extends HibernateDaoSupport implements ICommConfigInputDictDAO {

	private static final Logger log = LogManager.getLogger(CommConfigInputDictDAO.class);

    public CommConfigInputDictDAO() {
    }

    public String getInputCode(String name){
    	try{
    		String temp = "";
    		String sql = "select a.inputCode ";
	        sql += " from CommConfigInputDict a ";
	        sql += " where a.itemName = '" + name.trim() + "' ";
	        
	        List<?> ls = getHibernateTemplate().find(sql);
	        if(ls != null && ls.size() > 0){
	        	temp = (String)ls.get(0);
	        }
	        if(temp == null){
	        	temp = "";
	        }
	        
	        log.debug("getInputCode success!");
	        return temp;
    	} catch(Exception re){
	        log.error("getInputCode fail!", re);
	        re.printStackTrace();
	        return "";
    	}
    }
    
}