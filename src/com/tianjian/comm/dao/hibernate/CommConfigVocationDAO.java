package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigVocation;
import com.tianjian.comm.dao.ICommConfigVocationDAO;

public class CommConfigVocationDAO extends HibernateDaoSupport implements ICommConfigVocationDAO {

	private static final Logger log = LogManager.getLogger(CommConfigVocationDAO.class);
	/**
	 * 通过主键获取纪录
	 * @param id
	 * @return
	 */
	public CommConfigVocation findById(String id) {
		try{
			CommConfigVocation fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommConfigVocation a where a.itemCode = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommConfigVocation) ls.get(0);
			}
			log.debug("findById success!");
			return fac;
		}catch(Exception re){
			log.error("findById fail!",re);
			re.printStackTrace();
			return null; 
		}
	}
	
	
	
	/**
	 * 查找所有记录
	 * @return
	 */
	public List<?> findAll() {
		try{
			String sql="from CommConfigVocation a";
			Query q=getSession().createQuery(sql);
			List<?> l=q.list();
			log.debug("findAll success");
			return l;
		}catch(Exception re){
			log.error("findAll error",re);
			re.printStackTrace();
			return null;
		}
	}
	/**
	 * 通过ID获取职称名称
	 * @param id
	 * @return
	 */
	public String getNameById(String id) {
		String itemName = "";
		try {
			List<?> l = getHibernateTemplate().find(" from CommConfigVocation aa where aa.itemCode = ? ", id);
			if(l != null && l.size() > 0){
				CommConfigVocation pub = (CommConfigVocation)l.get(0);
				itemName = pub.getItemName();
			}
			log.debug("getNameById success!");
			return itemName;
        } catch (Exception re) {
        	log.error("getNameById fail!",re);
        	re.printStackTrace();
			return null; 
        }
	}
	/**
	 * 保存记录
	 * @param commConfigVocation
	 */
	public void save(CommConfigVocation commConfigVocation) {
		try{
			this.getHibernateTemplate().save(commConfigVocation);
			log.debug("save success");
		}catch(Exception re){
			log.error("save error",re);
			re.printStackTrace();
		}
		
	}
	/**
	 * 修改记录
	 * @param commConfigUnittype
	 */
	public void update(CommConfigVocation commConfigVocation) {
		try{
			this.getHibernateTemplate().update(commConfigVocation);
			log.debug("update success");
		}catch(Exception re){
			log.error("update error",re);
			re.printStackTrace();
		}
	}
	/**
     * 删除记录
     * @param commConfigVocation
     */
	public void delete(CommConfigVocation commConfigVocation) {
		try{
			this.getHibernateTemplate().delete(commConfigVocation);
			log.debug("delete success");
		}catch(Exception re){
			log.error("delete error",re);
			re.printStackTrace();
		}
	}

	/**
	 * 根据条件获取当前页记录
	 * @param itemCode
	 * @param itemName
	 * @param inputCode
	 * @param seqNo
	 * @param orderNo
	 * @param curCount
	 * @param pageSize
	 * @return
	 */
	public List<?> getData(String itemCode, String itemName, String inputCode,
			String seqNo, String orderNo, int curCount, int pageSize) {
		try {
    		String sql = " select a.itemCode, a.itemName,a.inputCode,a.comments,a.seqNo  ";
	    	sql += " from CommConfigVocation a  where 1=1 ";
	       
	    
	    	if(itemCode.trim().length() > 0){
	    		sql += " and a.itemCode = '" + itemCode.trim() + "' ";
	    	}
	    	if(itemName.trim().length() > 0){
	    		sql += " and lower(a.itemName) like '%" + itemName.trim().toLowerCase() + "%' ";
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
	 /**
     * 根据查询条件获取记录总数
     * @param itemCode
     * @param itemName
     * @param inputCode
     * @param seqNo
     * @return
     */
	public int getCount(String itemCode, String itemName, String inputCode,
			String seqNo) {
		try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommConfigVocation a  where 1=1  ";
	    	
	    	if(itemCode.trim().length() > 0){
	    		sql += " and a.itemCode = '" + itemCode.trim() + "' ";
	    	}
	    	if(itemName.trim().length() > 0){
	    		sql += " and a.itemName like '%" + itemName.trim() + "%' ";
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
