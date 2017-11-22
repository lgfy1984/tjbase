package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.dao.ICommConfigLocationTownDAO;
import com.tianjian.comm.struts.servlet.CommConfigLocationTownInit;
/**
 * COMM_CONFIG_LOCATION_TOWN�����ֵ���Dao�ӿ�ʵ��
 * @author Dzenall
 * @since 2008-9-17
 */
public class CommConfigLocationTownDAO extends HibernateDaoSupport implements ICommConfigLocationTownDAO {

	private static final Logger log = LogManager.getLogger(ICommConfigLocationTownDAO.class);
	private String flag = CommConfigLocationTownInit.getProperty("PRINT_FLAG");

	public void delete(CommConfigLocationTown data) {
		try {
			getHibernateTemplate().delete(data);
		}
		catch (Exception re) {
			log.error("delete error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
		}
	}

	public CommConfigLocationTown findByItemCode(String itemCode) {
		try {
			CommConfigLocationTown temp = null;
			List<?> ls = getHibernateTemplate().find(" from CommConfigLocationTown a where a.itemCode = ? ", itemCode);
			if (ls != null && ls.size() > 0) {
				temp = (CommConfigLocationTown) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findByItemCode error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
			return null;
		}
	}
	
	public CommConfigLocationTown findById(String id) {
		try {
			CommConfigLocationTown temp = null;
			List<?> ls = getHibernateTemplate().find(" from CommConfigLocationTown a where a.id = ? ", id);
			if (ls != null && ls.size() > 0) {
				temp = (CommConfigLocationTown) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findById error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
			return null;
		}
	}

	public String findItemNameById(String id) {
		try {
			String temp = null;
			List<?> ls = getHibernateTemplate().find(" select a.itemName from CommConfigLocation a where a.id = ? ", id);
			if (ls != null && ls.size() > 0) {
				temp = (String) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findItemNameById error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
			return null;
		}
	}

	public String findParentIdById(String id) {
		try {
			String temp = null;
			List<?> ls = getHibernateTemplate().find(" select a.parentId from CommConfigLocation a where a.id = ? ", id);
			if (ls != null && ls.size() > 0) {
				temp = (String) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findParentIdById error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
			return null;
		}
	}
	
	public String findParentIdByItemCode(String itemCode) {
		try {
			String temp = null;
			List<?> ls = getHibernateTemplate().find(" select a.parentId from CommConfigLocation a where a.itemCode = ? ", itemCode);
			if (ls != null && ls.size() > 0) {
				temp = (String) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findParentIdByItemCode error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
			return null;
		}
	}

	public List<?> getByParent(String parentId, String levelFlag) {
		try {
			String sql = "";
			sql += " from CommConfigLocation a where 1=1 ";
			if (parentId.trim().length() > 0) {
				sql += " and a.parentId = '" + parentId.trim() + "' ";
			}
			if (levelFlag.trim().length() > 0) {
				sql += " and a.levelFlag = '" + levelFlag.trim() + "' ";
			}
			sql += " order by a.seqNo ";
			return getHibernateTemplate().find(sql);
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;			
		}
	}

	public int getCount(String itemCode, String itemName, String inputCode) {
		try {
			int count = 0;
			String sql = "select count(*) "
				+ " from CommConfigLocationTown a, "
				+ " CommConfigLocation b where a.commConfigLocationId=b.id ";
			if(itemCode.trim().length() > 0){
				sql += " and a.itemCode = '" + itemCode.trim() + "' ";
			}
			if(itemName.trim().length() > 0){
				sql += " and a.itemName = '" + itemName.trim() + "' ";
			}
			if(inputCode.trim().length() > 0){
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			List<?> list = getHibernateTemplate().find(sql);
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			return count;
		}
		catch (Exception re) {
			log.error("getCount error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
			return 0;
		}
	}
	public String getItemName(String itemCode){
		String itemName = "";
		try {
			List<?> l = getHibernateTemplate().find(" from CommConfigLocationTown aa where aa.itemCode = ? ", itemCode);
			if(l != null && l.size() > 0){
				CommConfigLocationTown pub = (CommConfigLocationTown)l.get(0);
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
	public List<?> getData(String itemCode, String itemName, String inputCode, String order, int curCount, int pageSize) {
		try {
			String sql = " select a.itemCode, "
				+ " a.itemName, "
				+ " a.inputCode, "
				+ " a.comments, "
				+ " a.commConfigLocationId, "
				+ " b.itemName, "
				+ " a.seqNo, "
				+ " a.id "
				+ " from CommConfigLocationTown a, "
				+ " CommConfigLocation b where a.commConfigLocationId=b.id ";
			if(itemCode.trim().length() > 0){
				sql += " and a.itemCode = '" + itemCode.trim() + "' ";
			}
			if(itemName.trim().length() > 0){
				sql += " and lower(a.itemName) = '" + itemName.trim().toLowerCase() + "' ";
			}
			if(inputCode.trim().length() > 0){
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if(order.trim().length() > 0){
				sql += " order by " + order;
			} 
			Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(pageSize); 
			List<?> l=q.list();

			return l;
		}
		catch (Exception re) {
			log.error("getData error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
			return null;
		}
	}

	public void save(CommConfigLocationTown data) {
		try {
			getHibernateTemplate().save(data);
		}
		catch (Exception re) {
			log.error("save error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
		}
	}

	public Long seqNoMaker(String seqNo) {
		try {
			Long temp = (Long)this.getSession().createQuery(" select max(a.seqNo) from CommConfigLocationTown a ").uniqueResult();
			if(temp==null)
				return 1L;
			else
				return temp+1;
		}
		catch (Exception re) {
			log.error("seqNoMaker error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
			return 1L;
		}

	}

	public void update(CommConfigLocationTown data) {
		try {
			getHibernateTemplate().update(data);
		}
		catch (Exception re) {
			log.error("update error!", re);
			if(flag.equalsIgnoreCase("true")){
				re.printStackTrace();
			}
		}
	}
	//定义乡镇编号
	public String getItemCode(String country){
       try{
			
	    	int count=0;
	    	String sql=" select count(*) ";
	    	sql+=" from CommConfigLocationTown a where  a.commConfigLocationId='"+country.trim()+"'";

	    	List<?> list = getHibernateTemplate().find(sql);
	    	if(list != null && list.size() > 0){
	    		count = Integer.valueOf(transNullToZero(list.get(0))).intValue();
	    	}		    	
	    	return String.valueOf(count+1);
    	} catch (RuntimeException re) {
        	log.error(re.toString());
        	throw re;
        }
	}
	
	private String transNullToZero(Object obj) {
		String temp = "0";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}
}
