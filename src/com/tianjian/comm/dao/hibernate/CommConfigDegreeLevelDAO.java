package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigDegreeLevel;
import com.tianjian.comm.dao.ICommConfigDegreeLevelDAO;

/**
 * Created by TemplateDao
 * @author atEcd
 * @since 2009-4-1 17:55:44
 */
public class CommConfigDegreeLevelDAO extends HibernateDaoSupport implements ICommConfigDegreeLevelDAO{
	private static final Logger log = LogManager.getLogger(CommConfigDegreeLevelDAO.class);

	public void save(CommConfigDegreeLevel data) {
		try{
			this.getHibernateTemplate().save(data);
			log.debug("save success!");
		}catch(Exception e){
			log.error("save success!");
//			e.printStackTrace();
		}
	}

	public void update(CommConfigDegreeLevel data) {
		try{
			this.getHibernateTemplate().update(data);
			log.debug("update success!");
		}catch(Exception e){
			log.error("update success!");
//			e.printStackTrace();
		}
	}

	public void delete(CommConfigDegreeLevel data) {
		try{
			this.getHibernateTemplate().delete(data);
			log.debug("delete success!");
		}catch(Exception e){
			log.error("delete success!");
//			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findAll() {
		try {
			String sql = " from CommConfigDegreeLevel a ";
			return this.getHibernateTemplate().find(sql);
		}catch(Exception e){
			log.error("findAll fail",e);
//			e.printStackTrace(); 
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	/**Not suggested to use this method*/
	public List<CommConfigDegreeLevel> findList(String idName, String idValue) {
		try {
			String sql = " from  CommConfigDegreeLevel a where a."+idName+"='"+idValue+"' order by a."+idName+" ";
			return this.getHibernateTemplate().find(sql);
		} catch (Exception re) {
			log.error("findList fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByItemCode(String itemCodeValue) {
		try {
			String sql = " from  CommConfigDegreeLevel a where a.itemCode=? order by a.itemCode ";
			return this.getHibernateTemplate().find(sql, new String[]{itemCodeValue});
		} catch (Exception re) {
			log.error("findListByItemCode fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByItemName(String itemNameValue) {
		try {
			String sql = " from  CommConfigDegreeLevel a where a.itemName=? order by a.itemName ";
			return this.getHibernateTemplate().find(sql, new String[]{itemNameValue});
		} catch (Exception re) {
			log.error("findListByItemName fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByInputCode(String inputCodeValue) {
		try {
			String sql = " from  CommConfigDegreeLevel a where a.inputCode=? order by a.inputCode ";
			return this.getHibernateTemplate().find(sql, new String[]{inputCodeValue});
		} catch (Exception re) {
			log.error("findListByInputCode fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByLevelFlag(String levelFlagValue) {
		try {
			String sql = " from  CommConfigDegreeLevel a where a.levelFlag=? order by a.levelFlag ";
			return this.getHibernateTemplate().find(sql, new String[]{levelFlagValue});
		} catch (Exception re) {
			log.error("findListByLevelFlag fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByParentItemCode(String parentItemCodeValue) {
		try {
			String sql = " from  CommConfigDegreeLevel a where a.parentItemCode=? order by a.parentItemCode ";
			return this.getHibernateTemplate().find(sql, new String[]{parentItemCodeValue});
		} catch (Exception re) {
			log.error("findListByParentItemCode fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByComments(String commentsValue) {
		try {
			String sql = " from  CommConfigDegreeLevel a where a.comments=? order by a.comments ";
			return this.getHibernateTemplate().find(sql, new String[]{commentsValue});
		} catch (Exception re) {
			log.error("findListByComments fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListBySeqNo(String seqNoValue) {
		try {
			String sql = " from  CommConfigDegreeLevel a where a.seqNo=? order by a.seqNo ";
			return this.getHibernateTemplate().find(sql, new String[]{seqNoValue});
		} catch (Exception re) {
			log.error("findListBySeqNo fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	/**Not suggested to use this method*/
	public CommConfigDegreeLevel findCommConfigDegreeLevel(String idName, String idValue) {
		try {
			String sql = " from CommConfigDegreeLevel a where a."+idName+"='"+idValue+"' order by a."+idName+" ";
			return (CommConfigDegreeLevel)this.getHibernateTemplate().find(sql).get(0);
		} catch (Exception re) {
			log.error("findObject fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	public CommConfigDegreeLevel findByItemCode(String itemCodeValue) {
		try {
			String sql = " from CommConfigDegreeLevel a where a.itemCode=? order by a.itemCode ";
			return (CommConfigDegreeLevel)this.getHibernateTemplate().find(sql, new String[]{itemCodeValue}).get(0);
		} catch (Exception re) {
			log.error("findByItemCode fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	public CommConfigDegreeLevel findByItemName(String itemNameValue) {
		try {
			String sql = " from CommConfigDegreeLevel a where a.itemName=? order by a.itemName ";
			return (CommConfigDegreeLevel)this.getHibernateTemplate().find(sql, new String[]{itemNameValue}).get(0);
		} catch (Exception re) {
			log.error("findByItemName fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	public CommConfigDegreeLevel findByInputCode(String inputCodeValue) {
		try {
			String sql = " from CommConfigDegreeLevel a where a.inputCode=? order by a.inputCode ";
			return (CommConfigDegreeLevel)this.getHibernateTemplate().find(sql, new String[]{inputCodeValue}).get(0);
		} catch (Exception re) {
			log.error("findByInputCode fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	public CommConfigDegreeLevel findByLevelFlag(String levelFlagValue) {
		try {
			String sql = " from CommConfigDegreeLevel a where a.levelFlag=? order by a.levelFlag ";
			return (CommConfigDegreeLevel)this.getHibernateTemplate().find(sql, new String[]{levelFlagValue}).get(0);
		} catch (Exception re) {
			log.error("findByLevelFlag fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	public CommConfigDegreeLevel findByParentItemCode(String parentItemCodeValue) {
		try {
			String sql = " from CommConfigDegreeLevel a where a.parentItemCode=? order by a.parentItemCode ";
			return (CommConfigDegreeLevel)this.getHibernateTemplate().find(sql, new String[]{parentItemCodeValue}).get(0);
		} catch (Exception re) {
			log.error("findByParentItemCode fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	public CommConfigDegreeLevel findByComments(String commentsValue) {
		try {
			String sql = " from CommConfigDegreeLevel a where a.comments=? order by a.comments ";
			return (CommConfigDegreeLevel)this.getHibernateTemplate().find(sql, new String[]{commentsValue}).get(0);
		} catch (Exception re) {
			log.error("findByComments fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	public CommConfigDegreeLevel findBySeqNo(String seqNoValue) {
		try {
			String sql = " from CommConfigDegreeLevel a where a.seqNo=? order by a.seqNo ";
			return (CommConfigDegreeLevel)this.getHibernateTemplate().find(sql, new String[]{seqNoValue}).get(0);
		} catch (Exception re) {
			log.error("findBySeqNo fail",re);
//			re.printStackTrace(); 
			return null;
		}
	}

	public CommConfigDegreeLevel checkData(String itemCode){
		try{
			List<?> list = this.getHibernateTemplate().find(" from CommConfigDegreeLevel a where 1=1 and a.itemCode=? ", itemCode.trim());
			if(list != null && list.size() > 0){
				return (CommConfigDegreeLevel)list.get(0);
			}
		}catch(Exception e){
			log.error("checkData fail",e);
//			e.printStackTrace();
		}
		return null;
	}

	public int getCount(String itemCode, String itemName, String inputCode){
		try{
			String countSql = "select count(*) from CommConfigDegreeLevel a where 1=1";
			if(itemCode!=null && itemCode.trim().length()>0){
				countSql += " and a.itemCode='"+itemCode+"'";
			}
			if(itemName!=null && itemName.trim().length()>0){
				countSql += " and a.itemName like '"+itemName+"%'";
			}
			if(inputCode!=null && inputCode.trim().length()>0){
				countSql += " and a.inputCode like '"+inputCode+"%'";
			}
			return Integer.valueOf(String.valueOf(getHibernateTemplate().find(countSql).get(0))).intValue();
		}catch(Exception e){
			log.error("getCount fail",e);
//			e.printStackTrace();
			return 0;
		}
	}

	public List<?> getData(String itemCode, String itemName, String inputCode, String ascNo, String orderNo, int currentPageIndex, int pageSize){
		try{
			String dataSql = "from CommConfigDegreeLevel a where 1=1";
			if(itemCode!=null && itemCode.trim().length()>0){
				dataSql += " and a.itemCode='"+itemCode+"'";
			}
			if(itemName!=null && itemName.trim().length()>0){
				dataSql += " and a.itemName like '"+itemName+"%'";
			}
			if(inputCode!=null && inputCode.trim().length()>0){
				dataSql += " and a.inputCode like '"+inputCode+"%'";
			}
			if(ascNo!=null && ascNo.trim().length()>0 && orderNo!=null && orderNo.trim().length()>0){
				dataSql += " order by ";
				if(orderNo.equals("1"))
					dataSql += "a.itemCode ";
				else if(orderNo.equals("2"))
					dataSql += "a.itemName ";
				else if(orderNo.equals("3"))
					dataSql += "a.inputCode ";
				else if(orderNo.equals("4"))
					dataSql += "a.parentItemCode ";
				else
					dataSql += "a.itemCode ";
				if(ascNo.equals("1"))
					dataSql += " asc ";
				else
					dataSql += " desc ";
			}else{
				dataSql += " order by a.itemCode, a.itemName, a.inputCode, a.parentItemCode";
			}
			return getSession().createQuery(dataSql).setFirstResult(currentPageIndex).setMaxResults(pageSize).list();
		}catch(Exception e){
			log.error("getData fail",e);
//			e.printStackTrace();
			return null;
		}
	}
}
