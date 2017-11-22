package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublic;
import com.tianjian.security.bean.SecurityXtSysLog;
import com.tianjian.security.dao.ISecurityXtSysLogDAO;
import com.tianjian.util.comm.TJInit;

/**
 * 模块类别表DAO
 * @author ch_f001
 * @since 2008-3-24 16:34
 * @version 1.0
 */
public class SecurityXtSysLogDAO extends HibernateDaoSupport implements ISecurityXtSysLogDAO {

	private static final Logger log = LogManager.getLogger(SecurityXtSysLogDAO.class);

	public SecurityXtSysLog findById(String Id) {
		try {
			SecurityXtSysLog temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityXtSysLog a where a.id = ? ", Id);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityXtSysLog) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findById error!", re);
			re.printStackTrace();
			return null;
		}
	}
	


	public List<?> getData(String logLevel,String logMessage,String startTime, String endTime, String orderNo, int curCount, int pageSize) {
		try {
			String sql = "  ";
			sql += " from SecurityXtSysLog a  where 1=1 ";
			if (startTime != null && startTime.trim().length() > 0) {
				sql += " and a.logTime >= to_date('"+startTime.trim()+"','yyyy-MM-dd')";
			}
			if (endTime!=null && endTime.trim().length() > 0) {
				sql += " and a.logTime <= to_date('"+endTime.trim()+"','yyyy-MM-dd')";
			}
			if(logLevel!=null && logLevel.trim().length()>0){
				sql += " and Lower(a.logLevel) = '"+ logLevel.toLowerCase().trim() +"'";
			}
			if(logMessage != null && logMessage.trim().length() > 0 ){
				sql += " and a.logMessage like '%"+logMessage.trim()+"%'";
			}
			Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount);
			q.setMaxResults(pageSize);
			List<?> l = q.list();
			log.debug("getDataList success!");
			return l;
		}
		catch (Exception re) {
			log.error("getDataList fail!", re);
			re.printStackTrace();
			return null;
		}
	}

	public int getCount(String logLevel,String logMessage,String startTime, String endTime) {
		try {
			int count = 0;
			String sql = "select count(*) ";
			sql += " from SecurityXtSysLog a where 1=1  ";
			if (startTime != null && startTime.trim().length() > 0) {
				sql += " and a.logTime >= to_date('"+startTime.trim()+"','yyyy-MM-dd')";
			}
			if (endTime!=null && endTime.trim().length() > 0) {
				sql += " and a.logTime <= to_date('"+endTime.trim()+"','yyyy-MM-dd')";
			}
			if(logLevel!=null && logLevel.trim().length()>0){
				sql += " and Lower(a.logLevel) = '"+ logLevel.toLowerCase().trim() +"'";
			}
			if(logMessage != null && logMessage.trim().length() > 0 ){
				sql += " and a.logMessage like '%"+logMessage.trim()+"%'";
			}
			List<?> list = getHibernateTemplate().find(sql);
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			log.debug("getCount success!");
			return count;
		}
		catch (Exception re) {
			log.error("getCount fail!", re);
			re.printStackTrace();
			return new Integer("0");
		}
	}
}
