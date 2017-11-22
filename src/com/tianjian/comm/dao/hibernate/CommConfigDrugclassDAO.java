package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigDrugclass;
import com.tianjian.comm.dao.ICommConfigDrugclassDAO;

public class CommConfigDrugclassDAO extends HibernateDaoSupport implements ICommConfigDrugclassDAO{
   
	private static final Logger log = LogManager.getLogger(CommConfigDrugclassDAO.class);
	//++++++++++++++++++++++++保存+++++++++++++++++++++++
	public void save(CommConfigDrugclass commConfigDrugclass) {
		// TODO Auto-generated method stub
		try{
            getHibernateTemplate().save(commConfigDrugclass);
            log.debug("save success!");
        } catch(Exception re) {
            log.error("save fail!", re);
            re.printStackTrace();
        }
	}
	//++++++++++++++++++++++++更新+++++++++++++++++++++++
	public void update(CommConfigDrugclass commConfigDrugclass) {
		// TODO Auto-generated method stub
		try{
            getHibernateTemplate().update(commConfigDrugclass);
            log.debug("update success!");
        } catch(Exception re) {
            log.error("update fail!", re);
            re.printStackTrace();
        }
	}
	//++++++++++++++++++++++++删除+++++++++++++++++++++++
	public void delete(CommConfigDrugclass commConfigDrugclass) {
		// TODO Auto-generated method stub
		try{
            getHibernateTemplate().delete(commConfigDrugclass);
            log.debug("delete success!");
        } catch(Exception re) {
            log.error("delete fail!", re);
            re.printStackTrace();
        }
	}
	//+++++++++++++++++++++++++公共查询方法+++++++++++++++++++++++++
	public String findValueByProp(String nameOfTable, String nameOfField,
			String nameOfProp, String valueOfProp) {
		try {
			if(nameOfTable==null || nameOfTable.trim().length()<=0
					|| nameOfField==null || nameOfField.trim().length()<=0
					|| nameOfProp==null || nameOfProp.trim().length()<=0
					|| valueOfProp==null || valueOfProp.trim().length()<=0){
				return "";
			}
			String temp = "";
			List<?> ls = getHibernateTemplate().find(" select a."+nameOfField
					+ " from " + nameOfTable + " a "
					+ " where a." + nameOfProp + " = '" + valueOfProp +"' "
					+ " order by a." + nameOfField);
			if (ls != null && ls.size() > 0)
				temp = String.valueOf(ls.get(0));
			else
				temp = "";			
			return temp;
		} catch (Exception re) {
			log.error("findValueByProp error!", re);
			re.printStackTrace();
			return "";
		}
	}
	public Object findObjectByProp(String nameOfTable, String nameOfProp, String valueOfProp) {
		try {
			Object temp = null;
			List<?> ls = getHibernateTemplate().find(" from "+nameOfTable+" a where a."+nameOfProp+" = ? order by a."+nameOfProp+" ", valueOfProp);
			if (ls != null && ls.size() > 0) {
				temp = (Object) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findObjectByProp error!", re);
			re.printStackTrace();
			return null;
		}
	}
	public List<?> findAllInTab(String nameOfTable){

		try {
			String sql = " from "+nameOfTable+" a ";

			Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("findAllInTab success!");
			return l;
		} catch (Exception re) {
			log.error("findAllInTab fail",re);
			re.printStackTrace(); 
			return null;
		}

	}
	public List<?> findAllOrder(){

		try {
			String sql = " select "
				+ " a.itemCode, "
				+ " a.itemName, "
				+ " a.inputCode, "
				+ " a.levelFlag, "
				+ " a.parentId, "
				+ " a.comments, "
				+ " a.seqNo "
				+ " from CommConfigDrugclass a order by a.seqNo,a.itemCode asc";

			Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("findAllOrder success!");
			return l;
		} catch (Exception re) {
			log.error("findAllOrder fail",re);
			re.printStackTrace(); 
			return null;
		}

	}
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public CommConfigDrugclass getByItemCode(String itemCode){
		try{
			CommConfigDrugclass info = null;
	    	List<?> ls = getHibernateTemplate().find(" from CommConfigDrugclass a where a.itemCode = ? ", itemCode);
	        if(ls != null && ls.size() > 0){
	        	info = (CommConfigDrugclass)ls.get(0);
	        }
	        log.debug("getByItemCode success!");
	        return info;
    	} catch(Exception re){
    		log.error("getByItemCode fail!", re);
            re.printStackTrace();
            return null;
    	}   
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public List<?> getAllByParentId(String parentId){
		try{
	    	List<?> ls = getHibernateTemplate().find(" from CommConfigDrugclass a where a.parentId = ? ", parentId); 
	        log.debug("getAllByParentId success!");
	        return ls;
    	} catch(Exception re){
    		log.error("getAllByParentId fail!", re);
            re.printStackTrace();
            return null;
    	}   
	}
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++取得符合查询条件的总数++++++++++++++++++++++++++++++
	public int getCount(String itemCode, String itemName, String inputCode) {
		try {
			int count = 0;
			String sql = " select count(*) "
				+ " from CommConfigDrugclass a "
				+ " where 1=1 ";
			if(itemCode != null && itemCode.trim().length() > 0){
				sql += " and a.itemCode = '" + itemCode.trim() + "' ";
			}
			if(itemName != null && itemName.trim().length() > 0){
				sql += " and a.itemName like '" + itemName.trim() + "%' ";
			}
			if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
			List<?> list = getHibernateTemplate().find(sql);
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			log.info("sql="+sql);
			log.info("search result="+count);
			return count;
		}
		catch (Exception re) {
			log.error("getCount error!", re);
			re.printStackTrace();
			return 0;
		}	
	}
	//++++++++++++++++++++++++取得符合条件的list++++++++++++++++++++++++++++
	public List<?> getData(String itemCode, String itemName, String inputCode, String orderNo, int curCount,int quChuCount) {
		try {
			String sql = " select "
				+ " a.itemCode, "
				+ " a.itemName, "
				+ " a.inputCode, "
				+ " a.levelFlag, "
				+ " a.parentId, "
				+ " a.comments, "
				+ " a.seqNo "
				+ " from CommConfigDrugclass a "
				+ " where 1=1 ";
			
			if(itemCode != null && itemCode.trim().length() > 0){
				sql += " and a.itemCode = '" + itemCode.trim() + "' ";
			}
			if(itemName != null && itemName.trim().length() > 0){
				sql += " and a.itemName like '" + itemName.trim() + "%' ";
			}
			if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
			if(orderNo != null && orderNo.trim().length() > 0){
				sql += " order by " + orderNo;
			} 
			Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(quChuCount); 
			List<?> l=q.list();
			log.info("sql="+sql);
			log.info("search size="+l.size());
			return l;
		}
		catch (Exception re) {
			log.error("getData error!", re);
			re.printStackTrace();
			return null;
		}	
	}
}
