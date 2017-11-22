package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigFtManage;
import com.tianjian.comm.dao.ICommConfigFtManageDAO;

public class CommConfigFtManageDAO extends HibernateDaoSupport implements ICommConfigFtManageDAO {
	private static final Logger log = LogManager.getLogger(ICommConfigFtManageDAO.class);

	@SuppressWarnings("unchecked")
	
	public List<CommConfigFtManage> findAll() {
		try {
			String sql = " from  CommConfigFtManage a order by a.seqNo ";
			return this.getHibernateTemplate().find(sql);
		} catch (Exception re) {
//			log.error("findAll fail ["+re.getMessage()+"]");
//			re.printStackTrace(); 
			return null;
		}
	}
	
	public String getItemNameByItemCode(String itemCode) {
		try {
			String sql = "select a.itemName from CommConfigFtManage a where a.itemCode = ? order by a.seqNo ";
			return String.valueOf(this.getHibernateTemplate().find(sql, itemCode).get(0));
		} catch (Exception re) {
//			log.error("getItemNameByItemCode fail ["+re.getMessage()+"]");
//			re.printStackTrace(); 
			return "";
		}
	}
	
	public CommConfigFtManage findByItemCode(String itemCode) {
		try{
			String sql = " from CommConfigFtManage a where a.itemCode = ? order by a.seqNo ";
			return (CommConfigFtManage)this.getHibernateTemplate().find(sql, itemCode).get(0);
		}catch(Exception e){
//			log.error("findByItemCode fail ["+e.getMessage()+"]");
//			e.printStackTrace(); 
			return null;
		}
	}
	
	public int getCount(String countSql) {
		try{
			int count = 0;
			List<?> list = getHibernateTemplate().find(countSql);
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			return count;
		}catch(Exception e){
//			log.error("getCount fail ["+e.getMessage()+"]");
//			e.printStackTrace(); 
			return 0;
		}
	}
	
	public List<?> getData(String dataSql, int currentPageIndex, int pageSize) {
		try{	    
			return getSession().createQuery(dataSql).setFirstResult(currentPageIndex).setMaxResults(pageSize).list();
		}catch(Exception e){
//			log.error("getData fail ["+e.getMessage()+"]");
//			e.printStackTrace(); 
			return null;
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