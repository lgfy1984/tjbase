package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigLocationGroup;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.dao.ICommConfigLocationGroupDAO;
import com.tianjian.comm.struts.servlet.CommConfigLocationTownInit;
/**
 * COMM_CONFIG_LOCATION_GROUP居民组字典用DAO接口实现
 * @author Dzenall
 * @since 2008-9-18
 */
public class CommConfigLocationGroupDAO extends HibernateDaoSupport implements ICommConfigLocationGroupDAO {

	private static final Logger log = LogManager.getLogger(ICommConfigLocationGroupDAO.class);
	private String flag = CommConfigLocationTownInit.getProperty("PRINT_FLAG");
	//-------------------------------------------------------------------------------------


	
	public void delete(CommConfigLocationGroup data) {
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

	
	public CommConfigLocationGroup findByItemCode(String itemCode) {
		try {
			CommConfigLocationGroup temp = null;
			List<?> ls = getHibernateTemplate().find(" from CommConfigLocationGroup a where a.itemCode = ? ", itemCode);
			if (ls != null && ls.size() > 0) {
				temp = (CommConfigLocationGroup) ls.get(0);
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
	
	
	public CommConfigLocationGroup findById(String id) {
		try {
			CommConfigLocationGroup temp = null;
			List<?> ls = getHibernateTemplate().find(" from CommConfigLocationGroup a where a.id = ? ", id);
			if (ls != null && ls.size() > 0) {
				temp = (CommConfigLocationGroup) ls.get(0);
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
	
	
	public String findValueByProp(String nameOfTab, String nameOfField, String nameOfProp, String valueOfProp) {
		try {
			String temp = "";
			if(valueOfProp != null && valueOfProp.trim().length() > 0){
				String sql = " select a." + nameOfField + " "
				+ " from " + nameOfTab + " a "
				+ " where a." + nameOfProp + " = '" + valueOfProp + "' ";
				List<?> ls = getHibernateTemplate().find(sql);
				if (ls != null && ls.size() > 0) {
					temp = (String) ls.get(0);
				}
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findValueByProp error!", re);
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
				+ " from CommConfigLocationGroup a, "
				+ " CommConfigLocationVillage b where a.commClvId=b.id ";
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

	
	public List<?> getData(String itemCode, String itemName, String inputCode, String order, int curCount, int pageSize) {
		try {
			String sql = " select a.id, "
				+ " a.seqNo, "
				+ " a.itemCode, "
				+ " a.itemName, "
				+ " a.inputCode, "
				+ " a.comments, "
				+ " a.commClvId, "
				+ " b.itemName "				
				+ " from CommConfigLocationGroup a, "
				+ " CommConfigLocationVillage b where a.commClvId=b.id ";
			if(itemCode.trim().length() > 0){
				sql += " and a.itemCode = '" + itemCode.trim() + "' ";
			}
			if(itemName.trim().length() > 0){
				sql += " and a.itemName = '" + itemName.trim() + "' ";
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

	
	public List<?> getTownsByParent(String parentId) {
		try {
			String sql = "";
			sql += " from CommConfigLocationTown a where 1=1 ";
			if (parentId.trim().length() > 0) {
				sql += " and a.commConfigLocationId = '" + parentId.trim() + "' ";
			}
			sql += " order by a.seqNo ";
			return getHibernateTemplate().find(sql);
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;			
		}
	}

	
	public List<?> getVillagesByParent(String parentId) {
		try {
			String sql = "";
			sql += " from CommConfigLocationVillage a where 1=1 ";
			if (parentId.trim().length() > 0) {
				sql += " and a.commCltId = '" + parentId.trim() + "' ";
			}
			sql += " order by a.seqNo ";
			return getHibernateTemplate().find(sql);
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;			
		}
	}

	
	public void save(CommConfigLocationGroup data) {
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

	
	public Long seqNoMaker() {
		try {
			Long temp = (Long)this.getSession().createQuery(" select max(a.seqNo) from CommConfigLocationGroup a ").uniqueResult();
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

	
	public void update(CommConfigLocationGroup data) {
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
	//定义行政组编号
	public String getItemCode(String country){
       try{
			
	    	int count=0;
	    	String sql=" select count(*) ";
	    	sql+=" from CommConfigLocationGroup a where  a.commClvId='"+country.trim()+"'";

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
	//得到行政村编号
	public String getVillageItemCode(String commCltId){
		try{
			String villageItemCode="";
			String sql=" select a ";
	    	sql+=" from CommConfigLocationVillage a where  a.id='"+commCltId.trim()+"'";
	
	    	List<?> list = getHibernateTemplate().find(sql);
	    	if(list != null && list.size() > 0){
	    		CommConfigLocationVillage town = (CommConfigLocationVillage)list.get(0);
	    		villageItemCode=town.getItemCode();
	    	}
	    	return villageItemCode;
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
