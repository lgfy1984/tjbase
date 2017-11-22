package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

//import com.tianjian.comm.dao.ICommConfigLocationGroupDAO;
import com.tianjian.comm.struts.servlet.CommConfigLocationTownInit;
import com.tianjian.comm.dao.ICommConfigLocationSearchDAO;

public class CommConfigLocationSearchDAO extends HibernateDaoSupport implements ICommConfigLocationSearchDAO{
	private static final Logger log = LogManager.getLogger(ICommConfigLocationSearchDAO.class);
	private String flag = CommConfigLocationTownInit.getProperty("PRINT_FLAG");
	 
	public String findValueByProp(String nameOfTab, String nameOfField, String nameOfProp, String valueOfProp) {
		try {
			String temp = null;
			String sql = " select a." + nameOfField + " "
			+ " from " + nameOfTab + " a "
			+ " where a." + nameOfProp + " = '" + valueOfProp + "' ";
			List<?> ls = getHibernateTemplate().find(sql);
			if (ls != null && ls.size() > 0) {
				temp = (String) ls.get(0);
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
			String sql = "select a ";
			sql += " from CommConfigLocation a ";
			sql += " where 1=1 ";
			
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
	
//	public List<?> getByParent(String parentId, String levelFlag, String staffId) {
//		try {
//			String sql = "select a ";
//			sql += " from CommConfigLocation a ";
//			if(staffId != null && staffId.trim().length() > 0){
//				if(levelFlag.equals("1")){
//					sql += " ,SecurityvStaffVsProvince b ";
//				} else if(levelFlag.equals("2")){
//					sql += " ,SecurityvStaffVsCity b ";
//				} else if(levelFlag.equals("3")){
//					sql += " ,SecurityvStaffVsCounty b ";
//				}
//			}
//			
//			sql += " where 1=1 ";
//			
//			if(staffId != null && staffId.trim().length() > 0){
//				if(levelFlag.equals("1")){
//					sql += " and a.id = b.id.pvcId ";
//					sql += " and b.id.sssbId = '" + staffId.trim() + "' ";					
//				} else if(levelFlag.equals("2")){
//					sql += " and a.id = b.id.cityId ";
//					sql += " and b.id.sssbId = '" + staffId.trim() + "' ";	
//				} else if(levelFlag.equals("3")){
//					sql += " and a.id = b.id.countyId ";
//					sql += " and b.id.sssbId = '" + staffId.trim() + "' ";	
//				}
//			}
//			if (parentId.trim().length() > 0) {
//				sql += " and a.parentId = '" + parentId.trim() + "' ";
//			}
//			if (levelFlag.trim().length() > 0) {
//				sql += " and a.levelFlag = '" + levelFlag.trim() + "' ";
//			}
//			sql += " order by a.seqNo ";
//			return getHibernateTemplate().find(sql);
//		}
//		catch (RuntimeException re) {
//			log.error(re.toString());
//			throw re;			
//		}
//	}
	
	
	
	public List<?> getByParent(String parentId, String levelFlag, String staffId) {
		try {
			String sql = "select a ";
			sql += " from CommConfigLocation a ";
			
			sql += " where 1=1 ";
			
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

	
	
	
	//处理AJAX
	 
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
	
//	public List<?> getTownsByParent(String parentId, String staffId) {
//		try {
//			String sql = "select a ";
//			sql += " from CommConfigLocationTown a ";
//			if(staffId != null && staffId.trim().length() > 0){
//				sql += " ,SecurityvStaffVsTown b ";
//			}
//			
//			sql += " where 1=1 ";
//			if (parentId.trim().length() > 0) {
//				sql += " and a.commConfigLocationId = '" + parentId.trim() + "' ";
//			}
//			if(staffId != null && staffId.trim().length() > 0){
//				sql += " and a.id = b.id.townId ";
//				sql += " and b.id.sssbId = '" + staffId.trim() + "' ";			
//			}
//			sql += " order by a.seqNo ";
//			return getHibernateTemplate().find(sql);
//		}
//		catch (RuntimeException re) {
//			log.error(re.toString());
//			throw re;			
//		}
//	}
	
	
	public List<?> getTownsByParent(String parentId, String staffId) {
		try {
			String sql = "select a ";
			sql += " from CommConfigLocationTown a ";

			
			sql += " where 1=1 ";

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
	
//	public List<?> getVillagesByParent(String parentId, String staffId) {
//		try {
//			String sql = "select a ";
//			sql += " from CommConfigLocationVillage a ";
//			if(staffId != null && staffId.trim().length() > 0){
//				sql += " ,SecurityvStaffVsVillage b ";
//			}
//			
//			sql += " where 1=1 ";
//			if (parentId.trim().length() > 0) {
//				sql += " and a.commCltId = '" + parentId.trim() + "' ";
//			}
//			if(staffId != null && staffId.trim().length() > 0){
//				sql += " and a.id = b.id.villageId ";
//				sql += " and b.id.sssbId = '" + staffId.trim() + "' ";			
//			}
//			
//			sql += " order by a.seqNo ";
//			return getHibernateTemplate().find(sql);
//		}
//		catch (RuntimeException re) {
//			log.error(re.toString());
//			throw re;			
//		}
//	}

	
	
	public List<?> getVillagesByParent(String parentId, String staffId) {
		try {
			String sql = "select a ";
			sql += " from CommConfigLocationVillage a ";
			
			sql += " where 1=1 ";
			
			sql += " order by a.seqNo ";
			return getHibernateTemplate().find(sql);
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;			
		}
	}
	
	
	
	
	public List<?> getGroupByParent(String parentId){
		try {
			String sql = "";
			sql += " from CommConfigLocationGroup a where 1=1 ";

			sql += " order by a.seqNo ";
			return getHibernateTemplate().find(sql);
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;			
		}
	}
}
